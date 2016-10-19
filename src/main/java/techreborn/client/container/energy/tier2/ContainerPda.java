package techreborn.client.container.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.common.container.RebornContainer;

public class ContainerPda extends RebornContainer {

    EntityPlayer player;

    public ContainerPda(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
