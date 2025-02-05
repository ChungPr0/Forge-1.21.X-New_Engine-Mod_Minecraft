package net.chung.newengine.item;

import net.chung.newengine.NewEngine;
import net.chung.newengine.armor.ModArmorMaterials;
import net.chung.newengine.item.custom.UnknownPearl;
import net.chung.newengine.tooltiers.ModToolTiers;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NewEngine.MOD_ID);

    public static final RegistryObject<Item> UNKNOWN_PEARL = ITEMS.register("unknown_pearl",
            () -> new UnknownPearl(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> UNKNOWNRITE_INGOT = ITEMS.register("unknownrite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNKNOWNRITE_DUST = ITEMS.register("unknownrite_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNKNOWN_SWORD = ITEMS.register("unknown_sword",
            () -> new SwordItem(ModToolTiers.UNKNOWNRITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.UNKNOWNRITE, 5, -2.4f))));
    public static final RegistryObject<Item> UNKNOWN_PICKAXE = ITEMS.register("unknown_pickaxe",
            () -> new PickaxeItem(ModToolTiers.UNKNOWNRITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.UNKNOWNRITE, 1, -2.8f))));
    public static final RegistryObject<Item> UNKNOWN_SHOVEL = ITEMS.register("unknown_shovel",
            () -> new ShovelItem(ModToolTiers.UNKNOWNRITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.UNKNOWNRITE, 1, -3.0f))));
    public static final RegistryObject<Item> UNKNOWN_AXE = ITEMS.register("unknown_axe",
            () -> new AxeItem(ModToolTiers.UNKNOWNRITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.UNKNOWNRITE, 7, -3.2f))));
    public static final RegistryObject<Item> UNKNOWN_HOE = ITEMS.register("unknown_hoe",
            () -> new HoeItem(ModToolTiers.UNKNOWNRITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.UNKNOWNRITE, 1, -3.0f))));

    public static final RegistryObject<Item> UNKNOWN_HELMET = ITEMS.register("unknown_helmet",
            () -> new ArmorItem(ModArmorMaterials.UNKNOWNRITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(18))));
    public static final RegistryObject<Item> UNKNOWN_CHESTPLATE = ITEMS.register("unknown_chestplate",
            () -> new ArmorItem(ModArmorMaterials.UNKNOWNRITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(18))));
    public static final RegistryObject<Item> UNKNOWN_LEGGINGS = ITEMS.register("unknown_leggings",
            () -> new ArmorItem(ModArmorMaterials.UNKNOWNRITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(18))));
    public static final RegistryObject<Item> UNKNOWN_BOOTS = ITEMS.register("unknown_boots",
            () -> new ArmorItem(ModArmorMaterials.UNKNOWNRITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(18))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}