package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerMachineBase;
import techreborn.tiles.TileMachineBase;

/**
 * Created by Prospector
 */
public class GuiMachineBase extends GuiBase {

	public TileMachineBase tile;
	public ContainerMachineBase container;

	public GuiMachineBase(EntityPlayer player, TileMachineBase tile, ContainerMachineBase container, String name) {
		super(player, tile, container, name);
		this.tile = tile;
		this.container = container;
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
		builder.drawUpgradeSlots(this, guiLeft, guiTop);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		builder.drawMultiEnergyBar(this, 9, 7, container.energy, (int) tile.getMaxPower(), mouseX - guiLeft, mouseY - guiTop);
	}
}