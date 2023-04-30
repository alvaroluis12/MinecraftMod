package com.alvaro.rpgmod.worldgen;

import java.util.List;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_KEY = registerKey("ebony");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_OPAL_ORE_LARGE = registerKey("black_opal_ore_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_OPAL_ORE = registerKey("black_opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_OPAL_ORE_SMALL = registerKey("black_opal_ore_smal");
    //public static final ResourceKey<ConfiguredFeature<?, ?>> END_BLACK_OPAL_ORE_KEY = registerKey("end_black_opal_ore");
    //public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BLACK_OPAL_ORE_KEY = registerKey("nether_black_opal_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepstoneslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        //RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        //RuleTest endstoneReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldBlackOpalOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.BLACK_OPAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepstoneslateReplaceables, ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get().defaultBlockState()));
        
        register(context, BLACK_OPAL_ORE_LARGE, Feature.ORE, new OreConfiguration(overworldBlackOpalOres, 12));
        register(context, BLACK_OPAL_ORE, Feature.ORE, new OreConfiguration(overworldBlackOpalOres, 4));
        register(context, BLACK_OPAL_ORE_SMALL, Feature.ORE, new OreConfiguration(overworldBlackOpalOres, 8));
        //register(context, END_BLACK_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceables, ModBlocks.ENDSTONE_BLACK_OPAL_ORE.get().defaultBlockState(), 7));
        //register(context, NETHER_BLACK_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, ModBlocks.NETHERRACK_BLACK_OPAL_ORE.get().defaultBlockState(), 6));
        
        register(context, EBONY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(ModBlocks.EBONY_LOG.get()),
            new StraightTrunkPlacer(5, 6, 3),
            BlockStateProvider.simple(ModBlocks.EBONY_LEAVES.get()), 
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
            new TwoLayersFeatureSize(1, 0, 2)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(RPGMod.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>>context,
                                                                                            ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
