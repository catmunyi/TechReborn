package techreborn.client.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by Prospector
 */
public class OutputSlot extends TRSlot {
	public OutputSlot(IInventory inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y, Priority.NONE);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}
