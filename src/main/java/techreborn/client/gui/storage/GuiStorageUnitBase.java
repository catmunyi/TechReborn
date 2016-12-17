package techreborn.client.gui.storage;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerMachineBase;
import techreborn.client.gui.GuiMachineBase;
import techreborn.tiles.TileStorageUnitBase;

/**
 * Created by Prospector
 */
public class GuiStorageUnitBase extends GuiMachineBase {

	public GuiStorageUnitBase(EntityPlayer player, TileStorageUnitBase tile, ContainerMachineBase container, String name) {
		super(player, tile, container, name);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		drawTitle();
		builder.drawMultiEnergyBar(this, 81, 30, container.getEnergy(), (int) tile.getMaxPower(), mouseX - guiLeft, mouseY - guiTop);
	}
}
