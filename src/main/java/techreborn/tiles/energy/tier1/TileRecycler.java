package techreborn.tiles.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import techreborn.client.container.energy.tier1.ContainerRecycler;
import techreborn.init.ModBlocks;
import techreborn.items.ItemParts;

public class TileRecycler extends TileMachineInventory {
	private int chance = 8;

	public int inputSlot = 0;
	public int outputSlot = 1;

	public TileRecycler() {
		super(EnumPowerTier.LOW, 1000, 1, 45, "TileRecycler", 6, 64);
	}

	public void machineFinish() // recycleItems
	{
		ItemStack itemstack = ItemParts.getPartByName("scrap");
		int randomchance = worldObj.rand.nextInt(chance);

		if (getInventory().getStackInSlot(outputSlot) == null) {
			if (randomchance == 1) {
				getInventory().setInventorySlotContents(outputSlot, itemstack.copy());
			}
		} else if (getInventory().getStackInSlot(outputSlot).isItemEqual(itemstack)) {
			if (randomchance == 1) {
				getInventory().getStackInSlot(outputSlot).stackSize += itemstack.stackSize;
			}
		}
		if (getInventory().getStackInSlot(inputSlot).stackSize > 1) {
			getInventory().decrStackSize(inputSlot, 1);
		} else {
			getInventory().setInventorySlotContents(inputSlot, null);
		}
	}

	public boolean canRecycle() {
		return getInventory().getStackInSlot(inputSlot) != null && hasSlotGotSpace(outputSlot);
	}

	public boolean hasSlotGotSpace(int slot) {
		if (getInventory().getStackInSlot(slot) == null) {
			return true;
		} else if (getInventory().getStackInSlot(slot).stackSize < getInventory().getStackInSlot(slot).getMaxStackSize()) {
			return true;
		}
		return true;
	}

	@Override
	public boolean canWork() {
		return super.canWork() && canRecycle();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.recycler, 1);
	}

	//	// ISidedInventory
	//	@Override
	//	public int[] getSlotsForFace(EnumFacing side)
	//	{
	//		return side == EnumFacing.DOWN ? new int[] { outputSlot } : new int[] { inputSlot };
	//	}
	//
	//	@Override
	//	public boolean canInsertItem(int slotIndex, ItemStack itemStack, EnumFacing side) {
	//		return slotIndex != outputSlot && isItemValidForSlot(slotIndex, itemStack);
	//	}
	//
	//	@Override
	//	public boolean canExtractItem(int slotIndex, ItemStack itemStack, EnumFacing side)
	//	{
	//		return slotIndex == outputSlot;
	//	}

	@Override
	public double getMaxOutput() {
		return 0;
	}

	@Override
	public double getMaxInput() {
		return 32;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerRecycler.class, this);
	}

	@Override
	public void updateInventory() {

	}
}
