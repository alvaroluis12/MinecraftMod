package com.alvaro.rpgmod.item;

import com.alvaro.rpgmod.RPGMod;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RPGMod.MODID);
    

    public static final RegistryObject<Item> BLACK_OPAL = ITEMS.register( "black_opal", 
            () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> RAW_BLACK_OPAL = ITEMS.register( "raw_black_opal", 
            () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<SwordItem> BLACK_OPAL_SWORD = ITEMS.register( "black_opal_sword", 
            () -> new SwordItem(Tiers.BLACK_OPAL, 5, 3.5f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> BLACK_OPAL_PICKAXE = ITEMS.register( "black_opal_pickaxe", 
            () -> new PickaxeItem(Tiers.BLACK_OPAL,0, 0f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> BLACK_OPAL_SHOVEL = ITEMS.register( "black_opal_shovel", 
            () -> new ShovelItem(Tiers.BLACK_OPAL, 0f, 0f, new Item.Properties()));
    public static final RegistryObject<AxeItem> BLACK_OPAL_AXE = ITEMS.register( "black_opal_axe", 
            () -> new AxeItem(Tiers.BLACK_OPAL, 10f, 0f, new Item.Properties()));
    public static final RegistryObject<HoeItem> BLACK_OPAL_HOE = ITEMS.register( "black_opal_hoe", 
            () -> new HoeItem(Tiers.BLACK_OPAL, 0, 0f, new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


    public static class Tiers {
        public static final Tier BLACK_OPAL = new ForgeTier(
            5, 
            8000, 
            15f, 
            10, 
            30, 
            null, 
            () -> Ingredient.of(ModItems.BLACK_OPAL.get()));
    }
}
