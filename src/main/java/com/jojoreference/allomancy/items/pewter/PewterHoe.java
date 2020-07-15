package com.jojoreference.allomancy.items.pewter;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class PewterHoe extends HoeItem {

    public PewterHoe(IItemTier tier, float attackSpeedIn, ItemGroup group) {
        super(tier, attackSpeedIn, new Properties().group(group));
        setRegistryName("pewterhoe");
    }
}
