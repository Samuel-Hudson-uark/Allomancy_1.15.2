package com.jojoreference.allomancy.items.copper;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.HoeItem;

public class CopperHoe extends HoeItem {

    public CopperHoe(IItemTier tier, float attackSpeedIn, ItemGroup group) {
        super(tier, attackSpeedIn, new Properties().group(group));
        setRegistryName("copperhoe");
    }
}
