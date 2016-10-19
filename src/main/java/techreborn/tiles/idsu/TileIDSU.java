package techreborn.tiles.idsu;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang3.StringUtils;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.IWrenchable;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.tiles.energy.storage.TileEnergyStorage;

public class TileIDSU extends TileEnergyStorage implements IWrenchable {

	public String ownerUdid;
	private double euLastTick = 0;
	private double euChange;
	private int ticks;

	public TileIDSU(EnumPowerTier tier, int maxStorage) {
		super("IDSU", ModBlocks.idsu, tier, maxStorage);
	}

	public TileIDSU() {
		this(EnumPowerTier.EXTREME, 100000000);
	}

	@Override
	public double getEnergy() {
		if (ownerUdid == null && StringUtils.isBlank(ownerUdid) || StringUtils.isEmpty(ownerUdid)) {
			return 0.0;
		}
		return IDSUManager.INSTANCE.getSaveDataForWorld(worldObj, ownerUdid).storedPower;
	}

	@Override
	public void setEnergy(double energy) {
		if (ownerUdid == null && StringUtils.isBlank(ownerUdid) || StringUtils.isEmpty(ownerUdid)) {
			return;
		}
		IDSUManager.INSTANCE.getSaveDataForWorld(worldObj, ownerUdid).storedPower = energy;
	}

	public float getChargeLevel() {
		float ret = (float) this.getEnergy() / (float) this.getMaxPower();
		if (ret > 1.0F) {
			ret = 1.0F;
		}

		return ret;
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.ownerUdid = nbttagcompound.getString("ownerUdid");
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		if (ownerUdid == null && StringUtils.isBlank(ownerUdid) || StringUtils.isEmpty(ownerUdid)) {
			return nbttagcompound;
		}
		nbttagcompound.setString("ownerUdid", this.ownerUdid);
		return nbttagcompound;
	}

	public void updateEntity() {
		super.updateEntity();

		if (ticks == ConfigTechReborn.AverageEuOutTickTime) {
			euChange = -1;
			ticks = 0;
		} else {
			ticks++;
			euChange += getEnergy() - euLastTick;
			if (euLastTick == getEnergy()) {
				euChange = 0;
			}
		}

		euLastTick = getEnergy();
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer p0) {
		return true;
	}

	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		NBTTagCompound tileTag = new NBTTagCompound();
		ItemStack dropStack = new ItemStack(ModBlocks.idsu, 1);
		writeToNBT(tileTag);
		dropStack.setTagCompound(new NBTTagCompound());
		dropStack.getTagCompound().setTag("tileEntity", tileTag);
		return dropStack;
	}

	public double getEuChange() {
		if (euChange == -1) {
			return -1;
		}
		return (euChange / ticks);
	}

	public void handleGuiInputFromClient(int id) {
		//		if (id == 0) {
		//			setMaxOutput(getMaxOutput() + 256);
		//		}
		//		if (id == 1) {
		//            setMaxOutput(getMaxOutput() + 64);
		//		}
		//		if (id == 2) {
		//            setMaxOutput(getMaxOutput() - 64);
		//		}
		//		if (id == 3) {
		//            setMaxOutput(getMaxOutput() - 256);
		//		}
		//
		//        setMaxOutput(Math.max(0, Math.min(getMaxOutput(), 4096)));
		//TODO // FIXME: 19/10/2016
	}
}
