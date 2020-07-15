package com.jojoreference.allomancy.items.lerasium;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;

public class LerasiumLeggings extends ArmorItem {
    public LerasiumLeggings(IArmorMaterial materialIn, EquipmentSlotType slot, ItemGroup group) {
        super(materialIn, slot, new Properties().group(group));
        setRegistryName("lerasiumleggings");
    }
}

