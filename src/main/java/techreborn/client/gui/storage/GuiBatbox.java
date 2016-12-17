package techreborn.client.gui.storage;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.storage.ContainerBatbox;
import techreborn.client.gui.GuiBase;
import techreborn.tiles.storage.TileBatBox;

public class GuiBatbox extends GuiStorageUnitBase {

	public TileBatBox tile;

	public GuiBatbox(EntityPlayer player, TileBatBox tile) {
		super(player, tile, new ContainerBatbox(tile, player), "techreborn.batbox");
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
		drawSpriteSlotBackground(61, 46, GuiBase.SlotSprite.ARROW_RIGHT_TOP_RIGHT);
		drawSpriteSlotBackground(97, 46, GuiBase.SlotSprite.ARROW_RIGHT_TOP_LEFT);
	}
}
