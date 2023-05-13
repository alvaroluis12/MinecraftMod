package com.alvaro.rpgmod.networking.packet.s2c;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashSet;
import java.util.function.Supplier;

import com.alvaro.rpgmod.client.ClientMissionsData;

import it.unimi.dsi.fastutil.ints.IntList;

public class MissionsDataSyncS2C {
    private final HashSet<Integer> missions = new HashSet<Integer>();


    public MissionsDataSyncS2C(HashSet<Integer> missions) {
        this.missions.addAll(missions);
    }

    public MissionsDataSyncS2C(FriendlyByteBuf buf){
        IntList list = buf.readIntIdList();
        this.missions.addAll(list);
    }

    public void toBytes(FriendlyByteBuf buf){
        int[] missionsArr = this.missions.stream().mapToInt(Integer::intValue).toArray();
        buf.writeVarIntArray(missionsArr);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT
            ClientMissionsData.setMissions(missions);
        });
    }

}
