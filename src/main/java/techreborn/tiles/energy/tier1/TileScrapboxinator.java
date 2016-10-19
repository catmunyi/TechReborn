package techreborn.tiles.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import techreborn.api.ScrapboxList;
import techreborn.client.container.energy.tier1.ContainerScrapboxinator;
import techreborn.init.ModBlocks;

import java.util.Random;

public class TileScrapboxinator extends TileMachineInventory {

	public int chance = 4;

	public int random;
	public int input1 = 0;
	public int output = 1;

	public TileScrapboxinator() {
		super(EnumPowerTier.LOW, 1000, 20, 200, "TileScrapboxinator", 2, 64);
	}

	//	@Override
	//	public void updateEntity()
	//	{
	//		boolean burning = isBurning();
	//		boolean updateInventory = false;
	//		if (getEnergy() <= cost && canOpen())
	//		{
	//			if (getEnergy() > cost)
	//			{
	//				updateInventory = true;
	//			}
	//		}
	//		if (isBurning() && canOpen())
	//		{
	//			updateState();
	//
	//			progress++;
	//			if (progress >= time)
	//			{
	//				progress = 0;
	//				recycleItems();
	//				updateInventory = true;
	//			}
	//		} else
	//		{
	//			progress = 0;
	//			updateState();
	//		}
	//		if (burning != isBurning())
	//		{
	//			updateInventory = true;
	//		}
	//		if (updateInventory)
	//		{
	//			markDirty();
	//		}
	//	}

	@Override
	public void machineFinish() {
		if (this.canOpen() && !worldObj.isRemote) {
			int random = new Random().nextInt(ScrapboxList.stacks.size());
			ItemStack out = ScrapboxList.stacks.get(random).copy();
			if (getInventory().getStackInSlot(output) == null) {
				getInventory().setInventorySlotContents(output, out);
			}

			if (getInventory().getStackInSlot(input1).stackSize > 1) {
				getInventory().decrStackSize(input1, 1);
			} else {
				getInventory().setInventorySlotContents(input1, null);
			}
		}
	}

	public boolean canOpen() {
		return getInventory().getStackInSlot(input1) != null && getInventory().getStackInSlot(output) == null;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.scrapboxinator, 1);
	}

	// ISidedInventory
	//	@Override
	//	public int[] getSlotsForFace(EnumFacing side)
	//	{
	//		return side == EnumFacing.DOWN ? new int[] { 0, 1, 2 } : new int[] { 0, 1, 2 };
	//	}
	//
	//	@Override
	//	public boolean canInsertItem(int slotIndex, ItemStack itemStack, EnumFacing side)
	//	{
	//		if (slotIndex == 2)
	//			return false;
	//		if (slotIndex == 1)
	//		{
	//			if (itemStack.getItem() == ModItems.scrapBox)
	//			{
	//				return true;
	//			}
	//		}
	//		return isItemValidForSlot(slotIndex, itemStack);
	//	}
	//
	//	@Override
	//	public boolean canExtractItem(int slotIndex, ItemStack itemStack, EnumFacing side)
	//	{
	//		return slotIndex == 2;
	//	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerScrapboxinator.class, this);
	}
}
