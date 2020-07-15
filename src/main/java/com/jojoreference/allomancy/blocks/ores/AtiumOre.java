package com.jojoreference.allomancy.blocks.ores;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class AtiumOre extends Block {
    public AtiumOre() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(2.0f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));
        setRegistryName("atiumore");
    }
}
    
    