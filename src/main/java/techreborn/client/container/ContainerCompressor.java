package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.api.gui.SlotUpgrade;
import techreborn.tiles.teir1.TileCompressor;

public class ContainerCompressor extends ContainerCrafting
{

	public int connectionStatus;
	EntityPlayer player;
	TileCompressor tile;

	public ContainerCompressor(TileCompressor tileGrinder, EntityPlayer player)
	{
		super(tileGrinder.crafter);
		tile = tileGrinder;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tileGrinder.inventory, 0, 56, 34));
		this.addSlotToContainer(new SlotOutput(tileGrinder.inventory, 1, 116, 34));

		// upgrades
		this.addSlotToContainer(new SlotUpgrade(tileGrinder.inventory, 2, 152, 8));
		this.addSlotToContainer(new SlotUpgrade(tileGrinder.inventory, 3, 152, 26));
		this.addSlotToContainer(new SlotUpgrade(tileGrinder.inventory, 4, 152, 44));
		this.addSlotToContainer(new SlotUpgrade(tileGrinder.inventory, 5, 152, 62));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}
}
