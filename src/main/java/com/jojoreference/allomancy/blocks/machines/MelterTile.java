package com.jojoreference.allomancy.blocks.machines;

import com.jojoreference.allomancy.Allomancy;
import com.jojoreference.allomancy.recipes.MelterRecipe;
import com.jojoreference.allomancy.util.CapabilityCustomFurnace;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.jojoreference.allomancy.blocks.ModBlocks.MELTER_TILE;

public class MelterTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public ItemStackHandler handler = createHandler();
    private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> handler);

    public FluidTank tank = new FluidTank(FluidAttributes.BUCKET_VOLUME);
    private final LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> tank);

    public IntArray customFurnaceStats = new IntArray(4);
    private final LazyOptional<IIntArray> customFurnaceStatsHandler = LazyOptional.of(() -> customFurnaceStats);

    public int burnTime = 0;
    public int totalBurnTime = 1;
    public int processingTime = 0;
    public int totalProcessingTime = 1;

    private RecipeWrapper wrapper = new RecipeWrapper(handler);

    public MelterTile() {
        super(MELTER_TILE);
    }

    @Override
    public void tick() {
        if(burnTime > 0) {
            burnTime--;
        }
        if(processingTime > 0) {
            processingTime--;
        }
        MelterRecipe recipe = world.getRecipeManager().getRecipe(Allomancy.MELTER_RECIPE, wrapper, world).orElse(null);
        if(recipe != null && canProcess(recipe)) {
            int itemBurnTime = ForgeHooks.getBurnTime(handler.getStackInSlot(1));
            if(burnTime <= 0 && itemBurnTime > 0) {
                burnTime = itemBurnTime;
                totalBurnTime = burnTime;
                handler.extractItem(1, 1, false);
            }
            if(burnTime > 0 && processingTime <= 0) {
                handler.extractItem(0, 1, false);
                tank.fill(recipe.getOutputFluid(), IFluidHandler.FluidAction.EXECUTE);
                processingTime = recipe.getBurnTime();
                totalProcessingTime = processingTime;
                markDirty();
            }

        }
    }

    private boolean canProcess(MelterRecipe recipe) {
        boolean itemInput = !handler.extractItem(0, 1, true).isEmpty() || recipe.getIngredients().isEmpty();
        boolean fluidOutput = tank.fill(recipe.getOutputFluid(), IFluidHandler.FluidAction.SIMULATE) == recipe.getOutputFluid().getAmount();
        return itemInput && fluidOutput;
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("input");
        itemHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        tank.readFromNBT(tag);
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        itemHandler.ifPresent(h ->{
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("input", compound);
        });
        tank.writeToNBT(tag);
        return super.write(tag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {
            //the below isn't necessary for slots that can accept any item
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return (slot == 1 && ForgeHooks.getBurnTime(stack) > 0)
                        || (slot == 0 && ForgeHooks.getBurnTime(stack) <= 0); //and item has recipe
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                //if(stack.getItem() != some item {return stack;}
                //if(slot != 2)
                    return super.insertItem(slot, stack, simulate);
                //else
                //    return stack;
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidHandler.cast();
        }
        if(cap == CapabilityCustomFurnace.CUSTOM_FURNACE_CAPABILITY) {
            customFurnaceStats.set(0, burnTime);
            customFurnaceStats.set(1, totalBurnTime);
            customFurnaceStats.set(2, processingTime);
            customFurnaceStats.set(3, totalProcessingTime);
            return customFurnaceStatsHandler.cast();
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
        return new MelterContainer(i, world, pos, playerInventory, playerEntity);
    }

}

