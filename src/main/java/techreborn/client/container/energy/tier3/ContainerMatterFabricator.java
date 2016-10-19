package techreborn.client.container.energy.tier3;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerBase;
import techreborn.tiles.energy.tier3.TileMatterFabricator;

public class ContainerMatterFabricator extends ContainerBase {

    public int progressTime;

    private TileMatterFabricator tileMatterFabricator;

    public ContainerMatterFabricator(TileMatterFabricator tileEntity, EntityPlayer player) {
        super(tileEntity, player);
        this.tileMatterFabricator = tileEntity;

        // input
        addSlotToContainer(new BaseSlot(tileEntity.getInventory(), getNextSlotIndex(), 33, 17));
        addSlotToContainer(new BaseSlot(tileEntity.getInventory(), getNextSlotIndex(), 33, 35));
        addSlotToContainer(new BaseSlot(tileEntity.getInventory(), getNextSlotIndex(), 33, 53));
        addSlotToContainer(new BaseSlot(tileEntity.getInventory(), getNextSlotIndex(), 51, 17));
        addSlotToContainer(new BaseSlot(tileEntity.getInventory(), getNextSlotIndex(), 51, 35));
        addSlotToContainer(new BaseSlot(tileEntity.getInventory(), getNextSlotIndex(), 51, 53));

        // outputs
        addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 35));
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener IContainerListener = this.listeners.get(i);
            if (this.progressTime != this.tileMatterFabricator.progresstime) {
                IContainerListener.sendProgressBarUpdate(this, 0, this.tileMatterFabricator.progresstime);
            }
        }
    }

    @Override
    public void addListener(IContainerListener crafting) {
        super.addListener(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tileMatterFabricator.progresstime);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.progressTime = value;
        }
    }

    public int getProgressScaled(int scale) {
        if (progressTime != 0) {
            return progressTime * scale / tileMatterFabricator.maxProgresstime();
        }
        return 0;
    }

}
