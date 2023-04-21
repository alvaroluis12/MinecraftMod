package com.alvaro.rpgmod.fluid;

import org.joml.Vector3f;

import com.alvaro.rpgmod.RPGMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidsTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation MANA_OVERLAY_RL = new ResourceLocation(RPGMod.MODID, "misc/in_mana_liquid");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, RPGMod.MODID);

    public static final RegistryObject<FluidType> MANA_FLUID_TYPE = register("mana_fluid", FluidType.Properties.create());

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties){
        return FLUID_TYPES.register(name, () ->
            //new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, MANA_OVERLAY_RL, 0xA1A2B5C8, new Vector3f(162/255, 181/255, 200/255), properties));
            new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, MANA_OVERLAY_RL, 0xA1A2B5C8, new Vector3f(255/255, 255/255, 255/255), properties));
    }

    public static void register(IEventBus eventBus){
        FLUID_TYPES.register(eventBus);
    }
}
