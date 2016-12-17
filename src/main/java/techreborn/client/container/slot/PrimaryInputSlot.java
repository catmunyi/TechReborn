package techreborn.client.container.slot;

import net.minecraft.inventory.IInventory;

/**
 * Created by Prospector
 */
public class PrimaryInputSlot extends TRSlot {
	public PrimaryInputSlot(IInventory inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y, Priority.PRIMARY_INPUT);
	}
}
