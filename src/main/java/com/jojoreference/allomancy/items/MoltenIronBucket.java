package com.jojoreference.allomancy.items;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class MoltenIronBucket extends Item {
    public MoltenIronBucket() {
        super(new Item.Properties()
        .maxStackSize(1)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("moltenironbucket");
    }
}