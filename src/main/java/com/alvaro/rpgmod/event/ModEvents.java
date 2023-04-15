package com.alvaro.rpgmod.event;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.capabilities.mana.PlayerManaProvider;
import com.alvaro.rpgmod.entity.ModEntities;
import com.alvaro.rpgmod.entity.custom.TigerEntity;
import com.alvaro.rpgmod.entity.custom.TrollEntity;
import com.alvaro.rpgmod.networking.ModMessages;
import com.alvaro.rpgmod.networking.packet.ManaDataSyncS2C;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;


public class ModEvents {

    @Mod.EventBusSubscriber(modid = RPGMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntities.TIGER.get(), TigerEntity.setAttributes());
            event.put(ModEntities.TROLL.get(), TrollEntity.setAttributes());
        }
        
        @SubscribeEvent
        public static void entitySpawnRestriction(SpawnPlacementRegisterEvent event) {
            event.register(ModEntities.TIGER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, Operation.REPLACE);
            event.register(ModEntities.TROLL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, Operation.REPLACE);

        }
    }

    @Mod.EventBusSubscriber(modid = RPGMod.MODID)
    public static class ForgeEvents{
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player) {
                if(!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()) {
                    event.addCapability(new ResourceLocation(RPGMod.MODID, "properties"), new PlayerManaProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event){
            if(event.isWasDeath()){
                event.getOriginal().reviveCaps();
                event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                        newStore.setMana(newStore.getMaxMana());
                    });
                });
            event.getOriginal().invalidateCaps();
            }
        }

        

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER) {
                event.player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    if(mana.getMaxMana() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                        mana.addMana(1);
                        ModMessages.sendToPlayer(new ManaDataSyncS2C(mana.getMana(), mana.getMaxMana()), ((ServerPlayer) event.player));
                    }
                });
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if(!event.getLevel().isClientSide()) {
                if(event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                        ModMessages.sendToPlayer(new ManaDataSyncS2C(mana.getMana(), mana.getMaxMana()), player);
                    });
                }
            }
        }
    }
}