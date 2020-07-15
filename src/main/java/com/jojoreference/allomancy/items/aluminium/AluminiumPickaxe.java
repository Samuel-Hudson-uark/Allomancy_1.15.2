package com.jojoreference.allomancy.items.aluminium;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class AluminiumPickaxe extends PickaxeItem {

    public AluminiumPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("aluminiumpickaxe");
    }
}
