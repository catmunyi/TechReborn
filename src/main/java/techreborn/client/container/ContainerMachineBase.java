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
		super(tile.getName());
		addSlotToContainer(new SlotCharge(tile.inventory, 0, 8, 72));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 1, 152, 18));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 2, 152, 36));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 3, 152, 54));
		addSlotToContainer(new SlotUpgrade(tile.inventory, 4, 152, 72));
		drawPlayersInvAndHotbar(player, 8, 94);
		this.tile = tile;
	}

	public ContainerMachineBase(TileMachineBase tile, EntityPlayer player, boolean noCharge, boolean noUpgrade) {
		super(tile.getName());
		if (!noCharge)
			addSlotToContainer(new SlotCharge(tile.inventory, 0, 8, 72));
		if (!noUpgrade) {
			addSlotToContainer(new SlotUpgrade(tile.inventory, 1, 152, 18));
			addSlotToContainer(new SlotUpgrade(tile.inventory, 2, 152, 36));
			addSlotToContainer(new SlotUpgrade(tile.inventory, 3, 152, 54));
			addSlotToContainer(new SlotUpgrade(tile.inventory, 4, 152, 72));
		}
		drawPlayersInvAndHotbar(player, 8, 94);
		this.tile = tile;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return false;
	}
}
