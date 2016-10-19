package techreborn.client.container.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier2.TileIndustrialSawmill;

public class ContainerIndustrialSawmill extends ContainerCrafting {

	public ContainerIndustrialSawmill(TileIndustrialSawmill tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		// input
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 32, 26));
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 32, 44));

		// outputs
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 84, 35));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 102, 35));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 120, 35));
	}

}
