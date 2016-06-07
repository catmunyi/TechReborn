package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import reborncore.common.container.RebornContainer;
import techreborn.client.container.ContainerAssemblingMachine;
import techreborn.tiles.TileAssemblingMachine;

public class GuiAssemblingMachine extends GuiTechReborn
{
    public static final ResourceLocation texture = new ResourceLocation("techreborn", "textures/gui/assembling_machine.png");
    TileAssemblingMachine assemblingmachine;

    public GuiAssemblingMachine(EntityPlayer player, TileEntity tileEntity)
    {
        super(RebornContainer.createContainer(ContainerAssemblingMachine.class, tileEntity, player), tileEntity, player, texture, "techreborn.assemblingmachine");
        assemblingmachine = (TileAssemblingMachine) tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawPowerBar(assemblingmachine, 15, 50);
        drawProgressBar(assemblingmachine, 79, 35);
    }
}
