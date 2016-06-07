package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.TileDigitalChest;

public class ContainerDigitalChest extends RebornContainer
{
	public TileDigitalChest tileDigitalChest;
	public EntityPlayer player;

	public ContainerDigitalChest(TileDigitalChest tileDigitalChest, EntityPlayer player)
	{
		super();
		this.tileDigitalChest = tileDigitalChest;
		this.player = player;

		this.addSlotToContainer(new BaseSlot(tileDigitalChest.inventory, 0, 80, 17));
		this.addSlotToContainer(new SlotOutput(tileDigitalChest.inventory, 1, 80, 53));
		this.addSlotToContainer(new SlotFake(tileDigitalChest.inventory, 2, 59, 42, false, false, Integer.MAX_VALUE));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

}