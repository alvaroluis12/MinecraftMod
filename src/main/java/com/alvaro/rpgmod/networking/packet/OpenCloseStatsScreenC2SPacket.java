package com.alvaro.rpgmod.networking.packet;

import java.util.function.Supplier;

import com.alvaro.rpgmod.client.ClientStatsData;
import com.alvaro.rpgmod.screen.StatsMenu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

public class OpenCloseStatsScreenC2SPacket {
    public OpenCloseStatsScreenC2SPacket() {

    }

    public OpenCloseStatsScreenC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            NetworkHooks.openScreen(player, new SimpleMenuProvider(
                (containerId, playerInventory, nul) -> new StatsMenu(containerId, playerInventory),
                Component.literal("Level: " + ClientStatsData.getLevel())
            ));
            
        });
    }
    
}
