package com.jojoreference.allomancy.blocks.fluidblocks;

import com.jojoreference.allomancy.fluids.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class MoltenIronBlock extends FlowingFluidBlock {
    public MoltenIronBlock() {
        super(() -> ModFluids.IRON, Block.Properties
                .create(Material.LAVA)
                .doesNotBlockMovement()
        .noDrops());
        setRegistryName("molteniron");
    }
}
