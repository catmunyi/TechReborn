package techreborn.tiles.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.tile.TileMachineInventory;
import techreborn.api.Reference;
import techreborn.client.container.energy.tier1.ContainerAlloySmelter;
import techreborn.init.ModBlocks;

public class TileAlloySmelter extends TileMachineInventory implements IRecipeCrafterProvider {

	public RecipeCrafter crafter;

	public TileAlloySmelter() {
		super(EnumPowerTier.LOW, 1000, 0, 1, "AlloySmelter", 4, 64);
		// Input slots
		int[] inputs = new int[] { 0, 1 };
		int[] outputs = new int[] { 2 };
		this.crafter = new RecipeCrafter(Reference.alloySmelteRecipe, this, 2, 1, getInventory(), inputs, outputs);
		//upgrades = new UpgradeHandler(crafter, inventory, 4, 5, 6, 7);
	}

	@Override
	public void updateEntity() {
		//super.updateEntity();
		crafter.updateEntity();
	}

	@Override
	public void machineTick() {
		if (!this.crafter.machineTick())
			return;

		super.machineTick();
	}

	@Override
	public void machineFinish() {
		this.crafter.machineFinish();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.alloySmelter, 1);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		//crafter.readFromNBT(tagCompound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		//crafter.writeToNBT(tagCompound);
		return tagCompound;
	}

	// @Override
	// public void addWailaInfo(List<String> info){
	// super.addWailaInfo(info);
	// info.add("Power Stored " + energy.getEnergyStored() + "/" +
	// energy.getCapacity() +" EU");
	// if(crafter.currentRecipe !=null){
	// info.add("Power Usage " + crafter.currentRecipe.euPerTick() + " EU/t");
	// }
	// }

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
	//		return isItemValidForSlot(slotIndex, itemStack);
	//	}
	//
	//	@Override
	//	public boolean canExtractItem(int slotIndex, ItemStack itemStack, EnumFacing side)
	//	{
	//		return slotIndex == 2;
	//	}

	public int getProgressScaled(int scale) {
		if (crafter.currentTickTime != 0 && crafter.currentNeededTicks != 0) {
			return crafter.currentTickTime * scale / crafter.currentNeededTicks;
		}
		return 0;
	}

	@Override
	public void updateInventory() {
		this.crafter.updateInventory();
	}

	@Override
	public RecipeCrafter getRecipeCrafter() {
		return crafter;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerAlloySmelter.class, this);
	}
}
