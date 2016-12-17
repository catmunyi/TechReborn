package techreborn.client.container.storage;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerMachineBase;
import techreborn.client.container.slot.ChargeSlot;
import techreborn.tiles.storage.TileBatBox;

public class ContainerBatbox extends ContainerMachineBase {

	public ContainerBatbox(TileBatBox tile, EntityPlayer player) {
		super(tile, player);
		addChargeSlot(tile.inventory, 62, 47, ChargeSlot.IO.OUTPUT);
		addChargeSlot(tile.inventory, 98, 47, ChargeSlot.IO.INPUT);
	}
}
