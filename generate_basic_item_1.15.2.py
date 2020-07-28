import os

'Change this before running.'
itemEnglishName = "Copper Clip"

itemClassName = itemEnglishName.replace(" ", "")
itemName = itemClassName.lower()

with open('./src/main/java/com/jojoreference/allomancy/items/{}.java'.format(itemClassName), 'w') as f:
    f.write("""package com.jojoreference.allomancy.items;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.item.Item;

public class {0} extends Item {{
    public {0}() {{
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("{1}");
    }}
}}""".format(itemClassName, itemName))

with open('./src/main/java/com/jojoreference/allomancy/Allomancy.java', 'r') as f:
    file = f.read()
file = file.replace("// register a new item here", """// register a new item here
            event.getRegistry().register(new {}());""".format(itemClassName))

with open('./src/main/java/com/jojoreference/allomancy/Allomancy.java', 'w') as f:
    f.write(file)

with open('./src/main/resources/assets/allomancy/lang/en_us.json', 'r') as f:
    file = f.read()
file = file.replace('  "_comment": "Materials",', """  "_comment": "Materials",
  "item.allomancy.{}": "{}",""".format(itemName, itemEnglishName))

with open('./src/main/resources/assets/allomancy/lang/en_us.json', 'w') as f:
    f.write(file)

with open('./src/main/resources/assets/allomancy/models/item/{}.json'.format(itemName), 'w') as f:
    f.write("""{{
  "parent": "item/generated",
  "textures": {{
    "layer0": "allomancy:item/{}"
  }}
}}""".format(itemName))