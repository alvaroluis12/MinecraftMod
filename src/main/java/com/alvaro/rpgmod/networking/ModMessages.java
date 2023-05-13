package com.alvaro.rpgmod.networking;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.networking.packet.c2s.OpenScreenC2SPacket;
import com.alvaro.rpgmod.networking.packet.c2s.SelectClassC2SPacket;
import com.alvaro.rpgmod.networking.packet.c2s.SubManaC2SPacket;
import com.alvaro.rpgmod.networking.packet.c2s.SummonTammedTigerC2SPacket;
import com.alvaro.rpgmod.networking.packet.c2s.UpdateDatasC2SPacket;
import com.alvaro.rpgmod.networking.packet.c2s.addAttributeC2SPacket;
import com.alvaro.rpgmod.networking.packet.c2s.skills.UseDashSkillC2SPacket;
import com.alvaro.rpgmod.networking.packet.s2c.MissionsDataSyncS2C;
import com.alvaro.rpgmod.networking.packet.s2c.StatsDataSyncS2C;

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

        net.messageBuilder(UpdateDatasC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UpdateDatasC2SPacket::new)
                .encoder(UpdateDatasC2SPacket::toBytes)
                .consumerMainThread(UpdateDatasC2SPacket::handle)
                .add(); 

        net.messageBuilder(OpenScreenC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenScreenC2SPacket::new)
                .encoder(OpenScreenC2SPacket::toBytes)
                .consumerMainThread(OpenScreenC2SPacket::handle)
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

        net.messageBuilder(MissionsDataSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(MissionsDataSyncS2C::new)
                .encoder(MissionsDataSyncS2C::toBytes)
                .consumerMainThread(MissionsDataSyncS2C::handle)
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
