package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.tiles.teir1.TileElectricFurnace;

public class ContainerElectricFurnace extends ContainerMachineBase {

	public int connectionStatus;
	public int progress;
	EntityPlayer player;
	TileElectricFurnace tile;

	public ContainerElectricFurnace(TileElectricFurnace tile, EntityPlayer player) {
		super(tile, player);
		this.tile = tile;
		this.player = player;

		// input
		this.addSlotToContainer(new BaseSlot(tile.inventory, lastSlotIndex + 1, 55, 45));
		this.addSlotToContainer(new SlotOutput(tile.inventory, lastSlotIndex + 2, 101, 45));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.progress != tile.progress) {
				IContainerListener.sendProgressBarUpdate(this, 0, tile.progress);
			}
			if (this.energy != (int) tile.getEnergy()) {
				IContainerListener.sendProgressBarUpdate(this, 1, (int) tile.getEnergy());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, tile.progress);
		crafting.sendProgressBarUpdate(this, 1, (int) tile.getEnergy());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.progress = value;
		} else if (id == 1) {
			this.energy = value;
		}
		this.tile.setEnergy(energy);
	}

	public int getScaledProgress(int i) {
		return (int) (((float) progress / (float) tile.maxProgress) * i);
	}

}
