package com.jojoreference.allomancy.metal;

import net.minecraft.item.Item;
import net.minecraft.util.IntArray;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MetalStorageHandler implements IMetalStorageHandler{

    private ArrayList<MetalStorage> storageList = new ArrayList<>();

    public MetalStorageHandler() { this(1); }
    public MetalStorageHandler(int size) { this(size, 400); }
    public MetalStorageHandler(int size, int capacity) { this(size, 400, null, 0); }
    public MetalStorageHandler(int size, int capacity, Item metalType, int currentAmount) {
        for(int i = 0; i < size; i++) {
            storageList.add(new MetalStorage(capacity, metalType, currentAmount));
        }
    }
    @Override
    public int ReceiveMetal(int amount, Item type, boolean simulate) {
        int slot = storageList.size()-1;
        for(int i = slot; i >= 0; i--) {
            if(storageList.get(i).CanReceiveMetal(type)) {
                slot = i;
                if(storageList.get(i).GetMetalType() == type) {
                    return storageList.get(i).ReceiveMetal(amount, type, simulate);
                }
            }
        }
        return storageList.get(slot).ReceiveMetal(amount, type, simulate);
    }

    @Override
    public int SendMetal(int amountRequested, Item type, boolean simulate) {
        for (MetalStorage metalStorage : storageList) {
            if (metalStorage.GetMetalType() == type) {
                return metalStorage.SendMetal(amountRequested, type, simulate);
            }
        }
        return 0;
    }

    @Override
    public int ReceiveMetal(int amount, Item type, boolean simulate, int slot) {
        return storageList.get(slot).ReceiveMetal(amount, type, simulate);
    }

    @Override
    public int SendMetal(int amountRequested, Item type, boolean simulate, int slot) {
        return storageList.get(slot).SendMetal(amountRequested, type, simulate);
    }

    @Override
    public boolean CanReceiveMetal(Item type) {
        for (MetalStorage metalStorage : storageList) {
            if(metalStorage.CanReceiveMetal(type)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String GetMetalName(int i) {
        return storageList.get(i).GetMetalName();
    }

    @Override
    public int GetAmount(int i) {
        return storageList.get(i).GetAmount();
    }

    @Override
    public ArrayList<Integer> GetAmountAsList() {
        ArrayList<Integer> intList = new ArrayList<>();
        for (MetalStorage metalStorage : storageList) {
            intList.add(metalStorage.GetAmount());
        }
        return intList;
    }

    @Override
    public int GetMaxAmount() {
        return storageList.get(0).GetMaxAmount();
    }

    @Nullable
    @Override
    public Item GetMetalType(int i) {
        return storageList.get(i).GetMetalType();
    }

    @Override
    public void SetMetal(int amount, Item type, int slot) {
        storageList.get(slot).SetMetal(amount, type);
    }

    public boolean CanReceiveMetal(Item type, int i) {
        return storageList.get(i).CanReceiveMetal(type);
    }

    public int GetSize() {
        return storageList.size();
    }

    public IntArray MakeArray() {
        IntArray array = new IntArray(GetSize());
        for(int i = 0; i < GetSize(); i++) {
            array.set(i, storageList.get(i).GetAmount());
        }
        return array;
    }

}
