package techreborn.client.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotCharge;
import techreborn.tiles.energy.storage.TileEnergyStorage;

/**
 * Created by Lordmau5 on 10.06.2016.
 */
public abstract class ContainerEnergyStorage extends ContainerUpgradeable {

	private final TileEnergyStorage tileEnergyStorage;

	public ContainerEnergyStorage(TileEnergyStorage tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.tileEnergyStorage = tileEntity;

		//        this.addSlotToContainer(new SlotChargeItem(tileEntity.getInventoryCharging(), getNextSlotIndex(), 80, 17));
		//        this.addSlotToContainer(new SlotDischargeItem(tileEntity.getInventoryCharging(), getNextSlotIndex(), 80, 53));
		this.addSlotToContainer(new SlotCharge(tileEntity.getInventoryCharging(), getNextSlotIndex(), 80, 17));
		this.addSlotToContainer(new SlotCharge(tileEntity.getInventoryCharging(), getNextSlotIndex(), 80, 53));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.getEnergy() != (int) this.tileEnergyStorage.getEnergy()) {
				IContainerListener.sendProgressBarUpdate(this, 0, (int) this.tileEnergyStorage.getEnergy());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.getEnergy());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.tileEnergyStorage.setEnergy(value);
		}
	}

}
