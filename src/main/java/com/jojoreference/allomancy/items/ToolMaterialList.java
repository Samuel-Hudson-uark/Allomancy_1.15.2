package com.jojoreference.allomancy.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ToolMaterialList implements IItemTier {

    CopperTier(2.0f, 5.0f, 250, 2, 14, ModItems.COPPERINGOT),
    TinTier(2.0f, 4.0f, 170, 1, 8, ModItems.TININGOT),
    LeadTier(2.0f, 5.0f, 250, 2, 14, ModItems.LEADINGOT),
    AluminiumTier(2.0f, 5.0f, 250, 2, 14, ModItems.ALUMINIUMINGOT),
    AtiumTier(2.0f, 5.0f, 250, 2, 14, ModItems.ATIUMINGOT),
    BrassTier(2.0f, 5.0f, 250, 2, 14, ModItems.BRASSINGOT),
    BronzeTier(2.0f, 5.0f, 250, 2, 14, ModItems.BRONZEINGOT),
    LerasiumTier(2.0f, 5.0f, 250, 2, 14, ModItems.LERASIUMINGOT),
    PewterTier(2.0f, 5.0f, 250, 2, 14, ModItems.PEWTERINGOT),
    SteelTier(2.0f, 5.0f, 250, 2, 14, ModItems.STEELINGOT),
    ZincTier(2.0f, 5.0f, 250, 2, 14, ModItems.ZINCINGOT);

    private float attackDamage, efficiency;
    private int durability, harvestLevel, enchantability;
    private Item repairMaterial;

    ToolMaterialList(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial)
    {
        this.harvestLevel = harvestLevel;
        this.attackDamage = attackDamage;
        this.efficiency = efficiency;
        this.durability  = durability;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }
    @Override
    public int getMaxUses() {
        return this.durability;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairMaterial);
    }
}
