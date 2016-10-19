package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.IWrenchable;
import reborncore.common.powerSystem.TileEnergyBase;

/**
 * Created by Lordmau5 on 12.06.2016.
 */
public abstract class AbstractTileGenerator extends TileEnergyBase implements IWrenchable {

	private double euPerTick;
	private int tickTime;

	public AbstractTileGenerator(EnumPowerTier tier, int capacity) {
		super(tier, capacity);
	}

	@Override
	public void validate() {
		super.validate();

		updateRequirements();
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		updateTick();

		if (this.tickTime++ % 128 == 0) {
			updateRequirements();
		}

		if (canGenerate()) {
			addEnergy(getEuPerTick());
			markBlockForUpdate();
		}
	}

	public void updateTick() {
	}

	public abstract void updateRequirements();

	private boolean canGenerate() {
		return getEuPerTick() > 0.0D;
	}

	private double getEuPerTick() {
		return this.euPerTick;
	}

	public void setEuPerTick(double euPerTick) {
		this.euPerTick = euPerTick;
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing enumFacing) {
		return false;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing enumFacing) {
		return true;
	}

	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return entityPlayer.isSneaking();
	}

	@Override
	public void setFacing(EnumFacing enumFacing) {

	}

	@Override
	public EnumFacing getFacing() {
		return getFacingEnum();
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, EnumFacing enumFacing) {
		return false;
	}
}
