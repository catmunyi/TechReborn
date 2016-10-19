package techreborn.client.container.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier2.TileIndustrialBlastFurnace;

public class ContainerIndustrialBlastFurnace extends ContainerCrafting {

	public int heat;

	private TileIndustrialBlastFurnace tileEntityBlastFurnace;

	public ContainerIndustrialBlastFurnace(TileIndustrialBlastFurnace tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.tileEntityBlastFurnace = tileEntity;

		// input
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 40, 25));
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 40, 43));

		// outputs
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 100, 35));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 118, 35));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.heat != this.tileEntityBlastFurnace.getHeat()) {
				IContainerListener.sendProgressBarUpdate(this, 2, this.tileEntityBlastFurnace.getHeat());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 2, this.tileEntityBlastFurnace.getHeat());
	}

	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 2) {
			this.heat = value;
		}
		super.updateProgressBar(id, value);
	}
}
