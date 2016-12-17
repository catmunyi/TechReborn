package techreborn.client.container.slot;

import net.minecraft.inventory.IInventory;

/**
 * Created by Prospector
 */
public class InputSlot extends TRSlot {
	public InputSlot(IInventory inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y, Priority.INPUT);
	}
}
