package com.jojoreference.allomancy.items.steel;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class SteelIngot extends Item {
    public SteelIngot() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("steelingot");
    }
}
    