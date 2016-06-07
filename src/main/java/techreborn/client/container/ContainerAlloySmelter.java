package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import reborncore.api.tile.IContainerLayout;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotCharge;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.api.gui.SlotUpgrade;
import techreborn.tiles.TileAlloySmelter;

import javax.annotation.Nullable;
import java.util.List;

public class ContainerAlloySmelter extends ContainerCrafting implements IContainerLayout<TileAlloySmelter>
{

	public int tickTime;
	EntityPlayer player;
	TileAlloySmelter tile;

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void addInventorySlots() {

		// input
		this.addSlotToContainer(new SlotInput(tile.inventory, 0, 47, 17));
		this.addSlotToContainer(new SlotInput(tile.inventory, 1, 65, 17));
		// outputs
		this.addSlotToContainer(new SlotOutput(tile.inventory, 2, 116, 35));
		// battery
		this.addSlotToContainer(new SlotCharge(tile.inventory, 3, 56, 53));
		// upgrades
		this.addSlotToContainer(new SlotUpgrade(tile.inventory, 4, 152, 8));
		this.addSlotToContainer(new SlotUpgrade(tile.inventory, 5, 152, 26));
		this.addSlotToContainer(new SlotUpgrade(tile.inventory, 6, 152, 44));
		this.addSlotToContainer(new SlotUpgrade(tile.inventory, 7, 152, 62));
	}

	@Override
	public void addPlayerSlots() {
		addPlayersInventory(player);
		addPlayersHotbar(player);
	}

	@Override
	public void setTile(TileAlloySmelter tile) {
		this.tile = tile;
		setCrafter(tile.crafter);
	}

	@Nullable
	@Override
	public TileAlloySmelter getTile() {
		return tile;
	}

	@Override
	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}

	@Nullable
	@Override
	public EntityPlayer getPlayer() {
		return player;
	}

	@Nullable
	@Override
	public List<Integer> getSlotsForSide(EnumFacing facing) {
		return null;
	}
}
