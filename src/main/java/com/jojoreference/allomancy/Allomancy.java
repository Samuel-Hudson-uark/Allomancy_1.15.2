package com.jojoreference.allomancy;

import com.jojoreference.allomancy.blocks.CopperBlock;
import com.jojoreference.allomancy.blocks.CopperOre;
import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.items.ArmorMaterialList;
import com.jojoreference.allomancy.items.copper.*;
import com.jojoreference.allomancy.items.ToolMaterialList;
import com.jojoreference.allomancy.setup.ClientProxy;
import com.jojoreference.allomancy.setup.IProxy;
import com.jojoreference.allomancy.setup.ModSetup;
import com.jojoreference.allomancy.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("allomancy")
public class Allomancy
{
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup setup = new ModSetup();

    private static final Logger LOGGER = LogManager.getLogger();

    public Allomancy() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        setup.init();
        proxy.init();
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            // register a new block here
            event.getRegistry().register(new CopperOre());
            event.getRegistry().register(new CopperBlock());
        }
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties properties = new Item.Properties().group(setup.itemGroup);
            // register a new item here
            event.getRegistry().register(new CopperIngot());
            event.getRegistry().register(new CopperNugget());

            event.getRegistry().register(new CopperSword(ToolMaterialList.CopperTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new CopperAxe(ToolMaterialList.CopperTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new CopperPickaxe(ToolMaterialList.CopperTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new CopperShovel(ToolMaterialList.CopperTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new CopperHoe(ToolMaterialList.CopperTier, 0, 0, setup.itemGroup));

            event.getRegistry().register(new CopperHelmet(ArmorMaterialList.CopperTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new CopperChestplate(ArmorMaterialList.CopperTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new CopperLeggings(ArmorMaterialList.CopperTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new CopperBoots(ArmorMaterialList.CopperTier, EquipmentSlotType.FEET, setup.itemGroup));

            // register a new block item here
            event.getRegistry().register(new BlockItem(ModBlocks.COPPERORE, properties).setRegistryName("copperore"));
            event.getRegistry().register(new BlockItem(ModBlocks.COPPERBLOCK, properties).setRegistryName("copperblock"));
        }
    }
}
