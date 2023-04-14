package com.alvaro.rpgmod.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.mana.PlayerManaProvider;

public class TestC2SPacket {

    public TestC2SPacket() {

    }

    public TestC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            //ServerLevel level = player.getLevel();
            

            // Add (ItemStack) to correct ambiguous error
            //ModEntities.TIGER.get().spawn(level, (ItemStack)null, player, player.blockPosition(), MobSpawnType.COMMAND, true, false);
            
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                mana.addMana(1);
                player.sendSystemMessage(Component.literal("Current Mana: " + mana.getMana()).withStyle(ChatFormatting.AQUA));
                //Change player max health
                //player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getAttributeValue(Attributes.MAX_HEALTH) + 1);

            });
        });
        return true;
    }

}
