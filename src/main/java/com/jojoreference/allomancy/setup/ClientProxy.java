package com.jojoreference.allomancy.setup;

import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.blocks.machines.AlloyMixerScreen;
import com.jojoreference.allomancy.blocks.machines.MelterScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy{
    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.ALLOYMIXER_CONTAINER, AlloyMixerScreen::new);
        ScreenManager.registerFactory(ModBlocks.MELTER_CONTAINER, MelterScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
