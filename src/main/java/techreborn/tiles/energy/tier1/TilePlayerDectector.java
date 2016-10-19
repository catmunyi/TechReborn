package techreborn.tiles.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.powerSystem.TileEnergyBase;
import reborncore.common.util.WorldUtils;

import java.util.Iterator;

public class TilePlayerDectector extends TileEnergyBase {

    public String ownerUDID = "";
    private boolean redstone = false;

    public TilePlayerDectector() {
        super(EnumPowerTier.LOW, 10000);
    }

    @Override
    public boolean canAcceptEnergy(EnumFacing direction) {
        return true;
    }

    @Override
    public boolean canProvideEnergy(EnumFacing direction) {
        return false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (getWorld().getWorldTime() % 20 == 0) {
            boolean lastRedstone = this.redstone;
            this.redstone = false;
            if (canUseEnergy(10)) {
                Iterator tIterator = super.worldObj.playerEntities.iterator();
                while (tIterator.hasNext()) {
                    EntityPlayer player = (EntityPlayer) tIterator.next();
                    if (player.getDistanceSq((double) super.getPos().getX() + 0.5D,
                            (double) super.getPos().getY() + 0.5D, (double) super.getPos().getZ() + 0.5D) <= 256.0D) {
                        BlockMachineBase blockMachineBase = (BlockMachineBase) worldObj.getBlockState(pos).getBlock();
                        int meta = blockMachineBase.getMetaFromState(worldObj.getBlockState(pos));
                        if (meta == 0) {// ALL
                            redstone = true;
                        } else if (meta == 1) {// Others
                            if (!this.ownerUDID.isEmpty() && !this.ownerUDID.equals(player.getUniqueID().toString())) {
                                redstone = true;
                            }
                        } else {// You
                            if (!this.ownerUDID.isEmpty() && this.ownerUDID.equals(player.getUniqueID().toString())) {
                                redstone = true;
                            }
                        }
                        redstone = true;
                    }
                }
                useEnergy(10);
            }
            if (lastRedstone != redstone) {
                WorldUtils.updateBlock(worldObj, getPos());
                worldObj.notifyNeighborsOfStateChange(getPos(), worldObj.getBlockState(getPos()).getBlock());
            }
        }
    }

    public boolean isProvidingPower() {
        return this.redstone;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.ownerUDID = tag.getString("ownerID");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("ownerID", this.ownerUDID);
        return tag;
    }
}
