package techreborn.client.container.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier2.TileCentrifuge;

public class ContainerCentrifuge extends ContainerCrafting {

	public ContainerCentrifuge(TileCentrifuge tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		// input
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 80, 35));
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 50, 5));

		// outputs
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 80, 5));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 110, 35));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 80, 65));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 50, 35));
	}
}
