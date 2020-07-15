package com.jojoreference.allomancy.items.steel;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class SteelNugget extends Item {
    public SteelNugget() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("steelnugget");
    }
}
    