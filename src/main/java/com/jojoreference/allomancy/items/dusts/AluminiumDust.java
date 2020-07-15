package com.jojoreference.allomancy.items.dusts;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class AluminiumDust extends Item {
    public AluminiumDust() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("aluminiumdust");
    }
}