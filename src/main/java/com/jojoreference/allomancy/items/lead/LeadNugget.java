package com.jojoreference.allomancy.items.lead;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class LeadNugget extends Item {
    public LeadNugget() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("leadnugget");
    }
}
    