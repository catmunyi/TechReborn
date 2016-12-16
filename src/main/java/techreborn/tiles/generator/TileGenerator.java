package techreborn.tiles.generator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.ForgeModContainer;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.blocks.BlockMachineBase;
import techreborn.client.container.ContainerMachineBase;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.tiles.TileMachineBase;

public class TileGenerator extends TileMachineBase {
	public static int outputAmount = ConfigTechReborn.GENERATOR_OUTPUT;
	public int fuelSlot = 1 + ContainerMachineBase.lastSlotIndex;
	ItemStack burnItem;

	public TileGenerator() {
		super(ContainerMachineBase.lastSlotIndex, "TileGenerator", 64, ConfigTechReborn.GENERATOR_MAX_POWER, EnumPowerTier.LOW, new ItemStack(ModBlocks.generator));
	}

	public static int getItemBurnTime(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack) / 4;
	}

	@Override
	public void update() {
		super.update();
		if (world.isRemote) {
			return;
		}
		if (getStackInSlot(fuelSlot).getItem() == Items.LAVA_BUCKET || getStackInSlot(fuelSlot).getItem() == ForgeModContainer.getInstance().universalBucket) {
			return;
		}
		if (getEnergy() < getMaxPower()) {
			if (burnTime > 0) {
				burnTime--;
				addEnergy(outputAmount);
				isActive = true;
			}
		} else {
			isActive = false;
		}

		if (burnTime == 0) {
			updateState();
			burnTime = totalBurnTime = getItemBurnTime(getStackInSlot(fuelSlot));
			if (burnTime > 0) {
				updateState();
				burnItem = getStackInSlot(fuelSlot);
				if (getStackInSlot(fuelSlot).getCount() == 1) {
					setInventorySlotContents(fuelSlot, ItemStack.EMPTY);
				} else {
					decrStackSize(fuelSlot, 1);
				}
			}
		}

		lastTickActive = isActive;
	}

	public void updateState() {
		IBlockState BlockStateContainer = world.getBlockState(pos);
		if (BlockStateContainer.getBlock() instanceof BlockMachineBase) {
			BlockMachineBase blockMachineBase = (BlockMachineBase) BlockStateContainer.getBlock();
			if (BlockStateContainer.getValue(BlockMachineBase.ACTIVE) != burnTime > 0)
				blockMachineBase.setActive(burnTime > 0, world, pos);
		}
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing direction) {
		return false;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing direction) {
		return true;
	}
}
