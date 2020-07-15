package com.jojoreference.allomancy.items.aluminium;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class AluminiumAxe extends AxeItem {

    public AluminiumAxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("aluminiumaxe");
    }
}
