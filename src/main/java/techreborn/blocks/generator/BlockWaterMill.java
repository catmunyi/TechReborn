package techreborn.blocks.generator;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import reborncore.common.blocks.BlockMachineBase;
import techreborn.client.TechRebornCreativeTab;
import techreborn.tiles.energy.generator.TileWaterMill;

/**
 * Created by modmuss50 on 25/02/2016.
 */
public class BlockWaterMill extends BlockMachineBase {

	public BlockWaterMill() {
		super();
		setUnlocalizedName("techreborn.watermill");
		setCreativeTab(TechRebornCreativeTab.instance);
		setHardness(2.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileWaterMill();
	}

}