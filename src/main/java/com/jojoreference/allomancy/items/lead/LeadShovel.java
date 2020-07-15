package com.jojoreference.allomancy.items.lead;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class LeadShovel extends ShovelItem {

    public LeadShovel(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("leadshovel");
    }
}
