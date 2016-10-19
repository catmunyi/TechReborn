package techreborn.client.container.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier1.TileExtractor;

public class ContainerExtractor extends ContainerCrafting {

	public ContainerExtractor(TileExtractor tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 56, 34));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 34));
	}

	// @Override
	// public void addListener(IContainerListener crafting) {
	// super.addListener(crafting);
	// crafting.sendProgressBarUpdate(this, 0, (int) tile.getProgressScaled(0));
	// crafting.sendProgressBarUpdate(this, 2, (int) tile.getEnergy());
	// }
	//
	// @SideOnly(Side.CLIENT)
	// @Override
	// public void updateProgressBar(int id, int value) {
	// if (id == 0) {
	// this.progress = value;
	// }
	// else if (id == 2) {
	// this.energy = value;
	// }
	// this.tile.setEnergy(energy);
	// }

	// @SideOnly(Side.CLIENT)
	// @Override
	// public void updateProgressBar(int id, int value) {
	// if (id == 10) {
	// this.connectionStatus = value;
	// }
	// }
}
