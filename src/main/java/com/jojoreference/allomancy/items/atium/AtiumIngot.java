package com.jojoreference.allomancy.items.atium;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class AtiumIngot extends Item {
    public AtiumIngot() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("atiumingot");
    }
}
    