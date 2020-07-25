package com.jojoreference.allomancy.blocks.fluidblocks;

import com.jojoreference.allomancy.fluids.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class MoltenIronBlock extends FlowingFluidBlock {
    public MoltenIronBlock() {
        super(() -> ModFluids.IRON, Block.Properties
                .create(Material.LAVA)
                .doesNotBlockMovement()
        .noDrops());
        setRegistryName("allomancy", "molteniron");
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(this.getFluid().isIn(ModFluids.Tags.MOLTENIRON)) {
            if(entityIn instanceof LivingEntity) {
                ((LivingEntity)entityIn).setFire(5);
            }
            else entityIn.remove();
        }
    }
}
