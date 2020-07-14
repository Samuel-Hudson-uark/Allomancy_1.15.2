package com.jojoreference.allomancy.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ArmorMaterialList implements IArmorMaterial {

    CopperTier(14, new int[]{2,5,4,1}, 9, 0, "allomancy:copper", SoundEvents.ITEM_ARMOR_EQUIP_IRON, ModItems.COPPERINGOT),
    TinTier(10, new int[]{2,4,2,1}, 8, 0, "allomancy:tin", SoundEvents.ITEM_ARMOR_EQUIP_IRON, ModItems.TININGOT);

    private int durability, enchantability;
    private int[] damageReductionAmount;
    private int[] durabilityMultiplier = {11, 16, 15, 13}; //vanilla values
    private float toughness;
    private String name;
    private SoundEvent soundEvent;
    private Item repairMaterial;

    ArmorMaterialList(int durability, int[] damageReductionAmount, int enchantability, float toughness, String name, SoundEvent soundEvent, Item repairMaterial) {
        this.durability = durability;
        this.damageReductionAmount = damageReductionAmount;
        this.enchantability = enchantability;
        this.toughness = toughness;
        this.name = name;
        this.soundEvent = soundEvent;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return durabilityMultiplier[(slotIn.ordinal()*-1)+5]*durability;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return damageReductionAmount[(slotIn.ordinal()*-1)+5];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairMaterial);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
}
