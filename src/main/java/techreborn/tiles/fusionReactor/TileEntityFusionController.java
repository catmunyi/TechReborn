package techreborn.tiles.fusionReactor;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import reborncore.common.util.ItemUtils;
import techreborn.api.reactor.FusionReactorRecipe;
import techreborn.api.reactor.FusionReactorRecipeHelper;
import techreborn.blocks.BlockFusionControlComputer;
import techreborn.client.container.energy.tier3.ContainerFusionReactor;
import techreborn.init.ModBlocks;

public class TileEntityFusionController extends TileMachineInventory {

	// 0= no coils, 1 = coils
	public int coilStatus = 0;
	public int crafingTickTime = 0;
	public int finalTickTime = 0;
	public int neededPower = 0;

	private int topStackSlot = 0;
	private int bottomStackSlot = 1;
	private int outputStackSlot = 2;

	private FusionReactorRecipe currentRecipe = null;
	private boolean hasStartedCrafting = false;

	public TileEntityFusionController() {
		super(EnumPowerTier.EXTREME, 100000000, 0, 0, "TileEntityFusionController", 3, 64);
	}

	@Override
	public double getMaxPower() {
		return 100000000;
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing direction) {
		return !(direction == EnumFacing.DOWN || direction == EnumFacing.UP);
	}

	@Override
	public boolean canProvideEnergy(EnumFacing direction) {
		return direction == EnumFacing.DOWN || direction == EnumFacing.UP;
	}

	@Override
	public double getMaxOutput() {
		if (!hasStartedCrafting) {
			return 0;
		}
		return 1000000;
	}

	@Override
	public double getMaxInput() {
		if (hasStartedCrafting) {
			return 0;
		}
		return 8192;
	}

	@Override
	public EnumPowerTier getTier() {
		return EnumPowerTier.EXTREME;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		crafingTickTime = tagCompound.getInteger("crafingTickTime");
		finalTickTime = tagCompound.getInteger("finalTickTime");
		neededPower = tagCompound.getInteger("neededPower");
		hasStartedCrafting = tagCompound.getBoolean("hasStartedCrafting");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		if (crafingTickTime == -1) {
			crafingTickTime = 0;
		}
		if (finalTickTime == -1) {
			finalTickTime = 0;
		}
		if (neededPower == -1) {
			neededPower = 0;
		}
		tagCompound.setInteger("crafingTickTime", crafingTickTime);
		tagCompound.setInteger("finalTickTime", finalTickTime);
		tagCompound.setInteger("neededPower", neededPower);
		tagCompound.setBoolean("hasStartedCrafting", hasStartedCrafting);
		return tagCompound;
	}

	public boolean checkCoils() {
		if ((isCoil(this.getPos().getX() + 3, this.getPos().getY(), this.getPos().getZ() + 1))
			&& (isCoil(this.getPos().getX() + 3, this.getPos().getY(), this.getPos().getZ()))
			&& (isCoil(this.getPos().getX() + 3, this.getPos().getY(), this.getPos().getZ() - 1))
			&& (isCoil(this.getPos().getX() - 3, this.getPos().getY(), this.getPos().getZ() + 1))
			&& (isCoil(this.getPos().getX() - 3, this.getPos().getY(), this.getPos().getZ()))
			&& (isCoil(this.getPos().getX() - 3, this.getPos().getY(), this.getPos().getZ() - 1))
			&& (isCoil(this.getPos().getX() + 2, this.getPos().getY(), this.getPos().getZ() + 2))
			&& (isCoil(this.getPos().getX() + 2, this.getPos().getY(), this.getPos().getZ() + 1))
			&& (isCoil(this.getPos().getX() + 2, this.getPos().getY(), this.getPos().getZ() - 1))
			&& (isCoil(this.getPos().getX() + 2, this.getPos().getY(), this.getPos().getZ() - 2))
			&& (isCoil(this.getPos().getX() - 2, this.getPos().getY(), this.getPos().getZ() + 2))
			&& (isCoil(this.getPos().getX() - 2, this.getPos().getY(), this.getPos().getZ() + 1))
			&& (isCoil(this.getPos().getX() - 2, this.getPos().getY(), this.getPos().getZ() - 1))
			&& (isCoil(this.getPos().getX() - 2, this.getPos().getY(), this.getPos().getZ() - 2))
			&& (isCoil(this.getPos().getX() + 1, this.getPos().getY(), this.getPos().getZ() + 3))
			&& (isCoil(this.getPos().getX() + 1, this.getPos().getY(), this.getPos().getZ() + 2))
			&& (isCoil(this.getPos().getX() + 1, this.getPos().getY(), this.getPos().getZ() - 2))
			&& (isCoil(this.getPos().getX() + 1, this.getPos().getY(), this.getPos().getZ() - 3))
			&& (isCoil(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() + 3))
			&& (isCoil(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() + 2))
			&& (isCoil(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() - 2))
			&& (isCoil(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() - 3))
			&& (isCoil(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() + 3))
			&& (isCoil(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() - 3))) {
			coilStatus = 1;
			return true;
		}
		coilStatus = 0;
		return false;
	}

	private boolean isCoil(int x, int y, int z) {
		return worldObj.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.fusionCoil;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		// TODO improve this code a lot

		if (worldObj.getTotalWorldTime() % 20 == 0) {
			checkCoils();
		}

		if (!worldObj.isRemote) {
			if (coilStatus == 1) {
				if (currentRecipe == null) {
					if (crafingTickTime != 0) {
						for (FusionReactorRecipe reactorRecipe : FusionReactorRecipeHelper.reactorRecipes) {
							if (ItemUtils.isItemEqual(getInventory().getStackInSlot(topStackSlot), reactorRecipe.getTopInput(), true,
								true, true)) {
								if (reactorRecipe.getBottomInput() != null) {
									if (!ItemUtils.isItemEqual(getInventory().getStackInSlot(bottomStackSlot),
										reactorRecipe.getBottomInput(), true, true, true)) {
										break;
									}
								}
								if (canFitStack(reactorRecipe.getOutput(), outputStackSlot, true)) {
									currentRecipe = reactorRecipe;
									if (crafingTickTime != 0) {
										finalTickTime = currentRecipe.getTickTime();
										neededPower = (int) currentRecipe.getStartEU();
									}
									hasStartedCrafting = false;
									crafingTickTime = 0;
									finalTickTime = currentRecipe.getTickTime();
									neededPower = (int) currentRecipe.getStartEU();
									break;
								}
							}
						}
					}
				} else {
					//if (inventory.hasChanged)
					//{
					if (!validateRecipe()) {
						resetCrafter();
						return;
					}
					//}
					if (!hasStartedCrafting) {
						if (canUseEnergy(currentRecipe.getStartEU() + 64)) {
							useEnergy(currentRecipe.getStartEU());
							hasStartedCrafting = true;
						}
					} else {
						if (crafingTickTime < currentRecipe.getTickTime()) {
							if (currentRecipe.getEuTick() > 0) { // Power gen
								addEnergy(currentRecipe.getEuTick()); // Waste
								// power
								// if it
								// has
								// no
								// where
								// to go
								crafingTickTime++;
							} else { // Power user
								if (canUseEnergy(currentRecipe.getEuTick() * -1)) {
									setEnergy(getEnergy() - (currentRecipe.getEuTick() * -1));
									crafingTickTime++;
								}
							}
						} else {
							if (canFitStack(currentRecipe.getOutput(), outputStackSlot, true)) {
								if (getInventory().getStackInSlot(outputStackSlot) == null) {
									getInventory().setInventorySlotContents(outputStackSlot, currentRecipe.getOutput().copy());
								} else {
									getInventory().decrStackSize(outputStackSlot, -currentRecipe.getOutput().stackSize);
								}
								getInventory().decrStackSize(topStackSlot, currentRecipe.getTopInput().stackSize);
								if (currentRecipe.getBottomInput() != null) {
									getInventory().decrStackSize(bottomStackSlot, currentRecipe.getBottomInput().stackSize);
								}
								resetCrafter();
							}
						}
					}
				}
			} else {
				if (currentRecipe != null) {
					resetCrafter();
				}
			}
		}
	}


	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.fusionControlComputer, 1);
	}

	private boolean validateRecipe() {
		if (ItemUtils.isItemEqual(getInventory().getStackInSlot(this.topStackSlot), this.currentRecipe.getTopInput(), true, true, true)) {
			if (this.currentRecipe.getBottomInput() != null) {
				if (!ItemUtils.isItemEqual(getInventory().getStackInSlot(this.bottomStackSlot), this.currentRecipe.getBottomInput(), true, true, true)) {
					return false;
				}
			}

			if (canFitStack(this.currentRecipe.getOutput(), this.outputStackSlot, true)) {
				return true;
			}
		}

		return false;
	}

	private void resetCrafter() {
		this.currentRecipe = null;
		this.crafingTickTime = -1;
		this.finalTickTime = -1;
		this.neededPower = -1;
		this.hasStartedCrafting = false;
	}

	private boolean canFitStack(ItemStack stack, int slot, boolean oreDict) { // Checks to see if it can fit the stack
		if (stack == null) {
			return true;
		}

		ItemStack itemStack = getInventory().getStackInSlot(slot);
		if (itemStack == null) {
			return true;
		}

		if (ItemUtils.isItemEqual(itemStack, stack, true, true, oreDict)) {
			if (stack.stackSize + itemStack.stackSize <= stack.getMaxStackSize()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerFusionReactor.class, this);
	}

	public int getFacingInt()
	{
		Block block = worldObj.getBlockState(pos).getBlock();
		if (block instanceof BlockFusionControlComputer)
		{
			return ((BlockFusionControlComputer) block).getFacing(worldObj.getBlockState(pos)).getIndex();
		}
		return 0;
	}
}
