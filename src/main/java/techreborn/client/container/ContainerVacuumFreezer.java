package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import reborncore.client.gui.slots.BaseSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.tiles.TileVacuumFreezer;

public class ContainerVacuumFreezer extends ContainerCrafting
{
	public int tickTime;
	public int machineStatus;
	EntityPlayer player;
	TileVacuumFreezer tile;
	public ContainerVacuumFreezer(TileVacuumFreezer tile, EntityPlayer player)
	{
		super(tile.crafter);
		tile = tile;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tile.inventory, 0, 56, 34));
		// outputs
		this.addSlotToContainer(new SlotOutput(tile.inventory, 1, 116, 35));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++)
		{
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.machineStatus != tile.multiBlockStatus)
			{
				IContainerListener.sendProgressBarUpdate(this, 3, tile.multiBlockStatus);
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting)
	{
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 3, tile.multiBlockStatus);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		super.updateProgressBar(id, value);
		if (id == 3)
		{
			machineStatus = value;
		}
	}
}
