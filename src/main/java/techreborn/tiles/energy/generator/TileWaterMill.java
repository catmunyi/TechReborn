package techreborn.tiles.energy.generator;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import techreborn.init.ModBlocks;

/**
 * Created by modmuss50 on 25/02/2016.
 */
public class TileWaterMill extends AbstractTileGenerator {

    private int waterBlocks;

    public TileWaterMill() {
        super(EnumPowerTier.LOW, 4);
    }

    @Override
    public void updateRequirements() {
        updateWaterCount();
    }

    private void updateWaterCount() {
        this.waterBlocks = 0;

        for (int x = this.getPos().getX() - 1; x < this.getPos().getZ() + 2; x++) {
            for (int y = this.getPos().getY() - 1; y < this.getPos().getY() + 2; y++) {
                for (int z = this.getPos().getZ() - 1; z < this.getPos().getZ() + 2; z++) {
                    Block block = this.getWorld().getBlockState(getPos()).getBlock();
                    if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) {
                        this.waterBlocks++;
                    }
                }
            }
        }

        setEuPerTick(this.waterBlocks);
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(ModBlocks.waterMill, 1);
    }
}
