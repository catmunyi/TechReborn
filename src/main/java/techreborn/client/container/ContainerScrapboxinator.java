package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.api.gui.SlotUpgrade;
import techreborn.tiles.TileScrapboxinator;

public class ContainerScrapboxinator extends RebornContainer
{

	public int connectionStatus;
	EntityPlayer player;
	TileScrapboxinator tile;

	public ContainerScrapboxinator(TileScrapboxinator tileScrapboxinator, EntityPlayer player)
	{
		super();
		tile = tileScrapboxinator;
		this.player = player;

		// input
		this.addSlotToContainer(new SlotScrapbox(tileScrapboxinator.inventory, 0, 56, 34));
		this.addSlotToContainer(new SlotOutput(tileScrapboxinator.inventory, 1, 116, 34));

		// upgrades
		this.addSlotToContainer(new SlotUpgrade(tileScrapboxinator.inventory, 2, 152, 8));
		this.addSlotToContainer(new SlotUpgrade(tileScrapboxinator.inventory, 3, 152, 26));
		this.addSlotToContainer(new SlotUpgrade(tileScrapboxinator.inventory, 4, 152, 44));
		this.addSlotToContainer(new SlotUpgrade(tileScrapboxinator.inventory, 5, 152, 62));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		if (id == 10)
		{
			this.connectionStatus = value;
		}
	}
}
