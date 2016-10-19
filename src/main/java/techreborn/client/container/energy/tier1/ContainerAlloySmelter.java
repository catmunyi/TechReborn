package techreborn.client.container.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotCharge;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier1.TileAlloySmelter;

public class ContainerAlloySmelter extends ContainerCrafting {

    public ContainerAlloySmelter(TileAlloySmelter tileEntity, EntityPlayer player) {
        super(tileEntity, player);

        // input
        this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 47, 17));
        this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 65, 17));

        // outputs
        this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 35));

        // battery
//		this.addSlotToContainer(new SlotDischargeItem(tileEntity.getInventory(), getNextSlotIndex(), 56, 53));
        this.addSlotToContainer(new SlotCharge(tileEntity.getInventory(), getNextSlotIndex(), 56, 53));
    }
}
