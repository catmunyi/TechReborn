package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.TileChunkLoader;

public class ContainerChunkloader extends RebornContainer
{

	EntityPlayer player;

	public ContainerChunkloader(TileChunkLoader tilechunkloader, EntityPlayer player)
	{
		this.player = player;
		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

}
