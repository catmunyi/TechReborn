package techreborn.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import techreborn.client.container.energy.tier0.ContainerIronAlloyFurnace;
import techreborn.tiles.energy.tier0.TileIronAlloyFurnace;


public class GuiAlloyFurnace extends GuiTechReborn {
    TileIronAlloyFurnace alloyfurnace;
    ContainerIronAlloyFurnace containerAlloyFurnace;

    public GuiAlloyFurnace(EntityPlayer player, TileIronAlloyFurnace tileAlloyFurnace) {
        super(new ContainerIronAlloyFurnace(tileAlloyFurnace, player));
        this.xSize = 176;
        this.ySize = 167;
        this.alloyfurnace = tileAlloyFurnace;
        this.containerAlloyFurnace = (ContainerAlloyFurnace) this.inventorySlots;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawBasicMachine(partialTicks, mouseX, mouseY);
        builder.drawSlot(this, guiLeft + 46, guiTop + 16);
        builder.drawSlot(this, guiLeft + 64, guiTop + 16);
        builder.drawOutputSlot(this, guiLeft + 112, guiTop + 30);
        builder.drawSlot(this, guiLeft + 55, guiTop + 52);

        builder.drawProgressBar(this, alloyfurnace.getCookProgressScaled(24), guiLeft + 84, guiTop + 34);
        builder.drawBurnBar(this, alloyfurnace.getBurnTimeRemainingScaled(13), guiLeft + 57, guiTop + 34);
    }
}
