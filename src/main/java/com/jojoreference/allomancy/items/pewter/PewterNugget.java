package com.jojoreference.allomancy.items.pewter;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class PewterNugget extends Item {
    public PewterNugget() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("pewternugget");
    }
}
    