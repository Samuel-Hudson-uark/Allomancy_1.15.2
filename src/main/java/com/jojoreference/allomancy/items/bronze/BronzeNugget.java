package com.jojoreference.allomancy.items.bronze;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class BronzeNugget extends Item {
    public BronzeNugget() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("bronzenugget");
    }
}
    