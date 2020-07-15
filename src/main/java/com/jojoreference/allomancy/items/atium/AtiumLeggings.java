package com.jojoreference.allomancy.items.atium;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;

public class AtiumLeggings extends ArmorItem {
    public AtiumLeggings(IArmorMaterial materialIn, EquipmentSlotType slot, ItemGroup group) {
        super(materialIn, slot, new Properties().group(group));
        setRegistryName("atiumleggings");
    }
}

