package com.jojoreference.allomancy.metal;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nullable;

public class MetalStorage implements IMetalStorage{

    private int metal;
    private int maxMetal;
    private Item metalType;

    public MetalStorage(int capacity) { this(capacity, null); }

    public MetalStorage(int capacity, Item metalType) {
        this(capacity, metalType, 0);
    }

    public MetalStorage(int capacity, Item metalType, int initialAmount) {
        this.maxMetal = capacity;
        this.metalType = metalType;
        this.metal = initialAmount;
    }

    @Override
    public int ReceiveMetal(int amount, Item type, boolean simulate) {
        if(metalType == null) {
            metalType = type;
        }
        else if(metalType != type) {
            return 0;
        }
        int metalReceived = Math.min(maxMetal-metal, amount);
        if(!simulate) {
            metal += metalReceived;
        }
        return metalReceived;
    }

    @Override
    public int SendMetal(int amountRequested, Item type, boolean simulate) {
        if(metalType != type) {
            return 0;
        }
        int metalSent = Math.min(amountRequested, metal);
        if(!simulate) {
            metal -= metalSent;
            if(metal == 0) {
                metalType = null;
            }
        }
        return metalSent;
    }

    @Override
    public boolean CanReceiveMetal(Item type) {
        return ((type != null) && (metalType == null || metalType == type));
    }

    @Override
    public String GetMetalName() {
        if(metalType == null) {
            return "None";
        }
        return new ItemStack(() -> metalType).getDisplayName().getFormattedText();
    }

    public void SetMetal(int amount, Item type) {
        this.metal = amount;
        if(amount == 0) {
            metalType = null;
        } else {
            metalType = type;
        }
    }

    @Override
    public int GetAmount() {
        return metal;
    }

    @Override
    public int GetMaxAmount() {
        return maxMetal;
    }

    @Nullable
    @Override
    public Item GetMetalType() {
        return metalType;
    }

}
