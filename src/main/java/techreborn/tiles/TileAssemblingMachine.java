package techreborn.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.api.tile.IContainerProvider;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.IWrenchable;
import reborncore.common.container.RebornContainer;
import reborncore.common.powerSystem.TilePowerAcceptor;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.util.Inventory;
import techreborn.api.Reference;
import techreborn.client.container.ContainerAssemblingMachine;
import techreborn.init.ModBlocks;

public class TileAssemblingMachine extends TilePowerAcceptor implements IWrenchable, ISidedInventory, IInventoryProvider, IRecipeCrafterProvider {

	public int tickTime;
	public Inventory inventory = new Inventory(8, "TileAssemblingMachine", 64, this);
	public RecipeCrafter crafter;

	public TileAssemblingMachine() {
		super(2);
		// Input slots
		int[] inputs = new int[2];
		inputs[0] = 0;
		inputs[1] = 1;
		int[] outputs = new int[1];
		outputs[0] = 2;
		crafter = new RecipeCrafter(Reference.assemblingMachineRecipe, this, 2, 2, inventory, inputs, outputs);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		charge(3);
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, EnumFacing side) {
		return false;
	}

	@Override
	public EnumFacing getFacing() {
		return getFacingEnum();
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return entityPlayer.isSneaking();
	}

	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.assemblyMachine, 1);
	}

	public boolean isComplete() {
		return false;
	}

	// @Override
	// public void addWailaInfo(List<String> info)
	// {
	// super.addWailaInfo(info);
	// info.add("Power Stored " + energy.getEnergyStored() +" EU");
	// if(crafter.currentRecipe !=null){
	// info.add("Power Usage " + crafter.currentRecipe.euPerTick() + " EU/t");
	// }
	// }

	public int getProgressScaled(int scale) {
		if (crafter.currentTickTime != 0) {
			return crafter.currentTickTime * scale / crafter.currentNeededTicks;
		}
		return 0;
	}

	@Override
	public double getMaxPower() {
		return 10000;
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing direction) {
		return true;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing direction) {
		return false;
	}

	@Override
	public double getMaxOutput() {
		return 0;
	}

	@Override
	public double getMaxInput() {
		return 128;
	}

	@Override
	public EnumPowerTier getTier() {
		return EnumPowerTier.LOW;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public RecipeCrafter getRecipeCrafter() {
		return crafter;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] { 0, 1, 2 };
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return index == 0 || index == 1;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 2;
	}

}
