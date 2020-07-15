package com.jojoreference.allomancy.items.atium;

import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class AtiumSword extends SwordItem {

    public AtiumSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("atiumsword");
    }
}
