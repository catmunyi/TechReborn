package techreborn.tiles.energy.tier3;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import techreborn.client.container.energy.tier3.ContainerQuantumTank;
import techreborn.init.ModBlocks;

public class TileQuantumTank extends TileMachineInventory implements IFluidHandler {

	public FluidTank tank = new FluidTank(Integer.MAX_VALUE);

	public TileQuantumTank() {
		super(EnumPowerTier.HIGH, 10000, 0, 1, "TileQuantumTank", 3, 64);
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
	public void updateEntity() {
		super.updateEntity();
		if (tank.getFluid() != null && getInventory().getStackInSlot(2) == null) {
			//				inventory.setInventorySlotContents(2, new ItemStack(tank.getFluidType().getBlock()));
		} else if (tank.getFluid() == null && getInventory().getStackInSlot(2) != null) {
			getInventory().setInventorySlotContents(2, null);
		}
	}

	@Override
	public void machineFinish() {

	}

	// IFluidHandler
	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		int fill = tank.fill(resource, doFill);
		return fill;
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		FluidStack drain = tank.drain(resource.amount, doDrain);
		return drain;
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		FluidStack drain = tank.drain(maxDrain, doDrain);
		return drain;
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		return tank.getFluid() == null || tank.getFluid().getFluid() == fluid;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		return tank.getFluid() == null || tank.getFluid().getFluid() == fluid;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return getDropWithNBT();
	}

	public ItemStack getDropWithNBT() {
		NBTTagCompound tileEntity = new NBTTagCompound();
		ItemStack dropStack = new ItemStack(ModBlocks.quantumTank, 1);
		tank.writeToNBT(tileEntity);
		dropStack.setTagCompound(new NBTTagCompound());
		dropStack.getTagCompound().setTag("tileEntity", tileEntity);
		return dropStack;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerQuantumTank.class, this);
	}
}
