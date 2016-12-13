package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotCharge;
import techreborn.api.gui.SlotUpgrade;
import techreborn.tiles.TileMachineBase;

/**
 * Created by Prospector
 */
public class ContainerMachineBase extends ContainerBase {
	public static final int lastSlotIndex = 4;

	public ContainerMachineBase(TileMachineBase tile, EntityPlayer player) {
		super(tile, player);
		addSlotToContainer(new SlotCharge(tile.inventory, 0, 8, 60));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 1, 152, 6));
		addSlotToContainer(new SlotCharge(tile.inventory, 2, 152, 24));
		addSlotToContainer(new SlotCharge(tile.inventory, 3, 152, 42));
		addSlotToContainer(new SlotCharge(tile.inventory, 4, 152, 60));
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return false;
	}
}
