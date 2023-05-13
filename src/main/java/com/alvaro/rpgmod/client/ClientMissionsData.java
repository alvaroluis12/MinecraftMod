package com.alvaro.rpgmod.client;

import java.util.HashSet;

public class ClientMissionsData {
    private static HashSet<Integer> missions;
    
    public static void setMissions(HashSet<Integer> missions){
        ClientMissionsData.missions = missions;
    }

    public static HashSet<Integer> getMissions(){
        return missions;
    }

}
