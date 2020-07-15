package com.jojoreference.allomancy.items.aluminium;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class AluminiumIngot extends Item {
    public AluminiumIngot() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("aluminiumingot");
    }
}
    