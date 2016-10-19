package techreborn.client.container.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import reborncore.client.gui.slots.BaseSlot;
import reborncore.client.gui.slots.SlotCharge;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.api.RollingMachineRecipe;
import techreborn.client.container.base.ContainerBase;
import techreborn.tiles.energy.tier1.TileRollingMachine;

public class ContainerRollingMachine extends ContainerBase {

	private int currentItemBurnTime;
	private int burnTime;
	private int tickTime;

	private TileRollingMachine tileRollingMachine;

	public ContainerRollingMachine(TileRollingMachine tileEntity, EntityPlayer player) {
		super(tileEntity, player);
		this.tileRollingMachine = tileEntity;

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				addSlotToContainer(new BaseSlot(tileEntity.craftMatrix, y + x * 3, 30 + x * 18, 17 + x * 18));
			}
		}

		// output
		addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 124, 35));

		// battery
		//		addSlotToContainer(new SlotDischargeItem(tileEntity.getInventory(), getNextSlotIndex(), 8, 51));
		addSlotToContainer(new SlotCharge(tileEntity.getInventory(), getNextSlotIndex(), 8, 51));
	}

	@Override
	public final void onCraftMatrixChanged(IInventory inv) {
		ItemStack output = RollingMachineRecipe.instance.findMatchingRecipe(this.tileRollingMachine.craftMatrix, this.tileRollingMachine.getWorld());
		this.tileRollingMachine.getInventory().setInventorySlotContents(1, output);
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.tileRollingMachine.runTime);
		crafting.sendProgressBarUpdate(this, 1, this.tileRollingMachine.tickTime);
	}

	@Override
	public void detectAndSendChanges() {
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener crafting = this.listeners.get(i);
			if (this.currentItemBurnTime != this.tileRollingMachine.runTime) {
				crafting.sendProgressBarUpdate(this, 0, this.tileRollingMachine.runTime);
			}
			if (this.burnTime != this.tileRollingMachine.tickTime || this.tileRollingMachine.tickTime == -1) {
				crafting.sendProgressBarUpdate(this, 1, this.tileRollingMachine.tickTime);
			}
		}
		super.detectAndSendChanges();
	}

	@Override
	public void updateProgressBar(int id, int value) {
		super.updateProgressBar(id, value);
		if (id == 0) {
			this.currentItemBurnTime = value;
		} else if (id == 1) {
			this.burnTime = Math.max(0, value);
		}
		this.tileRollingMachine.runTime = this.currentItemBurnTime;
		this.tileRollingMachine.tickTime = this.burnTime;
	}

	public int getBurnTimeRemainingScaled(int scale) {
		if (burnTime == 0 || this.currentItemBurnTime == 0) {
			return 0;
		}
		return this.burnTime * scale / this.currentItemBurnTime;
	}

}
