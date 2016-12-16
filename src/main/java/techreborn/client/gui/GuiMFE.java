package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerMFE;
import techreborn.tiles.storage.TileMFE;

/**
 * Created by Rushmead
 */
public class GuiMFE extends GuiStorageUnitBase {

	public TileMFE tile;
	public EntityPlayer player;

	public GuiMFE(EntityPlayer player, TileMFE tile) {
		super(player, tile, new ContainerMFE(tile, player), "techreborn.mfe");
		this.tile = tile;
		this.player = player;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
		drawSpriteSlotBackground(61, 46, SlotSprite.ARROW_RIGHT_TOP_RIGHT);
		drawSpriteSlotBackground(97, 46, SlotSprite.ARROW_RIGHT_TOP_LEFT);
		drawSpriteSlotBackground(7, 17, SlotSprite.ARMOUR_HELMET);
		drawSpriteSlotBackground(7, 35, SlotSprite.ARMOUR_CHESTPLATE);
		drawSpriteSlotBackground(7, 53, SlotSprite.ARMOUR_LEGGINGS);
		drawSpriteSlotBackground(7, 71, SlotSprite.ARMOUR_BOOTS);
	}
}