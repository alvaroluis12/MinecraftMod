package com.alvaro.rpgmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class RPGModClientConfigs {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    static {
        BUILDER.push("Configs for RPG Mod");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    
}
