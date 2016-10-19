package techreborn.tiles.lesu;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import reborncore.api.power.EnumPowerTier;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.tiles.energy.storage.TileEnergyStorage;

import java.util.ArrayList;

public class TileLESU extends TileEnergyStorage {// TODO wrench

    public int connectedBlocks = 0;
    private ArrayList<LESUNetwork> countedNetworks = new ArrayList<>();
    private double euLastTick = 0;
    private double euChange;
    private int ticks;

    public TileLESU() {
        super("LESU", ModBlocks.lesu, EnumPowerTier.EXTREME, 8192);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        countedNetworks.clear();
        connectedBlocks = 0;
        for (EnumFacing dir : EnumFacing.values()) {
            BlockPos offsetPos = new BlockPos(getPos().getX() + dir.getFrontOffsetX(), getPos().getY() + dir.getFrontOffsetY(), getPos().getZ() + dir.getFrontOffsetZ());
            TileEntity tileEntity = worldObj.getTileEntity(offsetPos);
            if (tileEntity != null && tileEntity instanceof TileLESUStorage) {
                TileLESUStorage lesuStorage = (TileLESUStorage) tileEntity;
                if (lesuStorage.network != null) {
                    LESUNetwork network = lesuStorage.network;
                    if (!countedNetworks.contains(network)) {
                        if (network.master == null || network.master == this) {
                            connectedBlocks += network.storages.size();
                            countedNetworks.add(network);
                            network.master = this;
                            break;
                        }
                    }
                }
            }
        }
//		setMaxStorage(((connectedBlocks + 1) * ConfigTechReborn.LesuStoragePerBlock));
//		setMaxOutput((connectedBlocks * ConfigTechReborn.ExtraOutputPerLesuBlock) + ConfigTechReborn.BaseLesuOutput);

        if (ticks == ConfigTechReborn.AverageEuOutTickTime) {
            euChange = -1;
            ticks = 0;
        } else {
            ticks++;
            if (euChange == -1) {
                euChange = 0;
            }
            euChange += getEnergy() - euLastTick;
            if (euLastTick == getEnergy()) {
                euChange = 0;
            }
        }

        euLastTick = getEnergy();
    }

    public double getEuChange() {
        if (euChange == -1) {
            return 0;
        }
        return (euChange / ticks);
    }

    @Override
    public double getMaxInput() {
        return 8192;
    }
}
