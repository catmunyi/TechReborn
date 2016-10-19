package techreborn.tiles.energy.tier1;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.powerSystem.PowerSystem;
import reborncore.common.tile.TileMachineBase;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;

import java.util.List;

/**
 * Created by modmuss50 on 08/05/2016.
 */
public class TilePump extends TileMachineBase implements IFluidHandler {

    //public Tank tank = new Tank("TilePump", 10000, this);
    private FluidTank tank = new FluidTank(10000);

    public TilePump() {
        super(EnumPowerTier.LOW, 10000, ConfigTechReborn.pumpExtractEU, 1);
    }

    @Override
    public boolean canWork() {
        return super.canWork() && tank.getCapacity() - tank.getFluidAmount() >= 1000 && drainBlock(getWorld(), getPos().down(), false) != null;
    }

    @Override
    public void machineFinish() {
        FluidStack fluidStack = drainBlock(getWorld(), getPos().down(), false);
        if (fluidStack != null) {
            this.tank.fill(drainBlock(getWorld(), getPos().down(), true), true);
        }
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(ModBlocks.pump, 1);
    }

    @Override
    public void addInfo(List<String> info, boolean isRealTile) {
        super.addInfo(info, isRealTile);
        info.add(TextFormatting.LIGHT_PURPLE + "Eu per extract " + TextFormatting.GREEN
                + PowerSystem.getLocaliszedPower(ConfigTechReborn.pumpExtractEU));
        info.add(TextFormatting.LIGHT_PURPLE + "Speed: " + TextFormatting.GREEN
                + "1000mb/5 sec");
    }

    private FluidStack drainBlock(World world, BlockPos pos, boolean doDrain) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Fluid fluid = FluidRegistry.lookupFluidForBlock(block);

        if (fluid != null && FluidRegistry.isFluidRegistered(fluid)) {
            if (block instanceof IFluidBlock) {
                IFluidBlock fluidBlock = (IFluidBlock) block;
                if (!fluidBlock.canDrain(world, pos)) {
                    return null;
                }
                return fluidBlock.drain(world, pos, doDrain);
            } else {
                //Checks if source
                int level = state.getValue(BlockLiquid.LEVEL);
                if (level != 0) {
                    return null;
                }
                if (doDrain) {
                    world.setBlockToAir(pos);
                }
                return new FluidStack(fluid, 1000);
            }
        } else {
            return null;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.tank.readFromNBT(tagCompound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        this.tank.writeToNBT(tagCompound);
        return tagCompound;
    }

    // IFluidHandler
    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return tank.getFluid() == null || tank.getFluid().getFluid() == fluid;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[]{tank.getInfo()};
    }
}
