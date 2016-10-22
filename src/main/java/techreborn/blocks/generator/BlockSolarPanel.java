package techreborn.blocks.generator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.common.blocks.BlockMachineBase;
import techreborn.client.TechRebornCreativeTab;
import techreborn.tiles.generator.TileSolarPanel;

/**
 * Created by modmuss50 on 25/02/2016.
 */
public class BlockSolarPanel extends BlockMachineBase {

	public BlockSolarPanel() {
		super();
		setUnlocalizedName("techreborn.solarpanel");
		setCreativeTab(TechRebornCreativeTab.instance);
		setHardness(2.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileSolarPanel();
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock) {
		super.neighborChanged(state, worldIn, pos, neighborBlock);

		if(!worldIn.isRemote) {
			if (worldIn.canBlockSeeSky(pos.up()) && !worldIn.isRaining() && !worldIn.isThundering() && worldIn.isDaytime()) {
				setActive(true, worldIn, pos);

			} else {
				setActive(false, worldIn, pos);
			}
		}
	}


	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);

		if(!worldIn.isRemote) {
			if (worldIn.canBlockSeeSky(pos.up()) && !worldIn.isRaining() && !worldIn.isThundering() && worldIn.isDaytime()) {
				setActive(true, worldIn, pos);

			} else {
				setActive(false, worldIn, pos);
			}
		}
	}

}
