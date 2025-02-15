package net.chung.newengine.datagen;

import net.chung.newengine.NewEngine;
import net.chung.newengine.block.ModBlocks;
import net.chung.newengine.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> UNKNOWNRITE_SMELTABLES = List.of(ModItems.UNKNOWNRITE_DUST.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.UNKNOWNRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.UNKNOWNRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.UNKNOWNRITE_INGOT.get()), has(ModItems.UNKNOWNRITE_INGOT.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.UNKNOWNRITE_INGOT.get(), 9)
                .requires(ModBlocks.UNKNOWNRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.UNKNOWNRITE_BLOCK.get()), has(ModBlocks.UNKNOWNRITE_BLOCK.get())).save(pRecipeOutput);

//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 32)
//                .requires(ModBlocks.MAGIC_BLOCK.get())
//                .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK.get()), has(ModBlocks.ALEXANDRITE_BLOCK.get()))
//                .save(pRecipeOutput, TutorialMod.MOD_ID + ":alexandrite_from_magic_block");

        oreSmelting(pRecipeOutput, UNKNOWNRITE_SMELTABLES, RecipeCategory.MISC, ModItems.UNKNOWNRITE_INGOT.get(), 0.25f, 200, "unknownrite");
        oreBlasting(pRecipeOutput, UNKNOWNRITE_SMELTABLES, RecipeCategory.MISC, ModItems.UNKNOWNRITE_INGOT.get(), 0.25f, 100, "unknownrite");

    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, NewEngine.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}