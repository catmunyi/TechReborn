package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotCharge;
import techreborn.tiles.generator.TileGenerator;

public class ContainerGenerator extends ContainerBase {

	public int burnTime = 0;
	public int totalBurnTime = 0;
	public int energy;
	public int tickTime;
	EntityPlayer player;
	TileGenerator tile;

	public ContainerGenerator(TileGenerator tile, EntityPlayer player) {
		super();
		this.tile = tile;
		this.player = player;

		// fuel
		this.addSlotToContainer(new SlotFurnaceFuel(tile.inventory, 0, 80, 53));
		// charge
		this.addSlotToContainer(new SlotCharge(tile.inventory, 1, 80, 17));

		int i;

		drawPlayersInvAndHotbar(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.burnTime != tile.burnTime) {
				IContainerListener.sendProgressBarUpdate(this, 0, tile.burnTime);
			}
			if (this.totalBurnTime != tile.totalBurnTime) {
				IContainerListener.sendProgressBarUpdate(this, 1, tile.totalBurnTime);
			}
			if (this.energy != (int) tile.getEnergy()) {
				IContainerListener.sendProgressBarUpdate(this, 2, (int) tile.getEnergy());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, tile.burnTime);
		crafting.sendProgressBarUpdate(this, 1, tile.totalBurnTime);
		crafting.sendProgressBarUpdate(this, 2, (int) tile.getEnergy());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.burnTime = value;
		} else if (id == 1) {
			this.totalBurnTime = value;
		} else if (id == 2) {
			this.energy = value;
		}
		this.tile.setEnergy(energy);
	}

	public int getScaledBurnTime(int i) {
		return (int) (((float) burnTime / (float) totalBurnTime) * i);
	}

}
