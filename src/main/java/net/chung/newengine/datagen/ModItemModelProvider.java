package net.chung.newengine.datagen;

import net.chung.newengine.NewEngine;
import net.chung.newengine.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NewEngine.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.UNKNOWNRITE_INGOT.get());
        basicItem(ModItems.UNKNOWNRITE_DUST.get());

        basicItem(ModItems.UNKNOWN_PEARL.get());

        handheldItem(ModItems.UNKNOWN_SWORD);
        handheldItem(ModItems.UNKNOWN_PICKAXE);
        handheldItem(ModItems.UNKNOWN_AXE);
        handheldItem(ModItems.UNKNOWN_SHOVEL);
        handheldItem(ModItems.UNKNOWN_HOE);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(NewEngine.MOD_ID, "item/" + item.getId().getPath()));
    }
}