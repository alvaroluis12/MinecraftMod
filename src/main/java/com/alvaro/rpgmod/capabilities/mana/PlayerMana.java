package com.alvaro.rpgmod.capabilities.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerMana {
    private int mana = 0;
    private int MAX_MANA = 20;

    public int getMana(){
        return this.mana;
    }

    public void addMana(int add) {
        this.mana = Math.min((mana + add), MAX_MANA);
    }

    public void subMana(int sub){
        this.mana -= sub;
    }

    public void addMaxMana(int add){
        this.MAX_MANA += add;
    }

    public void subMaxMana(int sub){
        this.MAX_MANA -= sub;
    }

    public void resetManaToDefault(){
        MAX_MANA = 20;
    }
    
    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("mana", mana);
        nbt.putInt("max_mana", MAX_MANA);
    }

    public void loadNBTData(CompoundTag nbt){
        mana = nbt.getInt("mana");
        MAX_MANA = nbt.getInt("max_mana");
    }

}
