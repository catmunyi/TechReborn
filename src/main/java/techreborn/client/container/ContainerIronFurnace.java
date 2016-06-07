package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.TileIronFurnace;

public class ContainerIronFurnace extends RebornContainer
{

	public int connectionStatus;
	EntityPlayer player;
	TileIronFurnace tile;

	public ContainerIronFurnace(TileIronFurnace tileGrinder, EntityPlayer player)
	{
		super();
		tile = tileGrinder;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tileGrinder.inventory, 0, 56, 17));
		this.addSlotToContainer(new SlotOutput(tileGrinder.inventory, 1, 116, 34));
		// Fuel
		this.addSlotToContainer(new SlotFurnaceFuel(tileGrinder.inventory, 2, 56, 53));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
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
