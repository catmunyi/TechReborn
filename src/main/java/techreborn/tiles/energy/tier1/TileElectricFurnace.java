package techreborn.tiles.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import techreborn.client.container.energy.tier1.ContainerElectricFurnace;
import techreborn.init.ModBlocks;

public class TileElectricFurnace extends TileMachineInventory {
	private static final int inputSlot = 0;
	private static final int outputSlot = 1;

	public TileElectricFurnace() {
		super(EnumPowerTier.LOW, 1000, 3, 130, "TileElectricFurnace", 6, 64);
	}

	public void machineFinish() {
		if (this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(getInventory().getStackInSlot(inputSlot));

			if (getInventory().getStackInSlot(outputSlot) == null) {
				getInventory().setInventorySlotContents(outputSlot, itemstack.copy());
			} else if (getInventory().getStackInSlot(outputSlot).isItemEqual(itemstack)) {
				getInventory().getStackInSlot(outputSlot).stackSize += itemstack.stackSize;
			}
			if (getInventory().getStackInSlot(inputSlot).stackSize > 1) {
				getInventory().decrStackSize(inputSlot, 1);
			} else {
				getInventory().setInventorySlotContents(inputSlot, null);
			}
		}
	}

	public boolean canSmelt() {
		if (getInventory().getStackInSlot(inputSlot) == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(getInventory().getStackInSlot(inputSlot));
			if (itemstack == null)
				return false;
			if (getInventory().getStackInSlot(outputSlot) == null)
				return true;
			if (!getInventory().getStackInSlot(outputSlot).isItemEqual(itemstack))
				return false;
			int result = getInventory().getStackInSlot(outputSlot).stackSize + itemstack.stackSize;
			return (result <= getInventory().getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	public boolean canWork() {
		return super.canWork() && canSmelt();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.electricFurnace, 1);
	}

	public boolean isComplete() {
		return false;
	}

	@Override
	public double getMaxOutput() {
		return 0;
	}

	@Override
	public double getMaxInput() {
		return 32;
	}

	@Override
	public EnumPowerTier getTier() {
		return EnumPowerTier.LOW;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerElectricFurnace.class, this);
	}

	@Override
	public void updateInventory() {

	}
}
