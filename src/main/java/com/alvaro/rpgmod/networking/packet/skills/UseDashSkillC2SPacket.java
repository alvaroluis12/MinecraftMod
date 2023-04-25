package com.alvaro.rpgmod.networking.packet.skills;

import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.skills.Berserker.BerserSkillsProvider;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

public class UseDashSkillC2SPacket {
    public UseDashSkillC2SPacket(){

    }
    public UseDashSkillC2SPacket(FriendlyByteBuf buf){

    }
    public void toBytes(FriendlyByteBuf buf){

    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            assert player != null;
            player.getCapability(BerserSkillsProvider.BERSERKER_SKILLS).ifPresent((skills) -> {
                if(skills.hasDash()){
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, 2, false, false, false), player);
                }
            });
        });
    }
}
