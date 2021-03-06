package techreborn.tiles.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.IWrenchable;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.powerSystem.TilePowerAcceptor;
import reborncore.common.util.FluidUtils;
import reborncore.common.util.Inventory;
import reborncore.common.util.Tank;
import techreborn.api.generator.EFluidGenerator;
import techreborn.api.generator.FluidGeneratorRecipeList;
import techreborn.api.generator.GeneratorRecipeHelper;

public abstract class TileBaseFluidGenerator extends TilePowerAcceptor implements IWrenchable, IInventoryProvider {

	private final FluidGeneratorRecipeList recipes;

	private final int euTick;

	public final Tank tank;
	public final Inventory inventory;

	/*
	 * We use this to keep track of fractional millibuckets, allowing us to hit
	 * our eu/bucket targets while still only ever removing integer millibucket
	 * amounts.
	 */
	double pendingWithdraw = 0.0;

	public TileBaseFluidGenerator(EFluidGenerator type, int tier, String tileName, int tankCapacity, int euTick) {
		super(tier);

		recipes = GeneratorRecipeHelper.getFluidRecipesForGenerator(type);

		tank = new Tank(tileName, tankCapacity, this);
		inventory = new Inventory(3, tileName, 64, this);
		this.euTick = euTick;
	}

	public TileBaseFluidGenerator(EFluidGenerator type, EnumPowerTier tier, String tileName, int tankCapacity,
			int euTick) {
		this(type, tier.ordinal(), tileName, tankCapacity, euTick);
	}

	protected long lastOutput = 0;

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (!this.world.isRemote) {
			if ((this.acceptFluid() && FluidUtils.drainContainers(this.tank, this.inventory, 0, 1))
					|| FluidUtils.fillContainers(tank, inventory, 0, 1, tank.getFluidType()))
				this.syncWithAll();
		}

		if (this.tank.getFluidAmount() > 0) {
			this.getRecipes().getRecipeForFluid(tank.getFluidType()).ifPresent(recipe -> {

				if(tryAddingEnergy(euTick))
				{
					final Integer euPerBucket = recipe.getEnergyPerMb() * 1000;
					final float millibucketsPerTick = 16000f / (float) euPerBucket;
					this.pendingWithdraw += millibucketsPerTick;

					final int currentWithdraw = (int) this.pendingWithdraw;
					
					this.pendingWithdraw -= currentWithdraw;

					this.tank.drain(currentWithdraw, true);
					
					this.lastOutput = this.world.getTotalWorldTime();
				}
			});
		}

		if (this.tank.getFluidType() != null && this.getStackInSlot(2) == ItemStack.EMPTY)
			this.inventory.setInventorySlotContents(2, new ItemStack(this.tank.getFluidType().getBlock()));
		else if (this.tank.getFluidType() == null && this.getStackInSlot(2) != ItemStack.EMPTY)
			this.setInventorySlotContents(2, ItemStack.EMPTY);

		if (!this.world.isRemote) {
			if (this.world.getTotalWorldTime() - this.lastOutput < 30 && !this.isActive())
				this.world.setBlockState(this.getPos(),
						this.world.getBlockState(this.getPos()).withProperty(BlockMachineBase.ACTIVE, true));
			else if (this.world.getTotalWorldTime() - this.lastOutput > 30 && this.isActive())
				this.world.setBlockState(this.getPos(),
						this.world.getBlockState(this.getPos()).withProperty(BlockMachineBase.ACTIVE, false));
		}
	}

	protected boolean tryAddingEnergy(int amount) {
		if (this.getMaxPower() - this.getEnergy() >= amount) {
			addEnergy(amount);
			return true;
		} else if (this.getMaxPower() - this.getEnergy() > 0) {
			addEnergy(this.getMaxPower() - this.getEnergy());
			return true;
		}
		return false;
	}

	protected boolean acceptFluid() {
		if (!this.getStackInSlot(0).isEmpty()) {
			FluidStack stack = FluidUtils.getFluidStackInContainer(this.getStackInSlot(0));
			if (stack != null)
				return recipes.getRecipeForFluid(stack.getFluid()).isPresent();
		}
		return false;
	}

	public FluidGeneratorRecipeList getRecipes() {
		return recipes;
	}

	@Override
	public double getMaxOutput() {
		return euTick;
	}

	@Override
	public double getMaxInput() {
		return 0;
	}

	@Override
	public EnumPowerTier getTier() {
		return EnumPowerTier.values()[this.tier];
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing direction) {
		return false;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing direction) {
		return true;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return (T) tank;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		tank.readFromNBT(tagCompound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tank.writeToNBT(tagCompound);
		return tagCompound;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		world.markBlockRangeForRenderUpdate(getPos().getX(), getPos().getY(), getPos().getZ(), getPos().getX(),
				getPos().getY(), getPos().getZ());
		readFromNBT(packet.getNbtCompound());
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, EnumFacing side) {
		return false;
	}

	@Override
	public EnumFacing getFacing() {
		return getFacingEnum();
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return entityPlayer.isSneaking();
	}

	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}
}
