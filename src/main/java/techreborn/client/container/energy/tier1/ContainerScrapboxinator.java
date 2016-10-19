package techreborn.client.container.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerBase;
import techreborn.client.container.base.SlotScrapbox;
import techreborn.tiles.energy.tier1.TileScrapboxinator;

public class ContainerScrapboxinator extends ContainerBase {

	public ContainerScrapboxinator(TileScrapboxinator tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		// input
		this.addSlotToContainer(new SlotScrapbox(tileEntity.getInventory(), getNextSlotIndex(), 56, 34));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 34));
	}
}
