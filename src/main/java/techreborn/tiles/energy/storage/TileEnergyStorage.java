package techreborn.tiles.energy.storage;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.power.IEnergyItemInfo;
import reborncore.common.IWrenchable;
import reborncore.common.powerSystem.PoweredItem;
import reborncore.common.powerSystem.TileEnergyUpgradeable;
import reborncore.common.util.inventory.Inventory;
import techreborn.blocks.storage.BlockEnergyStorage;

import java.util.List;

/**
 * Created by Rushmead
 */
public abstract class TileEnergyStorage extends TileEnergyUpgradeable implements IWrenchable {

	public String name;
	public Block wrenchDrop;

	private Inventory inventoryCharging;

	public TileEnergyStorage(String name, Block wrenchDrop, EnumPowerTier tier, int maxStorage) {
		super(tier, maxStorage);
		this.wrenchDrop = wrenchDrop;
		this.name = name;

		this.inventoryCharging = new Inventory("Charging", 2, 1, this);
	}

	public int getEnergyScaled(int scale) {
		return (int) ((getEnergy() * scale / getMaxPower()));
	}

	public Inventory getInventoryCharging() {
		return this.inventoryCharging;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		this.inventoryCharging.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		this.inventoryCharging.writeToNBT(compound);

		return super.writeToNBT(compound);
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
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return entityPlayer.isSneaking();
	}

	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (this.inventoryCharging.getStackInSlot(0) != null) {
			ItemStack stack = this.inventoryCharging.getStackInSlot(0);
			if (!(stack.getItem() instanceof IEnergyItemInfo)) {
				return;
			}
			IEnergyItemInfo item = (IEnergyItemInfo) this.inventoryCharging.getStackInSlot(0).getItem();
			if (PoweredItem.getEnergy(stack) != PoweredItem.getMaxPower(stack)) {
				if (canUseEnergy(item.getMaxTransfer(stack))) {
					useEnergy(item.getMaxTransfer(stack));
					PoweredItem.setEnergy(PoweredItem.getEnergy(stack) + item.getMaxTransfer(stack), stack);
				}
			}
		}
		if (this.inventoryCharging.getStackInSlot(1) != null) {
			ItemStack stack = this.inventoryCharging.getStackInSlot(1);
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
	public void setFacing(EnumFacing enumFacing) {
		getWorld().setBlockState(getPos(), getWorld().getBlockState(getPos()).withProperty(BlockEnergyStorage.FACING, enumFacing));
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(this.wrenchDrop);
	}

	@Override
	public EnumFacing getFacingEnum() {
		Block block = getWorld().getBlockState(getPos()).getBlock();
		if (block instanceof BlockEnergyStorage) {
			return ((BlockEnergyStorage) block).getFacing(getWorld().getBlockState(getPos()));
		}
		return null;
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing direction) {
		return getFacingEnum() != direction;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing direction) {
		return getFacing() == direction;
	}

	@Override
	public void addInfo(List<String> info, boolean isRealTile) {
		info.add(this.name);
	}
}