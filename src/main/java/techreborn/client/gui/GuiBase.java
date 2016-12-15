package techreborn.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techreborn.client.container.ContainerBase;

/**
 * Created by Prospector
 */
public class GuiBase extends GuiContainer {
	public String name;
	public TileEntity tile;
	public ContainerBase container;
	public TRBuilder builder = new TRBuilder();
	public int xSize = 176;
	public int ySize = 176;

	public GuiBase(EntityPlayer player, TileEntity tile, ContainerBase container, String name) {
		super(container);
		this.container = container;
		this.name = name;
		this.tile = tile;
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
		builder.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 93, true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		drawTitle();
	}

	protected void drawTitle() {
		mc.fontRendererObj.drawString(I18n.translateToLocal("tile." + name + ".name"), (xSize / 2 - mc.fontRendererObj.getStringWidth(I18n.translateToLocal("tile." + name + ".name")) / 2), 6, 4210752);
		GlStateManager.color(1, 1, 1, 1);
	}
}