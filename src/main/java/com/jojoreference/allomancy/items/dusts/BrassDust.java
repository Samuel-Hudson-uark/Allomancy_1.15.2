package com.jojoreference.allomancy.items.dusts;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class BrassDust extends Item {
    public BrassDust() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("brassdust");
    }
}