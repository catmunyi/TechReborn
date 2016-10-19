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
import techreborn.client.container.energy.generator.ContainerThermalGenerator;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;

import javax.annotation.Nullable;

public class TileThermalGenerator extends AbstractTileGenerator implements IInventoryProvider, IContainerProvider {

	public Inventory inventory = new Inventory("TileThermalGenerator", 3, 64, this);
	public FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * 10);

	private int slotFakeFluid = 2;

	public TileThermalGenerator() {
		super(EnumPowerTier.LOW, ConfigTechReborn.ThermalGeneratorCharge);
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.thermalGenerator, 1);
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
		//super.updateEntity();
		//		if (!worldObj.isRemote)
		//		{
		//			FluidUtils.drainContainers(this, inventory, 0, 1);
		//			for (EnumFacing direction : EnumFacing.values())
		//			{
		//				if (worldObj.getBlockState(new BlockPos(getPos().getX() + direction.getFrontOffsetX(),
		//						getPos().getY() + direction.getFrontOffsetY(), getPos().getZ() + direction.getFrontOffsetZ()))
		//						.getBlock() == Blocks.LAVA)
		//				{
		//					addEnergy(1);
		//				}
		//			}
		//
		//			if (worldObj.getTotalWorldTime() % 40 == 0)
		//			{
		//				BlockMachineBase bmb = (BlockMachineBase) worldObj.getBlockState(pos).getBlock();
		//				boolean didFindLava = false;
		//				for (EnumFacing direction : EnumFacing.values())
		//				{
		//					if (worldObj.getBlockState(new BlockPos(getPos().getX() + direction.getFrontOffsetX(),
		//							getPos().getY() + direction.getFrontOffsetY(),
		//							getPos().getZ() + direction.getFrontOffsetZ())).getBlock() == Blocks.LAVA)
		//					{
		//						didFindLava = true;
		//					}
		//				}
		//				bmb.setActive(didFindLava, worldObj, pos);
		//			}
		//		}

		int euPerTick = ConfigTechReborn.ThermalGeneratorOutput;

		if (this.tank.getFluid() != null && this.tank.getFluidAmount() > 0 && canAddEnergy(euPerTick)) {
			this.tank.drain(1, true);
			addEnergy(euPerTick);
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
		return RebornContainer.getContainerFromClass(ContainerThermalGenerator.class, this);
	}
}
