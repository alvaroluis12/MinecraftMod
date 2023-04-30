package com.alvaro.rpgmod.networking.packet.skills;

import java.util.function.Supplier;

import com.alvaro.rpgmod.capabilities.skills.berserker.BerserSkillsProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
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
                    Vec3 teleportToVec3 = player.getLookAngle().scale(7F);
                    double pX = player.getX() + teleportToVec3.x;
                    double pY = player.getY() + teleportToVec3.y;
                    double pZ = player.getZ() + teleportToVec3.z;
                    System.out.println(pX);
                    System.out.println(pY);
                    System.out.println(pZ);
                    System.out.println(teleportToVec3);
                    Player nearestPlayer = level.getNearestPlayer(pX, pY, pZ, 2, false);
                    if (nearestPlayer != null){
                        teleportTo(level, player, nearestPlayer.getX(), nearestPlayer.getY(), nearestPlayer.getZ());
                    }
                    else{
                        teleportTo(level, player, pX, pY+1, pZ);
                    }
                }
            });
        });
    }
    public void teleportTo(ServerLevel level, ServerPlayer player, double pX, double pY, double pZ) {
        double d0 = player.getX();
        double d1 = player.getY();
        double d2 = player.getZ();
        double d3 = pY;
        BlockPos blockpos = BlockPos.containing(pX, pY, pZ);
        boolean flag1 = false;
        while(!flag1 && blockpos.getY() > level.getMinBuildHeight()) {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = level.getBlockState(blockpos1);
            if (!blockstate.getMaterial().blocksMotion()) {
                --d3;
                blockpos = blockpos1;
            } else {
                flag1 = true;
            }
        }
        int i = 0;
        player.teleportTo(pX, d3, pZ);
        while (!level.noCollision(player)) {
            i++;
            if (pX >= d0 && pZ >= d2){
                player.teleportTo(pX-i, pY, pZ-i);
            }
            else if(pX >= d0 && pZ <= d2){
                player.teleportTo(pX-i, pY, pZ+i);

            }
            else if(pX <= d0 && pZ >= d2){
                player.teleportTo(pX+i, pY, pZ-i);
            }
            else{
                player.teleportTo(pX+i, pY, pZ+i);

            }
            if (i == 5){
                System.out.println("a");
                player.teleportTo(d0, d1, d2);
                break;
            }
        }
     }
}
