package net.chung.newengine.item;

import net.chung.newengine.NewEngine;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NewEngine.MOD_ID);

    public static final RegistryObject<Item> UNKNOWNRITE = ITEMS.register("unknownrite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNKNOWNRITE_DUST = ITEMS.register("unknownrite_dust",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
