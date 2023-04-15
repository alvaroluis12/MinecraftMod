package com.alvaro.rpgmod.client;

public class ClientManaData {
    private static int playerMana;
    private static int playerMaxMana;

    public static void setMana(int mana){
        ClientManaData.playerMana = mana;
    }

    public static int getPlayerMana(){
        return playerMana;
    }

    public static void setMaxMana(int mana){
        ClientManaData.playerMaxMana = mana;
    }

    public static int getPlayerMaxMana(){
        return playerMaxMana;
    }

}
