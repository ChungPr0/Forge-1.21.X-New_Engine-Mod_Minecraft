package net.chung.newengine.entity.custom;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class UnknownPearlEntity extends ThrowableProjectile {

    public UnknownPearlEntity(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder)
    }
}
