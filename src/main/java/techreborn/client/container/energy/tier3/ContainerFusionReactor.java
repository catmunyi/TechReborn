package techreborn.client.container.energy.tier3;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerBase;
import techreborn.tiles.fusionReactor.TileEntityFusionController;

public class ContainerFusionReactor extends ContainerBase {

	public int coilStatus;
	public int tickTime;
	public int finalTickTime;
	public int neededEU;

	private TileEntityFusionController tileEntityFusionController;

	public ContainerFusionReactor(TileEntityFusionController tileEntity, EntityPlayer player) {
		super(tileEntity, player);
		this.tileEntityFusionController = tileEntity;

		addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 88, 17));
		addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 88, 53));

		addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 148, 35));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.coilStatus != this.tileEntityFusionController.coilStatus) {
				IContainerListener.sendProgressBarUpdate(this, 0, this.tileEntityFusionController.coilStatus);
			}
			if (this.tickTime != this.tileEntityFusionController.crafingTickTime) {
				IContainerListener.sendProgressBarUpdate(this, 1, this.tileEntityFusionController.crafingTickTime);
			}
			if (this.finalTickTime != this.tileEntityFusionController.finalTickTime) {
				IContainerListener.sendProgressBarUpdate(this, 2, this.tileEntityFusionController.finalTickTime);
			}
			if (this.neededEU != this.tileEntityFusionController.neededPower) {
				IContainerListener.sendProgressBarUpdate(this, 3, this.tileEntityFusionController.neededPower);
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.tileEntityFusionController.coilStatus);
		crafting.sendProgressBarUpdate(this, 1, this.tileEntityFusionController.crafingTickTime);
		crafting.sendProgressBarUpdate(this, 2, this.tileEntityFusionController.finalTickTime);
		crafting.sendProgressBarUpdate(this, 3, this.tileEntityFusionController.neededPower);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.coilStatus = value;
		} else if (id == 1) {
			this.tickTime = Math.max(0, value);
		} else if (id == 2) {
			this.finalTickTime = Math.max(0, value);
		} else if (id == 3) {
			this.neededEU = Math.max(0, value);
		}
	}

	public int getProgressScaled() {
		return Math.max(0, Math.min(24, (this.tickTime > 0 ? 1 : 0) + this.tickTime * 24 / (this.finalTickTime < 1 ? 1 : this.finalTickTime)));
	}
}
