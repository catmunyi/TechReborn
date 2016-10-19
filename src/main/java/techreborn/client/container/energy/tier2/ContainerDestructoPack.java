package techreborn.client.container.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.client.gui.slots.SlotFilteredVoid;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.inventory.Inventory;
import techreborn.init.ModItems;

public class ContainerDestructoPack extends RebornContainer {

	private EntityPlayer player;
	private Inventory inventory;

	public ContainerDestructoPack(EntityPlayer player) {
		this.player = player;
		this.inventory = new Inventory("destructopack", 1, 64, null);

		addPlayersHotbar(player);
		addPlayersInventory(player);

		addSlotToContainer(new SlotFilteredVoid(this.inventory, 0, 80, 36, new ItemStack[] { new ItemStack(ModItems.parts, 1, 37) }));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
