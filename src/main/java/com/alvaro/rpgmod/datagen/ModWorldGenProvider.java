package com.alvaro.rpgmod.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.worldgen.ModConfiguredFeatures;
import com.alvaro.rpgmod.worldgen.ModPlacedFeatures;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(RPGMod.MODID));
    }
    
}
