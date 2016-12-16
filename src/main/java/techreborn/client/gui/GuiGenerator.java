package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerGenerator;
import techreborn.tiles.generator.TileGenerator;

/**
 * Created by Prospector
 */
public class GuiGenerator extends GuiMachineBase {
	public ContainerGenerator container;

	public GuiGenerator(EntityPlayer player, TileGenerator tile, ContainerGenerator container) {
		super(player, tile, container, "techreborn.generator");
		this.container = container;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
		drawSpriteSlotBackground(7, 71, SlotSprite.ARROW_DOWN_TOP_RIGHT);
		drawSlotBackground(79, 57);
		builder.drawBurnBar(this, container.getScaledBurnTime(13), container.getScaledBurnTime(100), guiLeft + 81, guiTop + 41, mouseX, mouseY);
	}
}
