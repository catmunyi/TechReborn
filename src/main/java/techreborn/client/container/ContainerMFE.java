package techreborn.client.container;

/**
 * Created by Rushmead
 */

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotCharge;
import techreborn.tiles.storage.TileMFE;

public class ContainerMFE extends ContainerMachineBase {

	private static final EntityEquipmentSlot[] equipmentSlots = new EntityEquipmentSlot[] { EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET };
	public int tickTime;
	EntityPlayer player;
	TileMFE tile;

	public ContainerMFE(TileMFE tile, EntityPlayer player) {
		super(tile, player, true, false);
		this.tile = tile;
		this.player = player;

		for (int k = 0; k < 4; k++) {
			final EntityEquipmentSlot slot = equipmentSlots[k];
			addSlotToContainer(new BaseSlot(player.inventory, player.inventory.getSizeInventory() - 2 - k, 8, 18 + k * 18) {
				@Override
				public int getSlotStackLimit() { return 1; }

				@Override
				public boolean isItemValid(ItemStack stack) {
					return stack != null && stack.getItem().isValidArmor(stack, slot, player);
				}
			});
		}

		this.addSlotToContainer(new SlotCharge(tile.inventory, 0, 62, 47));
		this.addSlotToContainer(new SlotCharge(tile.inventory, 1, 98, 47));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.energy != (int) tile.getEnergy()) {
				IContainerListener.sendProgressBarUpdate(this, 0, (int) tile.getEnergy());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, (int) tile.getEnergy());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.energy = value;
		}
		this.tile.setEnergy(energy);
	}
}
