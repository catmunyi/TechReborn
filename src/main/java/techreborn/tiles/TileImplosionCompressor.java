package techreborn.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import techreborn.init.ModBlocks;
import techreborn.util.Inventory;
import ic2.api.energy.prefab.BasicSink;
import ic2.api.tile.IWrenchable;

public class TileImplosionCompressor extends TileMachineBase implements IWrenchable{
	
	public int tickTime;
	public BasicSink energy;
	public Inventory inventory = new Inventory(4, "TileImplosionCompressor", 64);
	

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side)
	{
		return false;
	}

	@Override
	public short getFacing()
	{
		return 0;
	}

	@Override
	public void setFacing(short facing)
	{
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer)
	{
		return true;
	}

	@Override
	public float getWrenchDropRate()
	{
		return 1.0F;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer)
	{
		return new ItemStack(ModBlocks.ImplosionCompressor, 1);
	}

	public boolean isComplete()
	{
		return false;
	}

}