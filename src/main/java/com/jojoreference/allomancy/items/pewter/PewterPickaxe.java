package com.jojoreference.allomancy.items.pewter;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class PewterPickaxe extends PickaxeItem {

    public PewterPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("pewterpickaxe");
    }
}
