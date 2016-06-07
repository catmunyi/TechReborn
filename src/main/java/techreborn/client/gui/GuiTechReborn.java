package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import reborncore.client.gui.BaseGui;
import reborncore.common.powerSystem.TilePowerAcceptor;

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
		j = tile.getEnergyScaled(5); //alloysmelter.getProgressScaled(24);
		if (j > 0)
		{
            this.mc.getTextureManager().bindTexture(overlays);
			this.drawTexturedModalRect(k + x, l + y, 40, 2, j + 1, 16);
//            System.out.print("" + j);
		}
    }

    public void drawPowerBar(TilePowerAcceptor tile, int x, int y)
    {
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        int j = 100;
       // j = tile.getEnergyScaled(24); //alloysmelter.getProgressScaled(24);
        if (j > 0)
        {
            this.mc.getTextureManager().bindTexture(overlays);
//            this.drawTexturedModalRect(k + x, l + y, 40, 2, j + 1, 16);
            this.drawTexturedModalRect(k + x, l + y + 12 - j, 0, 20 - j, 14, j + 2);
//            System.out.print("" + j);
        }
    }
}
