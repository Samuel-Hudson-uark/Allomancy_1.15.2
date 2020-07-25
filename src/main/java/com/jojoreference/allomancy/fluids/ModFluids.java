package com.jojoreference.allomancy.fluids;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModFluids {
    public static MoltenIron.Source IRON = (MoltenIron.Source) new MoltenIron.Source().setRegistryName(new ResourceLocation("allomancy", "molteniron"));
    public static MoltenIron.Flowing FLOWING_IRON = (MoltenIron.Flowing) new MoltenIron.Flowing().setRegistryName(new ResourceLocation("allomancy", "molteniron_flowing"));

    public static class Tags {
        public static final Tag<Fluid> MOLTENIRON = new FluidTags.Wrapper(new ResourceLocation("allomancy", "molteniron"));
    }
}
