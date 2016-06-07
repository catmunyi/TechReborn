package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.tiles.TileIndustrialElectrolyzer;

public class ContainerIndustrialElectrolyzer extends ContainerCrafting
{

	public int tickTime;
	EntityPlayer player;
	TileIndustrialElectrolyzer tile;

	public ContainerIndustrialElectrolyzer(TileIndustrialElectrolyzer electrolyzer, EntityPlayer player)
	{
		super(electrolyzer.crafter);
		tile = electrolyzer;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(electrolyzer.inventory, 0, 80, 51));
		this.addSlotToContainer(new BaseSlot(electrolyzer.inventory, 1, 50, 51));
		// outputs
		this.addSlotToContainer(new SlotOutput(electrolyzer.inventory, 2, 50, 19));
		this.addSlotToContainer(new SlotOutput(electrolyzer.inventory, 3, 70, 19));
		this.addSlotToContainer(new SlotOutput(electrolyzer.inventory, 4, 90, 19));
		this.addSlotToContainer(new SlotOutput(electrolyzer.inventory, 5, 110, 19));

		// battery
		this.addSlotToContainer(new BaseSlot(electrolyzer.inventory, 6, 18, 51));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

}
