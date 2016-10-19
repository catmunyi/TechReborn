package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.tile.IContainerProvider;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.inventory.Inventory;
import techreborn.client.container.energy.generator.ContainerSemifluidGenerator;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class TileSemifluidGenerator extends AbstractTileGenerator implements IInventoryProvider, IContainerProvider {

	// TODO: run this off config
	//public static final int euTick = 8;
	//public Tank tank = new Tank("TileSemifluidGenerator", FluidContainerRegistry.BUCKET_VOLUME * 10, this);
	public Inventory inventory = new Inventory("TileSemifluidGenerator", 3, 64, this);
	public FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * 10);
	private Map<String, Integer> fluids = new HashMap<>();

	private int slotFakeFluid = 2;

	// We use this to keep track of fractional millibuckets, allowing us to hit
	// our eu/bucket targets while still only ever removing integer millibucket
	// amounts.
	private double pendingWithdraw = 0.0;

	public TileSemifluidGenerator() {
		super(EnumPowerTier.LOW, ConfigTechReborn.ThermalGeneratorCharge);
		// TODO: fix this to have SemiFluid generator values

		this.fluids.put("creosote", 3000);
		this.fluids.put("biomass", 8000);
		this.fluids.put("oil", 64000);
		this.fluids.put("this.fluidsodium", 30000);
		this.fluids.put("fluidlithium", 60000);
		this.fluids.put("biofuel", 32000);
		this.fluids.put("bioethanol", 32000);
		this.fluids.put("fuel", 128000);
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.semifluidGenerator, 1);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		this.tank.readFromNBT(tagCompound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		this.tank.writeToNBT(tagCompound);
		return tagCompound;
	}

	@Override
	public boolean hasCapability(Capability<?> capability,
	                             @Nullable
		                             EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability,
	                           @Nullable
		                           EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.tank;

		return super.getCapability(capability, facing);
	}

	@Override
	public void updateRequirements() {
	}

	@Override
	public void updateEntity() {
		//		super.updateEntity();
		//		if (!worldObj.isRemote)
		//			FluidUtils.drainContainers(this, inventory, 0, 1);

		int euPerTick = 8;
		if (this.tank.getFluid() != null && this.tank.getFluidAmount() > 0 && canAddEnergy(euPerTick)) {
			if (this.fluids.containsKey(this.tank.getFluid().getFluid().getName())) {
				int euPerBucket = this.fluids.get(this.tank.getFluid().getFluid().getName());

				// float totalTicks = (float)euPerBucket / 8f; //x eu per bucket / 8
				// eu per tick
				// float millibucketsPerTick = 1000f / totalTicks;

				float millibucketsPerTick = 16000f / (float) euPerBucket;
				this.pendingWithdraw += millibucketsPerTick;

				int currentWithdraw = (int) this.pendingWithdraw; // float --> int

				// conversion floors
				// the float

				this.pendingWithdraw -= currentWithdraw;

				this.tank.drain(currentWithdraw, true);
				addEnergy(euPerTick);
			}
		}

		if (this.tank.getFluid() != null && getInventory().getStackInSlot(this.slotFakeFluid) == null) {
			getInventory().setInventorySlotContents(this.slotFakeFluid, new ItemStack(this.tank.getFluid().getFluid().getBlock()));
		} else if (this.tank.getFluid() == null && getInventory().getStackInSlot(this.slotFakeFluid) != null) {
			getInventory().setInventorySlotContents(this.slotFakeFluid, null);
		}

		markBlockForUpdate();
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerSemifluidGenerator.class, this);
	}
}
