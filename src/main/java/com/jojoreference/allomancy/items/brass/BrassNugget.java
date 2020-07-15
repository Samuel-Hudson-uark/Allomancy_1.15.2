package com.jojoreference.allomancy.items.brass;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class BrassNugget extends Item {
    public BrassNugget() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("brassnugget");
    }
}
    