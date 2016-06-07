package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.generator.TileSemifluidGenerator;

public class ContainerSemifluidGenerator extends RebornContainer
{
	public TileSemifluidGenerator tileSemifluidGenerator;
	public EntityPlayer player;

	public ContainerSemifluidGenerator(TileSemifluidGenerator tileSemifluidGenerator, EntityPlayer player)
	{
		super();
		this.tileSemifluidGenerator = tileSemifluidGenerator;
		this.player = player;

		this.addSlotToContainer(new BaseSlot(tileSemifluidGenerator.inventory, 0, 80, 17));
		this.addSlotToContainer(new SlotOutput(tileSemifluidGenerator.inventory, 1, 80, 53));
		this.addSlotToContainer(new SlotFake(tileSemifluidGenerator.inventory, 2, 59, 42, false, false, 1));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}
