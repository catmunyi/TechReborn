package techreborn.client.container.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.SlotInput;
import reborncore.client.gui.slots.SlotOutput;
import techreborn.client.container.base.ContainerCrafting;
import techreborn.tiles.energy.tier2.TileImplosionCompressor;

public class ContainerImplosionCompressor extends ContainerCrafting {

	public int multIBlockState = 0;

	private TileImplosionCompressor tileImplosionCompressor;

	public ContainerImplosionCompressor(TileImplosionCompressor tileEntity, EntityPlayer player) {
		super(tileEntity, player);

		this.tileImplosionCompressor = tileEntity;

		// input
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 37, 26));
		this.addSlotToContainer(new SlotInput(tileEntity.getInventory(), getNextSlotIndex(), 37, 44));

		// outputs
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 93, 35));
		this.addSlotToContainer(new SlotOutput(tileEntity.getInventory(), getNextSlotIndex(), 111, 35));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener IContainerListener = this.listeners.get(i);
			if (this.multIBlockState != getMultIBlockStateint()) {
				IContainerListener.sendProgressBarUpdate(this, 2, getMultIBlockStateint());
			}
		}
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 2, getMultIBlockStateint());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 2) {
			this.multIBlockState = value;
		}
	}

	public int getMultIBlockStateint() {
		return tileImplosionCompressor.getMultiBlock() ? 1 : 0;
	}

}
