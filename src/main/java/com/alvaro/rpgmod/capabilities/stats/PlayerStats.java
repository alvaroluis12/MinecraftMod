package com.alvaro.rpgmod.capabilities.stats;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerStats {
    public static final int LEVEL_UP_POINTS = 3, STR_INDEX = 0, DEX_INDEX = 1, CON_INDEX = 2, INT_INDEX = 3, WIS_INDEX = 4;
    //STRENGTH = attack damage, crit chance
    //DEXTERITY = evsasion, attack speed
    //CONSTITUTION = max_hp, defense
    //INTELLIGENCE = magic attack, mana cost
    //WISDOM = max_mana, mana regen
    private int LEVEL = 0, POINTS = 5,  MANA = 0, STRENGTH = 0, DEXTERITY = 0, CONSTITUTION = 0, INTELLIGENCE = 0, WISDOM = 0;
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
        if (this.STRENGTH < 300){
            if(hasEnoughPoints(str)){
                this.STRENGTH = Math.min(this.STRENGTH+str, 300);
                subPoints(str);
                return true;
            }
        }
        return false;
    }

    public void setCon(int con){
        this.CONSTITUTION = Math.min(con, 300);
    }

    public boolean addCon(int con){
        if (this.CONSTITUTION < 300){
            if (hasEnoughPoints(con)){
                this.CONSTITUTION = Math.min(this.CONSTITUTION+con, 300);
                subPoints(con);
                return true;
            }  
        }
        return false;
    }

    public void setDex(int dex){
        this.DEXTERITY = Math.min(dex, 300);
    }

    public boolean addDex(int dex){
        if(this.DEXTERITY < 300){
            if(hasEnoughPoints(dex)){
                this.DEXTERITY = Math.min(this.DEXTERITY+dex, 300);
                subPoints(dex);
            }
        }
        return false;
    }
    
    public void setIntelligence(int intell){
        this.INTELLIGENCE = Math.min(intell, 300);
    }

    public void addIntelligence(int intell){
        if(this.INTELLIGENCE < 300){
            if (hasEnoughPoints(intell)){
                this.INTELLIGENCE = Math.min(this.INTELLIGENCE+intell, 300);
                subPoints(intell);
            }
        }
    }

    public void setWisdom(int wis){
        this.WISDOM = Math.min(wis, 300);
    }

    public boolean addWisdom(int wis){
        if(this.WISDOM < 300){
            if (hasEnoughPoints(wis)){
                this.WISDOM = Math.min(this.WISDOM+wis, 300);
                subPoints(wis);

                addMaxMana(wis*10);
                return true;
            }
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

    
    public void resetStatsToDefault(){
        this.LEVEL = 0;
        this.POINTS = 5; 
        this.MANA = 0;
        this.STRENGTH = 0;
        this.DEXTERITY = 0;
        this.CONSTITUTION = 0;
        this.INTELLIGENCE = 0;
        this.WISDOM = 0;
        resetManaToDefault();
    }

    public void resetManaToDefault(){
        this.MAX_MANA = (this.WISDOM * 10) + 20;
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
    }

}
