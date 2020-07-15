package com.jojoreference.allomancy.items.zinc;

import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class ZincSword extends SwordItem {

    public ZincSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("zincsword");
    }
}
