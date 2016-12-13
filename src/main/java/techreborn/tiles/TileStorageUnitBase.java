package techreborn.tiles;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.power.IEnergyItemInfo;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.IWrenchable;
import reborncore.common.powerSystem.PoweredItem;
import techreborn.blocks.storage.BlockEnergyStorage;

/**
 * Created by Prospector
 */
public abstract class TileStorageUnitBase extends TileMachineBase implements IWrenchable, ITickable, IInventoryProvider {

	public TileStorageUnitBase(int invSize, String invName, int invStackLimit, int maxPower, EnumPowerTier tier, ItemStack wrenchDrop) {
		super(invSize, invName, invStackLimit, maxPower, tier, wrenchDrop);
	}

	@Override
	public void updateEntity() {
		if (inventory.getStackInSlot(0) != ItemStack.EMPTY) {
			ItemStack stack = inventory.getStackInSlot(0);
			if (!(stack.getItem() instanceof IEnergyItemInfo)) {
				return;
			}
			IEnergyItemInfo item = (IEnergyItemInfo) inventory.getStackInSlot(0).getItem();
			if (PoweredItem.getEnergy(stack) != PoweredItem.getMaxPower(stack)) {
				if (canUseEnergy(item.getMaxTransfer(stack))) {
					useEnergy(item.getMaxTransfer(stack));
					PoweredItem.setEnergy(PoweredItem.getEnergy(stack) + item.getMaxTransfer(stack), stack);
				}
			}
		}
		if (inventory.getStackInSlot(1) != ItemStack.EMPTY) {
			ItemStack stack = inventory.getStackInSlot(1);
			if (!(stack.getItem() instanceof IEnergyItemInfo)) {
				return;
			}
			IEnergyItemInfo item = (IEnergyItemInfo) stack.getItem();
			if (item.canProvideEnergy(stack)) {
				if (getEnergy() != getMaxPower()) {
					addEnergy(item.getMaxTransfer(stack));
					PoweredItem.setEnergy(PoweredItem.getEnergy(stack) - item.getMaxTransfer(stack), stack);
				}
			}
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return true;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] { 0, 1 };
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, EnumFacing side) {
		return true;
	}

	@Override
	public EnumFacing getFacing() {
		return getFacingEnum();
	}

	@Override
	public void setFacing(EnumFacing enumFacing) {
		world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockEnergyStorage.FACING, enumFacing));
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing direction) {

		return getFacingEnum() != direction;
	}

	@Override
	public EnumFacing getFacingEnum() {
		Block block = world.getBlockState(pos).getBlock();
		if (block instanceof BlockEnergyStorage) {
			return ((BlockEnergyStorage) block).getFacing(world.getBlockState(pos));
		}
		return null;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing direction) {
		return getFacing() == direction;
	}
}
