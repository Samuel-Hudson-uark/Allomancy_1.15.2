package com.jojoreference.allomancy.blocks.machines;

import com.jojoreference.allomancy.Allomancy;
import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.metal.RecipeManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class AlloyMixerScreen extends ContainerScreen<AlloyMixerContainer> {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 202;

    public static RecipeManager manager = new RecipeManager();

    private ResourceLocation GUI = new ResourceLocation(Allomancy.MODID, "textures/gui/alloymixer_gui.png");
    private ResourceLocation GUIBottle = new ResourceLocation(Allomancy.MODID, "textures/gui/bottle_gui.png");

    public AlloyMixerScreen(AlloyMixerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, ModBlocks.ALLOYMIXER.getNameTextComponent());
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
        //Render the bottles of dust. 8 should be the number of bottles.
        for(int i = 0; i < 8; i++) {
            int metalAmount = container.getMetalAmount(i);
            int maxMetal = container.getMaxMetal(i);
            float ratio = (float)metalAmount/(float)maxMetal;
            String name = container.getMetalName(i);
            int spritePosition = ((!name.equals("None"))&& RecipeManager.DUSTS.containsKey(name)) ? RecipeManager.DUSTS.get(name) : 0;
            if(metalAmount > 0) {
                //drawString(Minecraft.getInstance().fontRenderer, container.getMetalName(i) + ": " + metalAmount, 10, 90, 0xffffff);
                drawString(Minecraft.getInstance().fontRenderer, String.valueOf(spritePosition), 10, 90, 0xffffff);
            }
            this.minecraft.getTextureManager().bindTexture(GUI);
            GuiUtils.drawTexturedModalRect(21 + 18*i, (int)(49-(32*ratio)), 1+(14*spritePosition), 206, 12, (int)(32*ratio), 0);
        }
        this.font.drawString(String.format("%1$4s", "10%"), 30, 91, 4210752);
        this.font.drawString("90%", 127, 91, 4210752);
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
