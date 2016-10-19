package techreborn.tiles.energy.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.tile.TileMachineInventory;
import techreborn.api.Reference;
import techreborn.client.container.energy.tier1.ContainerIndustrialElectrolyzer;
import techreborn.init.ModBlocks;

public class TileIndustrialElectrolyzer extends TileMachineInventory implements IRecipeCrafterProvider {

    public RecipeCrafter crafter;

    public TileIndustrialElectrolyzer() {
        super(EnumPowerTier.MEDIUM, 1000, 0, 1, "TileIndustrialElectrolyzer", 8, 64);

        // Input slots
        int[] inputs = new int[2];
        inputs[0] = 0;
        inputs[1] = 1;
        int[] outputs = new int[4];
        outputs[0] = 2;
        outputs[1] = 3;
        outputs[2] = 4;
        outputs[3] = 5;
        crafter = new RecipeCrafter(Reference.industrialElectrolyzerRecipe, this, 2, 4, getInventory(), inputs, outputs);
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
        return new ItemStack(ModBlocks.industrialElectrolyzer, 1);
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
//		return side == EnumFacing.DOWN ? new int[] { 0, 1, 2, 3, 4, 5 } : new int[] { 0, 1, 2, 3, 4, 5 };
//	}
//
//	@Override
//	public boolean canInsertItem(int slotIndex, ItemStack itemStack, EnumFacing side)
//	{
//		if (slotIndex >= 1)
//			return false;
//		return isItemValidForSlot(slotIndex, itemStack);
//	}
//
//	@Override
//	public boolean canExtractItem(int slotIndex, ItemStack itemStack, EnumFacing side)
//	{
//		return slotIndex == 2 || slotIndex == 3 || slotIndex == 4 || slotIndex == 5;
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
        return RebornContainer.getContainerFromClass(ContainerIndustrialElectrolyzer.class, this);
    }
}
