package techreborn.client.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import reborncore.common.container.RebornContainer;
import reborncore.common.powerSystem.TileEnergyBase;

/**
 * Created by Lordmau5 on 11.06.2016.
 */
public abstract class ContainerBase extends RebornContainer {

	protected TileEntity tileEntity;
	protected EntityPlayer player;

	private int lastSlotIndex;

	public ContainerBase(TileEntity tileEntity, EntityPlayer player) {
		super();

		this.tileEntity = tileEntity;
		this.player = player;

        /* Add the player inventory */
        if(player != null){
	        addPlayersHotbar(player);
	        addPlayersInventory(player);
        }
	}

	protected int getNextSlotIndex() {
		this.lastSlotIndex += 1;
		return this.lastSlotIndex - 1;
	}

	public int getEnergy() {
		if (tileEntity instanceof TileEnergyBase) {
			return (int) ((TileEnergyBase) tileEntity).getEnergy();
		}

		return 0;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
