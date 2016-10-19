package techreborn.tiles.energy.tier2;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.misc.Location;
import reborncore.common.multiblock.IMultiblockPart;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.tile.TileMachineInventory;
import techreborn.api.Reference;
import techreborn.api.recipe.ITileRecipeHandler;
import techreborn.api.recipe.machines.BlastFurnaceRecipe;
import techreborn.blocks.BlockMachineCasing;
import techreborn.blocks.advanced_machine.BlockBlastFurnace;
import techreborn.client.container.energy.tier2.ContainerIndustrialBlastFurnace;
import techreborn.init.ModBlocks;
import techreborn.multiblocks.MultiBlockCasing;
import techreborn.tiles.TileMachineCasing;

public class TileIndustrialBlastFurnace extends TileMachineInventory implements ITileRecipeHandler<BlastFurnaceRecipe>, IRecipeCrafterProvider {

	public RecipeCrafter crafter;

	public TileIndustrialBlastFurnace() {
		super(EnumPowerTier.MEDIUM, 1000, 0, 1, "TileIndustrialBlastFurnace", 4, 64);

		int[] inputs = new int[2];
		inputs[0] = 0;
		inputs[1] = 1;
		int[] outputs = new int[2];
		outputs[0] = 2;
		outputs[1] = 3;
		crafter = new RecipeCrafter(Reference.blastFurnaceRecipe, this, 2, 2, getInventory(), inputs, outputs);
	}

	@Override
	public void machineTick() {
		if (!this.crafter.machineTick())
			return;

		super.machineTick();
	}

	@Override
	public void machineFinish() {
		this.crafter.machineFinish();
	}

	@Override
	public void updateInventory() {
		this.crafter.updateInventory();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.blastFurnace, 1);
	}

	public int getHeat() {
		for (EnumFacing direction : EnumFacing.values()) {
			TileEntity tileEntity = worldObj.getTileEntity(new BlockPos(getPos().getX() + direction.getFrontOffsetX(),
				getPos().getY() + direction.getFrontOffsetY(), getPos().getZ() + direction.getFrontOffsetZ()));
			if (tileEntity instanceof TileMachineCasing) {
				if (((TileMachineCasing) tileEntity).isConnected()
					&& ((TileMachineCasing) tileEntity).getMultiblockController().isAssembled()) {
					MultiBlockCasing casing = ((TileMachineCasing) tileEntity).getMultiblockController();
					Location location = new Location(getPos().getX(), getPos().getY(), getPos().getZ(), direction);
					location.modifyPositionFromSide(direction, 1);
					int heat = 0;
					if (worldObj.getBlockState(new BlockPos(location.getX(), location.getY() - 1, location.getZ()))
						.getBlock() == tileEntity.getBlockType()) {
						return 0;
					}

					for (IMultiblockPart part : casing.connectedParts) {
						BlockMachineCasing casing1 = (BlockMachineCasing) worldObj.getBlockState(part.getPos())
							.getBlock();
						heat += casing1
							.getHeatFromState(part.getWorld().getBlockState(part.getWorldLocation().toBlockPos()));
						// TODO meta fix
					}

					if (worldObj.getBlockState(new BlockPos(location.getX(), location.getY(), location.getZ()))
						.getBlock().getUnlocalizedName().equals("tile.lava")
						&& worldObj
						.getBlockState(new BlockPos(location.getX(), location.getY() + 1, location.getZ()))
						.getBlock().getUnlocalizedName().equals("tile.lava")) {
						heat += 500;
					}
					return heat;
				}
			}
		}
		return 0;
	}

	// ISidedInventory
	//	@Override
	//	public int[] getSlotsForFace(EnumFacing side)
	//	{
	//		return side == EnumFacing.DOWN ? new int[] { 0, 1, 2, 3 } : new int[] { 0, 1, 2, 3 };
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
	//		return slotIndex == 2 || slotIndex == 3;
	//	}

	public int getProgressScaled(int scale) {
		if (crafter.currentTickTime != 0) {
			return crafter.currentTickTime * scale / crafter.currentNeededTicks;
		}
		return 0;
	}

	@Override
	public boolean canCraft(TileEntity tile, BlastFurnaceRecipe recipe) {
		if (tile instanceof TileIndustrialBlastFurnace) {
			TileIndustrialBlastFurnace blastFurnace = (TileIndustrialBlastFurnace) tile;
			return blastFurnace.getHeat() >= recipe.neededHeat;
		}
		return false;
	}

	@Override
	public boolean onCraft(TileEntity tile, BlastFurnaceRecipe recipe) {
		return true;
	}

	@Override
	public RecipeCrafter getRecipeCrafter() {
		return crafter;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerIndustrialBlastFurnace.class, this);
	}

	public int getFacingInt()
	{
		Block block = worldObj.getBlockState(pos).getBlock();
		if (block instanceof BlockBlastFurnace)
		{
			return ((BlockBlastFurnace) block).getFacing(worldObj.getBlockState(pos)).getIndex();
		}
		return 0;
	}
}
