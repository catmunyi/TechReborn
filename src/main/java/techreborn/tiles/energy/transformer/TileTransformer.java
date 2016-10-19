package techreborn.tiles.energy.transformer;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.IWrenchable;
import reborncore.common.powerSystem.TileEnergyBase;
import techreborn.blocks.transformers.BlockTransformer;

/**
 * Created by Rushmead
 */
public class TileTransformer extends TileEnergyBase implements IWrenchable {

    public String name;
    private Block wrenchDrop;

    public TileTransformer(String name, Block wrenchDrop, EnumPowerTier tier, int capacity) {
        super(tier, capacity);
        this.wrenchDrop = wrenchDrop;
        this.tier = tier;
        this.name = name;
    }

    @Override
    public boolean wrenchCanSetFacing(EntityPlayer p0, EnumFacing p1) {
        return true;
    }

    @Override
    public EnumFacing getFacing() {
        return getFacingEnum();
    }

    @Override
    public void setFacing(EnumFacing enumFacing) {

    }

    @Override
    public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
        return entityPlayer.isSneaking();
    }

    @Override
    public float getWrenchDropRate() {
        return 1.0F;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer p0) {
        return new ItemStack(wrenchDrop);
    }

    @Override
    public boolean canAcceptEnergy(EnumFacing direction) {

        return getFacingEnum() != direction;
    }

    @Override
    public EnumFacing getFacingEnum() {
        Block block = worldObj.getBlockState(pos).getBlock();
        if (block instanceof BlockTransformer) {
            return ((BlockTransformer) block).getFacing(worldObj.getBlockState(pos));
        }
        return null;
    }

    @Override
    public boolean canProvideEnergy(EnumFacing direction) {
        return getFacing() == direction;
    }
}
