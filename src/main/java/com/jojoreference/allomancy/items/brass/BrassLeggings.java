package com.jojoreference.allomancy.items.brass;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;

public class BrassLeggings extends ArmorItem {
    public BrassLeggings(IArmorMaterial materialIn, EquipmentSlotType slot, ItemGroup group) {
        super(materialIn, slot, new Properties().group(group));
        setRegistryName("brassleggings");
    }
}

