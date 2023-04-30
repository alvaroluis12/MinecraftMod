package com.alvaro.rpgmod.capabilities.skills.berserker;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class BerserkerSkills {
    private boolean dash = false;

    public void setDash(boolean dash){
        this.dash = dash;
    }
    public boolean hasDash(){
        return dash;
    }

    public void copyFrom(BerserkerSkills source) {
        this.dash = source.dash;

    }
    public void saveNBTData(CompoundTag nbt){
        nbt.putBoolean("dash", dash);

    }
    public void loadNBTData(CompoundTag nbt){
        dash = nbt.getBoolean("dash");
    }
}
