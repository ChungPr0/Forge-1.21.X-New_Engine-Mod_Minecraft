package net.chung.newengine.datagen;

import net.chung.newengine.NewEngine;
import net.chung.newengine.item.ModItems;
import net.chung.newengine.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, NewEngine.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
//        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
//                .add(ModItems.ALEXANDRITE.get())
//                .add(ModItems.RAW_ALEXANDRITE.get())
//                .add(Items.COAL)
//                .add(Items.STICK)
//                .add(Items.COMPASS);
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.UNKNOWN_HELMET.get())
                .add(ModItems.UNKNOWN_CHESTPLATE.get())
                .add(ModItems.UNKNOWN_LEGGINGS.get())
                .add(ModItems.UNKNOWN_BOOTS.get());
    }
}