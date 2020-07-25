package com.jojoreference.allomancy.fluids;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModFluids {
    public static MoltenIron.Source IRON = (MoltenIron.Source) new MoltenIron.Source().setRegistryName("iron_still");
    public static MoltenIron.Flowing FLOWING_IRON = (MoltenIron.Flowing) new MoltenIron.Flowing().setRegistryName("iron_flowing");

    public static class Tags {
        public static final Tag<Fluid> MOLTENIRON = new FluidTags.Wrapper(ResourceLocation.create("allomancy:molteniron", ':'));
    }
}
