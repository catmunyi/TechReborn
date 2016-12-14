package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerMFE;
import techreborn.tiles.storage.TileMFE;

/**
 * Created by Rushmead
 */
public class GuiMFE extends GuiStorageUnitBase {

	public TileMFE tile;

	public GuiMFE(EntityPlayer player, TileMFE tile) {
		super(player, tile, new ContainerMFE(tile, player), "techreborn.mfe");
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
		builder.drawChargeSlot(this, guiLeft + 61, guiTop + 32, TRBuilder.Arrow.RIGHT_TOP_RIGHT);
		builder.drawChargeSlot(this, guiLeft + 97, guiTop + 32, TRBuilder.Arrow.RIGHT_TOP_LEFT);
		builder.drawArmourSlot(this, guiLeft + 7, guiTop + 5, TRBuilder.ArmourSlot.HEAD);
		builder.drawArmourSlot(this, guiLeft + 7, guiTop + 23, TRBuilder.ArmourSlot.CHESTPLATE);
		builder.drawArmourSlot(this, guiLeft + 7, guiTop + 41, TRBuilder.ArmourSlot.LEGGINGS);
		builder.drawArmourSlot(this, guiLeft + 7, guiTop + 59, TRBuilder.ArmourSlot.BOOTS);
	}
}