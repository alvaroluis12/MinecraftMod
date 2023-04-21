package com.alvaro.rpgmod.screen;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.screen.classes.ClassSelectMenu;
import com.alvaro.rpgmod.screen.stats.StatsMenu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RPGMod.MODID);

    public static final RegistryObject<MenuType<StatsMenu>> Stats_Menu = registerMenuType("stats_menu", StatsMenu::new);
    public static final RegistryObject<MenuType<ClassSelectMenu>> Class_Select_Menu = registerMenuType("class_select_menu", ClassSelectMenu::new);

    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    
    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
    
}
