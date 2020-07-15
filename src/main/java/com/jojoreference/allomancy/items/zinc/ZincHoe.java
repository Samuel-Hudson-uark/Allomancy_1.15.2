package com.jojoreference.allomancy.items.zinc;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class ZincHoe extends HoeItem {

    public ZincHoe(IItemTier tier, float attackSpeedIn, ItemGroup group) {
        super(tier, attackSpeedIn, new Properties().group(group));
        setRegistryName("zinchoe");
    }
}
