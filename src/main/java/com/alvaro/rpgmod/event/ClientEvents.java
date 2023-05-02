package com.alvaro.rpgmod.event;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.client.ManaHudOverlay;
import com.alvaro.rpgmod.networking.ModMessages;
import com.alvaro.rpgmod.networking.packet.OpenCloseStatsScreenC2SPacket;
import com.alvaro.rpgmod.networking.packet.SubManaC2SPacket;
import com.alvaro.rpgmod.networking.packet.skills.UseDashSkillC2SPacket;
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
             if(KeyBinding.GUI_KEY.consumeClick()){
                ModMessages.sendToServer(new OpenCloseStatsScreenC2SPacket());
             }
        }
    }

    @Mod.EventBusSubscriber(modid = RPGMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.TEST_KEY);
            event.register(KeyBinding.GUI_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("mana", ManaHudOverlay.HUD_MANA);
        }

    }
    
}
