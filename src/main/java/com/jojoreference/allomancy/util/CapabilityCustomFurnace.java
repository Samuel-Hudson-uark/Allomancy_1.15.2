package com.jojoreference.allomancy.util;

import com.jojoreference.allomancy.metal.IMetalStorageHandler;
import com.jojoreference.allomancy.metal.MetalStorageHandler;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityCustomFurnace {
    @CapabilityInject(IIntArray.class)
    public static Capability<IIntArray> CUSTOM_FURNACE_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IIntArray.class, new Capability.IStorage<IIntArray>() {

            @Nullable
            @Override
            public INBT writeNBT(Capability<IIntArray> capability, IIntArray instance, Direction side) {
                CompoundNBT CNBT = new CompoundNBT();
                CNBT.putInt("burnTime", instance.get(0));
                CNBT.putInt("totalBurnTime", instance.get(1));
                CNBT.putInt("processingTime", instance.get(2));
                CNBT.putInt("totalProcessingTime", instance.get(3));
                return CNBT;
            }

            @Override
            public void readNBT(Capability<IIntArray> capability, IIntArray instance, Direction side, INBT nbt) {
                if (!(instance instanceof IntArray))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                CompoundNBT CNBT = (CompoundNBT) nbt;
                instance.set(0, CNBT.getInt("burnTime"));
                instance.set(1, CNBT.getInt("totalBurnTime"));
                instance.set(2, CNBT.getInt("processingTime"));
                instance.set(3, CNBT.getInt("totalProcessingTime"));

            }
        },
                () -> new IntArray(4));

    }
}
