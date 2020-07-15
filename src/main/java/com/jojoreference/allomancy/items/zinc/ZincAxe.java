package com.jojoreference.allomancy.items.zinc;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class ZincAxe extends AxeItem {

    public ZincAxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("zincaxe");
    }
}
