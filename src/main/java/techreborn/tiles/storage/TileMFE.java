package techreborn.tiles.storage;

import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.tiles.TileStorageUnitBase;

/**
 * Created by modmuss50 on 14/03/2016.
 */
public class TileMFE extends TileStorageUnitBase {

	public TileMFE() {
		super(2, "TileMFE", 64, ConfigTechReborn.MFE_MAX_POWER, EnumPowerTier.MEDIUM, new ItemStack(ModBlocks.MFE));
	}

}