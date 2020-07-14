package com.jojoreference.allomancy.setup;

import com.jojoreference.allomancy.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {
    public static ItemGroup itemGroup = new ItemGroup("allomancy") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.COPPERINGOT);
        }
    };
    public static void init() {

    }
}
