package techreborn.client.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import reborncore.client.gui.slots.BaseSlot;
import techreborn.tiles.energy.storage.TileEnergyStorage;

/**
 * Created by Lordmau5 on 10.06.2016.
 */
public abstract class ContainerEnergyEquipment extends ContainerEnergyStorage {

	private static final EntityEquipmentSlot[] equipmentSlots = new EntityEquipmentSlot[] { EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET };

	public ContainerEnergyEquipment(TileEnergyStorage tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		for (int i = 0; i < 4; i++) {
			final EntityEquipmentSlot slot = equipmentSlots[i];
			addSlotToContainer(new BaseSlot(player.inventory, player.inventory.getSizeInventory() - 2 - i, 44, 6 + i * 19) {
				@Override
				public int getSlotStackLimit() {
					return 1;
				}

				@Override
				public boolean isItemValid(ItemStack stack) {
					return stack != null && stack.getItem().isValidArmor(stack, slot, player);
				}
			});
		}
	}
}
