package techreborn.client.container.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.SlotFurnaceFuel;
import reborncore.client.gui.slots.SlotCharge;
import techreborn.client.container.base.ContainerBase;
import techreborn.tiles.energy.generator.TileGenerator;

public class ContainerGenerator extends ContainerBase {

	//	public int burnTime = 0;
	//	public int totalBurnTime = 0;
	//	public int energy;
	//	public int tickTime;

	//	private TileGenerator tileGenerator;

	public ContainerGenerator(TileGenerator tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		//		this.tileGenerator = tileEntity;

		// fuel
		this.addSlotToContainer(new SlotFurnaceFuel(tileEntity.getInventory(), getNextSlotIndex(), 80, 53));

		// charge
		//		this.addSlotToContainer(new SlotChargeItem(tileEntity.getInventory(), getNextSlotIndex(), 80, 17));
		this.addSlotToContainer(new SlotCharge(tileEntity.getInventory(), getNextSlotIndex(), 80, 17));
	}

	//	@Override
	//	public void detectAndSendChanges() {
	//		super.detectAndSendChanges();
	//		for (int i=0; i<this.listeners.size(); i++) {
	//			IContainerListener IContainerListener = this.listeners.get(i);
	//			if (this.burnTime != this.tileGenerator.burnTime) {
	//				IContainerListener.sendProgressBarUpdate(this, 0, this.tileGenerator.burnTime);
	//			}
	//			if (this.totalBurnTime != this.tileGenerator.totalBurnTime) {
	//				IContainerListener.sendProgressBarUpdate(this, 1, this.tileGenerator.totalBurnTime);
	//			}
	//		}
	//	}
	//
	//	@Override
	//	public void addListener(IContainerListener crafting)
	//	{
	//		super.addListener(crafting);
	//		crafting.sendProgressBarUpdate(this, 0, this.tileGenerator.burnTime);
	//		crafting.sendProgressBarUpdate(this, 1, this.tileGenerator.totalBurnTime);
	//	}
	//
	//	@SideOnly(Side.CLIENT)
	//	@Override
	//	public void updateProgressBar(int id, int value)
	//	{
	//		if (id == 0) {
	//			this.burnTime = value;
	//		}
	//		else if (id == 1) {
	//			this.totalBurnTime = value;
	//		}
	//	}

	//	public int getScaledBurnTime(int i) {
	//		return (int) (((float) burnTime / (float) totalBurnTime) * i);
	//	}

}
