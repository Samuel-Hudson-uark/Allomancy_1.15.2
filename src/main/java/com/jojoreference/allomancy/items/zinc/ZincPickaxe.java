package com.jojoreference.allomancy.items.zinc;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class ZincPickaxe extends PickaxeItem {

    public ZincPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("zincpickaxe");
    }
}
