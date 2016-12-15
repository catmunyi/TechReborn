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
		builder.drawChargeSlot(this, guiLeft + 61, guiTop + 46, TRBuilder.Arrow.RIGHT_TOP_RIGHT);
		builder.drawChargeSlot(this, guiLeft + 97, guiTop + 46, TRBuilder.Arrow.RIGHT_TOP_LEFT);
		builder.drawArmourSlot(this, guiLeft + 7, guiTop + 17, TRBuilder.ArmourSlot.HEAD, player);
		builder.drawArmourSlot(this, guiLeft + 7, guiTop + 35, TRBuilder.ArmourSlot.CHESTPLATE, player);
		builder.drawArmourSlot(this, guiLeft + 7, guiTop + 53, TRBuilder.ArmourSlot.LEGGINGS, player);
		builder.drawArmourSlot(this, guiLeft + 7, guiTop + 71, TRBuilder.ArmourSlot.BOOTS, player);
	}
}