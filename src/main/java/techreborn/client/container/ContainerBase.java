package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import reborncore.common.container.RebornContainer;

/**
 * Created by Prospector
 */
public class ContainerBase extends RebornContainer {
	public String name;

	public ContainerBase(TileEntity tile, EntityPlayer player, String name) {
		drawPlayersInvAndHotbar(player);
		this.name = name;
	}

	public ContainerBase(String name) {
		this.name = name;
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
