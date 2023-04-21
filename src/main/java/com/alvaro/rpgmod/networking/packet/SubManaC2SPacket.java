package com.alvaro.rpgmod.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.stats.PlayerStatsProvider;

public class SubManaC2SPacket {

    public SubManaC2SPacket() {

    }

    public SubManaC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            assert player != null;
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                if (stats.getMana() > 0){
                    stats.subMana(1);
                    player.sendSystemMessage(Component.literal("Current Mana: " + stats.getMana()).withStyle(ChatFormatting.AQUA));
                }
                else {
                    player.sendSystemMessage(Component.literal("Not enough mana").withStyle(ChatFormatting.RED));
                }
            });
            
        });
    }

}
