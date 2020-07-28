package com.jojoreference.allomancy.recipes;

import net.minecraft.item.crafting.IRecipeType;

public class RecipeTypeAlloy implements IRecipeType<AlloyRecipe> {

    @Override
    public String toString () {

        // All vanilla recipe types return their ID in toString. I am not sure how vanilla uses
        // this, or if it does. Modded types should follow this trend for the sake of
        // consistency. I am also using it during registry to create the ResourceLocation ID.
        return "allomancy:alloy_recipe";
    }
}
