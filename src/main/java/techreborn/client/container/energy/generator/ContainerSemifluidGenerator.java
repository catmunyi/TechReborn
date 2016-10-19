package techreborn.client.container.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerBase;
import techreborn.tiles.energy.generator.TileSemifluidGenerator;

public class ContainerSemifluidGenerator extends ContainerBase {

	public ContainerSemifluidGenerator(TileSemifluidGenerator tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.addSlotToContainer(new BaseSlot(tileEntity.getInventory(), getNextSlotIndex(), 80, 17));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 80, 53));
		this.addSlotToContainer(new SlotFake(tileEntity.getInventory(), getNextSlotIndex(), 59, 42, false, false, 1));
	}
}
