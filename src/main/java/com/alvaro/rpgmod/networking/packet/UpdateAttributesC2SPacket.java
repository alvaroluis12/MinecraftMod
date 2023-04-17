package com.alvaro.rpgmod.networking.packet;

import com.alvaro.rpgmod.capabilities.stats.PlayerStatsProvider;
import com.alvaro.rpgmod.networking.ModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class UpdateAttributesC2SPacket {

    public UpdateAttributesC2SPacket() {

    }

    public UpdateAttributesC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){
        
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            //ServerLevel level = player.getLevel();
            assert player != null;
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {

                ModMessages.sendToPlayer(new StatsDataSyncS2C(stats.getMana(),
                                                              stats.getMaxMana(),
                                                              stats.getLevel(),
                                                              stats.getPoints(),
                                                              stats.getStrength(),
                                                              stats.getDex(),
                                                              stats.getCon(),
                                                              stats.getIntelligence(),
                                                              stats.getWisdom()), player);
                //Change player max health
                Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(20 + stats.getCon()*2);
                Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(1.0D + stats.getStrength()/10);
                Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(stats.getCon());
                Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(player.getAttributeValue(Attributes.ATTACK_SPEED) + (double) stats.getDex() /100);
                //Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(Math.min((double) 0.1F + stats.getDex()/100, 0.5D));

            });
        });
    }
}
