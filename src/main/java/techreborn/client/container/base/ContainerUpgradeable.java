package techreborn.client.container.base;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.common.powerSystem.TileEnergyUpgradeable;
import techreborn.api.gui.SlotUpgrade;

/**
 * Created by Lordmau5 on 10.06.2016.
 */
public abstract class ContainerUpgradeable extends ContainerBase {

	public ContainerUpgradeable(TileEnergyUpgradeable tileEntity, EntityPlayer player) {
		super(tileEntity, player);

        /* Initialize Upgrade Slots */
		this.addSlotToContainer(new SlotUpgrade(tileEntity.getInventoryUpgrades(), getNextSlotIndex(), 152, 8));
		this.addSlotToContainer(new SlotUpgrade(tileEntity.getInventoryUpgrades(), getNextSlotIndex(), 152, 26));
		this.addSlotToContainer(new SlotUpgrade(tileEntity.getInventoryUpgrades(), getNextSlotIndex(), 152, 44));
		this.addSlotToContainer(new SlotUpgrade(tileEntity.getInventoryUpgrades(), getNextSlotIndex(), 152, 62));
	}
}
