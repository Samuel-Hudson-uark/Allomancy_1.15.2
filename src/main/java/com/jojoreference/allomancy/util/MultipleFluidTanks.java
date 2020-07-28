package com.jojoreference.allomancy.util;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MultipleFluidTanks implements IFluidHandler{

    protected Predicate<FluidStack> validator;
    protected List<FluidTank> fluidTanks = new ArrayList<>();
    protected int amount;

    public MultipleFluidTanks() {
        this(FluidAttributes.BUCKET_VOLUME);
    }

    public MultipleFluidTanks(int capacity) {
        this(capacity, 1);
    }

    public MultipleFluidTanks(int capacity, int amount) {
        this(capacity, amount, (e -> true));
    }

    public MultipleFluidTanks(int capacity, int amount, Predicate<FluidStack> validator) {
        this.validator = validator;
        this.amount = amount;
        for(int i = 0; i < amount; i++) {
            fluidTanks.add(new FluidTank(capacity, validator));
        }
    }

    @Override
    public int getTanks() {
        return amount;
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return fluidTanks.get(tank).getFluid();
    }

    @Override
    public int getTankCapacity(int tank) {
        return fluidTanks.get(tank).getCapacity();
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        return fluidTanks.get(tank).isFluidValid(stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        //probably don't use this, use the other fill instead.
        for(int i = 0; i < amount; i++) {
            if(this.isFluidValid(i, resource)) {
                return fluidTanks.get(i).fill(resource, action);
            }
        }
        return 0;
    }

    public int fill(int tank, FluidStack resource, FluidAction action) {
        return fluidTanks.get(tank).fill(resource, action);
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        //probably don't use this, use the other drain instead.
        for(int i = 0; i < amount; i++) {
            if(this.isFluidValid(i, resource)) {
                return fluidTanks.get(i).drain(resource, action);
            }
        }
        return FluidStack.EMPTY;
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        //probably don't use this, use the other drain instead.
        return fluidTanks.get(0).drain(maxDrain, action);
    }

    public FluidStack drain(int tank, FluidStack resource, FluidAction action) {
        return fluidTanks.get(tank).drain(resource, action);
    }

    public MultipleFluidTanks readFromNBT(CompoundNBT nbt) {
        for(int i = 0; i < amount; i++) {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);
            setFluid(fluid, i);
        }
        return this;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt) {
        for(int i = 0; i < amount; i++) {
            fluidTanks.get(i).writeToNBT(nbt);
        }
        return nbt;
    }

    public void setFluid(FluidStack stack, int i)
    {
        fluidTanks.get(i).setFluid(stack);
    }
}
