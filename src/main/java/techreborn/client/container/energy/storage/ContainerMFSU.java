package techreborn.client.container.energy.storage;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.base.ContainerEnergyEquipment;
import techreborn.tiles.energy.storage.TileMFSU;

public class ContainerMFSU extends ContainerEnergyEquipment {

	public ContainerMFSU(TileMFSU tileEntity, EntityPlayer player) {
		super(tileEntity, player);
	}
}
