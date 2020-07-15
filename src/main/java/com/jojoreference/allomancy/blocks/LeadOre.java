package com.jojoreference.allomancy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class LeadOre extends Block {
    public LeadOre() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(2.0f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));
        setRegistryName("leadore");
    }
}
    
    