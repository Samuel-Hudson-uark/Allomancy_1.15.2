package com.jojoreference.allomancy.items.bronze;

import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class BronzeSword extends SwordItem {

    public BronzeSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("bronzesword");
    }
}
