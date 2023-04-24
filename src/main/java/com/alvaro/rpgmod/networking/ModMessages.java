package com.alvaro.rpgmod.networking;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.networking.packet.OpenCloseStatsScreenC2SPacket;
import com.alvaro.rpgmod.networking.packet.SelectClassC2SPacket;
import com.alvaro.rpgmod.networking.packet.StatsDataSyncS2C;
import com.alvaro.rpgmod.networking.packet.SummonTammedTigerC2SPacket;
import com.alvaro.rpgmod.networking.packet.UpdateAttributesC2SPacket;
import com.alvaro.rpgmod.networking.packet.addAttributeC2SPacket;
import com.alvaro.rpgmod.networking.packet.skills.UseDashSkillC2SPacket;
import com.alvaro.rpgmod.networking.packet.SubManaC2SPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    public static SimpleChannel INSTANCE;

    private static int packetId = 0;
    
    private static int id() {
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                            .named(new ResourceLocation(RPGMod.MODID, "messages"))
                            .networkProtocolVersion(() -> "1.0")
                            .clientAcceptedVersions(s -> true)
                            .serverAcceptedVersions(s -> true)
                            .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(SummonTammedTigerC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SummonTammedTigerC2SPacket::new)
                .encoder(SummonTammedTigerC2SPacket::toBytes)
                .consumerMainThread(SummonTammedTigerC2SPacket::handle)
                .add();

        net.messageBuilder(SubManaC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SubManaC2SPacket::new)
                .encoder(SubManaC2SPacket::toBytes)
                .consumerMainThread(SubManaC2SPacket::handle)
                .add();

        net.messageBuilder(UpdateAttributesC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UpdateAttributesC2SPacket::new)
                .encoder(UpdateAttributesC2SPacket::toBytes)
                .consumerMainThread(UpdateAttributesC2SPacket::handle)
                .add(); 

        net.messageBuilder(OpenCloseStatsScreenC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenCloseStatsScreenC2SPacket::new)
                .encoder(OpenCloseStatsScreenC2SPacket::toBytes)
                .consumerMainThread(OpenCloseStatsScreenC2SPacket::handle)
                .add(); 

        net.messageBuilder(addAttributeC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(addAttributeC2SPacket::new)
                .encoder(addAttributeC2SPacket::toBytes)
                .consumerMainThread(addAttributeC2SPacket::handle)
                .add(); 

        net.messageBuilder(StatsDataSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(StatsDataSyncS2C::new)
                .encoder(StatsDataSyncS2C::toBytes)
                .consumerMainThread(StatsDataSyncS2C::handle)
                .add();

        net.messageBuilder(SelectClassC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SelectClassC2SPacket::new)
                .encoder(SelectClassC2SPacket::toBytes)
                .consumerMainThread(SelectClassC2SPacket::handle)
                .add();

        net.messageBuilder(UseDashSkillC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UseDashSkillC2SPacket::new)
                .encoder(UseDashSkillC2SPacket::toBytes)
                .consumerMainThread(UseDashSkillC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
    
}
