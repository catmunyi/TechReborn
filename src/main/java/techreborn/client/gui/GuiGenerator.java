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
		builder.drawChargeSlot(this, guiLeft + 7, guiTop + 59, TRBuilder.Arrow.DOWN_TOP_RIGHT);
		builder.drawSlot(this, guiLeft + 79, guiTop + 42);
		builder.drawBurnBar(this, container.getScaledBurnTime(13), guiLeft + 81, guiTop + 27, mouseX, mouseY);
	}
}
