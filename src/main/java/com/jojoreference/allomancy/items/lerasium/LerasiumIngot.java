package com.jojoreference.allomancy.items.lerasium;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class LerasiumIngot extends Item {
    public LerasiumIngot() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("lerasiumingot");
    }
}
    