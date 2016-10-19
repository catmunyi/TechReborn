package techreborn.tiles.energy.tier3;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import techreborn.client.container.energy.tier3.ContainerMatterFabricator;
import techreborn.init.ModBlocks;
import techreborn.init.ModItems;
import techreborn.items.ItemParts;

public class TileMatterFabricator extends TileMachineInventory {

	public static int fabricationRate = 10000;
	public int progresstime = 0;
	private int amplifier = 0;

	public TileMatterFabricator() {
		super(EnumPowerTier.HIGH, 100000000, 0, 1, "TileMatterFabricator", 7, 64);
	}

	@Override
	public void machineFinish() {

	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.matterFabricator, 1);
	}

	//	// ISidedInventory
	//	@Override
	//	public int[] getSlotsForFace(EnumFacing side)
	//	{
	//		return side == EnumFacing.DOWN ? new int[] { 0, 1, 2, 3, 4, 5, 6 } : new int[] { 0, 1, 2, 3, 4, 5, 6 };
	//	}
	//
	//	@Override
	//	public boolean canInsertItem(int slotIndex, ItemStack itemStack, EnumFacing side)
	//	{
	//		if (slotIndex == 6)
	//			return false;
	//		return isItemValidForSlot(slotIndex, itemStack);
	//	}
	//
	//	@Override
	//	public boolean canExtractItem(int slotIndex, ItemStack itemStack, EnumFacing side)
	//	{
	//		return slotIndex == 6;
	//	}

	public int maxProgresstime() {
		return fabricationRate;
	}

	//	@Override
	//	public void updateEntity()
	//	{
	//		super.updateEntity();
	//
	//		if (!super.worldObj.isRemote)
	//		{
	//			for (int i = 0; i < 6; i++)
	//			{
	//				ItemStack stack = inventory.getStackInSlot(i);
	//				if (this.amplifier < 10000 && stack != null)
	//				{
	//					int amp = (int) ((long) (getValue(stack) / 32));
	//					if (ItemUtils.isItemEqual(stack, inventory.getStackInSlot(i), true, true))
	//					{
	//						if (canUseEnergy(1))
	//						{
	//							useEnergy(1);
	//							this.amplifier += amp;
	//							inventory.decrStackSize(i, 1);
	//						}
	//					}
	//				}
	//			}
	//
	//			if (this.amplifier > 0)
	//			{
	//				if (this.amplifier > this.getEnergy())
	//				{
	//					this.progresstime += this.getEnergy();
	//					this.amplifier -= this.getEnergy();
	//					this.decreaseStoredEnergy(this.getEnergy(), true);
	//				} else
	//				{
	//					this.progresstime += this.amplifier;
	//					this.decreaseStoredEnergy(this.amplifier, true);
	//					this.amplifier = 0;
	//				}
	//			}
	//			if (this.progresstime > this.maxProgresstime() && this.spaceForOutput())
	//			{
	//				this.progresstime -= this.maxProgresstime();
	//				this.addOutputProducts();
	//			}
	//
	//		}
	//
	//	}
	//
	//	private boolean spaceForOutput()
	//	{
	//		return inventory.getStackInSlot(6) == null
	//				|| ItemUtils.isItemEqual(inventory.getStackInSlot(6), new ItemStack(ModItems.uuMatter), true, true)
	//						&& inventory.getStackInSlot(6).stackSize < 64;
	//	}
	//
	//	private void addOutputProducts()
	//	{
	//
	//		if (inventory.getStackInSlot(6) == null)
	//		{
	//			inventory.setInventorySlotContents(6, new ItemStack(ModItems.uuMatter));
	//		} else if (ItemUtils.isItemEqual(inventory.getStackInSlot(6), new ItemStack(ModItems.uuMatter), true, true))
	//		{
	//			inventory.getStackInSlot(6).stackSize = Math.min(64, 1 + inventory.getStackInSlot(6).stackSize);
	//		}
	//	}
	//
	//	public boolean decreaseStoredEnergy(double aEnergy, boolean aIgnoreTooLessEnergy)
	//	{
	//		if (this.getEnergy() - aEnergy < 0 && !aIgnoreTooLessEnergy)
	//		{
	//			return false;
	//		} else
	//		{
	//			setEnergy(this.getEnergy() - aEnergy);
	//			if (this.getEnergy() < 0)
	//			{
	//				setEnergy(0);
	//				return false;
	//			} else
	//			{
	//				return true;
	//			}
	//		}
	//	}

	// TODO ic2
	public int getValue(ItemStack itemStack) {
		// int value = getValue(Recipes.matterAmplifier.getOutputFor(itemStack,
		// false));
		if (itemStack.getItem() == ModItems.parts && itemStack.getItemDamage() == ItemParts.getPartByName("scrap").getItemDamage()) {
			return 5000;
		} else if (itemStack.getItem() == ModItems.scrapBox) {
			return 45000;
		}
		return 0;
	}

	// private static Integer getValue(RecipeOutput output) {
	// if (output != null && output.metadata != null) {
	// return output.metadata.getInteger("amplification");
	// }
	// return 0;
	// }

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerMatterFabricator.class, this);
	}
}
