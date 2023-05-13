package com.alvaro.rpgmod.event;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.client.ManaHudOverlay;
import com.alvaro.rpgmod.networking.ModMessages;
import com.alvaro.rpgmod.networking.packet.c2s.OpenScreenC2SPacket;
import com.alvaro.rpgmod.networking.packet.c2s.SubManaC2SPacket;
import com.alvaro.rpgmod.networking.packet.c2s.skills.UseDashSkillC2SPacket;
import com.alvaro.rpgmod.util.KeyBinding;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = RPGMod.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
             if (KeyBinding.TEST_KEY.consumeClick()){
                ModMessages.sendToServer(new UseDashSkillC2SPacket());
                //ModMessages.sendToServer(new SummonTammedTigerC2SPacket());
             }

             if (KeyBinding.SKILL_1_KEY.consumeClick()){
                ModMessages.sendToServer(new SubManaC2SPacket());
             }
             if(KeyBinding.STATS_GUI_KEY.consumeClick()){
                ModMessages.sendToServer(new OpenScreenC2SPacket(0));
             }
             if(KeyBinding.QUESTS_GUI_KEY.consumeClick()){
                ModMessages.sendToServer(new OpenScreenC2SPacket(2));
             }
        }
    }

    @Mod.EventBusSubscriber(modid = RPGMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.TEST_KEY);
            event.register(KeyBinding.STATS_GUI_KEY);
            event.register(KeyBinding.QUESTS_GUI_KEY);
            event.register(KeyBinding.SKILL_1_KEY);
            event.register(KeyBinding.SKILL_2_KEY);
            event.register(KeyBinding.SKILL_3_KEY);
            event.register(KeyBinding.SKILL_4_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("mana", ManaHudOverlay.HUD_MANA);
        }

    }
    
}
