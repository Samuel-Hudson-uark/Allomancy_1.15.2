package com.jojoreference.allomancy.items.bronze;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;

public class BronzeChestplate extends ArmorItem {
    public BronzeChestplate(IArmorMaterial materialIn, EquipmentSlotType slot, ItemGroup group) {
        super(materialIn, slot, new Properties().group(group));
        setRegistryName("bronzechestplate");
    }
}

