package com.alvaro.rpgmod.networking.packet.c2s;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.alvaro.rpgmod.entity.ModEntities;
import com.alvaro.rpgmod.entity.custom.TigerEntity;

public class SummonTammedTigerC2SPacket {

    public SummonTammedTigerC2SPacket() {

    }

    public SummonTammedTigerC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            

            // Add (ItemStack) to correct ambiguous error
            TigerEntity tiger = ModEntities.TIGER.get().spawn(level, (ItemStack)null, null, player.blockPosition(), MobSpawnType.COMMAND, true, false);
            if (tiger != null) {
                 tiger.setTame(true);
                 tiger.setOwnerUUID(player.getUUID());
                }
        });
    }
    
    

}
