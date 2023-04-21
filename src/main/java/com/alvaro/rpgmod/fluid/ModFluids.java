package com.alvaro.rpgmod.fluid;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.block.ModBlocks;
import com.alvaro.rpgmod.item.ModItems;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, RPGMod.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_MANA = FLUIDS.register("mana_fluid", 
            () -> new ForgeFlowingFluid.Source(ModFluids.MANA_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_MANA = FLUIDS.register("flowing_mana_fluid", 
            () -> new ForgeFlowingFluid.Flowing(ModFluids.MANA_FLUID_PROPERTIES));
    
    public static final ForgeFlowingFluid.Properties MANA_FLUID_PROPERTIES = 
            new ForgeFlowingFluid.Properties(ModFluidsTypes.MANA_FLUID_TYPE, SOURCE_MANA, FLOWING_MANA)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.MANA_FLUID_BLOCK).bucket(ModItems.MANA_FLUID_BUCKET);

    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
    }
}
