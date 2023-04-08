package com.alvaro.testmod.datagen;


import com.alvaro.testmod.TestMod;
import com.alvaro.testmod.block.ModBlocks;
import com.alvaro.testmod.item.ModItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TestMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BLACK_OPAL);
        simpleItem(ModItems.RAW_BLACK_OPAL);
        saplingItem(ModBlocks.EBONY_SAPLING);
    }





    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TestMod.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item){
        return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TestMod.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(), 
        new ResourceLocation("item/handheld")).texture("layer0",
        new ResourceLocation(TestMod.MODID, "item/" + item.getId().getPath()));
    }
    
}
