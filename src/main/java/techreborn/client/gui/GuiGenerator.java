package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.ContainerGenerator;
import techreborn.tiles.generator.TileGenerator;

/**
 * Created by Prospector
 */
public class GuiGenerator extends GuiBase {
	public GuiGenerator(EntityPlayer player, TileGenerator tile) {
		super(player, tile, new ContainerGenerator(tile, player));
	}
}
