package com.jojoreference.allomancy.items.dusts;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class IronDust extends Item {
    public IronDust() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("irondust");
    }
}