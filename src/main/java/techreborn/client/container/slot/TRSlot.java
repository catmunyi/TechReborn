package techreborn.client.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by Prospector
 */
public class TRSlot extends Slot {
	public TRSlot(IInventory inventoryIn, int index, int x, int y, Priority priority) {
		super(inventoryIn, index, x, y);
	}

	public boolean canWorldBlockRemove() {
		return true;
	}

	public enum Priority {
		UPGRADE(100), CHARGE(100), PRIMARY_INPUT(20), INPUT(15), NONE(0);
		public int priority;

		Priority(int priority) {
			this.priority = priority;
		}
	}
}
