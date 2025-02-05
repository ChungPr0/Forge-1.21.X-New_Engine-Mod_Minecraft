package net.chung.newengine.datagen;

import net.chung.newengine.NewEngine;
import net.chung.newengine.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NewEngine.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.UNKNOWNRITE_INGOT.get());
        basicItem(ModItems.UNKNOWNRITE_DUST.get());

        basicItem(ModItems.UNKNOWN_PEARL.get());

    }
}