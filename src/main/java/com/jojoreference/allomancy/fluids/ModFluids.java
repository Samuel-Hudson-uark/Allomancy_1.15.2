package com.jojoreference.allomancy.fluids;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.registry.Registry;

public class ModFluids {
    public static final FlowingFluid FLOWING_IRON = register("flowing_iron", new MoltenIron.Flowing());
    public static final FlowingFluid IRON = register("iron", new MoltenIron.Source());

    private static <T extends Fluid> T register(String key, T p_215710_1_) {
        return (T)(Registry.register(Registry.FLUID, key, p_215710_1_));
    }

    static {
        for(Fluid fluid : Registry.FLUID) {
            for(IFluidState ifluidstate : fluid.getStateContainer().getValidStates()) {
                Fluid.STATE_REGISTRY.add(ifluidstate);
            }
        }

    }
}
