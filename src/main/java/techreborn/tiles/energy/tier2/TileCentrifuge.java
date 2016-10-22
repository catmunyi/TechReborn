package techreborn.tiles.energy.tier2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.tile.TileMachineInventory;
import techreborn.api.Reference;
import techreborn.client.container.energy.tier2.ContainerCentrifuge;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;

public class TileCentrifuge extends TileMachineInventory implements IRecipeCrafterProvider {
	public RecipeCrafter crafter;

	public int euTick = ConfigTechReborn.CentrifugeInputTick;

	public TileCentrifuge() {
		super(EnumPowerTier.MEDIUM, 10000, 0, 1, "TileCentrifuge", 11, 64);

		// Input slots
		int[] inputs = new int[2];
		inputs[0] = 0;
		inputs[1] = 1;
		int[] outputs = new int[4];
		outputs[0] = 2;
		outputs[1] = 3;
		outputs[2] = 4;
		outputs[3] = 5;

		crafter = new RecipeCrafter(Reference.centrifugeRecipe, this, 2, 4, getInventory(), inputs, outputs);
	}

	//	@Override
	//	public void updateEntity()
	//	{
	//		super.updateEntity();
	//		crafter.updateEntity();
	//		//charge(6);
	//		if (inventory.getStackInSlot(6) != null)
	//		{
	//			ItemStack stack = inventory.getStackInSlot(6);
	//			if(stack.getItem() instanceof IEnergyItemInfo){
	//				IEnergyItemInfo item = (IEnergyItemInfo) stack.getItem();
	//				if (item.canProvideEnergy(stack))
	//				{
	//					if (getEnergy() != getMaxPower())
	//					{
	//						addEnergy(item.getMaxTransfer(stack));
	//						PoweredItem.setEnergy(PoweredItem.getEnergy(stack) - item.getMaxTransfer(stack), stack);
	//					}
	//				}
	//			}
	//		}
	//	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.centrifuge, 1);
	}

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
		return RebornContainer.getContainerFromClass(ContainerCentrifuge.class, this);
	}
}