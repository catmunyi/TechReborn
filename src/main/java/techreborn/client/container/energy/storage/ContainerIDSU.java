package techreborn.client.container.energy.storage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techreborn.client.container.base.ContainerEnergyStorage;
import techreborn.tiles.idsu.TileIDSU;

public class ContainerIDSU extends ContainerEnergyStorage {

	public int euOut;
	public int euChange;
	private int channel;
	TileIDSU tileIDSU;

	public ContainerIDSU(TileIDSU tileEntity, EntityPlayer player) {
		super(tileEntity, player);
		this.tileIDSU = tileEntity;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.euOut != this.tileIDSU.getMaxOutput()) {
				IContainerListener.sendProgressBarUpdate(this, 0, (int) this.tileIDSU.getMaxOutput());
			}
			if (this.euChange != this.tileIDSU.getEuChange() && this.tileIDSU.getEuChange() != -1) {
				IContainerListener.sendProgressBarUpdate(this, 1, (int) this.tileIDSU.getEuChange());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, (int) this.tileIDSU.getMaxOutput());
		crafting.sendProgressBarUpdate(this, 1, (int) this.tileIDSU.getEuChange());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.euOut = value;
		} else if (id == 1) {
			this.euChange = value;
		} else if (id == 2) {
			this.channel = value;
		}
	}

}
