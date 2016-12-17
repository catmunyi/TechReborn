package techreborn.client.gui.tier1;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.tier1.ContainerElectricFurnace;
import techreborn.client.gui.GuiMachineBase;
import techreborn.client.gui.TRBuilder;
import techreborn.tiles.teir1.TileElectricFurnace;

public class GuiElectricFurnace extends GuiMachineBase {
	public ContainerElectricFurnace container;
	public TileElectricFurnace tile;

	public GuiElectricFurnace(EntityPlayer player, TileElectricFurnace tile, ContainerElectricFurnace container) {
		super(player, tile, container, "techreborn.electricfurnace");
		this.tile = tile;
		this.container = container;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
		drawSpriteSlotBackground(7, 71, SlotSprite.ARROW_DOWN_TOP_RIGHT);
		drawSlotBackground(54, 44);
		drawOutputSlotBackground(96, 40);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		builder.drawProgressBar(this, container.getScaledProgress(16), container.getScaledProgress(100), 76, 48, mouseX - guiLeft, mouseY - guiTop, TRBuilder.ProgressDirection.RIGHT);
	}
}
