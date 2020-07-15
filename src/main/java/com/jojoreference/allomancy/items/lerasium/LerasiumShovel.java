package com.jojoreference.allomancy.items.lerasium;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class LerasiumShovel extends ShovelItem {

    public LerasiumShovel(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("lerasiumshovel");
    }
}
