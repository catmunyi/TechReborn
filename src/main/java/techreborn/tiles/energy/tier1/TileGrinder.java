package techreborn.tiles.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.api.tile.IContainerProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.tile.TileMachineInventory;
import techreborn.api.Reference;
import techreborn.client.container.energy.tier1.ContainerGrinder;
import techreborn.init.ModBlocks;

public class TileGrinder extends TileMachineInventory implements IRecipeCrafterProvider, IContainerProvider {
	public RecipeCrafter crafter;

	public TileGrinder() {
		super(EnumPowerTier.LOW, 1000, 0, 1, "TileGrinder", 6, 64);
		int[] inputs = new int[1];
		inputs[0] = 0;
		int[] outputs = new int[1];
		outputs[0] = 1;
		crafter = new RecipeCrafter(Reference.grinderRecipe, this, 2, 1, getInventory(), inputs, outputs);
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
	public void updateInventory() {
		this.crafter.updateInventory();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.grinder, 1);
	}

	//	// ISidedInventory
	//	@Override
	//	public int[] getSlotsForFace(EnumFacing side) {
	//		return side == EnumFacing.DOWN ? new int[] { 0, 1, 2 } : new int[] { 0, 1, 2 };
	//	}
	//
	//	@Override
	//	public boolean canInsertItem(int slotIndex, ItemStack itemStack, EnumFacing side) {
	//		return slotIndex != 2 && isItemValidForSlot(slotIndex, itemStack);
	//	}
	//
	//	@Override
	//	public boolean canExtractItem(int slotIndex, ItemStack itemStack, EnumFacing side) {
	//		return slotIndex == 2;
	//	}

	@Override
	public RecipeCrafter getRecipeCrafter() {
		return this.crafter;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerGrinder.class, this);
	}

	public int getProgressScaled(int scale) {
		if (crafter.currentTickTime != 0) {
			return crafter.currentTickTime * scale / crafter.currentNeededTicks;
		}
		return 0;
	}
}
