package techreborn.tiles.energy.tier0;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.inventory.Inventory;
import techreborn.client.container.energy.tier0.ContainerIronFurnace;

public class TileIronFurnace extends AbstractTileTier0 {

    private int inputSlot = 2;

    public TileIronFurnace() {
        super(160, new Inventory("TileIronFurnace", 3, 64));
    }

    @Override
    void processItems() {
        if (this.canSmelt()) {
            ItemStack inputStack = getInventory().getStackInSlot(this.inputSlot);
            if (inputStack != null) {
                ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(inputStack);
                if (smeltingResult != null) {
                    ItemStack outputSlotStack = getInventory().getStackInSlot(this.outputSlot);
                    if (outputSlotStack == null) {
                        getInventory().setInventorySlotContents(this.outputSlot, smeltingResult.copy());
                    } else if (outputSlotStack.isItemEqual(inputStack)) {
                        outputSlotStack.stackSize += smeltingResult.stackSize;
                    }

                    if (inputStack.stackSize > 1) {
                        getInventory().decrStackSize(this.inputSlot, 1);
                    } else {
                        getInventory().setInventorySlotContents(this.inputSlot, null);
                    }
                }
            }
        }
    }

    @Override
    boolean canSmelt() {
        ItemStack inputStack = getInventory().getStackInSlot(this.inputSlot);
        if (inputStack == null) {
            return false;
        } else {
            ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(inputStack);
            if (smeltingResult == null)
                return false;

            ItemStack outputSlotStack = getInventory().getStackInSlot(this.outputSlot);
            if (outputSlotStack == null)
                return true;

            if (!outputSlotStack.isItemEqual(smeltingResult))
                return false;

            int result = outputSlotStack.stackSize + smeltingResult.stackSize;
            return (result <= getInventory().getInventoryStackLimit() && result <= smeltingResult.getMaxStackSize());
        }
    }

    @Override
    public RebornContainer getContainer() {
        return RebornContainer.getContainerFromClass(ContainerIronFurnace.class, this);
    }
}
