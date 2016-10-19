package techreborn.tiles.energy.storage;

import reborncore.api.power.EnumPowerTier;
import techreborn.init.ModBlocks;

/**
 * Created by modmuss50 on 14/03/2016.
 */
public class TileMFSU extends TileEnergyStorage {

	public TileMFSU() {
		super("MFSU", ModBlocks.mfsu, EnumPowerTier.HIGH, 40000000);
	}
}