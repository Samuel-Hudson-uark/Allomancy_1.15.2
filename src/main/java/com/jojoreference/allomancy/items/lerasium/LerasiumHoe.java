package com.jojoreference.allomancy.items.lerasium;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class LerasiumHoe extends HoeItem {

    public LerasiumHoe(IItemTier tier, float attackSpeedIn, ItemGroup group) {
        super(tier, attackSpeedIn, new Properties().group(group));
        setRegistryName("lerasiumhoe");
    }
}
