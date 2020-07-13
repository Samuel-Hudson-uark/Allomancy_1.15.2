package com.jojoreference.allomancy;

import com.jojoreference.allomancy.blocks.CopperOre;
import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.items.CopperIngot;
import com.jojoreference.allomancy.setup.ClientProxy;
import com.jojoreference.allomancy.setup.IProxy;
import com.jojoreference.allomancy.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("allomancy")
public class Allomancy
{
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Allomancy() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            // register a new block here
            event.getRegistry().register(new CopperOre());
        }
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            // register a new item here
            event.getRegistry().register(new CopperIngot());

            // register a new block item here
            event.getRegistry().register(new BlockItem(ModBlocks.COPPERORE, new Item.Properties()).setRegistryName("copperore"));
        }
    }
}
