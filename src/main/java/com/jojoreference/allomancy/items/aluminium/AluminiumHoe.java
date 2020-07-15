package com.jojoreference.allomancy.items.aluminium;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class AluminiumHoe extends HoeItem {

    public AluminiumHoe(IItemTier tier, float attackSpeedIn, ItemGroup group) {
        super(tier, attackSpeedIn, new Properties().group(group));
        setRegistryName("aluminiumhoe");
    }
}
