package com.jojoreference.allomancy.items;

import com.jojoreference.allomancy.Allomancy;
import com.jojoreference.allomancy.fluids.ModFluids;
import com.jojoreference.allomancy.fluids.MoltenIron;
import com.jojoreference.allomancy.setup.ModSetup;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class MoltenIronBucket extends BucketItem {

    public MoltenIronBucket() {
        super(() -> ModFluids.IRON, new Item.Properties()
                .maxStackSize(1)
                .group(ModSetup.itemGroup));
        setRegistryName("moltenironbucket");
    }
}