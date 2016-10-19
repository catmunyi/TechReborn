package techreborn.client.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.common.powerSystem.TileEnergyBase;
import reborncore.common.recipes.RecipeCrafter;

public abstract class ContainerCrafting extends ContainerBase {

    private RecipeCrafter crafter;

    private int currentTickTime = 0;
    private int currentNeededTicks = 0;

    public ContainerCrafting(TileEnergyBase tileEntity, EntityPlayer player) {
        super(tileEntity, player);

        if (tileEntity instanceof IRecipeCrafterProvider) {
            this.crafter = ((IRecipeCrafterProvider) tileEntity).getRecipeCrafter();
        }
    }

    public void setCrafter(RecipeCrafter crafter) {
        this.crafter = crafter;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener IContainerListener = this.listeners.get(i);
            if (this.currentTickTime != this.crafter.currentTickTime || this.crafter.currentTickTime == -1) {
                IContainerListener.sendProgressBarUpdate(this, 0, this.crafter.currentTickTime);
            }
            if (this.currentNeededTicks != this.crafter.currentNeededTicks) {
                IContainerListener.sendProgressBarUpdate(this, 1, this.crafter.currentNeededTicks);
            }
        }
    }

    @Override
    public void addListener(IContainerListener crafting) {
        super.addListener(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.crafter.currentTickTime);
        crafting.sendProgressBarUpdate(this, 1, this.crafter.currentNeededTicks);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.currentTickTime = value;
            if (this.currentTickTime == -1) {
                this.currentTickTime = 0;
            }
        } else if (id == 1) {
            this.currentNeededTicks = value;
        }
        this.crafter.currentTickTime = this.currentTickTime;
        this.crafter.currentNeededTicks = this.currentNeededTicks;
    }
}
