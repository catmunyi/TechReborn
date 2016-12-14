package techreborn.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
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

	public GuiBase(EntityPlayer player, TileEntity tile, ContainerBase container, String name) {
		super(container);
		this.container = container;
		this.name = name;
		this.tile = tile;
		this.xSize = 176;
		this.ySize = 162;
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
		builder.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 79, true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		drawTitle();
	}

	protected void drawTitle() {
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, I18n.translateToLocal("tile." + name + ".name"), xSize / 2, -9, 0xFFFFFFFF);
	}
}