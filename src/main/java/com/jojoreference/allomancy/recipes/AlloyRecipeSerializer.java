package com.jojoreference.allomancy.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.TypeRewriteRule;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.List;

public class AlloyRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AlloyRecipe> {


    public AlloyRecipeSerializer() {
        this.setRegistryName(new ResourceLocation("allomancy", "alloy_recipe"));
    }
    @Override
    public AlloyRecipe read(ResourceLocation recipeId, JsonObject json) {

        ResourceLocation resource = ResourceLocation.create(JSONUtils.getString(json, "inputFluid_1", "minecraft:empty"), ':');
        final Fluid input1 = ForgeRegistries.FLUIDS.getValue(resource);

        resource = ResourceLocation.create(JSONUtils.getString(json, "inputFluid_2", "minecraft:empty"), ':');
        final Fluid input2 = ForgeRegistries.FLUIDS.getValue(resource);

        final int ratioTop = JSONUtils.getInt(json, "ratioTop", 50);
        final int ratioBottom = JSONUtils.getInt(json, "ratioBottom", 50);

        resource = ResourceLocation.create(JSONUtils.getString(json, "outputFluid", "minecraft:empty"), ':');
        final Fluid output = ForgeRegistries.FLUIDS.getValue(resource);

        return new AlloyRecipe(input1, input2, output, recipeId, ratioTop, ratioBottom);
    }

    @Nullable
    @Override
    public AlloyRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ResourceLocation resource = ResourceLocation.create(buffer.readString(32767), ':');
        final Fluid input1 = ForgeRegistries.FLUIDS.getValue(resource);

        resource = ResourceLocation.create(buffer.readString(32767), ':');
        final Fluid input2 = ForgeRegistries.FLUIDS.getValue(resource);

        final int ratioTop = buffer.readVarInt();
        final int ratioBottom = buffer.readVarInt();

        resource = ResourceLocation.create(buffer.readString(32767), ':');
        final Fluid output = ForgeRegistries.FLUIDS.getValue(resource);

        return new AlloyRecipe(input1, input2, output, recipeId, ratioTop, ratioBottom);
    }

    @Override
    public void write(PacketBuffer buffer, AlloyRecipe recipe) {
        new FluidStack(recipe.getInputFluid(0), 144).writeToPacket(buffer);
        new FluidStack(recipe.getInputFluid(1), 144).writeToPacket(buffer);
        buffer.writeInt(recipe.getRatioTop());
        buffer.writeInt(recipe.getRatioBottom());
        new FluidStack(recipe.getOutputFluid(), 144).writeToPacket(buffer);
        buffer.writeString(recipe.getOutputFluid().getFluid().getRegistryName().toString());
    }
}
