package com.alvaro.rpgmod.networking.packet.skills;

import java.util.Optional;
import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.skills.berserker.BerserSkillsProvider;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
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
            ServerLevel level = player.getLevel();

            assert player != null;
            player.getCapability(BerserSkillsProvider.BERSERKER_SKILLS).ifPresent((skills) -> {
                if(skills.hasDash()){
                    Vec3 startPos = player.getEyePosition();

                    Vec3 lookAngle = player.getLookAngle();

                    double d0 = 100;
                    Vec3 endVec = startPos.add(lookAngle.x * d0, lookAngle.y * d0, lookAngle.z * d0);
                    AABB startEndBox = new AABB(startPos, endVec);
                    Entity entity = null;

                    for(Entity entity1 : level.getEntities(player, startEndBox, (val) -> true)) {
                      AABB aabb = entity1.getBoundingBox().inflate(entity1.getPickRadius());
                      Optional<Vec3> optional = aabb.clip(startPos, endVec);
                      if (!(entity instanceof Player)){
                        continue;
                      }
                      if (aabb.contains(startPos)) {
                        if (d0 >= 0.0D) {
                           entity = entity1;
                           d0 = 0.0D;
                        }
                      } else if (optional.isPresent()) {
                        Vec3 vec31 = optional.get();
                        double d1 = startPos.distanceToSqr(vec31);
                        if (d1 < d0 || d0 == 0.0D) {
                            if (entity1.getRootVehicle() == player.getRootVehicle() && !entity1.canRiderInteract()) {
                                if (d0 == 0.0D) {
                                   entity = entity1;
                                }
                             } else {
                                entity = entity1;
                                d0 = d1;
                             }
                        }
                      }
                    }
                    if (entity != null){
                        player.teleportTo(entity.getX(), entity.getY(), entity.getZ());
                    }
                }
            });
        });
    }
}
