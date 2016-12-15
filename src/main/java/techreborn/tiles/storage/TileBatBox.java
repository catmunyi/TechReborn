package techreborn.tiles.storage;

import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.tiles.TileStorageUnitBase;

/**
 * Created by modmuss50 on 14/03/2016.
 */
public class TileBatBox extends TileStorageUnitBase {

	public TileBatBox() {
		super(3, "TileBatBox", 64, ConfigTechReborn.BATBOX_MAX_POWER, EnumPowerTier.LOW, new ItemStack(ModBlocks.batBox));
	}
}
