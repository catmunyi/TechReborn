package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.slot.ChargeSlot;
import techreborn.tiles.TileMachineBase;

/**
 * Created by Prospector
 */
public class ContainerMachineBase extends TRContainer {

	public ContainerMachineBase(TileMachineBase tile, EntityPlayer player) {
		super(tile, player);
		addChargeSlot(tile.inventory, 8, 72, ChargeSlot.IO.OUTPUT);
		addUpgradeSlot(tile.inventory, 152, 18);
		addUpgradeSlot(tile.inventory, 152, 36);
		addUpgradeSlot(tile.inventory, 152, 54);
		addUpgradeSlot(tile.inventory, 152, 72);
	}

	public ContainerMachineBase(TileMachineBase tile, EntityPlayer player, boolean noCharge, boolean noUpgrade) {
		super(tile, player);
		if (!noCharge)
			addChargeSlot(tile.inventory, 8, 72, ChargeSlot.IO.OUTPUT);
		if (!noUpgrade) {
			addUpgradeSlot(tile.inventory, 152, 18);
			addUpgradeSlot(tile.inventory, 152, 36);
			addUpgradeSlot(tile.inventory, 152, 54);
			addUpgradeSlot(tile.inventory, 152, 72);
		}
	}
}
