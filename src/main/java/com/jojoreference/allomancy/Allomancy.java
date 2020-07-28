package com.jojoreference.allomancy;

import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.blocks.fluidblocks.MoltenIronBlock;
import com.jojoreference.allomancy.blocks.machines.*;
import com.jojoreference.allomancy.blocks.ores.*;
import com.jojoreference.allomancy.blocks.storage_blocks.*;
import com.jojoreference.allomancy.capabilities.Mistborn;
import com.jojoreference.allomancy.capabilities.MistbornProvider;
import com.jojoreference.allomancy.entity.CopperClipEntity;
import com.jojoreference.allomancy.entity.CopperClipRenderer;
import com.jojoreference.allomancy.entity.ModEntities;
import com.jojoreference.allomancy.fluids.ModFluids;
import com.jojoreference.allomancy.items.*;
import com.jojoreference.allomancy.items.aluminium.*;
import com.jojoreference.allomancy.items.atium.*;
import com.jojoreference.allomancy.items.brass.*;
import com.jojoreference.allomancy.items.bronze.*;
import com.jojoreference.allomancy.items.copper.*;
import com.jojoreference.allomancy.items.dusts.*;
import com.jojoreference.allomancy.items.lead.*;
import com.jojoreference.allomancy.items.lerasium.*;
import com.jojoreference.allomancy.items.pewter.*;
import com.jojoreference.allomancy.items.steel.*;
import com.jojoreference.allomancy.items.tin.*;
import com.jojoreference.allomancy.items.zinc.*;
import com.jojoreference.allomancy.recipes.*;
import com.jojoreference.allomancy.setup.ClientProxy;
import com.jojoreference.allomancy.setup.IProxy;
import com.jojoreference.allomancy.setup.ModSetup;
import com.jojoreference.allomancy.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("allomancy")
public class Allomancy
{
    public static final String MODID = "allomancy";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup setup = new ModSetup();

    public static final IRecipeType<MelterRecipe> MELTER_RECIPE = new RecipeTypeMelter();
    public static final IRecipeType<AlloyRecipe> ALLOY_RECIPE = new RecipeTypeAlloy();

    public Allomancy() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        setup.init();
        proxy.init();
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
    public static class EventRegister {

        @SubscribeEvent
        public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof PlayerEntity) {
                System.out.println("Mistborn capability attached");
                event.addCapability(new ResourceLocation("allomancy", "mistborn_cap"), new MistbornProvider());
            }
        }

        @SubscribeEvent
        public static void onPlayerLogsIn(PlayerEvent.PlayerLoggedInEvent event) {
            if(event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = ((PlayerEntity) event.getEntity());
                Mistborn mistborn = (Mistborn) player.getCapability(MistbornProvider.MISTBORN_CAPABILITY, null).orElse(new Mistborn());
                if(proxy instanceof ClientProxy) {
                    mistborn.gain(1, false);
                    player.sendMessage(new StringTextComponent("Amount of metal: " + mistborn.getResource()));
                }
            }

        }
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
            event.getRegistry().register(new Melter());

            //================================================================================
            // Fluid Blocks
            //================================================================================
            event.getRegistry().register(new MoltenIronBlock());

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
            event.getRegistry().register(TileEntityType.Builder.create(MelterTile::new, ModBlocks.MELTER).build(null).setRegistryName("melter"));

        }
        @SubscribeEvent
        public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
            event.getRegistry().registerAll(
                    ModEntities.COPPERCLIP_ENTITY = (EntityType<CopperClipEntity>) EntityType.Builder.<CopperClipEntity>create(CopperClipEntity::new, EntityClassification.MISC).size(1f, 1f).setCustomClientFactory((spawnEntity, world) -> new CopperClipEntity(world)).build("allomancy:copperclip").setRegistryName("allomancy:copperclip")
            );
        }

        @SubscribeEvent
        public static void clientRegistries(FMLClientSetupEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(ModEntities.COPPERCLIP_ENTITY, new CopperClipRenderer.Factory());
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new AlloyMixerContainer(windowId, proxy.getClientWorld(), pos, inv, proxy.getClientPlayer());
            }).setRegistryName("alloymixer"));
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new MelterContainer(windowId, proxy.getClientWorld(), pos, inv, proxy.getClientPlayer());
            }).setRegistryName("melter"));
        }

        @SubscribeEvent
        public static void onRegisterRecipeSerializers(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
            event.getRegistry().registerAll(
                    new MelterRecipeSerializer(),
                    new AlloyRecipeSerializer()
            );
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties properties = new Item.Properties().group(setup.itemGroup);
            //================================================================================
            // Machines and Materials
            //================================================================================
            event.getRegistry().registerAll(
                    new BlockItem(ModBlocks.ALLOYMIXER, properties).setRegistryName("alloymixer"),
            new BlockItem(ModBlocks.MELTER, properties).setRegistryName("melter"),

            new AllomancerPhial(),
            new AlcoholPhial(),
            new PhialAssembly(),
            new HopsSeeds(),
            new Hops(),
            new EmptyPhial(),
            new CopperClip(),
            //================================================================================
            // Dusts
            //================================================================================
            new CarbonDust(),
            new GoldDust(),
            new IronDust(),
            new ZincDust(),
            new TinDust(),
            new SteelDust(),
            new PewterDust(),
            new LeadDust(),
            new CopperDust(),
            new BronzeDust(),
            new BrassDust(),
            new AluminiumDust(),
            new FailDust(),
            //================================================================================
            // Tools
            //================================================================================
            new Crucible(),
            new MoltenIronBucket(),
            new DiamondDuelingPickaxe(ItemTier.DIAMOND, 0, 0, setup.itemGroup),
            new DiamondDuelingSword(ItemTier.DIAMOND, 7, 1.6f, setup.itemGroup),
            //================================================================================
            // Metal items
            //================================================================================
            new CopperIngot(),
            new CopperNugget(),

            new CopperSword(ToolMaterialList.CopperTier, 8, -3.1f, setup.itemGroup),
            new CopperAxe(ToolMaterialList.CopperTier, 8, -3.1f, setup.itemGroup),
            new CopperPickaxe(ToolMaterialList.CopperTier, 0, 0, setup.itemGroup),
            new CopperShovel(ToolMaterialList.CopperTier, 0, 0, setup.itemGroup),
            new CopperHoe(ToolMaterialList.CopperTier, 0, setup.itemGroup),

            new CopperHelmet(ArmorMaterialList.CopperTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new CopperChestplate(ArmorMaterialList.CopperTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new CopperLeggings(ArmorMaterialList.CopperTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new CopperBoots(ArmorMaterialList.CopperTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.COPPERORE, properties).setRegistryName("copperore"),
            new BlockItem(ModBlocks.COPPERBLOCK, properties).setRegistryName("copperblock"),

            new LerasiumIngot(),
            new LerasiumNugget(),

            new LerasiumSword(ToolMaterialList.LerasiumTier, 8, -3.1f, setup.itemGroup),
            new LerasiumAxe(ToolMaterialList.LerasiumTier, 8, -3.1f, setup.itemGroup),
            new LerasiumPickaxe(ToolMaterialList.LerasiumTier, 0, 0, setup.itemGroup),
            new LerasiumShovel(ToolMaterialList.LerasiumTier, 0, 0, setup.itemGroup),
            new LerasiumHoe(ToolMaterialList.LerasiumTier, 0, setup.itemGroup),

            new LerasiumHelmet(ArmorMaterialList.LerasiumTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new LerasiumChestplate(ArmorMaterialList.LerasiumTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new LerasiumLeggings(ArmorMaterialList.LerasiumTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new LerasiumBoots(ArmorMaterialList.LerasiumTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.LERASIUMORE, properties).setRegistryName("lerasiumore"),
            new BlockItem(ModBlocks.LERASIUMBLOCK, properties).setRegistryName("lerasiumblock"),

            new AtiumIngot(),
            new AtiumNugget(),

            new AtiumSword(ToolMaterialList.AtiumTier, 8, -3.1f, setup.itemGroup),
            new AtiumAxe(ToolMaterialList.AtiumTier, 8, -3.1f, setup.itemGroup),
            new AtiumPickaxe(ToolMaterialList.AtiumTier, 0, 0, setup.itemGroup),
            new AtiumShovel(ToolMaterialList.AtiumTier, 0, 0, setup.itemGroup),
            new AtiumHoe(ToolMaterialList.AtiumTier, 0, setup.itemGroup),

            new AtiumHelmet(ArmorMaterialList.AtiumTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new AtiumChestplate(ArmorMaterialList.AtiumTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new AtiumLeggings(ArmorMaterialList.AtiumTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new AtiumBoots(ArmorMaterialList.AtiumTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.ATIUMORE, properties).setRegistryName("atiumore"),
            new BlockItem(ModBlocks.ATIUMBLOCK, properties).setRegistryName("atiumblock"),

            new AluminiumIngot(),
            new AluminiumNugget(),

            new AluminiumSword(ToolMaterialList.AluminiumTier, 8, -3.1f, setup.itemGroup),
            new AluminiumAxe(ToolMaterialList.AluminiumTier, 8, -3.1f, setup.itemGroup),
            new AluminiumPickaxe(ToolMaterialList.AluminiumTier, 0, 0, setup.itemGroup),
            new AluminiumShovel(ToolMaterialList.AluminiumTier, 0, 0, setup.itemGroup),
            new AluminiumHoe(ToolMaterialList.AluminiumTier, 0, setup.itemGroup),

            new AluminiumHelmet(ArmorMaterialList.AluminiumTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new AluminiumChestplate(ArmorMaterialList.AluminiumTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new AluminiumLeggings(ArmorMaterialList.AluminiumTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new AluminiumBoots(ArmorMaterialList.AluminiumTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.ALUMINIUMORE, properties).setRegistryName("aluminiumore"),
            new BlockItem(ModBlocks.ALUMINIUMBLOCK, properties).setRegistryName("aluminiumblock"),

            new BrassIngot(),
            new BrassNugget(),

            new BrassSword(ToolMaterialList.BrassTier, 8, -3.1f, setup.itemGroup),
            new BrassAxe(ToolMaterialList.BrassTier, 8, -3.1f, setup.itemGroup),
            new BrassPickaxe(ToolMaterialList.BrassTier, 0, 0, setup.itemGroup),
            new BrassShovel(ToolMaterialList.BrassTier, 0, 0, setup.itemGroup),
            new BrassHoe(ToolMaterialList.BrassTier, 0, setup.itemGroup),

            new BrassHelmet(ArmorMaterialList.BrassTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new BrassChestplate(ArmorMaterialList.BrassTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new BrassLeggings(ArmorMaterialList.BrassTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new BrassBoots(ArmorMaterialList.BrassTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.BRASSBLOCK, properties).setRegistryName("brassblock"),

            new ZincIngot(),
            new ZincNugget(),

            new ZincSword(ToolMaterialList.ZincTier, 8, -3.1f, setup.itemGroup),
            new ZincAxe(ToolMaterialList.ZincTier, 8, -3.1f, setup.itemGroup),
            new ZincPickaxe(ToolMaterialList.ZincTier, 0, 0, setup.itemGroup),
            new ZincShovel(ToolMaterialList.ZincTier, 0, 0, setup.itemGroup),
            new ZincHoe(ToolMaterialList.ZincTier, 0, setup.itemGroup),

            new ZincHelmet(ArmorMaterialList.ZincTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new ZincChestplate(ArmorMaterialList.ZincTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new ZincLeggings(ArmorMaterialList.ZincTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new ZincBoots(ArmorMaterialList.ZincTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.ZINCORE, properties).setRegistryName("zincore"),
            new BlockItem(ModBlocks.ZINCBLOCK, properties).setRegistryName("zincblock"),

            new BronzeIngot(),
            new BronzeNugget(),

            new BronzeSword(ToolMaterialList.BronzeTier, 8, -3.1f, setup.itemGroup),
            new BronzeAxe(ToolMaterialList.BronzeTier, 8, -3.1f, setup.itemGroup),
            new BronzePickaxe(ToolMaterialList.BronzeTier, 0, 0, setup.itemGroup),
            new BronzeShovel(ToolMaterialList.BronzeTier, 0, 0, setup.itemGroup),
            new BronzeHoe(ToolMaterialList.BronzeTier, 0, setup.itemGroup),

            new BronzeHelmet(ArmorMaterialList.BronzeTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new BronzeChestplate(ArmorMaterialList.BronzeTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new BronzeLeggings(ArmorMaterialList.BronzeTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new BronzeBoots(ArmorMaterialList.BronzeTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.BRONZEBLOCK, properties).setRegistryName("bronzeblock"),

            new PewterIngot(),
            new PewterNugget(),

            new PewterSword(ToolMaterialList.PewterTier, 8, -3.1f, setup.itemGroup),
            new PewterAxe(ToolMaterialList.PewterTier, 8, -3.1f, setup.itemGroup),
            new PewterPickaxe(ToolMaterialList.PewterTier, 0, 0, setup.itemGroup),
            new PewterShovel(ToolMaterialList.PewterTier, 0, 0, setup.itemGroup),
            new PewterHoe(ToolMaterialList.PewterTier, 0, setup.itemGroup),

            new PewterHelmet(ArmorMaterialList.PewterTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new PewterChestplate(ArmorMaterialList.PewterTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new PewterLeggings(ArmorMaterialList.PewterTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new PewterBoots(ArmorMaterialList.PewterTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.PEWTERBLOCK, properties).setRegistryName("pewterblock"),

            new SteelIngot(),
            new SteelNugget(),

            new SteelSword(ToolMaterialList.SteelTier, 8, -3.1f, setup.itemGroup),
            new SteelAxe(ToolMaterialList.SteelTier, 8, -3.1f, setup.itemGroup),
            new SteelPickaxe(ToolMaterialList.SteelTier, 0, 0, setup.itemGroup),
            new SteelShovel(ToolMaterialList.SteelTier, 0, 0, setup.itemGroup),
            new SteelHoe(ToolMaterialList.SteelTier, 0, setup.itemGroup),

            new SteelHelmet(ArmorMaterialList.SteelTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new SteelChestplate(ArmorMaterialList.SteelTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new SteelLeggings(ArmorMaterialList.SteelTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new SteelBoots(ArmorMaterialList.SteelTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.STEELBLOCK, properties).setRegistryName("steelblock"),

            new LeadIngot(),
            new LeadNugget(),

            new LeadSword(ToolMaterialList.LeadTier, 8, -3.1f, setup.itemGroup),
            new LeadAxe(ToolMaterialList.LeadTier, 8, -3.1f, setup.itemGroup),
            new LeadPickaxe(ToolMaterialList.LeadTier, 0, 0, setup.itemGroup),
            new LeadShovel(ToolMaterialList.LeadTier, 0, 0, setup.itemGroup),
            new LeadHoe(ToolMaterialList.LeadTier, 0, setup.itemGroup),

            new LeadHelmet(ArmorMaterialList.LeadTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new LeadChestplate(ArmorMaterialList.LeadTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new LeadLeggings(ArmorMaterialList.LeadTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new LeadBoots(ArmorMaterialList.LeadTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.LEADORE, properties).setRegistryName("leadore"),
            new BlockItem(ModBlocks.LEADBLOCK, properties).setRegistryName("leadblock"),

            new TinIngot(),
            new TinNugget(),

            new TinSword(ToolMaterialList.TinTier, 8, -3.1f, setup.itemGroup),
            new TinAxe(ToolMaterialList.TinTier, 8, -3.1f, setup.itemGroup),
            new TinPickaxe(ToolMaterialList.TinTier, 0, 0, setup.itemGroup),
            new TinShovel(ToolMaterialList.TinTier, 0, 0, setup.itemGroup),
            new TinHoe(ToolMaterialList.TinTier, 0, setup.itemGroup),

            new TinHelmet(ArmorMaterialList.TinTier, EquipmentSlotType.HEAD, setup.itemGroup),
            new TinChestplate(ArmorMaterialList.TinTier, EquipmentSlotType.CHEST, setup.itemGroup),
            new TinLeggings(ArmorMaterialList.TinTier, EquipmentSlotType.LEGS, setup.itemGroup),
            new TinBoots(ArmorMaterialList.TinTier, EquipmentSlotType.FEET, setup.itemGroup),

            new BlockItem(ModBlocks.TINORE, properties).setRegistryName("tinore"),
            new BlockItem(ModBlocks.TINBLOCK, properties).setRegistryName("tinblock")
            );




        }

        @SubscribeEvent
        public static void registerFluids(final RegistryEvent.Register<Fluid> event) {
            event.getRegistry().register(ModFluids.IRON);
            event.getRegistry().register(ModFluids.FLOWING_IRON);
        }
    }
}
