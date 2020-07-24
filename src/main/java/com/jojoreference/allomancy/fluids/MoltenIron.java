package com.jojoreference.allomancy.fluids;

import com.jojoreference.allomancy.items.ModItems;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class MoltenIron extends LavaFluid {

    public Fluid getFlowingFluid() { return ModFluids.FLOWING_IRON; }

    public Fluid getStillFluid() {
        return ModFluids.IRON;
    }

    public Item getFilledBucket() {
        return ModItems.MOLTENIRONBUCKET;
    }

    @Override
    public boolean isSource(IFluidState state) {
        return this.getFluid().isSource(state);
    }

    @Override
    public int getLevel(IFluidState state) {
        return this.getFluid().getLevel(state);
    }
}
