package com.jojoreference.allomancy.blocks.machines;

import com.jojoreference.allomancy.Allomancy;
import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.metal.RecipeManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class MelterScreen extends ContainerScreen<MelterContainer> {

    public static final int WIDTH = 175;
    public static final int HEIGHT = 165;

    private ResourceLocation GUI = new ResourceLocation(Allomancy.MODID, "textures/gui/melter_gui.png");

    public MelterScreen(MelterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, ModBlocks.MELTER.getNameTextComponent());
        this.xSize = WIDTH;
        this.ySize = HEIGHT;

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, (float)(this.ySize -96 + 2), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize)/2;
        int relY = (this.height - this.ySize)/2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
