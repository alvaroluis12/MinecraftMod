package com.alvaro.rpgmod.item;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.ModEntities;
import com.alvaro.rpgmod.fluid.ModFluids;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeSpawnEggItem;
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
            () -> new SwordItem(ModTiers.BLACK_OPAL, 5, 3.5f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> BLACK_OPAL_PICKAXE = ITEMS.register( "black_opal_pickaxe", 
            () -> new PickaxeItem(ModTiers.BLACK_OPAL,0, 0f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> BLACK_OPAL_SHOVEL = ITEMS.register( "black_opal_shovel", 
            () -> new ShovelItem(ModTiers.BLACK_OPAL, 0f, 0f, new Item.Properties()));
    public static final RegistryObject<AxeItem> BLACK_OPAL_AXE = ITEMS.register( "black_opal_axe", 
            () -> new AxeItem(ModTiers.BLACK_OPAL, 10f, 0f, new Item.Properties()));
    public static final RegistryObject<HoeItem> BLACK_OPAL_HOE = ITEMS.register( "black_opal_hoe", 
            () -> new HoeItem(ModTiers.BLACK_OPAL, 0, 0f, new Item.Properties()));

    public static final RegistryObject<Item> TIGER_SPAWN_EGG = ITEMS.register("tiger_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TIGER, 0xD57E36, 0x1D0D00, new Item.Properties()));

    public static final RegistryObject<Item> TROLL_SPAWN_EGG = ITEMS.register("troll_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TROLL, 0xD57E36, 0x1D0D00, new Item.Properties()));

    public static final RegistryObject<Item> GLOBLIN_SPAWN_EGG = ITEMS.register("globlin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GLOBLIN, 0xD57E36, 0x1D0D00, new Item.Properties()));

    public static final RegistryObject<Item> ARCHER_GLOBLIN_SPAWN_EGG = ITEMS.register("archer_globlin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ARCHER_GLOBLIN, 0xD57E36, 0x1D0D00, new Item.Properties()));

    public static final RegistryObject<Item> WINDIGO_SPAWN_EGG = ITEMS.register("windigo_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.WINDIGO, 0xD57E36, 0x1D0D00, new Item.Properties()));

    public static final RegistryObject<Item> MANA_FLUID_BUCKET = ITEMS.register("mana_fluid_bucket", 
            () -> new BucketItem(ModFluids.SOURCE_MANA, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<SwordItem> GLOBLIN_DAGGER = ITEMS.register( "globlin_dagger", 
            () -> new SwordItem(Tiers.STONE, 3, -2.4f, new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


    public static class ModTiers {
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
