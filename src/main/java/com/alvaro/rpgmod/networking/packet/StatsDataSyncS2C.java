package com.alvaro.rpgmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.alvaro.rpgmod.client.ClientStatsData;

import it.unimi.dsi.fastutil.ints.IntList;

public class StatsDataSyncS2C {
    private final int mana, maxMana, level, points, strength, dexterity, constitution, intelligence, wisdom;


    public StatsDataSyncS2C(int mana, int maxMana, int level, int points, int strength, int dexterity, int constitution, int intelligence, int wisdom) {
        this.mana = mana;
        this.maxMana = maxMana;
        this.level = level;
        this.points = points;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
    }

    public StatsDataSyncS2C(FriendlyByteBuf buf){
        IntList list = buf.readIntIdList();
        this.mana = list.getInt(0);
        this.maxMana = list.getInt(1);
        this.level = list.getInt(2);
        this.points = list.getInt(3);
        this.strength = list.getInt(4);
        this.dexterity = list.getInt(5);
        this.constitution = list.getInt(6);
        this.intelligence = list.getInt(7);
        this.wisdom = list.getInt(8);
    }

    public void toBytes(FriendlyByteBuf buf){
        int[] attr = {this.mana,
                      this.maxMana,
                      this.level,
                      this.points,
                      this.strength,
                      this.dexterity,
                      this.constitution,
                      this.intelligence,
                      this.wisdom
                    };
        buf.writeVarIntArray(attr);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT
            ClientStatsData.setMana(mana);
            ClientStatsData.setMaxMana(maxMana);
            ClientStatsData.setLevel(level);
            ClientStatsData.setPoints(points);
            ClientStatsData.setStrength(strength);
            ClientStatsData.setDexterity(dexterity);
            ClientStatsData.setConstitution(constitution);
            ClientStatsData.setIntelligence(intelligence);
            ClientStatsData.setWisdom(wisdom);
        });
    }

}
