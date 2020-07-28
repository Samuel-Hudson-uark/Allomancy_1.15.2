package com.jojoreference.allomancy.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class MistbornProvider implements ICapabilitySerializable<CompoundNBT> {
    @CapabilityInject(IMistborn.class)
    public static Capability<IMistborn> MISTBORN_CAPABILITY = null;

    private LazyOptional<IMistborn> instance = LazyOptional.of(MISTBORN_CAPABILITY::getDefaultInstance);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return (cap == MISTBORN_CAPABILITY) ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) MISTBORN_CAPABILITY.getStorage().writeNBT(MISTBORN_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        MISTBORN_CAPABILITY.getStorage().readNBT(MISTBORN_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
    }
}
