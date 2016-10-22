package techreborn.tiles.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.container.RebornContainer;
import reborncore.common.tile.TileMachineInventory;
import reborncore.common.util.ItemUtils;
import techreborn.api.RollingMachineRecipe;
import techreborn.client.container.energy.tier1.ContainerRollingMachine;
import techreborn.init.ModBlocks;

public class TileRollingMachine extends TileMachineInventory {

	public final InventoryCrafting craftMatrix = new InventoryCrafting(new RollingTileContainer(), 3, 3);
	public boolean isRunning;
	public int tickTime;
	public int runTime = 250;
	public ItemStack currentRecipe;

	public int euTick = 5;

	public TileRollingMachine() {
		super(EnumPowerTier.LOW, 10000, 5, 250, "TileRollingMachine", 3, 64);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		//		charge(2);
		//		if (!worldObj.isRemote)
		//		{
		currentRecipe = RollingMachineRecipe.instance.findMatchingRecipe(craftMatrix, worldObj);
		if (currentRecipe != null && canMake()) {
			if (tickTime >= runTime) {
				currentRecipe = RollingMachineRecipe.instance.findMatchingRecipe(craftMatrix, worldObj);
				if (currentRecipe != null) {
					boolean hasCrafted = false;
					if (getInventory().getStackInSlot(0) == null) {
						getInventory().setInventorySlotContents(0, currentRecipe);
						tickTime = -1;
						hasCrafted = true;
					} else {
						if (getInventory().getStackInSlot(0).stackSize + currentRecipe.stackSize <= currentRecipe
							.getMaxStackSize()) {
							ItemStack stack = getInventory().getStackInSlot(0);
							stack.stackSize = stack.stackSize + currentRecipe.stackSize;
							getInventory().setInventorySlotContents(0, stack);
							tickTime = -1;
							hasCrafted = true;
						}
					}
					if (hasCrafted) {
						for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
							craftMatrix.decrStackSize(i, 1);
						}
						currentRecipe = null;
					}
				}
			}
		}
		if (currentRecipe != null) {
			if (canUseEnergy(euTick) && tickTime < runTime) {
				useEnergy(euTick);
				tickTime++;
			}
		}
		if (currentRecipe == null) {
			tickTime = -1;
		}
		//		} else
		//		{
		//			currentRecipe = RollingMachineRecipe.instance.findMatchingRecipe(craftMatrix, worldObj);
		//			if (currentRecipe != null)
		//			{
		//				inventory.setInventorySlotContents(1, currentRecipe);
		//			} else
		//			{
		//				inventory.setInventorySlotContents(1, null);
		//			}
		//		}
	}

	@Override
	public boolean canWork() {
		return super.canWork() && this.currentRecipe != null && canMake();
	}

	private boolean canMake() {
		return RollingMachineRecipe.instance.findMatchingRecipe(this.craftMatrix, getWorld()) != null;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.rollingMachine, 1);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		ItemUtils.readInvFromNBT(this.craftMatrix, "Crafting", tagCompound);
		this.isRunning = tagCompound.getBoolean("isRunning");
		this.tickTime = tagCompound.getInteger("tickTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		ItemUtils.writeInvToNBT(this.craftMatrix, "Crafting", tagCompound);
		tagCompound.setBoolean("isRunning", isRunning);
		tagCompound.setInteger("tickTime", tickTime);
		return tagCompound;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerRollingMachine.class, this);
	}

	private static class RollingTileContainer extends Container {

		@Override
		public boolean canInteractWith(EntityPlayer entityplayer) {
			return true;
		}
	}
}
