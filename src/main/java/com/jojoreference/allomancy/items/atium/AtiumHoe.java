package com.jojoreference.allomancy.items.atium;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class AtiumHoe extends HoeItem {

    public AtiumHoe(IItemTier tier, float attackSpeedIn, ItemGroup group) {
        super(tier, attackSpeedIn, new Properties().group(group));
        setRegistryName("atiumhoe");
    }
}
