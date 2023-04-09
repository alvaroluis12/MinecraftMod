package com.alvaro.rpgmod.worldgen.tree;

import javax.annotation.Nullable;

import com.alvaro.rpgmod.worldgen.ModConfiguredFeatures;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class EbonyTreeGrower extends AbstractTreeGrower{

    @Override
    @Nullable
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
        return ModConfiguredFeatures.EBONY_KEY;
    }
    
}
