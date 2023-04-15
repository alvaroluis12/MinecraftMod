package com.alvaro.rpgmod.capabilities.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerMana {
    private int MANA = 0;
    private int MAX_MANA = 20;

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
        MANA = 0;
        MAX_MANA = 20;
    }
    
    public void copyFrom(PlayerMana source) {
        this.MANA = source.MANA;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("mana", MANA);
        nbt.putInt("max_mana", MAX_MANA);
    }

    public void loadNBTData(CompoundTag nbt){
        MANA = nbt.getInt("mana");
        MAX_MANA = nbt.getInt("max_mana");
    }

}
