package com.jojoreference.allomancy.items.pewter;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class PewterShovel extends ShovelItem {

    public PewterShovel(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("pewtershovel");
    }
}
