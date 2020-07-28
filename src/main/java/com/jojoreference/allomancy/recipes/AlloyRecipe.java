package com.jojoreference.allomancy.recipes;

import com.jojoreference.allomancy.Allomancy;
import com.jojoreference.allomancy.blocks.ModBlocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class AlloyRecipe implements IRecipe<IInventory> {

    private final List<Fluid> input = new ArrayList<>();
    private final Fluid output;
    private final ResourceLocation id;
    private final int ratioTop;
    private final int ratioBottom;

    public static final AlloyRecipeSerializer SERIALIZER = new AlloyRecipeSerializer();

    public AlloyRecipe(Fluid input1, Fluid input2, Fluid output, ResourceLocation id, int ratioTop, int ratioBottom) {
        this.ratioBottom = ratioBottom;
        this.input.add(input1);
        this.input.add(input2);
        this.output = output;
        this.id = id;
        this.ratioTop = ratioTop;

        System.out.println("Loaded " + this.toString());
    }


    @Override
    public String toString () {

        // Overriding toString is not required, it's just useful for debugging.
        return "AlloRecipe [input=" + this.input + ", output=" + this.output.getFluid().getRegistryName() + ", ratio=" + this.ratioTop + ", id=" + this.id + "]";
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return true;
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
        return Allomancy.ALLOY_RECIPE;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.ALLOYMIXER);
    }

    public Fluid getOutputFluid() {
        return output;
    }

    public Fluid getInputFluid(int i) {
        return input.get(i);
    }

    public int getRatioTop() {
        return ratioTop;
    }

    public int getRatioBottom() {
        return ratioBottom;
    }


}
