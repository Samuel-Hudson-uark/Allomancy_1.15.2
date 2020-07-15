package com.jojoreference.allomancy.items.dusts;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class TinDust extends Item {
    public TinDust() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("tindust");
    }
}