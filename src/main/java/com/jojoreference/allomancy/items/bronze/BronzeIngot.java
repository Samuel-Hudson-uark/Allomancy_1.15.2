package com.jojoreference.allomancy.items.bronze;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class BronzeIngot extends Item {
    public BronzeIngot() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("bronzeingot");
    }
}
    