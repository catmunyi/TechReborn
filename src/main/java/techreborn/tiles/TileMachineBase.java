package techreborn.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.IWrenchable;
import reborncore.common.powerSystem.TilePowerAcceptor;
import reborncore.common.util.Inventory;
import techreborn.client.container.ContainerMachineBase;

/**
 * Created by Prospector
 */
public abstract class TileMachineBase extends TilePowerAcceptor implements IWrenchable, IInventoryProvider {

	public Inventory inventory;
	public int progress;
	public int burnTime;
	public int totalBurnTime;
	public boolean isActive;
	public boolean lastTickActive;
	public int maxProgress;
	public int maxPower;
	public boolean canRun;
	public boolean hasTank;
	public EnumPowerTier powerTier;
	public ItemStack wrenchDrop;

	public TileMachineBase(int invSize, String invName, int invStackLimit, int maxPower, EnumPowerTier tier, ItemStack wrenchDrop) {
		super(tier);
		this.inventory = new Inventory(invSize + ContainerMachineBase.lastSlotIndex, invName, invStackLimit, this);
		this.maxPower = maxPower;
		this.powerTier = tier;
		this.wrenchDrop = wrenchDrop;
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer player, EnumFacing side) {
		return !player.isSneaking();
	}

	@Override
	public EnumFacing getFacing() {
		return getFacingEnum();
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer player) {
		return player.isSneaking();
	}

	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}

	@Override
	public double getMaxPower() {
		return maxPower;
	}

	@Override
	public double getMaxOutput() {
		return powerTier.getMaxOutput();
	}

	@Override
	public double getMaxInput() {
		return powerTier.getMaxInput();
	}

	@Override
	public EnumPowerTier getTier() {
		return powerTier;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer player) {
		return wrenchDrop;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if (tag.hasKey("burnTime") && tag.hasKey("totalBurnTime")) {
			burnTime = tag.getInteger("burnTime");
			totalBurnTime = tag.getInteger("totalBurnTime");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("burnTime", burnTime);
		tag.setInteger("totalBurnTime", totalBurnTime);
		return tag;
	}
}
