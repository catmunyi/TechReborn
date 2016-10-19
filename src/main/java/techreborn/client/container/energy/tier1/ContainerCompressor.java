package techreborn.client.container.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier1.TileCompressor;

public class ContainerCompressor extends ContainerCrafting {

	public ContainerCompressor(TileCompressor tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 56, 34));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 34));
	}

}
