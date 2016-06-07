package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import reborncore.client.gui.slots.BaseSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.tiles.TileImplosionCompressor;

public class ContainerImplosionCompressor extends ContainerCrafting
{

	public int tickTime;
	public int multIBlockState = 0;
	EntityPlayer player;
	TileImplosionCompressor tile;

	public ContainerImplosionCompressor(TileImplosionCompressor tilecompressor, EntityPlayer player)
	{
		super(tilecompressor.crafter);
		tile = tilecompressor;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tilecompressor.inventory, 0, 37, 26));
		this.addSlotToContainer(new BaseSlot(tilecompressor.inventory, 1, 37, 44));
		// outputs
		this.addSlotToContainer(new SlotOutput(tilecompressor.inventory, 2, 93, 35));
		this.addSlotToContainer(new SlotOutput(tilecompressor.inventory, 3, 111, 35));

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
			if (this.multIBlockState != getMultIBlockStateint())
			{
				IContainerListener.sendProgressBarUpdate(this, 3, getMultIBlockStateint());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting)
	{
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 3, getMultIBlockStateint());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		if (id == 3)
		{
			this.multIBlockState = value;
		}
	}

	public int getMultIBlockStateint()
	{
		return tile.getMutliBlock() ? 1 : 0;
	}

}
