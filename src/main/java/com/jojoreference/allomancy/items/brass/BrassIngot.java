package com.jojoreference.allomancy.items.brass;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class BrassIngot extends Item {
    public BrassIngot() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("brassingot");
    }
}
    