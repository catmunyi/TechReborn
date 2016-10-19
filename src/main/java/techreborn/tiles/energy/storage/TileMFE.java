package techreborn.tiles.energy.storage;

import reborncore.api.power.EnumPowerTier;
import techreborn.init.ModBlocks;

/**
 * Created by modmuss50 on 14/03/2016.
 */
public class TileMFE extends TileEnergyStorage {

	public TileMFE() {
		super("MFE", ModBlocks.mfe, EnumPowerTier.MEDIUM, 4000000);
	}

}