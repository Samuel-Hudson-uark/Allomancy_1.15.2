package com.jojoreference.allomancy.items.lerasium;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class LerasiumAxe extends AxeItem {

    public LerasiumAxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("lerasiumaxe");
    }
}
