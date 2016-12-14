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
	public int energy;
	public TileMachineBase tile;

	public ContainerMachineBase(TileMachineBase tile, EntityPlayer player) {
		addSlotToContainer(new SlotCharge(tile.inventory, 0, 8, 60));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 1, 152, 6));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 2, 152, 24));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 3, 152, 42));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 4, 152, 60));
		drawPlayersInvAndHotbar(player, 8, 80);
		this.tile = tile;
	}

	public ContainerMachineBase(TileMachineBase tile, EntityPlayer player, boolean noCharge, boolean noUpgrade) {
		if (!noCharge)
			addSlotToContainer(new SlotCharge(tile.inventory, 0, 8, 60));
		if (!noUpgrade) {
			addSlotToContainer(new SlotUpgrade(tile.inventory, 1, 152, 6));
			addSlotToContainer(new SlotUpgrade(tile.inventory, 2, 152, 24));
			addSlotToContainer(new SlotUpgrade(tile.inventory, 3, 152, 42));
			addSlotToContainer(new SlotUpgrade(tile.inventory, 4, 152, 60));
		}
		drawPlayersInvAndHotbar(player, 8, 80);
		this.tile = tile;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return false;
	}
}
