import os
from pathlib import Path

material = 'Tin'
hasore = True
sworddamage = 8

try:
    os.makedirs('./src/main/java/com/jojoreference/allomancy/items/{}'.format(material.lower()))
except OSError:
    print ("Creation of the directory failed.\n")
else:
    print ("Successfully created the directory.\n")

tooltypes = {'Axe', 'Hoe', 'Pickaxe', 'Shovel', 'Sword'}
toolcode = """package com.jojoreference.allomancy.items.{0};

import net.minecraft.item.{3}Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class {1}{3} extends {3}Item {{

    public {1}{3}(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {{
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("{0}{2}");
    }}
}}
"""

for tool in tooltypes:
    with open('./src/main/java/com/jojoreference/allomancy/items/{}/{}{}.java'.format(material.lower(), material, tool), 'w') as f:
        f.write(toolcode.format(material.lower(), material, tool.lower(), tool))

armortypes = {'Helmet', 'Chestplate', 'Leggings', 'Boots'}
armorcode = """package com.jojoreference.allomancy.items.{0};

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;

public class {1}{3} extends ArmorItem {{
    public {1}{3}(IArmorMaterial materialIn, EquipmentSlotType slot, ItemGroup group) {{
        super(materialIn, slot, new Properties().group(group));
        setRegistryName("{0}{2}");
    }}
}}

"""
for armor in armortypes:
    with open('./src/main/java/com/jojoreference/allomancy/items/{}/{}{}.java'.format(material.lower(), material, armor), 'w') as f:
        f.write(armorcode.format(material.lower(), material, armor.lower(), armor))

with open('./src/main/java/com/jojoreference/allomancy/blocks/{}Block.java'.format(material), 'w') as f:
    f.write("""package com.jojoreference.allomancy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class {0}Block extends Block {{
    public {0}Block() {{
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(5f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));
        setRegistryName("{1}block");
    }}
}}
    
    """.format(material, material.lower()))

with open('./src/main/java/com/jojoreference/allomancy/blocks/{}Ore.java'.format(material), 'w') as f:
    f.write("""package com.jojoreference.allomancy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class {0}Ore extends Block {{
    public {0}Ore() {{
        super(Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(2.0f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));
        setRegistryName("{1}ore");
    }}
}}
    
    """.format(material, material.lower()))

with open('./src/main/java/com/jojoreference/allomancy/blocks/ModBlocks.java', 'r') as f:
    file = f.read()
if file.find(material) == -1:
    file = file[0:-1] + """
    @ObjectHolder("allomancy:{0}ore")
    public static {1}Ore {2}ORE;

    @ObjectHolder("allomancy:{0}block")
    public static {1}Block {2}BLOCK;
}}""".format(material.lower(), material, material.upper())
    with open('./src/main/java/com/jojoreference/allomancy/blocks/ModBlocks.java', 'w') as f:
        f.write(file)

with open('./src/main/java/com/jojoreference/allomancy/items/ModItems.java', 'r') as f:
    file = f.read()
if file.find(material) == -1:
    file = file[0:-1] + """
    @ObjectHolder("allomancy:{0}ingot")
    public static {1}Ingot {2}INGOT;
}}""".format(material.lower(), material, material.upper())
    with open('./src/main/java/com/jojoreference/allomancy/items/ModItems.java', 'w') as f:
        f.write(file)

with open('./src/main/java/com/jojoreference/allomancy/items/{}/{}Ingot.java'.format(material.lower(), material), 'w') as f:
    f.write("""package com.jojoreference.allomancy.items.{1};

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class {0}Ingot extends Item {{
    public {0}Ingot() {{
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("{1}ingot");
    }}
}}
    """.format(material, material.lower()))

with open('./src/main/java/com/jojoreference/allomancy/items/{}/{}Nugget.java'.format(material.lower(), material), 'w') as f:
    f.write("""package com.jojoreference.allomancy.items.{1};

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class {0}Nugget extends Item {{
    public {0}Nugget() {{
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("{1}nugget");
    }}
}}
    """.format(material, material.lower()))

with open('./src/main/java/com/jojoreference/allomancy/Allomancy.java', 'r') as f:
    file = f.read()
if file.find(material) == -1:
    file = file.replace("event.getRegistry().register(new CopperBlock());", 
"""event.getRegistry().register(new CopperBlock());

            event.getRegistry().register(new {0}Ore());
            event.getRegistry().register(new {0}Block());""".format(material))

    file = file.replace('.setRegistryName("copperblock"));', """.setRegistryName("copperblock"));

            event.getRegistry().register(new {0}Ingot());
            event.getRegistry().register(new {0}Nugget());

            event.getRegistry().register(new {0}Sword(ToolMaterialList.{0}Tier, {3}, -3.1f, setup.itemGroup));
            event.getRegistry().register(new {0}Axe(ToolMaterialList.{0}Tier, {3}, -3.1f, setup.itemGroup));
            event.getRegistry().register(new {0}Pickaxe(ToolMaterialList.{0}Tier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new {0}Shovel(ToolMaterialList.{0}Tier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new {0}Hoe(ToolMaterialList.{0}Tier, 0, 0, setup.itemGroup));

            event.getRegistry().register(new {0}Helmet(ArmorMaterialList.{0}Tier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new {0}Chestplate(ArmorMaterialList.{0}Tier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new {0}Leggings(ArmorMaterialList.{0}Tier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new {0}Boots(ArmorMaterialList.{0}Tier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.{2}ORE, properties).setRegistryName("{1}ore"));
            event.getRegistry().register(new BlockItem(ModBlocks.{2}BLOCK, properties).setRegistryName("{1}block"));""".format(material, material.lower(), material.upper(), sworddamage))
    with open('./src/main/java/com/jojoreference/allomancy/Allomancy.java', 'w') as f:
        f.write(file) 

with open('./src/main/resources/assets/allomancy/lang/en_us.json', 'r') as f:
    file = f.read()
if file.find(material.lower()) == -1:
    file = file.replace("\"Copper Boots\"", """\"Copper Boots\",

  "_comment": "{0}",
  "block.allomancy.{1}ore": "{0} Ore",
  "block.allomancy.{1}block": "Block of {0}",
  "item.allomancy.{1}ingot": "{0} Ingot",
  "item.allomancy.{1}nugget": "{0} Nugget",
  "item.allomancy.{1}pickaxe": "{0} Pickaxe",
  "item.allomancy.{1}axe": "{0} Axe",
  "item.allomancy.{1}sword": "{0} Sword",
  "item.allomancy.{1}shovel": "{0} Shovel",
  "item.allomancy.{1}hoe": "{0} Hoe",
  "item.allomancy.{1}helmet": "{0} Helmet",
  "item.allomancy.{1}chestplate": "{0} Chestplate",
  "item.allomancy.{1}leggings": "{0} Leggings",
  "item.allomancy.{1}boots": "{0} Boots\"""".format(material, material.lower()))
    with open('./src/main/resources/assets/allomancy/lang/en_us.json', 'w') as f:
        f.write(file)

material = material.lower()
blocks = {'block', 'ore'}
for block in blocks:
    with open('./src/main/resources/assets/allomancy/blockstates/{}{}.json'.format(material, block), 'w') as f:
        f.write("""{{
  "variants": {{
    "": {{"model": "allomancy:block/{}{}"}}
  }}
}}""".format(material, block))

    with open('./src/main/resources/assets/allomancy/models/block/{}{}.json'.format(material, block), 'w') as f:
        f.write("""{{
  "parent": "block/cube_all",
  "textures": {{
    "particle": "allomancy:block/{0}{1}",
    "all": "allomancy:block/{1}{1}"
  }}
}}""".format(material, block))

    with open('./src/main/resources/assets/allomancy/models/item/{}{}.json'.format(material, block), 'w') as f:
        f.write("""{{
  "parent": "allomancy:block/{}{}"
}}""".format(material, block))

    with open('./src/main/resources/data/allomancy/loot_tables/blocks/{}{}.json'.format(material, block), 'w') as f:
        f.write("""{{
  "type": "minecraft:block",
  "pools": [
    {{
      "name": "pool1",
      "rolls": 1,
      "entries": [
        {{
          "type": "minecraft:item",
          "name": "allomancy:{}{}"
        }}
      ],
      "conditions": [
        {{
          "condition": "minecraft:survives_explosion"
        }}
      ]
    }}
  ]
}}""".format(material, block))

with open('./src/main/resources/data/forge/tags/blocks/ores/{}.json'.format(material), 'w') as f:
    f.write("""{{
  "replace": false,
  "values": [
    "allomancy:{}ore"
  ]
}}""".format(material))

with open('./src/main/resources/data/forge/tags/blocks/storage_blocks/{}.json'.format(material), 'w') as f:
    f.write("""{{
  "replace": false,
  "values": [
    "allomancy:{}block"
  ]
}}""".format(material))

with open('./src/main/resources/data/forge/tags/items/ingots/{}.json'.format(material), 'w') as f:
    f.write("""{{
  "replace": false,
  "values": [
    "allomancy:{}ingot"
  ]
}}""".format(material))

with open('./src/main/resources/data/forge/tags/items/nuggets/{}.json'.format(material), 'w') as f:
    f.write("""{{
  "replace": false,
  "values": [
    "allomancy:{}nugget"
  ]
}}""".format(material))

with open('./src/main/resources/data/forge/tags/items/ores/{}.json'.format(material), 'w') as f:
    f.write("""{{
  "replace": false,
  "values": [
    "allomancy:{}ore"
  ]
}}""".format(material))

with open('./src/main/resources/data/forge/tags/items/storage_blocks/{}.json'.format(material), 'w') as f:
    f.write("""{{
  "replace": false,
  "values": [
    "allomancy:{}block"
  ]
}}""".format(material))

for tool in tooltypes:
    tool = tool.lower()
    with open('./src/main/resources/assets/allomancy/models/item/{}{}.json'.format(material, tool), 'w') as f:
        f.write("""{{
  "parent": "item/handheld",
  "textures": {{
    "layer0": "allomancy:item/{}{}"
  }}
}}""".format(material, tool))

itemlist = {'ingot', 'nugget', 'boots', 'chestplate', 'helmet', 'leggings'}

for item in itemlist:
    with open('./src/main/resources/assets/allomancy/models/item/{}{}.json'.format(material, item), 'w') as f:
        f.write("""{{
  "parent": "item/generated",
  "textures": {{
    "layer0": "allomancy:item/{}{}"
  }}
}}""".format(material, item))

with open('./src/main/resources/data/allomancy/recipes/{0}_ingot_from_block.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    {{
      "tag": "forge:storage_blocks/{0}"
    }}
  ],
  "result": {{
    "item": "allomancy:{0}ingot",
    "count": 9
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}_block_from_ingot.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "###",
    "###",
    "###"
  ],
  "key": {{
    "#": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}block"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}_ingot_from_nugget.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "###",
    "###",
    "###"
  ],
  "key": {{
    "#": {{
      "tag": "forge:nuggets/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}ingot"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}_nugget_from_ingot.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    {{
      "tag": "forge:ingots/{0}"
    }}
  ],
  "result": {{
    "item": "allomancy:{0}nugget",
    "count": 9
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}_nugget_from_smelting.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:smelting",
  "ingredient": [
    {{
      "item": "allomancy:{0}pickaxe"
    }},
    {{
      "item": "allomancy:{0}shovel"
    }},
    {{
      "item": "allomancy:{0}axe"
    }},
    {{
      "item": "allomancy:{0}hoe"
    }},
    {{
      "item": "allomancy:{0}sword"
    }},
    {{
      "item": "allomancy:{0}helmet"
    }},
    {{
      "item": "allomancy:{0}chestplate"
    }},
    {{
      "item": "allomancy:{0}leggings"
    }},
    {{
      "item": "allomancy:{0}boots"
    }}
  ],
  "result": "allomancy:{0}nugget",
  "experience": 0.1,
  "cookingtime": 200
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}axe.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "XX ",
    "X# ",
    " # "
  ],
  "key": {{
    "#": {{
      "item": "minecraft:stick"
    }},
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}axe"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}boots.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "X X",
    "X X"
  ],
  "key": {{
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}boots"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}chestplate.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "X X",
    "XXX",
    "XXX"
  ],
  "key": {{
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}chestplate"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}helmet.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "XXX",
    "X X"
  ],
  "key": {{
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}helmet"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}hoe.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "XX ",
    " # ",
    " # "
  ],
  "key": {{
    "#": {{
      "item": "minecraft:stick"
    }},
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}hoe"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}ingot.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:smelting",
  "ingredient": {{
    "tag": "forge:ores/{0}"
  }},
  "result": "allomancy:{0}ingot",
  "experience": 0.1,
  "cookingtime": 200
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}leggings.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "XXX",
    "X X",
    "X X"
  ],
  "key": {{
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}leggings"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}pickaxe.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "XXX",
    " # ",
    " # "
  ],
  "key": {{
    "#": {{
      "item": "minecraft:stick"
    }},
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}pickaxe"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}shovel.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    " X ",
    " # ",
    " # "
  ],
  "key": {{
    "#": {{
      "item": "minecraft:stick"
    }},
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}shovel"
  }}
}}""".format(material))
with open('./src/main/resources/data/allomancy/recipes/{0}sword.json'.format(material), 'w') as f:
    f.write("""{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    " X ",
    " X ",
    " # "
  ],
  "key": {{
    "#": {{
      "item": "minecraft:stick"
    }},
    "X": {{
      "tag": "forge:ingots/{0}"
    }}
  }},
  "result": {{
    "item": "allomancy:{0}sword"
  }}
}}""".format(material))