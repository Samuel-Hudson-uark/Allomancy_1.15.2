package com.jojoreference.allomancy.items.aluminium;

import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class AluminiumSword extends SwordItem {

    public AluminiumSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("aluminiumsword");
    }
}
