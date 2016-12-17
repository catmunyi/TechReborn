package techreborn.client.container.tier1;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerMachineBase;
import techreborn.tiles.teir1.TileElectricFurnace;

public class ContainerElectricFurnace extends ContainerMachineBase {

	public ContainerElectricFurnace(TileElectricFurnace tile, EntityPlayer player) {
		super(tile, player);
		this.tile = tile;

		// input
		addInputSlot(tile.inventory, 55, 45);
		addOutputSlot(tile.inventory, 101, 45);
	}

	public int getScaledProgress(int i) {
		return (int) (((float) progress / (float) tile.maxProgress) * i);
	}

}
