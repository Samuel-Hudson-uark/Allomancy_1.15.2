package com.jojoreference.allomancy.blocks.machines;

import com.jojoreference.allomancy.items.ModItems;
import com.jojoreference.allomancy.metal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Set;

import static com.jojoreference.allomancy.blocks.ModBlocks.ALLOYMIXER_TILE;

public class AlloyMixerTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IItemHandler> inputHandler = LazyOptional.of(this::createHandler);
    private LazyOptional<IItemHandler> outputHandler = LazyOptional.of(this::createOutputHandler);
    private LazyOptional<IMetalStorageHandler> metalPhials = LazyOptional.of(this::createMetal);
    private LazyOptional<IItemHandler> templateHandler = LazyOptional.of(this::createDustHandler);

    private int counter = 0;
    private final int metalPerDust = 100;
    private final int size = 8;
    private final int capacity = 400;

    public AlloyMixerTile() {
        super(ALLOYMIXER_TILE);
    }

    @Override
    public void tick() {
        if(counter <= 0) {
            metalPhials.ifPresent(m -> inputHandler.ifPresent(h -> {
                for(int i = 0; i < size; i++) {
                    ItemStack stack = h.getStackInSlot(i);
                    if((!stack.isEmpty())&&(m.CanReceiveMetal(stack.getItem()))) {
                        if(m.ReceiveMetal(metalPerDust, stack.getItem(), true, i) == metalPerDust) {
                            m.ReceiveMetal(metalPerDust, stack.getItem(), false, i);
                            h.extractItem(i, 1, false);
                            counter = 20;
                        }
                    }
                }
            }));
        } else {
            counter--;
        }
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        inputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        CompoundNBT metalTag = tag.getCompound("metal");
        metalPhials.ifPresent(m -> {
            Set<String> set = metalTag.keySet();
            int i = 0;
            for (String s: set) {
                m.SetMetal(metalTag.getInt(s), Item.getItemById(Integer.getInteger(s)), i);
                i++;
            }
        });
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        inputHandler.ifPresent(h ->{
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("inv", compound);
        });
        metalPhials.ifPresent(m ->{
            CompoundNBT compound = new CompoundNBT();
            int size = m.GetSize();
            for (int i = 0; i < size; i++)
            {
                compound.putInt(String.valueOf(Item.getIdFromItem(m.GetMetalType(i))), m.GetAmount(i));
            }
            tag.put("metal", compound);
        });
        return super.write(tag);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(size) {
            //the below isn't necessary for slots that can accept any item
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                for(Item dust: ModItems.DUSTS) {
                    if(dust == stack.getItem()) {return true;}
                }
                return false;
            }
        };
    }

    private IItemHandler createOutputHandler() {
        return new ItemStackHandler(1) {
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return stack;
            }
        };
    }

    private IItemHandler createDustHandler() {
        return new ItemStackHandler(2) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                for(Item dust: ModItems.DUSTS) {
                    if(dust == stack.getItem()) {return true;}
                }
                return false;
            }
        };
    }

    private IMetalStorageHandler createMetal() {
        return new MetalStorageHandler(capacity, size);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inputHandler.cast();
        }
        if(cap == CapabilityMetal.METAL) {
            return metalPhials.cast();
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
