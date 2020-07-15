package com.jojoreference.allomancy.items.aluminium;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class AluminiumNugget extends Item {
    public AluminiumNugget() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("aluminiumnugget");
    }
}
    