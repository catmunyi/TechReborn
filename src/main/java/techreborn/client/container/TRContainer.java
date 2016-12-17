package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.client.gui.slots.BaseSlot;
import techreborn.client.container.slot.*;
import techreborn.tiles.TileMachineBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
public class TRContainer extends Container {
	public int lastSlotIndex = 4;
	protected List<TRSlot> slots = new ArrayList<>();
	protected TileMachineBase tile;
	protected int energy;
	protected int maxEnergy;
	protected int progress;
	protected int maxProgress;
	protected int burnTime;
	protected int totalBurnTime;
	protected String name;

	public TRContainer(TileMachineBase tile, EntityPlayer player) {
		addPlayerSlots(player);
		this.name = tile.getName();
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	public void addPlayerSlots(EntityPlayer player) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new BaseSlot(player.inventory, j + i * 9 + 9, 8 + j * 18, 94 + i * 18));
			}
		}
		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new BaseSlot(player.inventory, i, 8 + i * 18, 152));
		}
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < listeners.size(); ++i) {
			IContainerListener listener = this.listeners.get(i);
			if (energy != tile.getEnergy()) {
				listener.sendProgressBarUpdate(this, 0, (int) tile.getEnergy());
			}
			if (maxEnergy != tile.getMaxPower()) {
				listener.sendProgressBarUpdate(this, 1, (int) tile.getMaxPower());
			}
			if (progress != tile.progress) {
				listener.sendProgressBarUpdate(this, 2, tile.progress);
			}
			if (maxProgress != tile.maxProgress) {
				listener.sendProgressBarUpdate(this, 3, tile.maxProgress);
			}
			if (burnTime != tile.burnTime) {
				listener.sendProgressBarUpdate(this, 4, tile.burnTime);
			}
			if (totalBurnTime != tile.totalBurnTime) {
				listener.sendProgressBarUpdate(this, 5, tile.totalBurnTime);
			}
		}
		energy = (int) tile.getEnergy();
		maxEnergy = (int) tile.getMaxPower();
		progress = tile.progress;
		maxProgress = tile.maxProgress;
		burnTime = tile.burnTime;
		totalBurnTime = tile.totalBurnTime;
	}

	@Override
	public void addListener(IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendProgressBarUpdate(this, 0, (int) tile.getEnergy());
		crafting.sendProgressBarUpdate(this, 1, (int) tile.getMaxPower());
		crafting.sendProgressBarUpdate(this, 2, tile.progress);
		crafting.sendProgressBarUpdate(this, 3, tile.maxProgress);
		crafting.sendProgressBarUpdate(this, 4, tile.burnTime);
		crafting.sendProgressBarUpdate(this, 5, tile.totalBurnTime);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.energy = value;
		} else if (id == 1) {
			this.maxEnergy = value;
		} else if (id == 2) {
			this.progress = value;
		} else if (id == 3) {
			this.maxProgress = value;
		} else if (id == 4) {
			this.burnTime = value;
		} else if (id == 5) {
			this.totalBurnTime = value;
		}
	}

	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		System.out.println(slotId);
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 1 && index != 0) {
				if (!FurnaceRecipes.instance().getSmeltingResult(itemstack1).isEmpty()) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (TileEntityFurnace.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 3 && index < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	public void addSlot(TRSlot slot) {
		addSlotToContainer(slot);
		slots.add(slot);
	}

	private void addSlot(IInventory inventory, int x, int y, int index) {
		addSlot(new TRSlot(inventory, index, x, y, TRSlot.Priority.NONE));
	}

	public void addSlot(IInventory inventory, int x, int y) {
		addSlot(new TRSlot(inventory, x, y, getNewSlotIndex(), TRSlot.Priority.NONE));
	}

	public void addChargeSlot(IInventory inventory, int x, int y, ChargeSlot.IO io) {
		addSlot(new ChargeSlot(inventory, x, y, getNewSlotIndex(), io));
	}

	public void addUpgradeSlot(IInventory inventory, int x, int y) {
		addSlot(new UpgradeSlot(inventory, x, y, getNewSlotIndex()));
	}

	public void addOutputSlot(IInventory inventory, int x, int y) {
		addSlot(new OutputSlot(inventory, x, y, getNewSlotIndex()));
	}

	public void addInputSlot(IInventory inventory, int x, int y) {
		addSlot(new InputSlot(inventory, x, y, getNewSlotIndex()));
	}

	public void addPrimaryInputSlot(IInventory inventory, int x, int y) {
		addSlot(new PrimaryInputSlot(inventory, x, y, getNewSlotIndex()));
	}

	protected int getNewSlotIndex() {
		return lastSlotIndex++;
	}

	public List<TRSlot> getSlots() {
		return slots;
	}

	public TileMachineBase getTile() {
		return tile;
	}

	public int getEnergy() {
		return energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public int getProgress() {
		return progress;
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public int getBurnTime() {
		return burnTime;
	}

	public int getTotalBurnTime() {
		return totalBurnTime;
	}
}
