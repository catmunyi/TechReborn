package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.tile.IContainerProvider;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.inventory.Inventory;
import techreborn.client.container.energy.generator.ContainerGenerator;
import techreborn.init.ModBlocks;

public class TileGenerator extends AbstractTileGenerator implements IInventoryProvider, IContainerProvider {

	private Inventory inventory = new Inventory("TileGenerator", 2, 64, this);
	private int fuelSlot = 0;

	public int burnTime;
	private int currentItemBurnTime;

	private ItemStack burnItem;

	public TileGenerator() {
		super(EnumPowerTier.LOW, 4000);
	}

	public int getScaledBurnTime(int i) {
		return (int) (((float) this.burnTime / (float) this.currentItemBurnTime) * i);
	}

	private static int getItemBurnTime(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack) / 4;
	}

	@Override
	public void updateTick() {
		super.updateTick();

		int euPerTick = 0;
		if (this.burnTime-- > 0) {
			euPerTick = 10;
		} else {
			this.burnItem = getInventory().getStackInSlot(this.fuelSlot);
			if (this.burnItem != null) {
				this.burnTime = this.currentItemBurnTime = getItemBurnTime(this.burnItem);
				if (this.burnTime > 0) {
					if (this.burnItem.stackSize == 1) {
						getInventory().setInventorySlotContents(this.fuelSlot, null);
					} else {
						getInventory().decrStackSize(this.fuelSlot, 1);
					}
				}

				euPerTick = 10;
			}
		}

		setEuPerTick(euPerTick);
	}

	@Override
	public void updateRequirements() {
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer p0) {
		return new ItemStack(ModBlocks.generator);
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerGenerator.class, this);
	}
}
