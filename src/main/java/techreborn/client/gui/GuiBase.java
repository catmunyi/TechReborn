package techreborn.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerGenerator;
import techreborn.tiles.generator.TileGenerator;

/**
 * Created by Prospector
 */
public class GuiBase extends GuiContainer {
	public String name;
	public TileGenerator tile;
	public ContainerGenerator container;
	public TRBuilder builder = new TRBuilder();

	public GuiBase(EntityPlayer player, TileGenerator tile, ContainerGenerator container) {
		super(container);
		this.container = container;
		this.name = "techreborn.generator";
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
		builder.drawChargeSlot(this, guiLeft + 7, guiTop + 59, TRBuilder.Arrow.UP_TOP_RIGHT);
	}

	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		builder.drawMultiEnergyBar(this, 9, 7, (int) container.energy, (int) tile.getMaxPower(), mouseX - guiLeft, mouseY - guiTop);
	}
}