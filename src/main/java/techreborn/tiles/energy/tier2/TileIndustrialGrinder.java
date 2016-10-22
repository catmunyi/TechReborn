package techreborn.tiles.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.*;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.tile.TileMachineInventory;
import techreborn.api.Reference;
import techreborn.api.recipe.ITileRecipeHandler;
import techreborn.api.recipe.machines.IndustrialGrinderRecipe;
import techreborn.client.container.energy.tier2.ContainerIndustrialGrinder;
import techreborn.init.ModBlocks;
import techreborn.init.ModFluids;
import techreborn.tiles.TileMachineCasing;

public class TileIndustrialGrinder extends TileMachineInventory implements IFluidHandler, ITileRecipeHandler<IndustrialGrinderRecipe>, IRecipeCrafterProvider {

	public FluidTank tank = new FluidTank(16000);
	public RecipeCrafter crafter;
	public int connectionStatus;

	public TileIndustrialGrinder() {
		super(EnumPowerTier.MEDIUM, 10000, 0, 1, "TileIndustrialGrinder", 6, 64);

		int[] inputs = new int[2];
		inputs[0] = 0;
		inputs[1] = 1;
		int[] outputs = new int[4];
		outputs[0] = 2;
		outputs[1] = 3;
		outputs[2] = 4;
		outputs[3] = 5;
		crafter = new RecipeCrafter(Reference.industrialGrinderRecipe, this, 1, 4, getInventory(), inputs, outputs);
	}

	@Override
	public boolean canWork() {
		return super.canWork() && getMultiBlock();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.industrialGrinder, 1);
	}

	public boolean getMultiBlock() {
		for (EnumFacing direction : EnumFacing.values()) {
			TileEntity tileEntity = worldObj.getTileEntity(new BlockPos(getPos().getX() + direction.getFrontOffsetX(),
				getPos().getY() + direction.getFrontOffsetY(), getPos().getZ() + direction.getFrontOffsetZ()));
			if (tileEntity instanceof TileMachineCasing) {
				if (((TileMachineCasing) tileEntity).isConnected()
					&& ((TileMachineCasing) tileEntity).getMultiblockController().isAssembled()
					&& ((TileMachineCasing) tileEntity).getMultiblockController().height == 3) {
					connectionStatus = 1;
					return true;
				}
			}
		}
		connectionStatus = 0;
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		tank.readFromNBT(tagCompound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tank.writeToNBT(tagCompound);
		return tagCompound;
	}

	/* IFluidHandler */
	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		if (resource.getFluid() == FluidRegistry.WATER || resource.getFluid() == ModFluids.fluidMercury
			|| resource.getFluid() == ModFluids.fluidSodiumpersulfate) {
			int filled = tank.fill(resource, doFill);
			return filled;
		}
		return 0;
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		FluidStack fluidStack = tank.drain(resource.amount, doDrain);
		return fluidStack;
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		FluidStack drained = tank.drain(maxDrain, doDrain);
		return drained;
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		return fluid == FluidRegistry.WATER || fluid == ModFluids.fluidMercury || fluid == ModFluids.fluidSodiumpersulfate;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

	// ISidedInventory
	//	@Override
	//	public int[] getSlotsForFace(EnumFacing side)
	//	{
	//		return side == EnumFacing.DOWN ? new int[] { 0, 1, 2, 3, 4, 5 } : new int[] { 0, 1, 2, 3, 4, 5 };
	//	}
	//
	//	@Override
	//	public boolean canInsertItem(int slotIndex, ItemStack itemStack, EnumFacing side)
	//	{
	//		if (slotIndex >= 2)
	//			return false;
	//		return isItemValidForSlot(slotIndex, itemStack);
	//	}
	//
	//	@Override
	//	public boolean canExtractItem(int slotIndex, ItemStack itemStack, EnumFacing side)
	//	{
	//		return slotIndex == 2 || slotIndex == 3 || slotIndex == 4 || slotIndex == 5;
	//	}

	public int getProgressScaled(int scale) {
		if (crafter.currentTickTime != 0) {
			return crafter.currentTickTime * scale / crafter.currentNeededTicks;
		}
		return 0;
	}

	@Override
	public boolean canCraft(TileEntity tile, IndustrialGrinderRecipe recipe) {
		if (recipe.fluidStack == null) {
			return true;
		}
		if (tile instanceof TileIndustrialGrinder) {
			TileIndustrialGrinder grinder = (TileIndustrialGrinder) tile;
			if (grinder.tank.getFluid() == null) {
				return false;
			}
			if (grinder.tank.getFluid() == recipe.fluidStack) {
				if (grinder.tank.getFluidAmount() >= recipe.fluidStack.amount) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onCraft(TileEntity tile, IndustrialGrinderRecipe recipe) {
		if (recipe.fluidStack == null) {
			return true;
		}
		if (tile instanceof TileIndustrialGrinder) {
			TileIndustrialGrinder grinder = (TileIndustrialGrinder) tile;
			if (grinder.tank.getFluid() == null) {
				return false;
			}
			if (grinder.tank.getFluid() == recipe.fluidStack) {
				if (grinder.tank.getFluidAmount() >= recipe.fluidStack.amount) {
					if (grinder.tank.getFluidAmount() > 0) {
						grinder.tank.setFluid(new FluidStack(recipe.fluidStack.getFluid(),
							grinder.tank.getFluidAmount() - recipe.fluidStack.amount));
					} else {
						grinder.tank.setFluid(null);
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public RecipeCrafter getRecipeCrafter() {
		return crafter;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerIndustrialGrinder.class, this);
	}
}
