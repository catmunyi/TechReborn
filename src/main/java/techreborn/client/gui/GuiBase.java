package techreborn.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techreborn.client.container.ContainerBase;

/**
 * Created by Prospector
 */
public class GuiBase extends GuiContainer {
	public String name;
	public TileEntity tile;
	public ContainerBase container;
	public TRBuilder builder = new TRBuilder();
	public int xSize = 176;
	public int ySize = 176;

	public GuiBase(EntityPlayer player, TileEntity tile, ContainerBase container, String name) {
		super(container);
		this.container = container;
		this.name = name;
		this.tile = tile;
	}

	protected void drawSlotForeground(int x, int y) {
		builder.drawSlot(this, x, y);
	}

	protected void drawSlotBackground(int x, int y) {
		drawSlotForeground(guiLeft + x, guiTop + y);
	}

	protected void drawSpriteSlotForeground(int x, int y, SlotSprite sprite) {
		drawSlotForeground(x, y);
		builder.drawSprite(this, x + sprite.guiX, y + sprite.guiY, sprite.x, sprite.y, sprite.width, sprite.height);
	}

	protected void drawSpriteSlotBackground(int x, int y, SlotSprite sprite) {
		drawSpriteSlotForeground(guiLeft + x, guiTop + y, sprite);
	}

	protected void drawOutputSlotForeground(int x, int y) {
		builder.drawOutputSlot(this, x, y);
	}

	protected void drawOutputSlotBackground(int x, int y) {
		drawOutputSlotForeground(guiLeft + x, guiTop + y);
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
		builder.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 93, true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		drawTitle();
	}

	protected void drawTitle() {
		mc.fontRendererObj.drawString(I18n.translateToLocal("tile." + name + ".name"), (xSize / 2 - mc.fontRendererObj.getStringWidth(I18n.translateToLocal("tile." + name + ".name")) / 2), 6, 4210752);
		GlStateManager.color(1, 1, 1, 1);
	}

	public enum SlotSprite {
		ARROW_UP_TOP_RIGHT(240, 0, 6, 5, 10, 2),
		ARROW_UP_TOP_LEFT(240, 0, 6, 5, 2, 2),
		ARROW_UP_BOTTOM_RIGHT(240, 0, 6, 5, 10, 11),
		ARROW_UP_BOTTOM_LEFT(240, 0, 6, 5, 2, 11),

		ARROW_DOWN_TOP_RIGHT(247, 0, 6, 5, 10, 2),
		ARROW_DOWN_TOP_LEFT(247, 0, 6, 5, 2, 2),
		ARROW_DOWN_BOTTOM_RIGHT(247, 0, 6, 5, 10, 11),
		ARROW_DOWN_BOTTOM_LEFT(247, 0, 6, 5, 2, 11),

		ARROW_RIGHT_TOP_RIGHT(240, 6, 5, 6, 11, 2),
		ARROW_RIGHT_TOP_LEFT(240, 6, 5, 6, 2, 2),
		ARROW_RIGHT_BOTTOM_RIGHT(240, 6, 5, 6, 11, 10),
		ARROW_RIGHT_BOTTOM_LEFT(240, 6, 5, 6, 2, 10),

		ARROW_LEFT_TOP_RIGHT(247, 6, 5, 6, 11, 2),
		ARROW_LEFT_TOP_LEFT(247, 6, 5, 6, 2, 2),
		ARROW_LEFT_BOTTOM_RIGHT(247, 6, 5, 6, 11, 10),
		ARROW_LEFT_BOTTOM_LEFT(247, 6, 5, 6, 2, 10),

		ARMOUR_HELMET(190, 118, 16, 16),
		ARMOUR_CHESTPLATE(206, 118, 16, 16),
		ARMOUR_LEGGINGS(190, 134, 16, 16),
		ARMOUR_BOOTS(206, 134, 16, 16);

		public int x;
		public int y;
		public int width;
		public int height;
		public int guiX;
		public int guiY;

		SlotSprite(int x, int y, int width, int height, int guiX, int guiY) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.guiX = guiX;
			this.guiY = guiY;
		}

		SlotSprite(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			if (width == 16 && height == 16) {
				this.guiX = 1;
				this.guiY = 1;
			} else {
				this.guiX = 0;
				this.guiY = 0;
			}
		}
	}
}