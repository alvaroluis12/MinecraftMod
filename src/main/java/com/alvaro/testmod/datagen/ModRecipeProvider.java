package com.alvaro.testmod.datagen;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.alvaro.testmod.TestMod;
import com.alvaro.testmod.block.ModBlocks;
import com.alvaro.testmod.item.ModItems;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder{

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        Smelting(consumer, List.of(ModItems.RAW_BLACK_OPAL.get()), RecipeCategory.MISC, 
                ModItems.BLACK_OPAL.get(), 0.7f, 200, "black_opal");
        
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.BLACK_OPAL.get(), RecipeCategory.MISC, ModBlocks.BLACK_OPAL_BLOCK.get());
        
        
        planksFromLogs(consumer, ModBlocks.EBONY_PLANKS.get(), ModBlocks.EBONY_LOG.get(), 4);
        planksFromLogs(consumer, ModBlocks.EBONY_PLANKS.get(), ModBlocks.EBONY_WOOD.get(), 4);
        planksFromLogs(consumer, ModBlocks.EBONY_PLANKS.get(), ModBlocks.STRIPPED_EBONY_LOG.get(), 4);
        planksFromLogs(consumer, ModBlocks.EBONY_PLANKS.get(), ModBlocks.STRIPPED_EBONY_WOOD.get(), 4);

        woodFromLogs(consumer, ModBlocks.EBONY_WOOD.get(), ModBlocks.EBONY_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_EBONY_WOOD.get(), ModBlocks.STRIPPED_EBONY_LOG.get());

        Smelting(consumer, List.of(ModBlocks.EBONY_LOG.get(), ModBlocks.STRIPPED_EBONY_LOG.get(), ModBlocks.EBONY_WOOD.get(), ModBlocks.STRIPPED_EBONY_WOOD.get()), 
            RecipeCategory.MISC, Items.CHARCOAL, 0.15f, 200, getName());

      
        // ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLACK_OPAL_BLOCK.get())
        //         .define('B', ModItems.BLACK_OPAL.get())
        //         .pattern("BBB")
        //         .pattern("BBB")
        //         .pattern("BBB")
        //         .unlockedBy("has_black_opal", inventoryTrigger(ItemPredicate.Builder.item()
        //                 .of(ModItems.BLACK_OPAL.get()).build()))
        //         .save(consumer);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> p_249580_, RecipeCategory p_251203_, ItemLike p_251689_, RecipeCategory p_251376_, ItemLike p_248771_) {
        nineBlockStorageRecipes(p_249580_, p_251203_, p_251689_, p_251376_, p_248771_, getSimpleRecipeName(p_248771_), (String)null, getSimpleRecipeName(p_251689_), (String)null);
     }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> p_250423_, RecipeCategory p_250083_, ItemLike p_250042_, RecipeCategory p_248977_, ItemLike p_251911_, String p_250475_, @Nullable String p_248641_, String p_252237_, @Nullable String p_250414_) {
        ShapelessRecipeBuilder.shapeless(p_250083_, p_250042_, 9).
                requires(p_251911_).
                group(p_250414_).
                unlockedBy(getHasName(p_251911_), has(p_251911_))
                .save(p_250423_, new ResourceLocation(TestMod.MODID, p_252237_));
        ShapedRecipeBuilder.shaped(p_248977_, p_251911_)
                .define('#', p_250042_)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(p_248641_)
                .unlockedBy(getHasName(p_250042_), has(p_250042_))
                .save(p_250423_, new ResourceLocation(TestMod.MODID, p_250475_));
     }

     protected static void Smelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        Cooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
     }

     protected static void Cooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for(ItemLike itemlike : p_249619_) {
           SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_)
                    .group(p_251450_)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(p_250791_, new ResourceLocation(TestMod.MODID, getItemName(p_250066_)) + p_249236_ + "_" + getItemName(itemlike));
        }
  
     }

     protected static void planksFromLogs(Consumer<FinishedRecipe> consumer, ItemLike plank, ItemLike logs, int result) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, plank, result)
                 .requires(logs)
                 .group("planks")
                 .unlockedBy("has_logs", inventoryTrigger(ItemPredicate.Builder.item()
                         .of(logs).build()))
                 .save(consumer, new ResourceLocation(TestMod.MODID, getItemName(plank) + "_from_" + getItemName(logs)));
     }
  
     protected static void woodFromLogs(Consumer<FinishedRecipe> p_126003_, ItemLike p_126004_, ItemLike p_126005_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, p_126004_, 3)
            .define('#', p_126005_)
            .pattern("##")
            .pattern("##")
            .group("bark")
            .unlockedBy("has_log", has(p_126005_))
            .save(p_126003_, new ResourceLocation(TestMod.MODID, getItemName(p_126004_) + "_from_" + getItemName(p_126005_)));
     }
    
}
