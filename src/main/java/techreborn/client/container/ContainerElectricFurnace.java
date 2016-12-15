package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.tiles.teir1.TileElectricFurnace;

public class ContainerElectricFurnace extends ContainerMachineBase {

	public int connectionStatus;
	EntityPlayer player;
	TileElectricFurnace tile;

	public ContainerElectricFurnace(TileElectricFurnace tile, EntityPlayer player) {
		super(tile, player);
		this.tile = tile;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tile.inventory, 5, 56, 34));
		this.addSlotToContainer(new SlotOutput(tile.inventory, 6, 116, 34));
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 10) {
			this.connectionStatus = value;
		}
	}

}
