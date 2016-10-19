package techreborn.client.container.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotCharge;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier1.TileAssemblingMachine;

public class ContainerAssemblingMachine extends ContainerCrafting {

	public ContainerAssemblingMachine(TileAssemblingMachine tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		// input
		addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 47, 17));
		addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 65, 17));

		// outputs
		addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 35));

		// power
		//addSlotToContainer(new SlotDischargeItem(tileEntity.getInventory(), getNextSlotIndex(), 56, 53));
		addSlotToContainer(new SlotCharge(tileEntity.getInventory(), getNextSlotIndex(), 56, 53));
	}
}
