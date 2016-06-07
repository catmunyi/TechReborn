package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import reborncore.client.gui.slots.BaseSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotCharge;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.storage.TileBatBox;

public class ContainerBatbox extends RebornContainer
{

	public int burnTime = 0;
	public int totalBurnTime = 0;
	public int energy;
	public int tickTime;
	EntityPlayer player;
	TileBatBox tile;

	public ContainerBatbox(TileBatBox tile, EntityPlayer player)
	{
		super();
		this.tile = tile;
		this.player = player;

		addPlayersInventory(player);
		addPlayersHotbar(player);

		this.addSlotToContainer(new SlotCharge(tile.inventory, 0, 80, 17));
		this.addSlotToContainer(new SlotCharge(tile.inventory, 1, 80, 53));
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
			if (this.energy != (int) tile.getEnergy())
			{
				IContainerListener.sendProgressBarUpdate(this, 2, (int) tile.getEnergy());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting)
	{
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 2, (int) tile.getEnergy());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		if (id == 0)
		{
			this.burnTime = value;
		} else if (id == 1)
		{
			this.totalBurnTime = value;
		} else if (id == 2)
		{
			this.energy = value;
		}
		this.tile.setEnergy(energy);
	}

	public int getScaledBurnTime(int i)
	{
		return (int) (((float) burnTime / (float) totalBurnTime) * i);
	}
}
