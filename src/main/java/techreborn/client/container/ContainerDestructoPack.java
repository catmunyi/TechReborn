package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.gui.slots.BaseSlot;
import net.minecraft.item.ItemStack;
import reborncore.client.gui.slots.SlotFilteredVoid;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.Inventory;
import techreborn.init.ModItems;

public class ContainerDestructoPack extends RebornContainer
{

	private EntityPlayer player;
	private Inventory inv;

	public ContainerDestructoPack(EntityPlayer player)
	{
		this.player = player;
		inv = new Inventory(1, "destructopack", 64, null);
		buildContainer();
	}

	@Override
	public boolean canInteractWith(EntityPlayer arg0)
	{
		return true;
	}

	private void buildContainer()
	{
		this.addSlotToContainer(new SlotFilteredVoid(inv, 0, 80, 36, new ItemStack[] { new ItemStack(ModItems.parts, 1, 37) }));
		addPlayersInventory(player);
		addPlayersHotbar(player);
	}
}
