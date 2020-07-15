package com.jojoreference.allomancy.items.lerasium;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class LerasiumPickaxe extends PickaxeItem {

    public LerasiumPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("lerasiumpickaxe");
    }
}
