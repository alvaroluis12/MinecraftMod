package com.alvaro.rpgmod.capabilities.stats;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerStats {
    public static final int LEVEL_UP_POINTS = 3, STR_INDEX = 0, DEX_INDEX = 1, CON_INDEX = 2, INT_INDEX = 3, WIS_INDEX = 4, LEVEL_INDEX = 5, POINTS_INDEX = 6,
                            MANA_INDEX = 7, MAX_MANA_INDEX = 8, XP_INDEX = 9;
    //STRENGTH = attack damage, crit chance
    //DEXTERITY = evsasion, attack speed
    //CONSTITUTION = max_hp, defense
    //INTELLIGENCE = magic attack, mana cost
    //WISDOM = max_mana, mana regen
    private int LEVEL = 0, POINTS = 0,  MANA = 0, STRENGTH = 0, DEXTERITY = 0, CONSTITUTION = 0, INTELLIGENCE = 0, WISDOM = 0, XP = 0, XP_NECESSARY = 20;
    private int MAX_MANA = this.WISDOM*10;

    public static final int NO_CLASS=-1, MAGE=0, DRUID=1, SUMMONER=2, RANGER=3, PALADIN=4, BERSERKER=5;
    private int playerClass = -1;

    public int getPlayerClass(){
        return playerClass;
    }

    public void setPlayerClass(int playerClass){
        this.playerClass = playerClass;
        resetStatsToDefault();
    }

    public boolean hasClass(){
        return this.playerClass > NO_CLASS;
    }
    
    public int getLevel(){
        return this.LEVEL;
    }

    public int getXp(){
        return this.XP;
    }

    public int getXpNecessary(){
        return this.XP_NECESSARY;
    }
    
    public void setXp(int xp){
        this.XP = xp;
        levelUp();
    }

    public void addXp(int xp){
        this.XP += xp;
        levelUp();
    }

    public void levelUp(){
        if (this.XP < this.XP_NECESSARY){
            return;
        }
        this.XP -= this.XP_NECESSARY;
        addLevel(1);
        setXpNecessary();
        levelUp();
    }

    public void setXpNecessary(){
        this.XP_NECESSARY = 100;
    }

    public int getPoints(){
        return this.POINTS;
    }

    public int getStrength(){
        return this.STRENGTH;
    }

    public int getDex(){
        return this.DEXTERITY;
    }

    public int getCon(){
        return this.CONSTITUTION;
    }

    public int getIntelligence(){
        return this.INTELLIGENCE;
    }

    public int getWisdom(){
        return this.WISDOM;
    }

    public void setLevel(int lvl){
        this.LEVEL = lvl;
    }

    public void addLevel(int lvl){
        this.LEVEL += lvl;
        addPoints(LEVEL_UP_POINTS*lvl);
    }
    
    public void setPoints(int pts){
        this.POINTS = pts;
    }

    public void addPoints(int pts){
        this.POINTS += pts;
    }

    public void subPoints(int pts){
        this.POINTS -= pts;
    }

    public void setStrength(int str){
        this.STRENGTH = Math.min(str, 300);
    }

    public void addStrength(int str){
        this.STRENGTH = Math.min(this.STRENGTH+str, 300);
    }

    public void setCon(int con){
        this.CONSTITUTION = Math.min(con, 300);
    }

    public void addCon(int con){
        this.CONSTITUTION = Math.min(this.CONSTITUTION+con, 300);
    }

    public void setDex(int dex){
        this.DEXTERITY = Math.min(dex, 300);
    }

    public void addDex(int dex){
        this.DEXTERITY = Math.min(this.DEXTERITY+dex, 300);
    }
    
    public void setIntelligence(int intell){
        this.INTELLIGENCE = Math.min(intell, 300);
    }

    public void addIntelligence(int intell){
        this.INTELLIGENCE = Math.min(this.INTELLIGENCE+intell, 300);
    }

    public void setWisdom(int wis){
        this.WISDOM = Math.min(wis, 300);
    }

    public void addWisdom(int wis){
        this.WISDOM = Math.min(this.WISDOM+wis, 300);
    }

    public int getMana(){
        return this.MANA;
    }

    public void setMana(int mana){
        this.MANA = mana;
    }

    public void addMana(int add) {
        this.MANA = Math.min((MANA + add), MAX_MANA);
    }

    public void subMana(int sub){
        this.MANA -= sub;
    }

    public int getMaxMana(){
        return this.MAX_MANA;
    }

    public void setMaxMana(int mana){
        this.MAX_MANA = mana;
    }

    public void addMaxMana(int add){
        this.MAX_MANA += add;
    }

    public void subMaxMana(int sub){
        this.MAX_MANA -= sub;
    }

    public void subStrength(int sub){
        this.STRENGTH -= sub;
    }

    public void subConstitution(int sub){
        this.CONSTITUTION -= sub;
    }

    public void subDexterity(int sub){
        this.DEXTERITY -= sub;
    }

    public void subIntelligence(int sub){
        this.INTELLIGENCE -= sub;
    }

    public void subWisdom(int sub){
        this.WISDOM -= sub;
    }

    public void subLevel(int sub){
        this.LEVEL -= sub;
        subPoints(3);
    }

    public void subXp(int sub){
        this.XP -= sub;
    }

    
    public void resetStatsToDefault(){
        this.POINTS = this.LEVEL*LEVEL_UP_POINTS;
        switch(this.playerClass){
            case PlayerStats.MAGE:
                this.STRENGTH = 0;
                this.DEXTERITY = 0;
                this.CONSTITUTION = 0;
                this.INTELLIGENCE = 5;
                this.WISDOM = 5;
                break;
            case PlayerStats.DRUID:
                this.STRENGTH = 0;
                this.DEXTERITY = 0;
                this.CONSTITUTION = 2;
                this.INTELLIGENCE = 3;
                this.WISDOM = 5;
                break;
            case PlayerStats.SUMMONER:
                this.STRENGTH = 0;
                this.DEXTERITY = 0;
                this.CONSTITUTION = 0;
                this.INTELLIGENCE = 3;
                this.WISDOM = 7;
                break;
            case PlayerStats.RANGER:
                this.STRENGTH = 3;
                this.DEXTERITY = 7;
                this.CONSTITUTION = 0;
                this.INTELLIGENCE = 0;
                this.WISDOM = 0;
                break;
            case PlayerStats.PALADIN:
                this.STRENGTH = 3;
                this.DEXTERITY = 0;
                this.CONSTITUTION = 7;
                this.INTELLIGENCE = 0;
                this.WISDOM = 0;
                break;
            case PlayerStats.BERSERKER:
                this.STRENGTH = 7;
                this.DEXTERITY = 0;
                this.CONSTITUTION = 3;
                this.INTELLIGENCE = 0;
                this.WISDOM = 0;
                break;
            default:
                this.STRENGTH = 0;
                this.DEXTERITY = 0;
                this.CONSTITUTION = 0;
                this.INTELLIGENCE = 0;
                this.WISDOM = 0;
                break;
        }
        resetManaToDefault();
    }

    public void resetPlayerToDefault(){
        this.LEVEL = 0;
        this.XP = 0;
        this.XP_NECESSARY = 20;
        this.playerClass = NO_CLASS;
        resetStatsToDefault();

    }

    public void resetManaToDefault(){
        this.MAX_MANA = (this.WISDOM * 10) + 20;
        this.MANA = MAX_MANA;
    }
    
    public void copyFrom(PlayerStats source) {
        this.MANA = source.MAX_MANA;
        this.MAX_MANA = source.MAX_MANA;
        this.STRENGTH = source.STRENGTH;
        this.CONSTITUTION = source.CONSTITUTION;
        this.DEXTERITY = source.DEXTERITY;
        this.INTELLIGENCE = source.INTELLIGENCE;
        this.WISDOM = source.WISDOM;
        this.LEVEL = source.LEVEL;
        this.POINTS = source.POINTS;
        this.playerClass = source.playerClass;
        this.XP = source.XP;
        this.XP_NECESSARY = source.XP_NECESSARY;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("mana", MANA);
        nbt.putInt("max_mana", MAX_MANA);
        nbt.putInt("strength", STRENGTH);
        nbt.putInt("dexterity", DEXTERITY);
        nbt.putInt("constitution", CONSTITUTION);
        nbt.putInt("intelligence", INTELLIGENCE);
        nbt.putInt("wisdom", WISDOM);
        nbt.putInt("points", POINTS);
        nbt.putInt("level", LEVEL);
        nbt.putInt("playerClass", playerClass);
        nbt.putInt("xp", XP);
        nbt.putInt("xpNecessary", XP_NECESSARY);
    }

    public void loadNBTData(CompoundTag nbt){
        MANA = nbt.getInt("mana");
        MAX_MANA = nbt.getInt("max_mana");
        STRENGTH = nbt.getInt("strength");
        DEXTERITY = nbt.getInt("dexterity");
        CONSTITUTION = nbt.getInt("constitution");
        INTELLIGENCE = nbt.getInt("intelligence");
        WISDOM = nbt.getInt("wisdom");
        POINTS = nbt.getInt("points");
        LEVEL = nbt.getInt("level");
        playerClass = nbt.getInt("playerClass");
        XP = nbt.getInt("xp");
        XP_NECESSARY = nbt.getInt("xpNecessary");
    }

}
