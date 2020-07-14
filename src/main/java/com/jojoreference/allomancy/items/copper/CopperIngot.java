package com.jojoreference.allomancy.items.copper;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class CopperIngot extends Item {
    public CopperIngot() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("copperingot");
    }
}
