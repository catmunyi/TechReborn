package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.tile.TileMachineBase;
import techreborn.init.ModBlocks;

/**
 * Created by Mark on 19/10/2016.
 */
public class TileLightningRod extends TileMachineBase {
	public TileLightningRod() {
		super(EnumPowerTier.HIGH, 10000, 0, 0);
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer p0) {
		return new ItemStack(ModBlocks.plasmaGenerator);
	}
}