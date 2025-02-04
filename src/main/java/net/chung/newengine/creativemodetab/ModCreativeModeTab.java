package net.chung.newengine.creativemodetab;

import net.chung.newengine.NewEngine;
import net.chung.newengine.block.ModBlocks;
import net.chung.newengine.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NewEngine.MOD_ID);

    public static final RegistryObject<CreativeModeTab> UNKNOWNRITE_ITEMS_TAB = CREATIVE_MODE_TAB.register("unknownrite_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.UNKNOWNRITE_INGOT.get()))
                    .title(Component.translatable("creativetab.newengine.unknownrite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.UNKNOWNRITE_INGOT.get());
                        output.accept(ModItems.UNKNOWNRITE_DUST.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> UNKNOWNRITE_BlOCKS_TAB = CREATIVE_MODE_TAB.register("unknownrite_bloks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.UNKNOWNRITE_BLOCK.get()))
                    .withTabsBefore(UNKNOWNRITE_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.newengine.unknownrite_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.UNKNOWNRITE_BLOCK.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
