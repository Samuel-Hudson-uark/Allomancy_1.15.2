package com.jojoreference.allomancy.recipes;

import com.jojoreference.allomancy.Allomancy;
import com.jojoreference.allomancy.blocks.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class MelterRecipe implements IRecipe<IInventory> {

    private final Ingredient input;
    private final FluidStack output;
    private final ResourceLocation id;
    private final int burnTime;

    public static final MelterRecipeSerializer SERIALIZER = new MelterRecipeSerializer();

    public MelterRecipe(Ingredient input, FluidStack output, ResourceLocation id, int burnTime) {
        this.input = input;
        this.output = output;
        this.id = id;
        this.burnTime = burnTime;

        System.out.println("Loaded " + this.toString());
    }


    @Override
    public String toString () {

        // Overriding toString is not required, it's just useful for debugging.
        return "MelterRecipe [input=" + this.input + ", output=" + this.output.getFluid().getRegistryName() + ", burn time=" + this.burnTime + ", id=" + this.id + "]";
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.input.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.output.getFluid().getFilledBucket().getDefaultInstance();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output.getFluid().getFilledBucket().getDefaultInstance();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return Allomancy.MELTER_RECIPE;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.MELTER);
    }

    public FluidStack getOutputFluid() {
        return output;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getBurnTime() {
        return burnTime;
    }


}
