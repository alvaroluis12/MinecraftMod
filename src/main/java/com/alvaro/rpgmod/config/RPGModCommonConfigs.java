package com.alvaro.rpgmod.config;

import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class RPGModCommonConfigs {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<? extends List<String>>> MISSIONS; 
    
    static {
        BUILDER.push("Configs for RPG Mod");

        MISSIONS = BUILDER.comment("Missions\n[id, name, kill, monster, amount, xp, xp_reward]")
        .defineList("missions", List.of(), x -> x instanceof List);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    
}
