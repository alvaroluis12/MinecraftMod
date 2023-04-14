package com.alvaro.rpgmod.networking;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.networking.packet.SummonTammedTigerC2SPacket;
import com.alvaro.rpgmod.networking.packet.TestC2SPacket;

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

        net.messageBuilder(TestC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TestC2SPacket::new)
                .encoder(TestC2SPacket::toBytes)
                .consumerMainThread(TestC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
    
}
