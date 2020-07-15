package com.jojoreference.allomancy.items.bronze;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class BronzeHoe extends HoeItem {

    public BronzeHoe(IItemTier tier, float attackSpeedIn, ItemGroup group) {
        super(tier, attackSpeedIn, new Properties().group(group));
        setRegistryName("bronzehoe");
    }
}
