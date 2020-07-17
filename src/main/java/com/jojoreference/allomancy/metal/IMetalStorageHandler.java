package com.jojoreference.allomancy.metal;

import net.minecraft.item.Item;
import net.minecraft.util.IntArray;

import javax.annotation.Nullable;
import java.util.ArrayList;

public interface IMetalStorageHandler {
    //Returns the amount of metal that was able to fit in storage.
    //Returns 0 if the metal stored is different than the type given, or if storage is full.
    int ReceiveMetal(int amount, Item type, boolean simulate);

    //Returns the amount of metal removed from this storage.
    //Returns 0 if the metal stored is different than the type given, or if storage is empty.
    int SendMetal(int amountRequested, Item type, boolean simulate);

    int ReceiveMetal(int amount, Item type, boolean simulate, int slot);

    int SendMetal(int amountRequested, Item type, boolean simulate, int slot);

    boolean CanReceiveMetal(Item type);

    boolean CanReceiveMetal(Item type, int slot);

    String GetMetalName(int slot);

    //Returns amount of metal
    int GetAmount(int slot);

    ArrayList<Integer> GetAmountAsList();

    //Returns max metal storage
    int GetMaxAmount();

    //Returns type of metal stored. Can be null.
    @Nullable
    Item GetMetalType(int slot);

    void SetMetal(int amount, Item type, int slot);

    int GetSize();

    IntArray MakeArray();
}
