package techreborn.tiles.storage;

import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.tiles.TileStorageUnitBase;

/**
 * Created by modmuss50 on 14/03/2016.
 */
public class TileMFSU extends TileStorageUnitBase {

	public TileMFSU() {
		super(2, "TileMFSU", 64, ConfigTechReborn.MFSU_MAX_POWER, EnumPowerTier.HIGH, new ItemStack(ModBlocks.MFSU));
	}
}