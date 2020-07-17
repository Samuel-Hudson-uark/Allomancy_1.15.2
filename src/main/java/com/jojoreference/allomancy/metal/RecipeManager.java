package com.jojoreference.allomancy.metal;

import com.jojoreference.allomancy.items.ModItems;
import com.jojoreference.allomancy.items.dusts.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RecipeManager {

    public static Map<String, Integer> DUSTS = new HashMap<>();

    public RecipeManager() {
        DUSTS.put(Name(ModItems.IRONDUST), 0);
        DUSTS.put(Name(ModItems.GOLDDUST), 1);
        DUSTS.put(Name(ModItems.ALUMINIUMDUST), 2);
        DUSTS.put(Name(ModItems.COPPERDUST), 3);
        DUSTS.put(Name(ModItems.LEADDUST), 4);
        DUSTS.put(Name(ModItems.PEWTERDUST), 5);
        DUSTS.put(Name(ModItems.STEELDUST), 6);
        DUSTS.put(Name(ModItems.TINDUST), 7);
        DUSTS.put(Name(ModItems.ZINCDUST), 8);
        DUSTS.put(Name(ModItems.BRASSDUST), 9);
        DUSTS.put(Name(ModItems.BRONZEDUST), 10);
        DUSTS.put(Name(ModItems.CARBONDUST), 11);
    }

    private String Name(Item item) {
        return new ItemStack(() -> item).getDisplayName().getFormattedText();
    }
}
