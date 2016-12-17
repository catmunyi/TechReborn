package techreborn.client.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import techreborn.utils.upgrade.IMachineUpgrade;

/**
 * Created by Prospector
 */
public class UpgradeSlot extends TRSlot {
	public UpgradeSlot(IInventory inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y, Priority.UPGRADE);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() instanceof IMachineUpgrade) {
			return true;
		}
		return false;
	}
}
