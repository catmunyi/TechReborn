package techreborn.tiles.energy.tier3;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import techreborn.client.container.energy.tier3.ContainerDigitalChest;
import techreborn.init.ModBlocks;

public class TileDigitalChest extends TileMachineInventory {

	// Slot 0 = Input
	// Slot 1 = Output
	// Slot 2 = Fake Item

	public ItemStack storedItem;
	private long storage = 32767;

	public TileDigitalChest() {
		super(EnumPowerTier.HIGH, 100000, 0, 1, "TileDigitalChest", 3, 32767);
	}

	//	@Override
	//	public void updateEntity()
	//	{
	//		if (!worldObj.isRemote)
	//		{
	//			if (storedItem != null)
	//			{
	//				ItemStack fakeStack = storedItem.copy();
	//				fakeStack.stackSize = 1;
	//				setInventorySlotContents(2, fakeStack);
	//			} else if (storedItem == null && getStackInSlot(1) != null)
	//			{
	//				ItemStack fakeStack = getStackInSlot(1).copy();
	//				fakeStack.stackSize = 1;
	//				setInventorySlotContents(2, fakeStack);
	//			} else
	//			{
	//				setInventorySlotContents(2, null);
	//			}
	//
	//			if (getStackInSlot(0) != null)
	//			{
	//				if (storedItem == null)
	//				{
	//					storedItem = getStackInSlot(0);
	//					setInventorySlotContents(0, null);
	//				} else if (ItemUtils.isItemEqual(storedItem, getStackInSlot(0), true, true))
	//				{
	//					if (storedItem.stackSize <= storage - getStackInSlot(0).stackSize)
	//					{
	//						storedItem.stackSize += getStackInSlot(0).stackSize;
	//						decrStackSize(0, getStackInSlot(0).stackSize);
	//					}
	//				}
	//			}
	//
	//			if (storedItem != null && getStackInSlot(1) == null)
	//			{
	//				ItemStack itemStack = storedItem.copy();
	//				itemStack.stackSize = itemStack.getMaxStackSize();
	//				setInventorySlotContents(1, itemStack);
	//				storedItem.stackSize -= itemStack.getMaxStackSize();
	//			} else if (ItemUtils.isItemEqual(getStackInSlot(1), storedItem, true, true))
	//			{
	//				int wanted = getStackInSlot(1).getMaxStackSize() - getStackInSlot(1).stackSize;
	//				if (storedItem.stackSize >= wanted)
	//				{
	//					decrStackSize(1, -wanted);
	//					storedItem.stackSize -= wanted;
	//				} else
	//				{
	//					decrStackSize(1, -storedItem.stackSize);
	//					storedItem = null;
	//				}
	//			}
	//		}
	//	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		readFromNBTWithoutCoords(tagCompound);
	}

	public void readFromNBTWithoutCoords(NBTTagCompound tagCompound) {
		//
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
		//		} else
		//			tagCompound.setInteger("storedQuantity", 0);
		return tagCompound;
	}



	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return getDropWithNBT();
	}

	public ItemStack getDropWithNBT() {
		NBTTagCompound tileEntity = new NBTTagCompound();
		ItemStack dropStack = new ItemStack(ModBlocks.digitalChest, 1);
		writeToNBTWithoutCoords(tileEntity);
		dropStack.setTagCompound(new NBTTagCompound());
		dropStack.getTagCompound().setTag("tileEntity", tileEntity);
		return dropStack;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerDigitalChest.class, this);
	}
}
