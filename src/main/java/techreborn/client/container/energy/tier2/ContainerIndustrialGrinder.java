package techreborn.client.container.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier2.TileIndustrialGrinder;

public class ContainerIndustrialGrinder extends ContainerCrafting {

    public int connectionStatus;

    private TileIndustrialGrinder tileIndustrialGrinder;

    public ContainerIndustrialGrinder(TileIndustrialGrinder tileEntity, EntityPlayer player) {
        super(tileEntity, player);

        this.tileIndustrialGrinder = tileEntity;

        // input
        this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 32, 26));
        this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 32, 44));

        // outputs
        this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 77, 35));
        this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 95, 35));
        this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 113, 35));
        this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 131, 35));
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener IContainerListener = this.listeners.get(i);
            if (this.connectionStatus != this.tileIndustrialGrinder.connectionStatus) {
                IContainerListener.sendProgressBarUpdate(this, 2, this.tileIndustrialGrinder.connectionStatus);
            }
        }
    }

    @Override
    public void addListener(IContainerListener crafting) {
        super.addListener(crafting);
        crafting.sendProgressBarUpdate(this, 2, this.tileIndustrialGrinder.connectionStatus);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value) {
        if (id == 2) {
            this.connectionStatus = value;
        }

        super.updateProgressBar(id, value);
    }

}
