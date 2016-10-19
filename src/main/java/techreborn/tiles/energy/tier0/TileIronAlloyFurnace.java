package techreborn.tiles.energy.tier0;

import net.minecraft.item.ItemStack;
import reborncore.api.recipe.IBaseRecipeType;
import reborncore.api.recipe.RecipeHandler;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.ItemUtils;
import reborncore.common.util.inventory.Inventory;
import techreborn.api.Reference;
import techreborn.client.container.energy.tier0.ContainerIronAlloyFurnace;

public class TileIronAlloyFurnace extends AbstractTileTier0 {

	private int inputSlot1 = 2;
	private int inputSlot2 = 3;

	public TileIronAlloyFurnace() {
		super(200, new Inventory("TileIronAlloyFurnace", 4, 64));
	}

	private boolean hasAllInputs(IBaseRecipeType recipeType) {
		if (recipeType == null) {
			return false;
		}

		for (ItemStack input : recipeType.getInputs()) {
			boolean hasItem = false;
			for (int inputSlot_id = 0; inputSlot_id < 2; inputSlot_id++) {
				ItemStack inputStack = getInventory().getStackInSlot(inputSlot_id);
				if (inputStack == null)
					continue;

				if (ItemUtils.isItemEqual(input, inputStack, true, true, recipeType.useOreDic()) && inputStack.stackSize >= input.stackSize) {
					hasItem = true;
					break;
				}
			}
			if (!hasItem) {
				return false;
			}
		}
		return true;
	}

	@Override
	void processItems() {
		if (this.canSmelt()) {
			ItemStack itemstack = null;
			for (IBaseRecipeType recipeType : RecipeHandler.getRecipeClassFromName(Reference.alloySmelteRecipe)) {
				if (hasAllInputs(recipeType)) {
					itemstack = recipeType.getOutput(0);
					break;
				}
			}

			if (getInventory().getStackInSlot(this.outputSlot) == null) {
				getInventory().setInventorySlotContents(this.outputSlot, itemstack.copy());
			} else if (getInventory().getStackInSlot(this.outputSlot).getItem() == itemstack.getItem()) {
				getInventory().decrStackSize(this.outputSlot, -itemstack.stackSize);
			}

			for (IBaseRecipeType recipeType : RecipeHandler.getRecipeClassFromName(Reference.alloySmelteRecipe)) {
				boolean hasAllRecipes = true;
				if (!hasAllInputs(recipeType)) {
					hasAllRecipes = false;
				}

				if (hasAllRecipes) {
					for (ItemStack input : recipeType.getInputs()) {
						for (int inputSlot = 0; inputSlot < 2; inputSlot++) {
							if (ItemUtils.isItemEqual(input, getInventory().getStackInSlot(inputSlot), true, true, recipeType.useOreDic())) {
								getInventory().decrStackSize(inputSlot, input.stackSize);
								break;
							}
						}
					}
				}
			}

		}
	}

	@Override
	boolean canSmelt() {
		if (getInventory().getStackInSlot(this.inputSlot1) == null || getInventory().getStackInSlot(this.inputSlot2) == null) {
			return false;
		} else {
			ItemStack itemstack = null;
			for (IBaseRecipeType recipeType : RecipeHandler.getRecipeClassFromName(Reference.alloySmelteRecipe)) {
				if (hasAllInputs(recipeType)) {
					itemstack = recipeType.getOutput(0);
					break;
				}
			}

			if (itemstack == null)
				return false;

			if (getInventory().getStackInSlot(this.outputSlot) == null)
				return true;

			if (!getInventory().getStackInSlot(this.outputSlot).isItemEqual(itemstack))
				return false;

			int result = getInventory().getStackInSlot(this.outputSlot).stackSize + itemstack.stackSize;
			return result <= getInventory().getInventoryStackLimit() && result <= getInventory().getStackInSlot(this.outputSlot).getMaxStackSize();
		}
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerIronAlloyFurnace.class, this);
	}
}
