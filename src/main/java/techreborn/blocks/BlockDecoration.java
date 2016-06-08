package techreborn.blocks;

import me.modmuss50.jsonDestroyer.api.ITexturedBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.common.BaseBlock;
import techreborn.client.TechRebornCreativeTabMisc;
import techreborn.init.ModBlocks;

import java.util.List;
import java.util.Random;

public class BlockDecoration extends BaseBlock implements ITexturedBlock
{

	public static final String[] types = new String[] { "cautionBlock", "test" };
	public PropertyInteger METADATA;

	public BlockDecoration(Material material)
	{
		super(material);
		setUnlocalizedName("techreborn.decoration");
		setCreativeTab(TechRebornCreativeTabMisc.instance);
		setHardness(2f);
		this.setDefaultState(this.getDefaultState().withProperty(METADATA, 0));
	}

	public static ItemStack getStorageBlockByName(String name, int count)
	{
		for (int i = 0; i < types.length; i++)
		{
			if (types[i].equals(name))
			{
				return new ItemStack(ModBlocks.storage, count, i);
			}
		}
		return BlockStorage2.getStorageBlockByName(name, count);
	}

	public static ItemStack getStorageBlockByName(String name)
	{
		return getStorageBlockByName(name, 1);
	}

	@Override public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override @SideOnly(Side.CLIENT) public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int meta = 0; meta < types.length; meta++)
		{
			list.add(new ItemStack(item, 1, meta));
		}
	}

	@Override public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}

	@Override public String getTextureNameFromState(IBlockState BlockStateContainer, EnumFacing facing)
	{
		if (types[getMetaFromState(BlockStateContainer)].equals("cautionBlock") && (facing.equals(EnumFacing.UP)
				|| facing.equals(EnumFacing.DOWN))){
			return "techreborn:blocks/decoration/cautionBlockCaps";
		}
			return "techreborn:blocks/decoration/" + types[getMetaFromState(BlockStateContainer)];
	}

	@Override public int amountOfStates()
	{
		return types.length;
	}

	@Override public IBlockState getStateFromMeta(int meta)
	{
		if (meta > types.length)
		{
			meta = 0;
		}
		return this.getDefaultState().withProperty(METADATA, meta);
	}

	@Override public int getMetaFromState(IBlockState state)
	{
		return state.getValue(METADATA);
	}

	protected BlockStateContainer createBlockState()
	{

		METADATA = PropertyInteger.create("type", 0, types.length - 1);
		return new BlockStateContainer(this, METADATA);
	}

}
