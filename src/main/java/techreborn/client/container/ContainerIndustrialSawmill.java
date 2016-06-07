package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.tiles.TileIndustrialSawmill;

public class ContainerIndustrialSawmill extends ContainerCrafting
{

	public int tickTime;
	EntityPlayer player;
	TileIndustrialSawmill tile;

	public ContainerIndustrialSawmill(TileIndustrialSawmill tileIndustrialSawmill, EntityPlayer player)
	{
		super(tileIndustrialSawmill.crafter);
		tile = tileIndustrialSawmill;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tileIndustrialSawmill.inventory, 0, 32, 26));
		this.addSlotToContainer(new BaseSlot(tileIndustrialSawmill.inventory, 1, 32, 44));
		// outputs
		this.addSlotToContainer(new SlotOutput(tileIndustrialSawmill.inventory, 2, 84, 35));
		this.addSlotToContainer(new SlotOutput(tileIndustrialSawmill.inventory, 3, 102, 35));
		this.addSlotToContainer(new SlotOutput(tileIndustrialSawmill.inventory, 4, 120, 35));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

}
