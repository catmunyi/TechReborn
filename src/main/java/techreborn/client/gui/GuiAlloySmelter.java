package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import reborncore.common.container.RebornContainer;
import techreborn.client.container.ContainerAlloySmelter;
import techreborn.tiles.TileAlloySmelter;

public class GuiAlloySmelter extends GuiTechReborn
{
	public static final ResourceLocation texture = new ResourceLocation("techreborn",  "textures/gui/electric_alloy_furnace.png");
	TileAlloySmelter alloysmelter;

    public GuiAlloySmelter(EntityPlayer player, TileEntity tileEntity)
    {
        super(RebornContainer.createContainer(ContainerAlloySmelter.class, tileEntity, player), tileEntity, player, texture, "techreborn.alloysmelter");
        alloysmelter = (TileAlloySmelter) tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawPowerBar(alloysmelter);
        drawProgressBar(alloysmelter, 82, 35);
    }
}
