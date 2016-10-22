package techreborn.tiles.energy.tier3;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.tile.TileMachineBase;
import techreborn.init.ModBlocks;

public class TileChunkLoader extends TileMachineBase {

	//public int euTick = 32;

	public TileChunkLoader() {
		super(EnumPowerTier.HIGH, 10000, 0, 1);
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.chunkLoader, 1);
	}
}
