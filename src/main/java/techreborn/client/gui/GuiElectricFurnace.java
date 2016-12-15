package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerElectricFurnace;
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
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);

		//builder.drawProgressBar(this, tile.gaugeProgressScaled(), );
	}
}
