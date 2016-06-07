package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import reborncore.api.power.IEnergyInterfaceTile;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.client.gui.BaseGui;
import reborncore.common.RebornCoreConfig;
import reborncore.common.powerSystem.TilePowerAcceptor;
import reborncore.common.recipes.RecipeCrafter;
import techreborn.config.ConfigTechReborn;

/**
 * Created by Gigabit101 on 07/06/2016.
 */
public class GuiTechReborn extends BaseGui
{
    public static final ResourceLocation overlays = new ResourceLocation("techreborn",  "textures/gui/guiSheet.png");

    public GuiTechReborn(Container container, TileEntity tileEntity, EntityPlayer player, ResourceLocation guitexture, String name)
    {
        super(container, tileEntity, player, overlays, guitexture, name);
    }

    public void drawProgressBar(TilePowerAcceptor tile, int x, int y)
    {
        int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

        int j = 0;
		j = getProgressScaled(24, tile);
		if (j > 0)
		{
            this.mc.getTextureManager().bindTexture(overlays);
			this.drawTexturedModalRect(k + x, l + y, 40, 2, j + 1, 16);
		}
    }

    public void drawPowerBar(TilePowerAcceptor tile, int x, int y)
    {
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        int j = 0;
        j = tile.getEnergyScaled(46);
        if (j > 0)
        {
            int red = 0;
            int orange = 13;
            int green = 26;

            this.mc.getTextureManager().bindTexture(overlays);
            this.drawTexturedModalRect(k + x, l + y + 12 - j, green, 48 - j, 14, j + 2);
        }
    }

    public int getProgressScaled(int scale, TilePowerAcceptor tile)
    {
        RecipeCrafter crafter = ((IRecipeCrafterProvider) tile).getRecipeCrafter();
        if (crafter.currentTickTime != 0 && crafter.currentNeededTicks != 0)
        {
            return crafter.currentTickTime * scale / crafter.currentNeededTicks;
        }
        return 0;
    }

    //TODO
    public int getColour()
    {
        return 0;
    }
}
