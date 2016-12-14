package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotCharge;
import techreborn.tiles.storage.TileBatBox;

public class ContainerBatbox extends ContainerMachineBase {

	public int tickTime;
	EntityPlayer player;
	TileBatBox tile;

	public ContainerBatbox(TileBatBox tile, EntityPlayer player) {
		super(tile, player, true, false);
		this.tile = tile;
		this.player = player;

		this.addSlotToContainer(new SlotCharge(tile.inventory, 5, 62, 33));
		this.addSlotToContainer(new SlotCharge(tile.inventory, 6, 98, 33));
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
			if (this.energy != (int) tile.getEnergy()) {
				IContainerListener.sendProgressBarUpdate(this, 0, (int) tile.getEnergy());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, (int) tile.getEnergy());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.energy = value;
		}
		this.tile.setEnergy(energy);
	}
}
