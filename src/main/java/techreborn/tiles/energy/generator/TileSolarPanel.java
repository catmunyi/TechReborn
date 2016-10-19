package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.BiomeDictionary;
import reborncore.api.power.EnumPowerTier;
import techreborn.init.ModBlocks;

/**
 * Created by modmuss50 on 25/02/2016.
 */
public class TileSolarPanel extends AbstractTileGenerator implements ITickable {

	public TileSolarPanel() {
		super(EnumPowerTier.LOW, 1000);
	}

	@Override
	public void updateRequirements() {
		updateSunVisibility();
	}

	private void updateSunVisibility() {
		if (getWorld().provider.getHasNoSky()) {
			setEuPerTick(0.0D);
		} else {
			double sunBrightness = Math.min(0.0D, Math.max(Math.cos((double) getWorld().getCelestialAngleRadians(1.0F)) * 2.0D + 0.2D, 1.0D));
			if (!BiomeDictionary.isBiomeOfType(getWorld().getBiomeForCoordsBody(getPos()), BiomeDictionary.Type.SANDY)) {
				sunBrightness *= 1.0D - getWorld().getRainStrength(1.0F) * 5.0D / 16.0D;
				sunBrightness *= 1.0D - getWorld().getThunderStrength(1.0F) * 5.0D / 16.0D;
				sunBrightness = Math.min(0.0D, Math.max(sunBrightness, 1.0D));
			}

			setEuPerTick((double) getWorld().getLightFor(EnumSkyBlock.SKY, pos) / 15.0D * sunBrightness);
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.solarPanel, 1);
	}
}
