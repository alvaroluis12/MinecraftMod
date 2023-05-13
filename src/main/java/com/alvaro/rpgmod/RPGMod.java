package com.alvaro.rpgmod;

//import org.slf4j.Logger;

import com.alvaro.rpgmod.block.ModBlocks;
import com.alvaro.rpgmod.commands.ModArgumentTypes;
import com.alvaro.rpgmod.config.RPGModClientConfigs;
import com.alvaro.rpgmod.config.RPGModCommonConfigs;
import com.alvaro.rpgmod.entity.ModEntities;
import com.alvaro.rpgmod.entity.client.monster.globlin.GloblinLordRenderer;
import com.alvaro.rpgmod.entity.client.monster.globlin.GloblinRenderer;
import com.alvaro.rpgmod.entity.client.monster.troll.TrollRenderer;
import com.alvaro.rpgmod.entity.client.monster.windigo.WindigoRenderer;
import com.alvaro.rpgmod.entity.client.tiger.TigerRenderer;
import com.alvaro.rpgmod.fluid.ModFluids;
import com.alvaro.rpgmod.fluid.ModFluidsTypes;
import com.alvaro.rpgmod.item.ModCreativeModeTabs;
import com.alvaro.rpgmod.item.ModItems;
//import com.mojang.logging.LogUtils;
import com.alvaro.rpgmod.networking.ModMessages;
import com.alvaro.rpgmod.screen.ModMenuTypes;
import com.alvaro.rpgmod.screen.classes.ClassSelectScreen;
import com.alvaro.rpgmod.screen.quests.QuestsScreen;
import com.alvaro.rpgmod.screen.stats.StatsScreen;
import com.alvaro.rpgmod.villager.ModVillagers;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RPGMod.MODID)
public class RPGMod
{
    public static final String MODID = "rpgmod";

    //private static final Logger LOGGER = LogUtils.getLogger();

    public RPGMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);
        ModVillagers.register(modEventBus);

        ModArgumentTypes.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
        ModMenuTypes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModFluids.register(modEventBus);
        ModFluidsTypes.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(Type.CLIENT, RPGModClientConfigs.SPEC, "rpgmod-client.toml");
        ModLoadingContext.get().registerConfig(Type.COMMON, RPGModCommonConfigs.SPEC, "rpgmod-common.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModMessages.register();

        event.enqueueWork(() -> {
            ModVillagers.registerPOIs();
        });
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.BLACK_OPAL);
            event.accept(ModItems.RAW_BLACK_OPAL);
        }

        if(event.getTab() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.BLACK_OPAL_BLOCK);
        }

        if(event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.QUEST_BLOCK);
        }

        if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.BLACK_OPAL_ORE);
            event.accept(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE);
            //event.accept(ModBlocks.NETHERRACK_BLACK_OPAL_ORE);
            //event.accept(ModBlocks.ENDSTONE_BLACK_OPAL_ORE);

            event.accept(ModBlocks.EBONY_LEAVES);
            event.accept(ModBlocks.EBONY_LOG);
            event.accept(ModBlocks.EBONY_WOOD);
            event.accept(ModBlocks.EBONY_PLANKS);
            event.accept(ModBlocks.STRIPPED_EBONY_LOG);
            event.accept(ModBlocks.STRIPPED_EBONY_WOOD);
            event.accept(ModBlocks.EBONY_SAPLING);
        }

        if(event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.BLACK_OPAL_SWORD);
            event.accept(ModItems.BLACK_OPAL_AXE);
            event.accept(ModItems.GLOBLIN_DAGGER);
            event.accept(ModItems.TOTEM_OF_GLOBLIN_FRIEND);
        }
        if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.BLACK_OPAL_HOE);
            event.accept(ModItems.BLACK_OPAL_AXE);
            event.accept(ModItems.BLACK_OPAL_PICKAXE);
            event.accept(ModItems.BLACK_OPAL_SHOVEL);
            event.accept(ModItems.MANA_FLUID_BUCKET);
        }
        if(event.getTab() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.TIGER_SPAWN_EGG);
            event.accept(ModItems.TROLL_SPAWN_EGG);
            event.accept(ModItems.GLOBLIN_SPAWN_EGG);
            event.accept(ModItems.ARCHER_GLOBLIN_SPAWN_EGG);
            event.accept(ModItems.GLOBLIN_LORD_SPAWN_EGG);
            event.accept(ModItems.WINDIGO_SPAWN_EGG);
        }

        if(event.getTab() == ModCreativeModeTabs.RPG_TAB) {
            event.accept(ModItems.BLACK_OPAL);
            event.accept(ModItems.RAW_BLACK_OPAL);
            event.accept(ModBlocks.BLACK_OPAL_BLOCK);
            event.accept(ModBlocks.BLACK_OPAL_ORE);
            event.accept(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE);
            //event.accept(ModBlocks.NETHERRACK_BLACK_OPAL_ORE);
            //event.accept(ModBlocks.ENDSTONE_BLACK_OPAL_ORE);
            event.accept(ModBlocks.EBONY_LEAVES);
            event.accept(ModBlocks.EBONY_LOG);
            event.accept(ModBlocks.EBONY_WOOD);
            event.accept(ModBlocks.EBONY_PLANKS);
            event.accept(ModBlocks.STRIPPED_EBONY_LOG);
            event.accept(ModBlocks.STRIPPED_EBONY_WOOD);
            event.accept(ModBlocks.EBONY_SAPLING);
            event.accept(ModItems.BLACK_OPAL_SWORD);
            event.accept(ModItems.GLOBLIN_DAGGER);
            event.accept(ModItems.BLACK_OPAL_HOE);
            event.accept(ModItems.BLACK_OPAL_AXE);
            event.accept(ModItems.BLACK_OPAL_PICKAXE);
            event.accept(ModItems.BLACK_OPAL_SHOVEL);
            event.accept(ModItems.TIGER_SPAWN_EGG);
            event.accept(ModItems.TROLL_SPAWN_EGG);
            event.accept(ModItems.GLOBLIN_SPAWN_EGG);
            event.accept(ModItems.GLOBLIN_LORD_SPAWN_EGG);
            event.accept(ModItems.ARCHER_GLOBLIN_SPAWN_EGG);
            event.accept(ModItems.WINDIGO_SPAWN_EGG);
            event.accept(ModItems.MANA_FLUID_BUCKET);
            event.accept(ModItems.TOTEM_OF_GLOBLIN_FRIEND);
            event.accept(ModBlocks.QUEST_BLOCK);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.TIGER.get(), TigerRenderer::new);
            EntityRenderers.register(ModEntities.TROLL.get(), TrollRenderer::new);
            EntityRenderers.register(ModEntities.GLOBLIN.get(), GloblinRenderer::new);
            EntityRenderers.register(ModEntities.ARCHER_GLOBLIN.get(), GloblinRenderer::new);
            EntityRenderers.register(ModEntities.GLOBLIN_LORD.get(), GloblinLordRenderer::new);
            EntityRenderers.register(ModEntities.WINDIGO.get(), WindigoRenderer::new);
            
            MenuScreens.register(ModMenuTypes.Class_Select_Menu.get(), ClassSelectScreen::new);
            MenuScreens.register(ModMenuTypes.Stats_Menu.get(), StatsScreen::new);
            MenuScreens.register(ModMenuTypes.Quests_Menu.get(), QuestsScreen::new);

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_MANA.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_MANA.get(), RenderType.translucent());
        }
    }
}
