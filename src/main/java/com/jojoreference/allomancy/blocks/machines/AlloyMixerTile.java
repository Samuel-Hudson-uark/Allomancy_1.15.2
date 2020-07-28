package com.jojoreference.allomancy.blocks.machines;

import com.jojoreference.allomancy.Allomancy;
import com.jojoreference.allomancy.recipes.AlloyRecipe;
import com.jojoreference.allomancy.util.MultipleFluidTanks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.jojoreference.allomancy.blocks.ModBlocks.ALLOYMIXER_TILE;

public class AlloyMixerTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private ItemStackHandler handler = new ItemStackHandler(0);
    private RecipeWrapper wrapper = new RecipeWrapper(handler);

    MultipleFluidTanks tanks = new MultipleFluidTanks(FluidAttributes.BUCKET_VOLUME, 3);
    private LazyOptional<IFluidHandler> fluidTanksHandler = LazyOptional.of(() -> tanks);

    public AlloyMixerTile() {
        super(ALLOYMIXER_TILE);
    }

    @Override
    public void tick() {
        AlloyRecipe recipe = world.getRecipeManager().getRecipe(Allomancy.ALLOY_RECIPE, wrapper, world).orElse(null);
    }

    @Override
    public void read(CompoundNBT tag) {
        tanks.readFromNBT(tag);
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tanks.writeToNBT(tag);
        return super.write(tag);
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidTanksHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new AlloyMixerContainer(i, world, pos, playerInventory, playerEntity);
    }

}
