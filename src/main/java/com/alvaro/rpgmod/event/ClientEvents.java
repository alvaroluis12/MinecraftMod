package com.alvaro.rpgmod.event;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.util.KeyBinding;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = RPGMod.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
             if (KeyBinding.TEST_KEY.consumeClick()){
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed a key!"));
             }
        }
    }

    @Mod.EventBusSubscriber(modid = RPGMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.TEST_KEY);
        }
    }
    
}
