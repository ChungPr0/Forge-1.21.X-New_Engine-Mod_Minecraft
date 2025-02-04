package net.chung.newengine.entity;

import net.chung.newengine.NewEngine;
import net.chung.newengine.entity.custom.UnknownPearlEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NewEngine.MOD_ID);

    public static final RegistryObject<EntityType<UnknownPearlEntity>> UNKNOWN_PEARL =
            ENTITY_TYPES.register("unknown_pearl", () -> EntityType.Builder.of(UnknownPearlEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("unknown_pearl"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

