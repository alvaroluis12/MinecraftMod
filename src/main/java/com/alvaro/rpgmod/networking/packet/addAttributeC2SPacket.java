package com.alvaro.rpgmod.networking.packet;

import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.stats.PlayerStats;
import com.alvaro.rpgmod.capabilities.stats.PlayerStatsProvider;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class addAttributeC2SPacket {
    private final int attribute;

    public addAttributeC2SPacket(int attributeIndex){
        this.attribute = attributeIndex;
    }

    public addAttributeC2SPacket(FriendlyByteBuf buf){
        this.attribute = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(attribute);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            assert player != null;
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                switch(attribute){
                    case PlayerStats.STR_INDEX:
                        stats.addStrength(1);
                        break;
                    case PlayerStats.CON_INDEX:
                        stats.addCon(1);
                        break;
                    
                    case PlayerStats.DEX_INDEX:
                        stats.addDex(1);
                        break;
                    case PlayerStats.INT_INDEX:
                        stats.addIntelligence(1);
                        break;
                    case PlayerStats.WIS_INDEX:
                        stats.addWisdom(1);
                        break;
                }
            });
            
        });
    }
    
}
