package com.jojoreference.allomancy.items.dusts;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class CarbonDust extends Item {
    public CarbonDust() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("carbondust");
    }
}