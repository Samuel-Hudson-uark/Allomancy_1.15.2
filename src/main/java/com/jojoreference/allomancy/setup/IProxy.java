package com.jojoreference.allomancy.setup;

import net.minecraft.world.World;

public interface IProxy {
    void init();
    World getClientWorld();
}
