package techreborn.client.container.energy.storage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techreborn.client.container.base.ContainerEnergyStorage;
import techreborn.config.ConfigTechReborn;
import techreborn.tiles.lesu.TileLESU;

public class ContainerLESU extends ContainerEnergyStorage {

    public int euOut;
    public int euChange;
    public int connectedBlocks;
    public double euStorage;
    TileLESU tileLESU;

    public ContainerLESU(TileLESU tileLESU, EntityPlayer player) {
        super(tileLESU, player);
        this.tileLESU = tileLESU;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener IContainerListener = this.listeners.get(i);
            if (this.euOut != this.tileLESU.getMaxOutput()) {
                IContainerListener.sendProgressBarUpdate(this, 0, (int) this.tileLESU.getMaxOutput());
            }
            if (this.euChange != this.tileLESU.getEuChange() && this.tileLESU.getEuChange() != -1) {
                IContainerListener.sendProgressBarUpdate(this, 1, (int) this.tileLESU.getEuChange());
            }
            if (this.connectedBlocks != this.tileLESU.connectedBlocks) {
                IContainerListener.sendProgressBarUpdate(this, 2, this.tileLESU.connectedBlocks);
            }
        }
    }

    @Override
    public void addListener(IContainerListener crafting) {
        super.addListener(crafting);
        crafting.sendProgressBarUpdate(this, 0, (int) this.tileLESU.getMaxOutput());
        crafting.sendProgressBarUpdate(this, 1, (int) this.tileLESU.getEuChange());
        crafting.sendProgressBarUpdate(this, 2, this.tileLESU.connectedBlocks);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.euOut = value;
        } else if (id == 1) {
            this.euChange = value;
        } else if (id == 2) {
            this.connectedBlocks = value;
        } else if (id == 3) {
            this.euStorage = value;
        }
        this.euStorage = ((connectedBlocks + 1) * ConfigTechReborn.LesuStoragePerBlock);
    }

}
