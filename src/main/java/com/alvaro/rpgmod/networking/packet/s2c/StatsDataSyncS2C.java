package com.alvaro.rpgmod.networking.packet.s2c;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.alvaro.rpgmod.client.ClientStatsData;

import it.unimi.dsi.fastutil.ints.IntList;

public class StatsDataSyncS2C {
    private final int playerClass, mana, maxMana, level, points, strength, dexterity, constitution, intelligence, wisdom, xp, xpNecessary;


    public StatsDataSyncS2C(int playerClass, int mana, int maxMana, int level, int points, int strength, int dexterity, int constitution, int intelligence, int wisdom, int xp, int xpNecessary) {
        this.playerClass = playerClass;
        this.mana = mana;
        this.maxMana = maxMana;
        this.level = level;
        this.points = points;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.xp = xp;
        this.xpNecessary = xpNecessary;
    }

    public StatsDataSyncS2C(FriendlyByteBuf buf){
        IntList list = buf.readIntIdList();
        this.playerClass = list.getInt(0);
        this.mana = list.getInt(1);
        this.maxMana = list.getInt(2);
        this.level = list.getInt(3);
        this.points = list.getInt(4);
        this.strength = list.getInt(5);
        this.dexterity = list.getInt(6);
        this.constitution = list.getInt(7);
        this.intelligence = list.getInt(8);
        this.wisdom = list.getInt(9);
        this.xp = list.getInt(10);
        this.xpNecessary = list.getInt(11);
    }

    public void toBytes(FriendlyByteBuf buf){
        int[] attr = {this.playerClass,
                      this.mana,
                      this.maxMana,
                      this.level,
                      this.points,
                      this.strength,
                      this.dexterity,
                      this.constitution,
                      this.intelligence,
                      this.wisdom,
                      this.xp,
                      this.xpNecessary
                    };
        buf.writeVarIntArray(attr);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT
            ClientStatsData.setPlayerClass(playerClass);
            ClientStatsData.setMana(mana);
            ClientStatsData.setMaxMana(maxMana);
            ClientStatsData.setLevel(level);
            ClientStatsData.setPoints(points);
            ClientStatsData.setStrength(strength);
            ClientStatsData.setDexterity(dexterity);
            ClientStatsData.setConstitution(constitution);
            ClientStatsData.setIntelligence(intelligence);
            ClientStatsData.setWisdom(wisdom);
            ClientStatsData.setXp(xp);
            ClientStatsData.setXpNecessary(xpNecessary);
        });
    }

}
