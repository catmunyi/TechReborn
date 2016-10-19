package techreborn.client.container.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotCharge;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier1.TileIndustrialElectrolyzer;

public class ContainerIndustrialElectrolyzer extends ContainerCrafting {

	public ContainerIndustrialElectrolyzer(TileIndustrialElectrolyzer tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		// input
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 80, 51));
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 50, 51));

		// outputs
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 50, 19));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 70, 19));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 90, 19));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 110, 19));

		// battery
		//		this.addSlotToContainer(new SlotDischargeItem(tileEntity.getInventory(), getNextSlotIndex(), 18, 51));
		this.addSlotToContainer(new SlotCharge(tileEntity.getInventory(), getNextSlotIndex(), 18, 51));
	}
}
