package techreborn.client.container.energy.storage;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.base.ContainerEnergyStorage;
import techreborn.tiles.energy.storage.TileBatBox;

public class ContainerBatBox extends ContainerEnergyStorage {

	public ContainerBatBox(TileBatBox tileEntity, EntityPlayer player) {
		super(tileEntity, player);
	}
}
