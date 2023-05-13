package com.alvaro.rpgmod.capabilities.missions;

import java.util.HashSet;
import java.util.List;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;


@AutoRegisterCapability
public class PlayerMissions {
    private HashSet<Integer> missions = new HashSet<Integer>();

    public void setMissions(List<Integer> list){
        this.missions.clear();
        this.missions.addAll(list);
    }

    public void setMissions(HashSet<Integer> list){
        this.missions = list;
    }

    public void addMission(int missionId){
        this.missions.add(missionId);
    }

    public void addMissions(List<Integer> missionsId){
        this.missions.addAll(missionsId);
    }

    public void addMissions(HashSet<Integer> missionsId){
        this.missions.addAll(missionsId);
    }

    public HashSet<Integer> getMissions(){
        return this.missions;
    }

    public void copyFrom(PlayerMissions source) {
        this.missions = source.missions;

    }
    public void saveNBTData(CompoundTag nbt){
        nbt.putIntArray("missions", missions.stream().mapToInt(Integer::intValue).toArray());

    }
    public void loadNBTData(CompoundTag nbt){
        missions.clear();
        for (int mission : nbt.getIntArray("missions")){
            addMission(mission);
        }
    }
}