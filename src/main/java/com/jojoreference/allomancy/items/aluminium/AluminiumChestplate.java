package com.jojoreference.allomancy.items.aluminium;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;

public class AluminiumChestplate extends ArmorItem {
    public AluminiumChestplate(IArmorMaterial materialIn, EquipmentSlotType slot, ItemGroup group) {
        super(materialIn, slot, new Properties().group(group));
        setRegistryName("aluminiumchestplate");
    }
}

