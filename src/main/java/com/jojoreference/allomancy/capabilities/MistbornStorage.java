package com.jojoreference.allomancy.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class MistbornStorage implements Capability.IStorage<IMistborn> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IMistborn> capability, IMistborn instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("iron", instance.getResource());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IMistborn> capability, IMistborn instance, Direction side, INBT nbt) {
        instance.set(((CompoundNBT) nbt).getFloat("iron"));
    }
}
