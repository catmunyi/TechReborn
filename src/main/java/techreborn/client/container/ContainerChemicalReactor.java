package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.api.gui.SlotUpgrade;
import techreborn.tiles.TileChemicalReactor;

public class ContainerChemicalReactor extends ContainerCrafting
{

	public int tickTime;
	EntityPlayer player;
	TileChemicalReactor tile;

	public ContainerChemicalReactor(TileChemicalReactor tilechemicalReactor, EntityPlayer player)
	{
		super(tilechemicalReactor.crafter);
		tile = tilechemicalReactor;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tilechemicalReactor.inventory, 0, 70, 21));
		this.addSlotToContainer(new BaseSlot(tilechemicalReactor.inventory, 1, 90, 21));
		// outputs
		this.addSlotToContainer(new SlotOutput(tilechemicalReactor.inventory, 2, 80, 51));
		// battery
		this.addSlotToContainer(new BaseSlot(tilechemicalReactor.inventory, 3, 8, 51));
		// upgrades
		this.addSlotToContainer(new SlotUpgrade(tilechemicalReactor.inventory, 4, 152, 8));
		this.addSlotToContainer(new SlotUpgrade(tilechemicalReactor.inventory, 5, 152, 26));
		this.addSlotToContainer(new SlotUpgrade(tilechemicalReactor.inventory, 6, 152, 44));
		this.addSlotToContainer(new SlotUpgrade(tilechemicalReactor.inventory, 7, 152, 62));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

}
