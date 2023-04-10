package com.alvaro.rpgmod.datagen;


import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.block.ModBlocks;
import com.alvaro.rpgmod.item.ModItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RPGMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BLACK_OPAL);
        simpleItem(ModItems.RAW_BLACK_OPAL);
        saplingItem(ModBlocks.EBONY_SAPLING);
        swordItem(ModItems.BLACK_OPAL_SWORD);
        pickaxeItem(ModItems.BLACK_OPAL_PICKAXE);
        axeItem(ModItems.BLACK_OPAL_AXE);
        shovelItem(ModItems.BLACK_OPAL_SHOVEL);
        hoeItem(ModItems.BLACK_OPAL_HOE);
        withExistingParent(ModItems.TIGER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.TROLL_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }





    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(RPGMod.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(RPGMod.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder swordItem(RegistryObject<SwordItem> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(RPGMod.MODID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder pickaxeItem(RegistryObject<PickaxeItem> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(RPGMod.MODID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder axeItem(RegistryObject<AxeItem> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(RPGMod.MODID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder shovelItem(RegistryObject<ShovelItem> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(RPGMod.MODID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder hoeItem(RegistryObject<HoeItem> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(RPGMod.MODID, "item/" + item.getId().getPath()));
    }
    
    
}
