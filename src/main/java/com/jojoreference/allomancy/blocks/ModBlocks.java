package com.jojoreference.allomancy.blocks;

import com.jojoreference.allomancy.blocks.fluidblocks.MoltenIronBlock;
import com.jojoreference.allomancy.blocks.machines.*;
import com.jojoreference.allomancy.blocks.ores.*;
import com.jojoreference.allomancy.blocks.storage_blocks.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {
    //machines
    @ObjectHolder("allomancy:alloymixer")
    public static AlloyMixer ALLOYMIXER;

    @ObjectHolder("allomancy:alloymixer")
    public static TileEntityType<AlloyMixerTile> ALLOYMIXER_TILE;

    @ObjectHolder("allomancy:alloymixer")
    public static ContainerType<AlloyMixerContainer> ALLOYMIXER_CONTAINER;

    @ObjectHolder("allomancy:melter")
    public static Melter MELTER;

    @ObjectHolder("allomancy:melter")
    public static TileEntityType<MelterTile> MELTER_TILE;

    @ObjectHolder("allomancy:melter")
    public static ContainerType<MelterContainer> MELTER_CONTAINER;

    //fluids
    @ObjectHolder("allomancy:molteniron")
    public static MoltenIronBlock MOLTEN_IRON_BLOCK;

    //ores and blocks
    @ObjectHolder("allomancy:copperore")
    public static CopperOre COPPERORE;

    @ObjectHolder("allomancy:copperblock")
    public static CopperBlock COPPERBLOCK;

    @ObjectHolder("allomancy:tinore")
    public static TinOre TINORE;

    @ObjectHolder("allomancy:tinblock")
    public static TinBlock TINBLOCK;

    @ObjectHolder("allomancy:leadore")
    public static LeadOre LEADORE;

    @ObjectHolder("allomancy:leadblock")
    public static LeadBlock LEADBLOCK;

    @ObjectHolder("allomancy:steelblock")
    public static SteelBlock STEELBLOCK;

    @ObjectHolder("allomancy:pewterblock")
    public static PewterBlock PEWTERBLOCK;

    @ObjectHolder("allomancy:bronzeblock")
    public static BronzeBlock BRONZEBLOCK;

    @ObjectHolder("allomancy:zincore")
    public static ZincOre ZINCORE;

    @ObjectHolder("allomancy:zincblock")
    public static ZincBlock ZINCBLOCK;

    @ObjectHolder("allomancy:brassblock")
    public static BrassBlock BRASSBLOCK;

    @ObjectHolder("allomancy:aluminiumore")
    public static AluminiumOre ALUMINIUMORE;

    @ObjectHolder("allomancy:aluminiumblock")
    public static AluminiumBlock ALUMINIUMBLOCK;

    @ObjectHolder("allomancy:atiumore")
    public static AtiumOre ATIUMORE;

    @ObjectHolder("allomancy:atiumblock")
    public static AtiumBlock ATIUMBLOCK;

    @ObjectHolder("allomancy:lerasiumore")
    public static LerasiumOre LERASIUMORE;

    @ObjectHolder("allomancy:lerasiumblock")
    public static LerasiumBlock LERASIUMBLOCK;
}