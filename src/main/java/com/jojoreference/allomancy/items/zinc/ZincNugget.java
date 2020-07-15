package com.jojoreference.allomancy.items.zinc;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class ZincNugget extends Item {
    public ZincNugget() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("zincnugget");
    }
}
    