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

    /*
     * BERSERKER
     * MENOS VIDA MAIS DANO
     * MELEE 110%
     * LIFESTEAL
     * GAP CLOSE(DASH DE 5 BLOCOS)
     * IMUNIDADE A CC
     * MACHADO
     * 
     * PALADIN
     * AMIGO DOS VILLAGERS
     * TANK E BUFFER
     * MELEE
     * TIPO A LEONINHA
     * MT CC
     * 
     * RANGER
     * ARCO
     * MT DEX
     * HORRIVEL
     * 
     * SUMMONER
     * SUMMONA BIXO
     * PRECISA MATAR PRA SUMMONAR
     * GASTA MANA PRA MANTER SUMMON
     * SACRIFICA VIDA PARA SPAWNAR BIXO MAIS FORTE
     * 
     * MAGE
     * O MAIS PIKA
     * O FAVORECIDO POR MIM
     * CRONTRA O CARALHO A QUATRO
     * CONTEMPLEM O MAGO
     * COM SEUS PODERES
     * INCRIVEIS PODERES
     */

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
        this.XP = Math.max(xp, 0);
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
        this.LEVEL = Math.max(lvl, 0);
    }

    public void addLevel(int lvl){
        this.LEVEL = Math.max(this.LEVEL + lvl, 0);
        addPoints(LEVEL_UP_POINTS*lvl);
    }
    
    public void setPoints(int pts){
        this.POINTS = Math.max(pts, 0);
    }

    public void addPoints(int pts){
        this.POINTS = Math.max(this.POINTS + pts, 0);
    }

    public void subPoints(int pts){
        this.POINTS = Math.max(this.POINTS - pts, 0);
    }

    public void setStrength(int str){
        this.STRENGTH = Math.min(Math.max(str, 0), 300)  ;
    }

    public void addStrength(int str){
        this.STRENGTH = Math.min(Math.max(this.STRENGTH+str, 0), 300);
    }

    public void setCon(int con){
        this.CONSTITUTION = Math.min(Math.max(con, 0), 300);
    }

    public void addCon(int con){
        this.CONSTITUTION = Math.min(Math.max(this.CONSTITUTION+con, 0), 300);
    }

    public void setDex(int dex){
        this.DEXTERITY = Math.min(Math.max(dex, 0), 300);
    }

    public void addDex(int dex){
        this.DEXTERITY = Math.min(Math.max(this.DEXTERITY+dex, 0), 300);
    }
    
    public void setIntelligence(int intell){
        this.INTELLIGENCE = Math.min(Math.max(intell, 0), 300);
    }

    public void addIntelligence(int intell){
        this.INTELLIGENCE = Math.min(Math.max(this.INTELLIGENCE+intell, 0), 300);
    }

    public void setWisdom(int wis){
        this.WISDOM = Math.min(Math.max(wis, 0), 300);
    }

    public void addWisdom(int wis){
        this.WISDOM = Math.min(Math.max(this.WISDOM+wis, 0), 300);
    }

    public int getMana(){
        return this.MANA;
    }

    public void setMana(int mana){
        this.MANA = Math.max(mana, 0);
    }

    public void addMana(int add) {
        this.MANA = Math.min(Math.max(MANA + add, 0), MAX_MANA);
    }

    public void subMana(int sub){
        this.MANA = Math.max(this.MANA - sub, 0);
    }

    public int getMaxMana(){
        return this.MAX_MANA;
    }

    public void setMaxMana(int mana){
        this.MAX_MANA = Math.max(mana, 20);
    }

    public void addMaxMana(int add){
        this.MAX_MANA = Math.max(this.MAX_MANA + add, 20);
    }

    public void subMaxMana(int sub){
        this.MAX_MANA = Math.max(this.MAX_MANA - sub, 20);
    }

    public void subStrength(int sub){
        this.STRENGTH = Math.max(this.STRENGTH - sub, 0);
    }

    public void subConstitution(int sub){
        this.CONSTITUTION = Math.max(this.CONSTITUTION - sub, 0);
    }

    public void subDexterity(int sub){
        this.DEXTERITY = Math.max(this.DEXTERITY - sub, 0);
    }

    public void subIntelligence(int sub){
        this.INTELLIGENCE = Math.max(this.INTELLIGENCE - sub, 0);
    }

    public void subWisdom(int sub){
        this.WISDOM = Math.max(this.WISDOM - sub, 0);
    }

    public void subLevel(int sub){
        Math.max(this.LEVEL - sub, 0);
        subPoints(3*sub);
    }

    public void subXp(int sub){
        this.XP = Math.max(this.XP - sub, 0);
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
