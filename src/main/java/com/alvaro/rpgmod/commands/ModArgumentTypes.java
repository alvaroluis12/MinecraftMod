package com.alvaro.rpgmod.commands;


import com.alvaro.rpgmod.RPGMod;

import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;

public class ModArgumentTypes {
    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ARGUMENT_TYPES = DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, RPGMod.MODID);

    public static final RegistryObject<ArgumentTypeInfo<?, ?>> ATTRIBUTE_TYPE = ARGUMENT_TYPES.register("attribute", () -> ArgumentTypeInfos.registerByClass(AttributeArgument.class, SingletonArgumentInfo.contextFree(AttributeArgument::attribute)));
    
    
    public static void register(IEventBus eventBus){
        ARGUMENT_TYPES.register(eventBus);
    }
}
