package com.jojoreference.allomancy.items.bronze;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class BronzeShovel extends ShovelItem {

    public BronzeShovel(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("bronzeshovel");
    }
}
