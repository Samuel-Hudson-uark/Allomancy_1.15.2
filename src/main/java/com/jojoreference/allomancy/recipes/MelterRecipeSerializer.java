package com.jojoreference.allomancy.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jojoreference.allomancy.blocks.machines.Melter;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class MelterRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<MelterRecipe> {


    public MelterRecipeSerializer() {
        this.setRegistryName(new ResourceLocation("allomancy", "melter_recipe"));
    }
    @Override
    public MelterRecipe read(ResourceLocation recipeId, JsonObject json) {
        final JsonElement inputElement = JSONUtils.isJsonArray(json, "input") ? JSONUtils.getJsonArray(json, "input") : JSONUtils.getJsonObject(json, "input");
        final Ingredient input = Ingredient.deserialize(inputElement);

        final int outputAmount = JSONUtils.getInt(json, "outputFluidAmount", 144);

        final ResourceLocation outputResource = ResourceLocation.create(JSONUtils.getString(json, "outputFluid", "minecraft:empty"), ':');
        final FluidStack output = new FluidStack(ForgeRegistries.FLUIDS.getValue(outputResource), 144);

        final int burnTime = JSONUtils.getInt(json, "burnTime", 200);
        return new MelterRecipe(input, output, recipeId, burnTime);
    }

    @Nullable
    @Override
    public MelterRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        final Ingredient input = Ingredient.read(buffer);

        final ResourceLocation outputResource = ResourceLocation.create(buffer.readString(32767), ':');
        final FluidStack output = new FluidStack(ForgeRegistries.FLUIDS.getValue(outputResource), buffer.readVarInt());

        final int burnTime = buffer.readVarInt();
        return new MelterRecipe(input, output, recipeId, burnTime);
    }

    @Override
    public void write(PacketBuffer buffer, MelterRecipe recipe) {
        recipe.getInput().write(buffer);
        buffer.writeInt(recipe.getOutputFluid().getAmount());
        buffer.writeString(recipe.getOutputFluid().getFluid().getRegistryName().toString());
        buffer.writeInt(recipe.getBurnTime());
    }
}
