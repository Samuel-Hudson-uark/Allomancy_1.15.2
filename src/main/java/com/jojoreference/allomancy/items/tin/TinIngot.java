package com.jojoreference.allomancy.items.tin;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class TinIngot extends Item {
    public TinIngot() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("tiningot");
    }
}
    