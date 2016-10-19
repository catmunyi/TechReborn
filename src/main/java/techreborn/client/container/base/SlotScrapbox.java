package techreborn.client.container.base;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import reborncore.client.gui.slots.BaseSlot;
import techreborn.init.ModItems;

public class SlotScrapbox extends BaseSlot {

    public SlotScrapbox(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == ModItems.scrapBox;
    }

    public int getSlotStackLimit() {
        return 64;
    }
}