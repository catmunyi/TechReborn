package techreborn.client.container.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.SlotFake;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerBase;
import techreborn.tiles.energy.generator.TileDieselGenerator;

public class ContainerDieselGenerator extends ContainerBase {

	public ContainerDieselGenerator(TileDieselGenerator tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 80, 17));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 80, 53));
		this.addSlotToContainer(new SlotFake(tileEntity.getInventory(), getNextSlotIndex(), 59, 42, false, false, 1));
	}

	//	@Override
	//	public void detectAndSendChanges() {
	//		super.detectAndSendChanges();
	//		for(int i=0; i<this.listeners.size(); i++) {
	//			IContainerListener IContainerListener = this.listeners.get(i);
	//			if (this.fluidAmount != this.tileDieselGenerator.tank.getFluidAmount()) {
	//				IContainerListener.sendProgressBarUpdate(this, 0, this.tileDieselGenerator.tank.getFluidAmount());
	//			}
	//		}
	//	}
	//
	//	@Override
	//	public void addListener(IContainerListener crafting) {
	//		super.addListener(crafting);
	//		crafting.sendProgressBarUpdate(this, 0, this.tileDieselGenerator.tank.getFluidAmount());
	//	}
	//
	//	@SideOnly(Side.CLIENT)
	//	@Override
	//	public void updateProgressBar(int id, int value) {
	//		if (id == 0) {
	//			this.fluidAmount = value;
	//		}
	//	}
}
