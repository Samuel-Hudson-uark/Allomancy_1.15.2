package com.jojoreference.allomancy.items.atium;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class AtiumPickaxe extends PickaxeItem {

    public AtiumPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("atiumpickaxe");
    }
}
