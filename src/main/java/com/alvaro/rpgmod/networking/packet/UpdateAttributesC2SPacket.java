package com.alvaro.rpgmod.networking.packet;

import com.alvaro.rpgmod.capabilities.skills.Berserker.BerserSkillsProvider;
import com.alvaro.rpgmod.capabilities.stats.PlayerStats;
import com.alvaro.rpgmod.capabilities.stats.PlayerStatsProvider;
import com.alvaro.rpgmod.networking.ModMessages;
import com.alvaro.rpgmod.screen.classes.ClassSelectMenu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

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
                //tick every 0,05s
                //20 ticks per second
                if (player.tickCount % 100 == 0){//Once every 5s
                    stats.addMana(stats.getWisdom());
                };

                if(!stats.hasClass()){
                    NetworkHooks.openScreen(player, new SimpleMenuProvider(
                        (containerId, playerInventory, nul) -> new ClassSelectMenu(containerId, playerInventory),
                        Component.literal("Select class")
                    ));
                }

                switch(stats.getPlayerClass()){
                    case PlayerStats.BERSERKER:
                        player.getCapability(BerserSkillsProvider.BERSERKER_SKILLS).ifPresent(skills -> {
                            skills.setDash(true);
                        });
                }
                
                ModMessages.sendToPlayer(new StatsDataSyncS2C(stats.getPlayerClass(),
                                                              stats.getMana(),
                                                              stats.getMaxMana(),
                                                              stats.getLevel(),
                                                              stats.getPoints(),
                                                              stats.getStrength(),
                                                              stats.getDex(),
                                                              stats.getCon(),
                                                              stats.getIntelligence(),
                                                              stats.getWisdom(),
                                                              stats.getXp(),
                                                              stats.getXpNecessary()), player);
                
                Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(20 + stats.getCon()*2);

                if (stats.getPlayerClass() == PlayerStats.BERSERKER){
                    Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(
                        (1.0D + stats.getStrength()*3/10)*(2-(player.getHealth()/player.getMaxHealth())));
                }
                else {
                    Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(1.0D + stats.getStrength()*3/10);
                }
                Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(stats.getCon());
                Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(4F + (double) stats.getDex() /100);
                Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.1F + stats.getDex()/350);
                /*if (!player.hasEffect(MobEffects.REGENERATION)){
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, -1, Math.round(stats.getCon()/100), false, false, false), player);
                }
                if (!player.hasEffect(MobEffects.DAMAGE_RESISTANCE)){
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, Math.round(stats.getCon()/100), false, false, false), player);
                }
                if(!player.hasEffect(MobEffects.DAMAGE_BOOST)){
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, Math.round(stats.getStrength()/100), false, false, false), player);
                }
                if(!player.hasEffect(MobEffects.DIG_SPEED)){
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, -1, Math.round(stats.getStrength()/100), false, false, false), player);
                }*/
            });
        });
    }
}
