package techreborn.client.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import reborncore.api.power.IEnergyInterfaceItem;

/**
 * Created by Prospector
 */
public class ChargeSlot extends TRSlot {
	IO io;

	public ChargeSlot(IInventory inventoryIn, int index, int x, int y, IO io) {
		super(inventoryIn, index, x, y, Priority.CHARGE);
		this.io = io;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() instanceof IEnergyInterfaceItem) {
			if (io == IO.INPUT && ((IEnergyInterfaceItem) stack.getItem()).canAcceptEnergy(stack)) {
				return true;
			}
			if (io == IO.OUTPUT && ((IEnergyInterfaceItem) stack.getItem()).canProvideEnergy(stack)) {
				return true;
			}
		}
		return false;
	}

	public enum IO {
		INPUT, OUTPUT
	}
}
