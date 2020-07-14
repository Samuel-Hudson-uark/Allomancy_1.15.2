package com.jojoreference.allomancy.items.copper;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.*;

import javax.tools.Tool;

public class CopperPickaxe extends PickaxeItem {

    public CopperPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("copperpickaxe");
    }
}
