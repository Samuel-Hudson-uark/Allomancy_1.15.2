package com.jojoreference.allomancy;

import com.jojoreference.allomancy.blocks.*;
import com.jojoreference.allomancy.blocks.machines.*;
import com.jojoreference.allomancy.blocks.ores.*;
import com.jojoreference.allomancy.blocks.storage_blocks.*;
import com.jojoreference.allomancy.items.copper.*;
import com.jojoreference.allomancy.items.lerasium.*;
import com.jojoreference.allomancy.items.atium.*;
import com.jojoreference.allomancy.items.aluminium.*;
import com.jojoreference.allomancy.items.brass.*;
import com.jojoreference.allomancy.items.zinc.*;
import com.jojoreference.allomancy.items.bronze.*;
import com.jojoreference.allomancy.items.pewter.*;
import com.jojoreference.allomancy.items.steel.*;
import com.jojoreference.allomancy.items.lead.*;
import com.jojoreference.allomancy.items.tin.*;
import com.jojoreference.allomancy.items.*;
import com.jojoreference.allomancy.items.dusts.*;
import com.jojoreference.allomancy.setup.ClientProxy;
import com.jojoreference.allomancy.setup.IProxy;
import com.jojoreference.allomancy.setup.ModSetup;
import com.jojoreference.allomancy.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("allomancy")
public class Allomancy
{
    public static final String MODID = "allomancy";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup setup = new ModSetup();

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
            //================================================================================
            // Machines
            //================================================================================
            event.getRegistry().register(new AlloyMixer());

            //================================================================================
            // Ores and Blocks
            //================================================================================
            event.getRegistry().register(new CopperOre());
            event.getRegistry().register(new CopperBlock());

            event.getRegistry().register(new LerasiumOre());
            event.getRegistry().register(new LerasiumBlock());

            event.getRegistry().register(new AtiumOre());
            event.getRegistry().register(new AtiumBlock());

            event.getRegistry().register(new AluminiumOre());
            event.getRegistry().register(new AluminiumBlock());

            event.getRegistry().register(new BrassBlock());

            event.getRegistry().register(new ZincOre());
            event.getRegistry().register(new ZincBlock());

            event.getRegistry().register(new BronzeBlock());

            event.getRegistry().register(new PewterBlock());

            event.getRegistry().register(new SteelBlock());

            event.getRegistry().register(new LeadOre());
            event.getRegistry().register(new LeadBlock());

            event.getRegistry().register(new TinOre());
            event.getRegistry().register(new TinBlock());
        }
        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().register(TileEntityType.Builder.create(AlloyMixerTile::new, ModBlocks.ALLOYMIXER).build(null).setRegistryName("alloymixer"));
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new AlloyMixerContainer(windowId, proxy.getClientWorld(), pos, inv, proxy.getClientPlayer());
            }).setRegistryName("alloymixer"));
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties properties = new Item.Properties().group(setup.itemGroup);
            // register a new item here
            event.getRegistry().register(new CarbonDust());

            //================================================================================
            // Machines and Materials
            //================================================================================
            event.getRegistry().register(new BlockItem(ModBlocks.ALLOYMIXER, properties).setRegistryName("alloymixer"));

            event.getRegistry().register(new AllomancerPhial());
            event.getRegistry().register(new AlcoholPhial());
            event.getRegistry().register(new PhialAssembly());
            event.getRegistry().register(new HopsSeeds());
            event.getRegistry().register(new Hops());
            event.getRegistry().register(new EmptyPhial());

            //================================================================================
            // Dusts
            //================================================================================
            event.getRegistry().register(new GoldDust());
            event.getRegistry().register(new IronDust());
            event.getRegistry().register(new ZincDust());
            event.getRegistry().register(new TinDust());
            event.getRegistry().register(new SteelDust());
            event.getRegistry().register(new PewterDust());
            event.getRegistry().register(new LeadDust());
            event.getRegistry().register(new CopperDust());
            event.getRegistry().register(new BronzeDust());
            event.getRegistry().register(new BrassDust());
            event.getRegistry().register(new AluminiumDust());
            event.getRegistry().register(new FailDust());

            //================================================================================
            // Metal items
            //================================================================================
            event.getRegistry().register(new CopperIngot());
            event.getRegistry().register(new CopperNugget());

            event.getRegistry().register(new CopperSword(ToolMaterialList.CopperTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new CopperAxe(ToolMaterialList.CopperTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new CopperPickaxe(ToolMaterialList.CopperTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new CopperShovel(ToolMaterialList.CopperTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new CopperHoe(ToolMaterialList.CopperTier, 0, setup.itemGroup));

            event.getRegistry().register(new CopperHelmet(ArmorMaterialList.CopperTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new CopperChestplate(ArmorMaterialList.CopperTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new CopperLeggings(ArmorMaterialList.CopperTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new CopperBoots(ArmorMaterialList.CopperTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.COPPERORE, properties).setRegistryName("copperore"));
            event.getRegistry().register(new BlockItem(ModBlocks.COPPERBLOCK, properties).setRegistryName("copperblock"));

            event.getRegistry().register(new LerasiumIngot());
            event.getRegistry().register(new LerasiumNugget());

            event.getRegistry().register(new LerasiumSword(ToolMaterialList.LerasiumTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new LerasiumAxe(ToolMaterialList.LerasiumTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new LerasiumPickaxe(ToolMaterialList.LerasiumTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new LerasiumShovel(ToolMaterialList.LerasiumTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new LerasiumHoe(ToolMaterialList.LerasiumTier, 0, setup.itemGroup));

            event.getRegistry().register(new LerasiumHelmet(ArmorMaterialList.LerasiumTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new LerasiumChestplate(ArmorMaterialList.LerasiumTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new LerasiumLeggings(ArmorMaterialList.LerasiumTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new LerasiumBoots(ArmorMaterialList.LerasiumTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.LERASIUMORE, properties).setRegistryName("lerasiumore"));
            event.getRegistry().register(new BlockItem(ModBlocks.LERASIUMBLOCK, properties).setRegistryName("lerasiumblock"));

            event.getRegistry().register(new AtiumIngot());
            event.getRegistry().register(new AtiumNugget());

            event.getRegistry().register(new AtiumSword(ToolMaterialList.AtiumTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new AtiumAxe(ToolMaterialList.AtiumTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new AtiumPickaxe(ToolMaterialList.AtiumTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new AtiumShovel(ToolMaterialList.AtiumTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new AtiumHoe(ToolMaterialList.AtiumTier, 0, setup.itemGroup));

            event.getRegistry().register(new AtiumHelmet(ArmorMaterialList.AtiumTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new AtiumChestplate(ArmorMaterialList.AtiumTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new AtiumLeggings(ArmorMaterialList.AtiumTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new AtiumBoots(ArmorMaterialList.AtiumTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.ATIUMORE, properties).setRegistryName("atiumore"));
            event.getRegistry().register(new BlockItem(ModBlocks.ATIUMBLOCK, properties).setRegistryName("atiumblock"));

            event.getRegistry().register(new AluminiumIngot());
            event.getRegistry().register(new AluminiumNugget());

            event.getRegistry().register(new AluminiumSword(ToolMaterialList.AluminiumTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new AluminiumAxe(ToolMaterialList.AluminiumTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new AluminiumPickaxe(ToolMaterialList.AluminiumTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new AluminiumShovel(ToolMaterialList.AluminiumTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new AluminiumHoe(ToolMaterialList.AluminiumTier, 0, setup.itemGroup));

            event.getRegistry().register(new AluminiumHelmet(ArmorMaterialList.AluminiumTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new AluminiumChestplate(ArmorMaterialList.AluminiumTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new AluminiumLeggings(ArmorMaterialList.AluminiumTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new AluminiumBoots(ArmorMaterialList.AluminiumTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.ALUMINIUMORE, properties).setRegistryName("aluminiumore"));
            event.getRegistry().register(new BlockItem(ModBlocks.ALUMINIUMBLOCK, properties).setRegistryName("aluminiumblock"));

            event.getRegistry().register(new BrassIngot());
            event.getRegistry().register(new BrassNugget());

            event.getRegistry().register(new BrassSword(ToolMaterialList.BrassTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new BrassAxe(ToolMaterialList.BrassTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new BrassPickaxe(ToolMaterialList.BrassTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new BrassShovel(ToolMaterialList.BrassTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new BrassHoe(ToolMaterialList.BrassTier, 0, setup.itemGroup));

            event.getRegistry().register(new BrassHelmet(ArmorMaterialList.BrassTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new BrassChestplate(ArmorMaterialList.BrassTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new BrassLeggings(ArmorMaterialList.BrassTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new BrassBoots(ArmorMaterialList.BrassTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.BRASSBLOCK, properties).setRegistryName("brassblock"));

            event.getRegistry().register(new ZincIngot());
            event.getRegistry().register(new ZincNugget());

            event.getRegistry().register(new ZincSword(ToolMaterialList.ZincTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new ZincAxe(ToolMaterialList.ZincTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new ZincPickaxe(ToolMaterialList.ZincTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new ZincShovel(ToolMaterialList.ZincTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new ZincHoe(ToolMaterialList.ZincTier, 0, setup.itemGroup));

            event.getRegistry().register(new ZincHelmet(ArmorMaterialList.ZincTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new ZincChestplate(ArmorMaterialList.ZincTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new ZincLeggings(ArmorMaterialList.ZincTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new ZincBoots(ArmorMaterialList.ZincTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.ZINCORE, properties).setRegistryName("zincore"));
            event.getRegistry().register(new BlockItem(ModBlocks.ZINCBLOCK, properties).setRegistryName("zincblock"));

            event.getRegistry().register(new BronzeIngot());
            event.getRegistry().register(new BronzeNugget());

            event.getRegistry().register(new BronzeSword(ToolMaterialList.BronzeTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new BronzeAxe(ToolMaterialList.BronzeTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new BronzePickaxe(ToolMaterialList.BronzeTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new BronzeShovel(ToolMaterialList.BronzeTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new BronzeHoe(ToolMaterialList.BronzeTier, 0, setup.itemGroup));

            event.getRegistry().register(new BronzeHelmet(ArmorMaterialList.BronzeTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new BronzeChestplate(ArmorMaterialList.BronzeTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new BronzeLeggings(ArmorMaterialList.BronzeTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new BronzeBoots(ArmorMaterialList.BronzeTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.BRONZEBLOCK, properties).setRegistryName("bronzeblock"));

            event.getRegistry().register(new PewterIngot());
            event.getRegistry().register(new PewterNugget());

            event.getRegistry().register(new PewterSword(ToolMaterialList.PewterTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new PewterAxe(ToolMaterialList.PewterTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new PewterPickaxe(ToolMaterialList.PewterTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new PewterShovel(ToolMaterialList.PewterTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new PewterHoe(ToolMaterialList.PewterTier, 0, setup.itemGroup));

            event.getRegistry().register(new PewterHelmet(ArmorMaterialList.PewterTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new PewterChestplate(ArmorMaterialList.PewterTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new PewterLeggings(ArmorMaterialList.PewterTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new PewterBoots(ArmorMaterialList.PewterTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.PEWTERBLOCK, properties).setRegistryName("pewterblock"));

            event.getRegistry().register(new SteelIngot());
            event.getRegistry().register(new SteelNugget());

            event.getRegistry().register(new SteelSword(ToolMaterialList.SteelTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new SteelAxe(ToolMaterialList.SteelTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new SteelPickaxe(ToolMaterialList.SteelTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new SteelShovel(ToolMaterialList.SteelTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new SteelHoe(ToolMaterialList.SteelTier, 0, setup.itemGroup));

            event.getRegistry().register(new SteelHelmet(ArmorMaterialList.SteelTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new SteelChestplate(ArmorMaterialList.SteelTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new SteelLeggings(ArmorMaterialList.SteelTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new SteelBoots(ArmorMaterialList.SteelTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.STEELBLOCK, properties).setRegistryName("steelblock"));

            event.getRegistry().register(new LeadIngot());
            event.getRegistry().register(new LeadNugget());

            event.getRegistry().register(new LeadSword(ToolMaterialList.LeadTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new LeadAxe(ToolMaterialList.LeadTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new LeadPickaxe(ToolMaterialList.LeadTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new LeadShovel(ToolMaterialList.LeadTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new LeadHoe(ToolMaterialList.LeadTier, 0, setup.itemGroup));

            event.getRegistry().register(new LeadHelmet(ArmorMaterialList.LeadTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new LeadChestplate(ArmorMaterialList.LeadTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new LeadLeggings(ArmorMaterialList.LeadTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new LeadBoots(ArmorMaterialList.LeadTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.LEADORE, properties).setRegistryName("leadore"));
            event.getRegistry().register(new BlockItem(ModBlocks.LEADBLOCK, properties).setRegistryName("leadblock"));

            event.getRegistry().register(new TinIngot());
            event.getRegistry().register(new TinNugget());

            event.getRegistry().register(new TinSword(ToolMaterialList.TinTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new TinAxe(ToolMaterialList.TinTier, 8, -3.1f, setup.itemGroup));
            event.getRegistry().register(new TinPickaxe(ToolMaterialList.TinTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new TinShovel(ToolMaterialList.TinTier, 0, 0, setup.itemGroup));
            event.getRegistry().register(new TinHoe(ToolMaterialList.TinTier, 0, setup.itemGroup));

            event.getRegistry().register(new TinHelmet(ArmorMaterialList.TinTier, EquipmentSlotType.HEAD, setup.itemGroup));
            event.getRegistry().register(new TinChestplate(ArmorMaterialList.TinTier, EquipmentSlotType.CHEST, setup.itemGroup));
            event.getRegistry().register(new TinLeggings(ArmorMaterialList.TinTier, EquipmentSlotType.LEGS, setup.itemGroup));
            event.getRegistry().register(new TinBoots(ArmorMaterialList.TinTier, EquipmentSlotType.FEET, setup.itemGroup));

            event.getRegistry().register(new BlockItem(ModBlocks.TINORE, properties).setRegistryName("tinore"));
            event.getRegistry().register(new BlockItem(ModBlocks.TINBLOCK, properties).setRegistryName("tinblock"));



        }
    }
}
