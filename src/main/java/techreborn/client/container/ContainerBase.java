package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotCharge;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.generator.TileGenerator;

/**
 * Created by Prospector
 */
public class ContainerBase extends RebornContainer {
	public ContainerBase(TileGenerator tile, EntityPlayer player) {
		addSlotToContainer(new SlotCharge(tile.inventory, 1, 8, 60));
		drawPlayersInvAndHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return false;
	}

	@Override
	public void drawPlayersInvAndHotbar(EntityPlayer player) {
		super.drawPlayersInvAndHotbar(player, 8, 80);
	}
}
