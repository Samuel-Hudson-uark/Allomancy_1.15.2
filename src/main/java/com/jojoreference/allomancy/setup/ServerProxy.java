package com.jojoreference.allomancy.setup;

import net.minecraft.world.World;

public class ServerProxy implements IProxy{
    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this from the client.");
    }
}
