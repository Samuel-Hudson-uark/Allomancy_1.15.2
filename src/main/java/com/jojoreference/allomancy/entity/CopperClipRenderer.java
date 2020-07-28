package com.jojoreference.allomancy.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class CopperClipRenderer extends SpriteRenderer<CopperClipEntity> {
    public CopperClipRenderer(EntityRendererManager rendererManagerIn, ItemRenderer itemRendererIn) {
        super(rendererManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<CopperClipEntity> {

        @Override
        public EntityRenderer<? super CopperClipEntity> createRenderFor(EntityRendererManager manager) {
            return new CopperClipRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }
}
