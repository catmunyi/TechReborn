package techreborn.tiles.energy.tier3;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import powercrystals.minefactoryreloaded.api.IDeepStorageUnit;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import reborncore.common.util.ItemUtils;
import techreborn.client.container.energy.tier3.ContainerQuantumTank;
import techreborn.init.ModBlocks;

public class TileQuantumChest extends TileMachineInventory implements IDeepStorageUnit {

	// Slot 0 = Input
	// Slot 1 = Output
	// Slot 2 = Fake Item

	public ItemStack storedItem;
	// TODO use long so we can have 9,223,372,036,854,775,807 items instead of
	// 2,147,483,647

	public TileQuantumChest() {
		super(EnumPowerTier.HIGH, 10000, 0, 1, "TileQuantumChest", 3, Integer.MAX_VALUE);
	}

	@Override
	public void updateEntity() {
		if (storedItem != null) {
			ItemStack fakeStack = storedItem.copy();
			fakeStack.stackSize = 1;
			getInventory().setInventorySlotContents(2, fakeStack);
		} else if (getInventory().getStackInSlot(1) != null) {
			ItemStack fakeStack = getInventory().getStackInSlot(1).copy();
			fakeStack.stackSize = 1;
			getInventory().setInventorySlotContents(2, fakeStack);
		} else {
			getInventory().setInventorySlotContents(2, null);
		}

		if (getInventory().getStackInSlot(0) != null) {
			if (storedItem == null) {
				storedItem = getInventory().getStackInSlot(0);
				getInventory().setInventorySlotContents(0, null);
			} else if (ItemUtils.isItemEqual(storedItem, getInventory().getStackInSlot(0), true, true)) {
				if (storedItem.stackSize <= getInventory().getInventoryStackLimit() - getInventory().getStackInSlot(0).stackSize) {
					storedItem.stackSize += getInventory().getStackInSlot(0).stackSize;
					getInventory().decrStackSize(0, getInventory().getStackInSlot(0).stackSize);
				}
			}
		}

		if (storedItem != null && getInventory().getStackInSlot(1) == null) {
			ItemStack itemStack = storedItem.copy();
			itemStack.stackSize = itemStack.getMaxStackSize();
			getInventory().setInventorySlotContents(1, itemStack);
			storedItem.stackSize -= itemStack.getMaxStackSize();
		} else if (ItemUtils.isItemEqual(getInventory().getStackInSlot(1), storedItem, true, true)) {
			int wanted = getInventory().getStackInSlot(1).getMaxStackSize() - getInventory().getStackInSlot(1).stackSize;
			if (storedItem.stackSize >= wanted) {
				getInventory().decrStackSize(1, -wanted);
				storedItem.stackSize -= wanted;
			} else {
				getInventory().decrStackSize(1, -storedItem.stackSize);
				storedItem = null;
			}
		}
	}

	@Override
	public void machineFinish() {

	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		readFromNBTWithoutCoords(tagCompound);
	}

	public void readFromNBTWithoutCoords(NBTTagCompound tagCompound) {

		//		storedItem = null;
		//
		//		if (tagCompound.hasKey("storedStack"))
		//		{
		//			storedItem = ItemStack.loadItemStackFromNBT((NBTTagCompound) tagCompound.getTag("storedStack"));
		//		}
		//
		//		if (storedItem != null)
		//		{
		//			storedItem.stackSize = tagCompound.getInteger("storedQuantity");
		//		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		writeToNBTWithoutCoords(tagCompound);
		return tagCompound;
	}

	public NBTTagCompound writeToNBTWithoutCoords(NBTTagCompound tagCompound) {
		//		if (storedItem != null)
		//		{
		//			tagCompound.setTag("storedStack", storedItem.writeToNBT(new NBTTagCompound()));
		//			tagCompound.setInteger("storedQuantity", storedItem.stackSize);
		//		} else{
		//			tagCompound.setInteger("storedQuantity", 0);
		//		}

		return tagCompound;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return getDropWithNBT();
	}

	public ItemStack getDropWithNBT() {
		NBTTagCompound tileEntity = new NBTTagCompound();
		ItemStack dropStack = new ItemStack(ModBlocks.quantumChest, 1);
		writeToNBTWithoutCoords(tileEntity);
		dropStack.setTagCompound(new NBTTagCompound());
		dropStack.getTagCompound().setTag("tileEntity", tileEntity);
		return dropStack;
	}

	@Override
	public ItemStack getStoredItemType() {
		return this.storedItem;
	}

	@Override
	public void setStoredItemCount(int amount) {
		this.storedItem.stackSize = 0;
		this.storedItem.stackSize += (amount);
		this.markDirty();
	}

	@Override
	public void setStoredItemType(ItemStack type, int amount) {
		this.storedItem = type;
		this.storedItem.stackSize = amount;
		this.markDirty();
	}

	@Override
	public int getMaxStoredCount() {
		return this.getInventory().getInventoryStackLimit();
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerQuantumTank.class, this);
	}
}
