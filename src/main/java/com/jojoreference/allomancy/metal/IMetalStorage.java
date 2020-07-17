package com.jojoreference.allomancy.metal;

import net.minecraft.item.Item;

import javax.annotation.Nullable;

public interface IMetalStorage {
    //Returns the amount of metal that was able to fit in storage.
    //Returns 0 if the metal stored is different than the type given, or if storage is full.
    int ReceiveMetal(int amount, Item type, boolean simulate);

    //Returns the amount of metal removed from this storage.
    //Returns 0 if the metal stored is different than the type given, or if storage is empty.
    int SendMetal(int amountRequested, Item type, boolean simulate);

    boolean CanReceiveMetal(Item type);

    String GetMetalName();

    //Returns amount of metal
    int GetAmount();

    //Returns max metal storage
    int GetMaxAmount();

    //Returns type of metal stored. Can be null.
    @Nullable
    Item GetMetalType();

    void SetMetal(int amount, Item type);
}
