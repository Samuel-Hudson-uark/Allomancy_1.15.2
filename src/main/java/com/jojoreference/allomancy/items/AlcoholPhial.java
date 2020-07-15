package com.jojoreference.allomancy.items;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class AlcoholPhial extends Item {
    public AlcoholPhial() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("alcoholphial");
    }
}