package com.alvaro.rpgmod.event;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.ModEntities;
import com.alvaro.rpgmod.entity.custom.TigerEntity;
import com.alvaro.rpgmod.entity.custom.TrollEntity;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RPGMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TIGER.get(), TigerEntity.setAttributes());
        event.put(ModEntities.TROLL.get(), TrollEntity.setAttributes());
    }
}
