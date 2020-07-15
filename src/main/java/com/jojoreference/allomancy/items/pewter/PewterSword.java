package com.jojoreference.allomancy.items.pewter;

import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class PewterSword extends SwordItem {

    public PewterSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("pewtersword");
    }
}
