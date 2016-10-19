package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import techreborn.init.ModBlocks;

/**
 * Created by modmuss50 on 25/02/2016.
 */
public class TileWindMill extends AbstractTileGenerator {

	private int obstructedBlockCount;

	public TileWindMill() {
		super(EnumPowerTier.LOW, 5);
	}

	@Override
	public void updateRequirements() {
		updateObscuratedBlockCount();
	}

	private void updateObscuratedBlockCount() {
		this.obstructedBlockCount = -1;

		for (int x = -4; x < 5; x++) {
			for (int y = -2; y < 5; y++) {
				for (int z = -4; z < 5; z++) {
					if (!this.getWorld().isAirBlock(this.getPos().add(x, y, z))) {
						this.obstructedBlockCount++;
					}
				}
			}
		}

		setEuPerTick(0.0D);
		if (getPos().getY() > 64) {
			double actualPower = Math.max(0, (getPos().getY() - 64 - this.obstructedBlockCount) / 750);

			if (getWorld().isThundering()) {
				actualPower *= 1.5D;
			} else if (getWorld().isRaining()) {
				actualPower *= 1.2D;
			}

			setEuPerTick(actualPower);
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.windMill, 1);
	}
}
