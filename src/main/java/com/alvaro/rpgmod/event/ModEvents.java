package com.alvaro.rpgmod.event;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.capabilities.skills.Berserker.BerserSkillsProvider;
import com.alvaro.rpgmod.capabilities.stats.PlayerStatsProvider;
import com.alvaro.rpgmod.commands.AttributesCommand;
import com.alvaro.rpgmod.entity.ModEntities;
import com.alvaro.rpgmod.entity.custom.GloblinEntity;
import com.alvaro.rpgmod.entity.custom.TigerEntity;
import com.alvaro.rpgmod.entity.custom.TrollEntity;
import com.alvaro.rpgmod.entity.custom.WindigoEntity;
import com.alvaro.rpgmod.networking.ModMessages;
import com.alvaro.rpgmod.networking.packet.UpdateAttributesC2SPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;


public class ModEvents {

    @Mod.EventBusSubscriber(modid = RPGMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntities.TIGER.get(), TigerEntity.setAttributes());
            event.put(ModEntities.TROLL.get(), TrollEntity.setAttributes());
            event.put(ModEntities.GLOBLIN.get(), GloblinEntity.setAttributes());
            event.put(ModEntities.WINDIGO.get(), WindigoEntity.setAttributes());
        }
        
        @SubscribeEvent
        public static void entitySpawnRestriction(SpawnPlacementRegisterEvent event) {
            event.register(ModEntities.TIGER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, Operation.REPLACE);
            event.register(ModEntities.TROLL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, Operation.REPLACE);
            event.register(ModEntities.GLOBLIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, Operation.REPLACE);
            event.register(ModEntities.WINDIGO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, Operation.REPLACE);

        }
    }

    @Mod.EventBusSubscriber(modid = RPGMod.MODID)
    public static class ForgeEvents{
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player) {
                if(!event.getObject().getCapability(PlayerStatsProvider.PLAYER_STATS).isPresent()) {
                    event.addCapability(new ResourceLocation(RPGMod.MODID, "properties"), new PlayerStatsProvider());
                }
                if(!event.getObject().getCapability(BerserSkillsProvider.BERSERKER_SKILLS).isPresent()){
                    event.addCapability(new ResourceLocation(RPGMod.MODID, "skills"), new BerserSkillsProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event){
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(oldStore -> {
                event.getEntity().getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(BerserSkillsProvider.BERSERKER_SKILLS).ifPresent(oldStore -> {
                event.getEntity().getCapability(BerserSkillsProvider.BERSERKER_SKILLS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().invalidateCaps();
            
        }

        
        
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.CLIENT) {
                ModMessages.sendToServer(new UpdateAttributesC2SPacket());
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if(event.getLevel().isClientSide()) {
                ModMessages.sendToServer(new UpdateAttributesC2SPacket());
            }
        }

        @SubscribeEvent
        public static void onPlayerAttacked(LivingAttackEvent event){
            DamageSource damageSource = event.getSource();
            Entity damageSourceEntity = damageSource.getEntity();

            if (damageSourceEntity != null){
                if (event.getEntity() instanceof ServerPlayer){
                    event.getEntity().getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                        if(event.getEntity().getRandom().nextFloat() < stats.getDex()*0.3F/100){
                            event.setCanceled(true);
                        }
                    });
                }
            }
        }
        
        @SubscribeEvent
        public static void onPlayerAttack(LivingDamageEvent event){
            DamageSource damageSource = event.getSource();
            Entity damageSourceEntity = damageSource.getEntity();

            if (damageSourceEntity instanceof ServerPlayer){
                damageSourceEntity.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                    if(event.getEntity().getRandom().nextFloat() < stats.getStrength() / 300 ){
                        event.setAmount(event.getAmount() * 1.5f);
                    }
                });
            }
        }

        @SubscribeEvent
        public static void onEntityDeath(LivingDeathEvent event){
            DamageSource damageSource = event.getSource();
            Entity damageSourceEntity = damageSource.getEntity();

            if(damageSourceEntity instanceof ServerPlayer){
                damageSourceEntity.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                    stats.addXp(event.getEntity().getExperienceReward());
                });
            }
        }

        
        @SubscribeEvent
        public static void onCommandsRegister(RegisterCommandsEvent event) {
            AttributesCommand.register(event.getDispatcher());
            ConfigCommand.register(event.getDispatcher());
        }
    }
}
