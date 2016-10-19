package techreborn.client.container.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier1.TileGrinder;

public class ContainerGrinder extends ContainerCrafting {

    public ContainerGrinder(TileGrinder tileEntity, EntityPlayer player) {
        super(tileEntity, player);

        this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 56, 34));
        this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 34));
    }
}
