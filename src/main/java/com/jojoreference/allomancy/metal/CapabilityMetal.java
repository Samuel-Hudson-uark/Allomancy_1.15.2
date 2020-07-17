package com.jojoreference.allomancy.metal;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.util.Set;

public class CapabilityMetal
{
    @CapabilityInject(IMetalStorageHandler.class)
    public static Capability<IMetalStorageHandler> METAL = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IMetalStorageHandler.class, new Capability.IStorage<IMetalStorageHandler>()
            {
                @Override
                public INBT writeNBT(Capability<IMetalStorageHandler> capability, IMetalStorageHandler instance, Direction side)
                {
                    CompoundNBT CNBT = new CompoundNBT();
                    int size = instance.GetSize();
                    for (int i = 0; i < size; i++)
                    {
                        CNBT.putInt(String.valueOf(Item.getIdFromItem(instance.GetMetalType(i))), instance.GetAmount(i));
                    }
                    return CNBT;
                }

                @Override
                public void readNBT(Capability<IMetalStorageHandler> capability, IMetalStorageHandler instance, Direction side, INBT nbt)
                {
                    if (!(instance instanceof MetalStorageHandler))
                        throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                    CompoundNBT CNBT = (CompoundNBT) nbt;
                    Set<String> set = CNBT.keySet();
                    int i = 0;
                    for (String s: set) {
                        instance.SetMetal(CNBT.getInt(s), Item.getItemById(Integer.getInteger(s)), i);
                        i++;
                    }
                }
            },
            () -> new MetalStorageHandler(1, 400));
    }
}
