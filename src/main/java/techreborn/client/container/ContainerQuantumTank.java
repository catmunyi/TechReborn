package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotFluid;
import reborncore.client.gui.slots.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.TileQuantumTank;

public class ContainerQuantumTank extends RebornContainer
{
	public TileQuantumTank tileQuantumTank;
	public EntityPlayer player;

	public ContainerQuantumTank(TileQuantumTank tileQuantumTank, EntityPlayer player)
	{
		super();
		this.tileQuantumTank = tileQuantumTank;
		this.player = player;

		this.addSlotToContainer(new SlotFluid(tileQuantumTank.inventory, 0, 80, 17));
		this.addSlotToContainer(new SlotOutput(tileQuantumTank.inventory, 1, 80, 53));
		this.addSlotToContainer(new SlotFake(tileQuantumTank.inventory, 2, 59, 42, false, false, 1));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}
