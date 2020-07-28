package com.jojoreference.allomancy.setup;

import com.jojoreference.allomancy.capabilities.IMistborn;
import com.jojoreference.allomancy.capabilities.Mistborn;
import com.jojoreference.allomancy.capabilities.MistbornFactory;
import com.jojoreference.allomancy.capabilities.MistbornStorage;
import com.jojoreference.allomancy.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModSetup {
    public static ItemGroup itemGroup = new ItemGroup("allomancy") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.COPPERINGOT);
        }
    };
    public static void init() {
        CapabilityManager.INSTANCE.register(IMistborn.class, new MistbornStorage(), new MistbornFactory());
        System.out.println("Mistborn Capability has been initialized. Haha lerasium machine go brr.");
    }
}
