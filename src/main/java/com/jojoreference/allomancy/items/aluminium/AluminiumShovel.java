package com.jojoreference.allomancy.items.aluminium;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class AluminiumShovel extends ShovelItem {

    public AluminiumShovel(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("aluminiumshovel");
    }
}
