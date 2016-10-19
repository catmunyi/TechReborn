package techreborn.client.container.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier2.TileVacuumFreezer;

public class ContainerVacuumFreezer extends ContainerCrafting {

	public int machineStatus;

	private TileVacuumFreezer tileVacuumFreezer;

	public ContainerVacuumFreezer(TileVacuumFreezer tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.tileVacuumFreezer = tileEntity;

		// input
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 56, 34));

		// outputs
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 35));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.machineStatus != this.tileVacuumFreezer.multiBlockStatus) {
				IContainerListener.sendProgressBarUpdate(this, 2, this.tileVacuumFreezer.multiBlockStatus);
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 2, this.tileVacuumFreezer.multiBlockStatus);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		super.updateProgressBar(id, value);
		if (id == 2) {
			this.machineStatus = value;
		}
	}

}
