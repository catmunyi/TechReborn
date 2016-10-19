package techreborn.client.container.energy.tier0;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.SlotFurnaceFuel;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerBase;
import techreborn.tiles.energy.tier0.TileIronAlloyFurnace;

public class ContainerIronAlloyFurnace extends ContainerBase {

	public ContainerIronAlloyFurnace(TileIronAlloyFurnace tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		// Fuel
		this.addSlotToContainer(new SlotFurnaceFuel(tileEntity.getInventory(), getNextSlotIndex(), 56, 53));

		// Output
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 116, 35));

		// Inputs
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 47, 17));
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 65, 17));

	}
}
