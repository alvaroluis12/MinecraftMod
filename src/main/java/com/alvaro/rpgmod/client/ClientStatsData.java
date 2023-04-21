package com.alvaro.rpgmod.client;

public class ClientStatsData {
    private static int playerClass, playerMana, playerMaxMana, playerStrength, playerConstitution, playerDexterity, playerIntelligence, playerWisdom, playerLevel, playerPoints;

    public static void setPlayerClass(int playerClass){
        ClientStatsData.playerClass = playerClass;
    }

    public static int getPlayerClass(){
        return playerClass;
    }
    public static void setMana(int mana){
        ClientStatsData.playerMana = mana;
    }

    public static int getPlayerMana(){
        return playerMana;
    }

    public static void setMaxMana(int mana){
        ClientStatsData.playerMaxMana = mana;
    }

    public static int getPlayerMaxMana(){
        return playerMaxMana;
    }

    public static void setStrength(int str){
        ClientStatsData.playerStrength = str;
    }

    public static int getStrength(){
        return playerStrength;
    }
    public static void setConstitution(int con){
        ClientStatsData.playerConstitution = con;
    }

    public static int getConstitution(){
        return playerConstitution;
    }

    public static void setDexterity(int dex){
        ClientStatsData.playerDexterity = dex;
    }

    public static int getDexterity(){
        return playerDexterity;
    }

    public static void setIntelligence(int intelligence){
        ClientStatsData.playerIntelligence = intelligence;
    }

    public static int getIntelligence(){
        return playerIntelligence;
    }

    public static void setWisdom(int wis){
        ClientStatsData.playerWisdom = wis;
    }

    public static int getWisdom(){
        return playerWisdom;
    }

    public static void setLevel(int level){
        ClientStatsData.playerLevel = level;
    }

    public static int getLevel(){
        return playerLevel;
    }

    public static void setPoints(int points){
        ClientStatsData.playerPoints = points;
    }

    public static int getPoints(){
        return playerPoints;
    }

}
