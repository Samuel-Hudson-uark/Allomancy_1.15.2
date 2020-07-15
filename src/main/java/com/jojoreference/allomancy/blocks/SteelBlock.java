package com.jojoreference.allomancy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SteelBlock extends Block {
    public SteelBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(5f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));
        setRegistryName("steelblock");
    }
}
    
    