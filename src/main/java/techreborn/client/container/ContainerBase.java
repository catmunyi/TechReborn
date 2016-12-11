package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.common.container.RebornContainer;

/**
 * Created by Prospector
 */
public class ContainerBase extends RebornContainer {
	public ContainerBase() {
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return false;
	}
}
