package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import reborncore.client.gui.slots.BaseSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.tiles.TileIndustrialGrinder;

public class ContainerIndustrialGrinder extends ContainerCrafting
{

	public int connectionStatus;
	EntityPlayer player;
	TileIndustrialGrinder tile;

	public ContainerIndustrialGrinder(TileIndustrialGrinder tileGrinder, EntityPlayer player)
	{
		super(tileGrinder.crafter);
		tile = tileGrinder;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tileGrinder.inventory, 0, 32, 26));
		this.addSlotToContainer(new BaseSlot(tileGrinder.inventory, 1, 32, 44));

		// outputs
		this.addSlotToContainer(new SlotOutput(tileGrinder.inventory, 2, 77, 35));
		this.addSlotToContainer(new SlotOutput(tileGrinder.inventory, 3, 95, 35));
		this.addSlotToContainer(new SlotOutput(tileGrinder.inventory, 4, 113, 35));
		this.addSlotToContainer(new SlotOutput(tileGrinder.inventory, 5, 131, 35));

		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
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
			if (this.connectionStatus != tile.connectionStatus)
			{
				IContainerListener.sendProgressBarUpdate(this, 10, tile.connectionStatus);
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting)
	{
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 10, tile.connectionStatus);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		if (id == 10)
		{
			this.connectionStatus = value;
		}
	}

}
