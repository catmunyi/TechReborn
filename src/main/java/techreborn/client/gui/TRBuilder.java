package techreborn.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import reborncore.client.guibuilder.GuiBuilder;
import reborncore.common.powerSystem.PowerSystem;
import techreborn.client.StackToolTipEvent;
import techreborn.config.ConfigTechReborn;
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
		if (energyStored > maxEnergyStored) {
			draw = (int) ((double) maxEnergyStored / (double) maxEnergyStored * (48));
		}
		gui.drawTexturedModalRect(x + 1, y + 49 - draw, PowerSystem.getDisplayPower().xBar, 48 + PowerSystem.getDisplayPower().yBar - draw, 12, draw);
		int percentage = StackToolTipEvent.percentage(
			maxEnergyStored,
			energyStored);
		if (isInRect(x + 1, y + 1, 11, 48, mouseX, mouseY)) {
			GlStateManager.disableLighting();
			GlStateManager.disableDepth();
			GlStateManager.colorMask(true, true, true, false);
			GuiUtils.drawGradientRect(0, x + 1, y + 1, x + 13, y + 49, 0x80FFFFFF, 0x80FFFFFF);
			GlStateManager.colorMask(true, true, true, true);
			GlStateManager.enableDepth();

			List<String> list = new ArrayList<>();
			//cause screw you insomnia
			TextFormatting powerColour = TextFormatting.GOLD;
			if (ConfigTechReborn.INSOMNIA_COLOUR) {
				powerColour = TextFormatting.WHITE;
			}
			list.add(powerColour + PowerSystem.getLocaliszedPowerFormattedNoSuffix(energyStored) + "/" + PowerSystem.getLocaliszedPowerFormattedNoSuffix(maxEnergyStored) + " " + PowerSystem.getDisplayPower().abbreviation);
			if (gui.isShiftKeyDown()) {
				list.add(getPercentageColour(percentage) + "" + percentage + "%" + TextFormatting.GRAY + " Charged");
			}
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawSprite(GuiScreen gui, int x, int y, int spriteX, int spriteY, int width, int height) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
		gui.drawTexturedModalRect(x, y, spriteX, spriteY, width, height);
	}

	public void drawArmourSlot(GuiScreen gui, int posX, int posY, ArmourSlot slot, EntityPlayer player) {
		boolean overlay = false;
		List<ItemStack> stacks = new ArrayList<>();
		for (ItemStack stack : player.getArmorInventoryList()) {
			stacks.add(stack);
		}
		if (slot.equals(ArmourSlot.HEAD) && stacks.get(3).isEmpty())
			overlay = true;
		if (slot == ArmourSlot.CHESTPLATE && stacks.get(2).isEmpty())
			overlay = true;
		if (slot == ArmourSlot.LEGGINGS && stacks.get(1).isEmpty())
			overlay = true;
		if (slot == ArmourSlot.BOOTS && stacks.get(0).isEmpty())
			overlay = true;
		drawSlot(gui, posX, posY);
		if (overlay) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
			gui.drawTexturedModalRect(posX + 1, posY + 1, slot.x, slot.y, 16, 16);
		}
	}

	public void drawUpgradeSlots(GuiScreen gui, int guiX, int guiY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
		drawSlot(gui, guiX + 151, guiY + 17);
		drawSlot(gui, guiX + 151, guiY + 35);
		drawSlot(gui, guiX + 151, guiY + 53);
		drawSlot(gui, guiX + 151, guiY + 71);
		gui.drawTexturedModalRect(guiX + 152, guiY + 18, 228, 18, 16, 16);
		gui.drawTexturedModalRect(guiX + 152, guiY + 36, 228, 18, 16, 16);
		gui.drawTexturedModalRect(guiX + 152, guiY + 54, 228, 18, 16, 16);
		gui.drawTexturedModalRect(guiX + 152, guiY + 72, 228, 18, 16, 16);
	}

	@Override
	public void drawOutputSlot(GuiScreen gui, int x, int y) {
		gui.mc.getTextureManager().bindTexture(resourceLocation);
		gui.drawTexturedModalRect(x, y, 150, 18, 26, 26);
	}

	public TextFormatting getPercentageColour(int percentage) {
		if (percentage <= 10) {
			return TextFormatting.RED;
		} else if (percentage >= 75) {
			return TextFormatting.GREEN;
		} else {
			return TextFormatting.YELLOW;
		}
	}

	public void drawProgressBar(GuiScreen gui, double progress, double progress100, int x, int y, int mouseX, int mouseY, ProgressDirection direction) {
		gui.mc.getTextureManager().bindTexture(resourceLocation);
		gui.drawTexturedModalRect(x, y, direction.x, direction.y, direction.width, direction.height);
		if (direction.equals(ProgressDirection.RIGHT) || direction.equals(ProgressDirection.LEFT)) {
			int j = 16 - (int) (progress);
			if (j > 0) {
				gui.drawTexturedModalRect(x, y, direction.x + 16, direction.y, direction.width - j, direction.height);
			}
		}
		if (isInRect(x, y, direction.width, direction.height, mouseX, mouseY)) {
			int percentage = StackToolTipEvent.percentage(
				100,
				(int) progress100);
			List<String> list = new ArrayList<String>();
			list.add(getPercentageColour(percentage) + "" + percentage + "%");
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawBurnBar(GuiScreen gui, double progress, double progress100, int x, int y, int mouseX, int mouseY) {
		gui.mc.getTextureManager().bindTexture(resourceLocation);
		gui.drawTexturedModalRect(x, y, 187, 84, 13, 13);
		int j = 13 - (int) (progress);
		if (j > 0) {
			gui.drawTexturedModalRect(x, y + j, 187, 70 + j, 13, 13 - j);

		}
		if (isInRect(x, y, 12, 12, mouseX, mouseY)) {
			int percentage = StackToolTipEvent.percentage(
				100,
				(int) progress100);
			List<String> list = new ArrayList<String>();
			list.add(getPercentageColour(percentage) + "" + percentage + "%");
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
		}
	}

	public int getScaledBurnTime(int scale, int burnTime, int totalBurnTime) {
		return (int) (((float) burnTime / (float) totalBurnTime) * scale);
	}

	public enum ArmourSlot {
		HEAD(190, 118), CHESTPLATE(190, 134), LEGGINGS(206, 118), BOOTS(206, 134);
		public int x;
		public int y;

		ArmourSlot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public enum ProgressDirection {
		UP(84, 171, 94, 171, 10, 16), DOWN(104, 171, 114, 171, 10, 16), RIGHT(84, 151, 100, 151, 16, 10), LEFT(84, 161, 100, 161, 16, 10);
		public int x;
		public int y;
		public int xActive;
		public int yActive;
		public int width;
		public int height;

		ProgressDirection(int x, int y, int xActive, int yActive, int width, int height) {
			this.x = x;
			this.y = y;
			this.xActive = xActive;
			this.yActive = yActive;
			this.width = width;
			this.height = height;
		}
	}
}
