package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import techreborn.init.ModBlocks;

public class TileHeatGenerator extends AbstractTileGenerator {

    public TileHeatGenerator() {
        super(EnumPowerTier.LOW, 10000);
    }

    @Override
    public void updateRequirements() {
        int euPerTick = 0;
        if (getWorld().getBlockState(getPos().add(1, 0, 0)).getBlock() == Blocks.LAVA) {
            euPerTick++;
        }
        if (getWorld().getBlockState(getPos().add(0, 0, 1)).getBlock() == Blocks.LAVA) {
            euPerTick++;
        }
        if (getWorld().getBlockState(getPos().add(0, 0, -1)).getBlock() == Blocks.LAVA) {
            euPerTick++;
        }
        if (getWorld().getBlockState(getPos().add(-1, 0, 0)).getBlock() == Blocks.LAVA) {
            euPerTick++;
        }
        if (getWorld().getBlockState(getPos().add(0, 1, 0)).getBlock() == Blocks.LAVA) {
            euPerTick++;
        }

        setEuPerTick(euPerTick);
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(ModBlocks.heatGenerator, 1);
    }
}
