package com.alvaro.rpgmod.villager;

import java.lang.reflect.InvocationTargetException;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.block.ModBlocks;
import com.google.common.collect.ImmutableSet;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, RPGMod.MODID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, RPGMod.MODID);

    public static final RegistryObject<PoiType> QUEST_BLOCK_POI = POI_TYPES.register("quest_block_poi", () -> 
        new PoiType(ImmutableSet.copyOf(ModBlocks.QUEST_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));
    
    public static final RegistryObject<VillagerProfession> QUEST_MASTER = VILLAGER_PROFESSIONS.register("quest_master", () ->
        new VillagerProfession("quest_master",
                                    x -> x.get() == QUEST_BLOCK_POI.get(),
                                    x -> x.get() == QUEST_BLOCK_POI.get(),
                                    ImmutableSet.of(), 
                                    ImmutableSet.of(),
                                    SoundEvents.VILLAGER_WORK_ARMORER));

    public static void registerPOIs(){
        try{
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, QUEST_BLOCK_POI.get());
        }catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
