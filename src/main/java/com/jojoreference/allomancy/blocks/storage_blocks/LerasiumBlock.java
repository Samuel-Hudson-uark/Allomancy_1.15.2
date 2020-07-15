package com.jojoreference.allomancy.blocks.storage_blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class LerasiumBlock extends Block {
    public LerasiumBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(5f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));
        setRegistryName("lerasiumblock");
    }
}
    
    