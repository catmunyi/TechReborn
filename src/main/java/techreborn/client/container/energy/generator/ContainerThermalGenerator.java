package techreborn.client.container.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotFluid;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerBase;
import techreborn.tiles.energy.generator.TileThermalGenerator;

public class ContainerThermalGenerator extends ContainerBase {

	public ContainerThermalGenerator(TileThermalGenerator tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.addSlotToContainer(new SlotFluid(tileEntity.getInventory(), getNextSlotIndex(), 80, 17));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 80, 53));
		this.addSlotToContainer(new SlotFake(tileEntity.getInventory(), getNextSlotIndex(), 59, 42, false, false, 1));
	}
}
