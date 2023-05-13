package com.alvaro.rpgmod.networking.packet.c2s;

import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.stats.PlayerStatsProvider;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class SelectClassC2SPacket {
    private final int playerClass;

    public SelectClassC2SPacket(int playerClass){
        this.playerClass = playerClass;
    }

    public SelectClassC2SPacket(FriendlyByteBuf buf){
        this.playerClass = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(playerClass);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                stats.setPlayerClass(this.playerClass);
            });
            
        });
    }
    
}
