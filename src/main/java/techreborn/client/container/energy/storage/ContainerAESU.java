package techreborn.client.container.energy.storage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techreborn.client.container.base.ContainerEnergyEquipment;
import techreborn.tiles.energy.storage.TileAESU;

public class ContainerAESU extends ContainerEnergyEquipment {

    public int euOut;
    public int euChange;

    private TileAESU tileAESU;

    public ContainerAESU(TileAESU tileEntity, EntityPlayer player) {
        super(tileEntity, player);

        this.tileAESU = tileEntity;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener IContainerListener = this.listeners.get(i);
            if (this.euOut != this.tileAESU.getMaxOutput()) {
                IContainerListener.sendProgressBarUpdate(this, 0, (int) this.tileAESU.getMaxOutput());
            }
            if (this.euChange != this.tileAESU.getEuChange() && this.tileAESU.getEuChange() != -1) {
                IContainerListener.sendProgressBarUpdate(this, 1, (int) this.tileAESU.getEuChange());
            }
        }
    }

    @Override
    public void addListener(IContainerListener crafting) {
        super.addListener(crafting);
        crafting.sendProgressBarUpdate(this, 0, (int) this.tileAESU.getMaxOutput());
        crafting.sendProgressBarUpdate(this, 1, (int) this.tileAESU.getEuChange());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.euOut = value;
        } else if (id == 1) {
            this.euChange = value;
        }
    }

}
