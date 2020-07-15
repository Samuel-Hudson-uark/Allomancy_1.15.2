package com.jojoreference.allomancy.items.brass;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class BrassHoe extends HoeItem {

    public BrassHoe(IItemTier tier, float attackSpeedIn, ItemGroup group) {
        super(tier, attackSpeedIn, new Properties().group(group));
        setRegistryName("brasshoe");
    }
}
