package techreborn.client.container.energy.storage;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.base.ContainerEnergyEquipment;
import techreborn.tiles.energy.storage.TileMFE;

public class ContainerMFE extends ContainerEnergyEquipment {

	public ContainerMFE(TileMFE tileEntity, EntityPlayer player) {
		super(tileEntity, player);
	}
}
