package com.jojoreference.allomancy.items.lead;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class LeadAxe extends AxeItem {

    public LeadAxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("leadaxe");
    }
}
