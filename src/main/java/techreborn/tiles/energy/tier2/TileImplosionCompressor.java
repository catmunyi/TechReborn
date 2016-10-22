package techreborn.tiles.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.misc.Location;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.tile.TileMachineInventory;
import techreborn.api.Reference;
import techreborn.blocks.BlockMachineCasing;
import techreborn.client.container.energy.tier2.ContainerImplosionCompressor;
import techreborn.init.ModBlocks;
import techreborn.tiles.TileMachineCasing;

public class TileImplosionCompressor extends TileMachineInventory implements IRecipeCrafterProvider {

	public RecipeCrafter crafter;

	public TileImplosionCompressor() {
		super(EnumPowerTier.MEDIUM, 100000, 0, 1, "TileImplosionCompressor", 4, 64);

		// Input slots
		int[] inputs = new int[2];
		inputs[0] = 0;
		inputs[1] = 1;
		int[] outputs = new int[2];
		outputs[0] = 2;
		outputs[1] = 3;
		crafter = new RecipeCrafter(Reference.implosionCompressorRecipe, this, 2, 2, getInventory(), inputs, outputs);
	}

	@Override
	public boolean canWork() {
		return super.canWork() && getMultiBlock();
	}



	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.implosionCompressor, 1);
	}

	public boolean getMultiBlock() {
		for (EnumFacing direction : EnumFacing.values()) {
			TileEntity tileEntity = worldObj.getTileEntity(new BlockPos(getPos().getX() + direction.getFrontOffsetX(),
				getPos().getY() + direction.getFrontOffsetY(), getPos().getZ() + direction.getFrontOffsetZ()));
			if (tileEntity instanceof TileMachineCasing) {
				if (!((TileMachineCasing) tileEntity).isConnected()) {
					return false;
				}
				if ((tileEntity.getBlockType() instanceof BlockMachineCasing)) {
					int heat;
					BlockMachineCasing machineCasing = (BlockMachineCasing) tileEntity.getBlockType();
					heat = machineCasing.getHeatFromState(tileEntity.getWorld().getBlockState(tileEntity.getPos()));
					Location location = new Location(getPos().getX(), getPos().getY(), getPos().getZ(), direction);
					location.modifyPositionFromSide(direction, 1);
					if (worldObj.getBlockState(new BlockPos(location.getX(), location.getY(), location.getZ()))
						.getBlock().getUnlocalizedName().equals("tile.lava")) {
						heat += 500;
					}
					return true;
				}
			}
		}
		return false;
	}

	// @Override
	// public void addWailaInfo(List<String> info)
	// {
	// super.addWailaInfo(info);
	// info.add("Power Stored " + energy.getEnergyStored() +" EU");
	// if(crafter.currentRecipe !=null){
	// info.add("Power Usage " + crafter.currentRecipe.euPerTick() + " EU/t");
	// }
	// }

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
	public RecipeCrafter getRecipeCrafter() {
		return crafter;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerImplosionCompressor.class, this);
	}
}
