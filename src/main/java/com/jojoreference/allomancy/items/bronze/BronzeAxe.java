package com.jojoreference.allomancy.items.bronze;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class BronzeAxe extends AxeItem {

    public BronzeAxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("bronzeaxe");
    }
}
