package techreborn.tiles.lesu;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.powerSystem.TileEnergyBase;

public class TileLESUStorage extends TileEnergyBase {

    public LESUNetwork network;

    public TileLESUStorage() {
        super(EnumPowerTier.LOW, 0);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (network == null) {
            findAndJoinNetwork(worldObj, getPos().getX(), getPos().getY(), getPos().getZ());
        } else {
            if (network.master != null) {
                if (network.master.getWorld().getTileEntity(new BlockPos(network.master.getPos().getX(), network.master.getPos().getY(), network.master.getPos().getZ())) != network.master) {
                    network.master = null;
                }
            }
        }
    }

    public final void findAndJoinNetwork(World world, int x, int y, int z) {
        network = new LESUNetwork();
        network.addElement(this);
        for (EnumFacing direction : EnumFacing.values()) {
            if (world.getTileEntity(new BlockPos(x + direction.getFrontOffsetX(), y + direction.getFrontOffsetY(), z + direction.getFrontOffsetZ())) instanceof TileLESUStorage) {
                TileLESUStorage lesu = (TileLESUStorage) world.getTileEntity(new BlockPos(x + direction.getFrontOffsetX(), y + direction.getFrontOffsetY(), z + direction.getFrontOffsetZ()));
                if (lesu.network != null) {
                    lesu.network.merge(network);
                }
            }
        }
    }

    public final void setNetwork(LESUNetwork n) {
        if (n != null) {
            network = n;
            network.addElement(this);
        }
    }

    public final void resetNetwork() {
        network = null;
    }

    public final void removeFromNetwork() {
        if (network != null)
            network.removeElement(this);
    }

    public final void rebuildNetwork() {
        this.removeFromNetwork();
        this.resetNetwork();
        this.findAndJoinNetwork(worldObj, getPos().getX(), getPos().getY(), getPos().getZ());
    }

    @Override
    public boolean canAcceptEnergy(EnumFacing enumFacing) {
        return false;
    }

    @Override
    public boolean canProvideEnergy(EnumFacing enumFacing) {
        return false;
    }
}
