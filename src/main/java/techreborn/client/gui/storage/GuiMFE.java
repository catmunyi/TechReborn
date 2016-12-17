package techreborn.client.gui.storage;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.storage.ContainerMFE;
import techreborn.client.gui.GuiBase;
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
		drawSpriteSlotBackground(61, 46, GuiBase.SlotSprite.ARROW_RIGHT_TOP_RIGHT);
		drawSpriteSlotBackground(97, 46, GuiBase.SlotSprite.ARROW_RIGHT_TOP_LEFT);
		drawSpriteSlotBackground(7, 17, GuiBase.SlotSprite.ARMOUR_HELMET);
		drawSpriteSlotBackground(7, 35, GuiBase.SlotSprite.ARMOUR_CHESTPLATE);
		drawSpriteSlotBackground(7, 53, GuiBase.SlotSprite.ARMOUR_LEGGINGS);
		drawSpriteSlotBackground(7, 71, GuiBase.SlotSprite.ARMOUR_BOOTS);
	}
}