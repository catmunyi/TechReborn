package techreborn.client.gui;

import com.google.common.base.CaseFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import reborncore.client.guibuilder.GuiBuilder;
import reborncore.common.powerSystem.PowerSystem;
import techreborn.lib.ModInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
public class TRBuilder extends GuiBuilder {
	public static final ResourceLocation resourceLocation = new ResourceLocation(ModInfo.MOD_ID.toLowerCase() + ":" + "textures/gui/builder.png");

	public TRBuilder() {
		super(resourceLocation);
	}

	public void drawMultiEnergyBar(GuiScreen gui, int x, int y, int energyStored, int maxEnergyStored, int mouseX, int mouseY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);

		gui.drawTexturedModalRect(x, y, PowerSystem.getDisplayPower().xBar - 15, PowerSystem.getDisplayPower().yBar - 1, 14, 50);

		int draw = (int) ((double) energyStored / (double) maxEnergyStored * (48));
		gui.drawTexturedModalRect(x + 1, y + 49 - draw, PowerSystem.getDisplayPower().xBar, 48 + PowerSystem.getDisplayPower().yBar - draw, 12, draw);

		if (isInRect(x + 1, y + 1, 11, 48, mouseX, mouseY)) {
			List<String> list = new ArrayList<>();
			list.add(PowerSystem.getLocaliszedPowerFormattedNoSuffix(energyStored) + "/" + PowerSystem.getLocaliszedPowerFormattedNoSuffix(maxEnergyStored) + " " + PowerSystem.getDisplayPower().abbreviation);
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
			GlStateManager.disableLighting();
		}
	}

	public void drawChargeSlot(GuiScreen gui, int posX, int posY) {
		drawSlot(gui, posX, posY);
		Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
		gui.drawTexturedModalRect(posX + 1, posY + 1, 169, 1, 16, 16);
	}

	public void drawBurnBar(GuiScreen gui, int burnTime, int totalBurnTime, int x, int y) {
		gui.mc.getTextureManager().bindTexture(resourceLocation);
		gui.drawTexturedModalRect(x, y, 42, 217, 13, 13);
		int j = (int) (12 - getScaledBurnTime(12, burnTime, totalBurnTime));
		if (j > 0) {
			gui.drawTexturedModalRect(x, y + j, 28, 217 + j, 13, 13 - j);
		}
	}

	public int getScaledBurnTime(int scale, int burnTime, int totalBurnTime) {
		return (int) (((float) burnTime / (float) totalBurnTime) * scale);
	}
}
