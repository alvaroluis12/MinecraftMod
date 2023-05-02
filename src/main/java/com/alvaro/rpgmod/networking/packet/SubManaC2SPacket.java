package com.alvaro.rpgmod.networking.packet;

import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.items.ItemEffectsProvider;
import com.alvaro.rpgmod.capabilities.stats.PlayerStatsProvider;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

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

            player.getCapability(ItemEffectsProvider.ITEM_EFFECTS).ifPresent(effects -> {
                effects.setGloblinFriend(false);
            });
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                stats.resetPlayerToDefault();
                if (stats.getMana() > 0){
                    stats.subMana(1);
                    //player.sendSystemMessage(Component.literal("Current Mana: " + stats.getMana()).withStyle(ChatFormatting.AQUA));
                }
                else {
                    player.sendSystemMessage(Component.literal("Not enough mana").withStyle(ChatFormatting.RED));
                }
            });
        });
    }

}
