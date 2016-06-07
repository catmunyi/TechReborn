package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.TileQuantumChest;

public class ContainerQuantumChest extends RebornContainer
{
	public TileQuantumChest tileQuantumChest;
	public EntityPlayer player;

	public ContainerQuantumChest(TileQuantumChest tileQuantumChest, EntityPlayer player)
	{
		super();
		this.tileQuantumChest = tileQuantumChest;
		this.player = player;

		this.addSlotToContainer(new BaseSlot(tileQuantumChest.inventory, 0, 80, 17));
		this.addSlotToContainer(new SlotOutput(tileQuantumChest.inventory, 1, 80, 53));
		this.addSlotToContainer(new SlotFake(tileQuantumChest.inventory, 2, 59, 42, false, false, Integer.MAX_VALUE));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

}
