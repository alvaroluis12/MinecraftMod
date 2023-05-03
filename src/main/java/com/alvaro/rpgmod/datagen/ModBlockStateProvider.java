package com.alvaro.rpgmod.datagen;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.block.ModBlocks;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider{

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RPGMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels(){
        blockWithItem(ModBlocks.BLACK_OPAL_BLOCK);
        blockWithItem(ModBlocks.BLACK_OPAL_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE);
        blockWithItem(ModBlocks.QUEST_BLOCK);
        //blockWithItem(ModBlocks.NETHERRACK_BLACK_OPAL_ORE);
        //blockWithItem(ModBlocks.ENDSTONE_BLACK_OPAL_ORE);


        logBlock(((RotatedPillarBlock) ModBlocks.EBONY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.EBONY_WOOD.get(), blockTexture(ModBlocks.EBONY_LOG.get()), blockTexture(ModBlocks.EBONY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_EBONY_LOG.get(), new ResourceLocation(RPGMod.MODID, "block/stripped_ebony_log"),
                new ResourceLocation(RPGMod.MODID, "block/stripped_ebony_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_EBONY_WOOD.get(), new ResourceLocation(RPGMod.MODID, "block/stripped_ebony_log"),
                new ResourceLocation(RPGMod.MODID, "block/stripped_ebony_log"));

        blockWithItem(ModBlocks.EBONY_LEAVES);
        blockWithItem(ModBlocks.EBONY_PLANKS);
        SaplingBlock(ModBlocks.EBONY_SAPLING);

        simpleBlockItem(ModBlocks.EBONY_LOG.get(), models().withExistingParent("rpgmod:ebony_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.EBONY_WOOD.get(), models().withExistingParent("rpgmod:ebony_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_EBONY_LOG.get(), models().withExistingParent("rpgmod:stripped_ebony_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_EBONY_WOOD.get(), models().withExistingParent("rpgmod:stripped_ebony_wood", "minecraft:block/cube_column"));

    }
    
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void SaplingBlock(RegistryObject<Block> blockRegistryObject){
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
