package com.alvaro.rpgmod.entity;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.custom.GloblinEntity;
import com.alvaro.rpgmod.entity.custom.TigerEntity;
import com.alvaro.rpgmod.entity.custom.TrollEntity;
import com.alvaro.rpgmod.entity.custom.WindigoEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RPGMod.MODID);

    public static final RegistryObject<EntityType<TigerEntity>> TIGER = 
            ENTITY_TYPES.register("tiger", 
                    () -> EntityType.Builder.of(TigerEntity::new, MobCategory.CREATURE)
                            .sized(1.5f, 1.75f)
                            .build(new ResourceLocation(RPGMod.MODID, "tiger").toString()));
    
    public static final RegistryObject<EntityType<TrollEntity>> TROLL = 
            ENTITY_TYPES.register("troll", 
                    () -> EntityType.Builder.of(TrollEntity::new, MobCategory.MONSTER)
                            .sized(1.5f, 3.5f)
                            .build(new ResourceLocation(RPGMod.MODID, "troll").toString()));
    
    public static final RegistryObject<EntityType<GloblinEntity>> GLOBLIN = 
            ENTITY_TYPES.register("globlin", 
                    () -> EntityType.Builder.of(GloblinEntity::new, MobCategory.MONSTER)
                            .sized(1.5f, 1.75f)
                            .build(new ResourceLocation(RPGMod.MODID, "globlin").toString()));
    
    public static final RegistryObject<EntityType<WindigoEntity>> WINDIGO = 
            ENTITY_TYPES.register("windigo", 
                    () -> EntityType.Builder.of(WindigoEntity::new, MobCategory.MONSTER)
                            .sized(1.5f, 3.5f)
                            .build(new ResourceLocation(RPGMod.MODID, "windigo").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
