package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.generator.TileGasTurbine;

public class ContainerGasTurbine extends RebornContainer
{
	public TileGasTurbine tileGasTurbine;
	public EntityPlayer player;

	public ContainerGasTurbine(TileGasTurbine tileGasTurbine, EntityPlayer player)
	{
		super();
		this.tileGasTurbine = tileGasTurbine;
		this.player = player;

		this.addSlotToContainer(new BaseSlot(tileGasTurbine.inventory, 0, 80, 17));
		this.addSlotToContainer(new SlotOutput(tileGasTurbine.inventory, 1, 80, 53));
		this.addSlotToContainer(new SlotFake(tileGasTurbine.inventory, 2, 59, 42, false, false, 1));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}
