package com.alvaro.rpgmod.capabilities.stats;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerStats {
    //STRENGTH = attack damage
    //DEXTERITY = speed, attack speed
    //CONSTITUTION = max_hp, defense
    //INTELLIGENCE = magic attack, mana cost
    //WISDOM = max_mana, mana regen
    private int LEVEL_UP_POINTS = 3, LEVEL = 0, POINTS = 0,  MANA = 0, STRENGTH = 0, DEXTERITY = 0, CONSTITUTION = 0, INTELLIGENCE = 0, WISDOM = 0;
    private int MAX_MANA = this.WISDOM*10;

    public int getLevel(){
        return this.LEVEL;
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
        this.LEVEL = lvl;
        addPoints(LEVEL_UP_POINTS);
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

    public boolean hasEnoughPoints(int num){
        if(this.POINTS >= num){
            return true;
        }
        return false;
    }

    public void setStrength(int str){
        this.STRENGTH = Math.min(str, 300);
    }

    public boolean addStrength(int str){
        if(hasEnoughPoints(str)){
            this.STRENGTH = Math.min(this.STRENGTH+str, 300);
            subPoints(str);
            return true;
        }
        return false;
    }

    public void setCon(int con){
        this.CONSTITUTION = Math.min(con, 300);
    }

    public boolean addCon(int con){
        if (hasEnoughPoints(con)){
            this.CONSTITUTION = Math.min(this.CONSTITUTION+con, 300);
            subPoints(con);
            return true;
        }  
        return false;
    }

    public void setDex(int dex){
        this.DEXTERITY = Math.min(dex, 300);
    }

    public boolean addDex(int dex){
        if(hasEnoughPoints(dex)){
            this.DEXTERITY = Math.min(this.DEXTERITY+dex, 300);
            subPoints(dex);
        }
        return false;
    }
    
    public void setIntelligence(int intell){
        this.INTELLIGENCE = Math.min(intell, 300);
    }

    public void addIntelligence(int intell){
        if (hasEnoughPoints(intell)){
            this.INTELLIGENCE = Math.min(this.INTELLIGENCE+intell, 300);
            subPoints(intell);
        }
    }

    public void setWisdom(int wis){
        this.WISDOM = Math.min(wis, 300);
    }

    public boolean addWisdom(int wis){
        if (hasEnoughPoints(wis)){
            this.WISDOM = Math.min(this.WISDOM+wis, 300);
            subPoints(wis);
            return true;
        }
        return false;
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

    public void resetManaToDefault(){
        MAX_MANA = this.WISDOM * 10;
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
        this.LEVEL_UP_POINTS = source.LEVEL_UP_POINTS;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("mana", MANA);
        nbt.putInt("max_mana", MAX_MANA);
        nbt.putInt("strength", STRENGTH);
        nbt.putInt("dexterity", DEXTERITY);
        nbt.putInt("constitution", CONSTITUTION);
        nbt.putInt("intelligence", INTELLIGENCE);
        nbt.putInt("wisdom", WISDOM);
        nbt.putInt("level_up_points", LEVEL_UP_POINTS);
        nbt.putInt("level", LEVEL);
    }

    public void loadNBTData(CompoundTag nbt){
        MANA = nbt.getInt("mana");
        MAX_MANA = nbt.getInt("max_mana");
        STRENGTH = nbt.getInt("strength");
        DEXTERITY = nbt.getInt("dexterity");
        CONSTITUTION = nbt.getInt("constitution");
        INTELLIGENCE = nbt.getInt("intelligence");
        WISDOM = nbt.getInt("wisdom");
        LEVEL_UP_POINTS = nbt.getInt("level_up_points");
        LEVEL = nbt.getInt("level");
    }

}
