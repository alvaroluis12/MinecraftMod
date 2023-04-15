package com.alvaro.rpgmod.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.mana.PlayerManaProvider;
import com.alvaro.rpgmod.networking.ModMessages;

public class SubManaC2SPacket {

    public SubManaC2SPacket() {

    }

    public SubManaC2SPacket(FriendlyByteBuf buf){

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
                if (mana.getMana() > 0){
                    mana.subMana(1);
                    player.sendSystemMessage(Component.literal("Current Mana: " + mana.getMana()).withStyle(ChatFormatting.AQUA));
                }
                else {
                    player.sendSystemMessage(Component.literal("Not enough mana").withStyle(ChatFormatting.RED));
                }

                ModMessages.sendToPlayer(new ManaDataSyncS2C(mana.getMana(), mana.getMaxMana()), player);
                //Change player max health
                //player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getAttributeValue(Attributes.MAX_HEALTH) + 1);

            });
        });
        return true;
    }

}
