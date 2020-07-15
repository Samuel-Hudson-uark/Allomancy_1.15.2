package com.jojoreference.allomancy.items.atium;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class AtiumNugget extends Item {
    public AtiumNugget() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("atiumnugget");
    }
}
    