package com.jojoreference.allomancy.fluids;

import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;

public abstract class MoltenIron extends FlowingFluid {

    @Override
    public Fluid getFlowingFluid() {
        return ModFluids.FLOWING_IRON;
    }

    @Override
    public Fluid getStillFluid() {
        return ModFluids.IRON;
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
    }

    @Override
    protected int getSlopeFindDistance(IWorldReader worldIn) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
        return 4;
    }

    @Override
    public Item getFilledBucket() {
        return ModItems.MOLTENIRONBUCKET;
    }

    @Override
    protected boolean canDisplace(IFluidState state, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(ModFluids.Tags.MOLTENIRON);
    }

    @Override
    public int getTickRate(IWorldReader reader) {
        return 10;
    }

    @Override
    protected float getExplosionResistance() {
        return 100f;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return ModBlocks.MOLTEN_IRON_BLOCK.getDefaultState()
                .with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluidIn) {
        return fluidIn == ModFluids.IRON || fluidIn == ModFluids.FLOWING_IRON;
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(new ResourceLocation("allomancy", "block/molteniron"),
                new ResourceLocation("allomancy", "block/molteniron_flowing"))
                .translationKey("block.allomancy.molteniron")
                .build(this);
    }

    public static class Flowing extends MoltenIron {

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return false;
        }

        @Override
        public int getLevel(IFluidState state) {
            return state.get(MoltenIron.LEVEL_1_8);
        }
    }

    public static class Source extends MoltenIron {

        @Override
        public boolean isSource(IFluidState state) {
            return true;
        }

        @Override
        public int getLevel(IFluidState state) {
            return 0;
        }
    }
}
