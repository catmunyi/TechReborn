package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotFluid;
import reborncore.client.gui.slots.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.generator.TileThermalGenerator;

public class ContainerThermalGenerator extends RebornContainer
{
	public TileThermalGenerator tileThermalGenerator;
	public EntityPlayer player;

	public ContainerThermalGenerator(TileThermalGenerator tileThermalGenerator, EntityPlayer player)
	{
		super();
		this.tileThermalGenerator = tileThermalGenerator;
		this.player = player;

		this.addSlotToContainer(new SlotFluid(tileThermalGenerator.inventory, 0, 80, 17));
		this.addSlotToContainer(new SlotOutput(tileThermalGenerator.inventory, 1, 80, 53));
		this.addSlotToContainer(new SlotFake(tileThermalGenerator.inventory, 2, 59, 42, false, false, 1));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}
