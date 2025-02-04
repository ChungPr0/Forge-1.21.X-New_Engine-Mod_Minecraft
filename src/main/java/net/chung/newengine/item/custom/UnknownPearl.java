package net.chung.newengine.item.custom;

import net.chung.newengine.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.EntityTeleportEvent;

import java.util.List;

public class UnknownPearl extends Item {
    public UnknownPearl(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        pPlayer.getCooldowns().addCooldown(this, 20);
        if (!pLevel.isClientSide) {
            ThrownUnknownpearl thrownunknownpearl = new ThrownUnknownpearl(pLevel, pPlayer);
            thrownunknownpearl.setItem(itemstack);
            thrownunknownpearl.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(thrownunknownpearl);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    public class ThrownUnknownpearl extends ThrowableItemProjectile {
        public ThrownUnknownpearl(Level pLevel, LivingEntity pShooter) {
            super(EntityType.ENDER_PEARL, pShooter, pLevel);
        }

        @Override
        protected Item getDefaultItem() {
            return ModItems.UNKNOWN_PEARL.get();
        }

        @Override
        protected void onHitEntity(EntityHitResult pResult) {
            super.onHitEntity(pResult);
            pResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
        }

        @Override
        protected void onHit(HitResult pResult) {
            super.onHit(pResult);

            for (int i = 0; i < 32; i++) {
                this.level()
                        .addParticle(
                                ParticleTypes.PORTAL,
                                this.getX(),
                                this.getY() + this.random.nextDouble() * 2.0,
                                this.getZ(),
                                this.random.nextGaussian(),
                                0.0,
                                this.random.nextGaussian()
                        );
            }

            if (this.level() instanceof ServerLevel serverlevel && !this.isRemoved()) {
                Entity entity = this.getOwner();
                if (entity != null && isAllowedToTeleportOwner(entity, serverlevel)) {
                    if (entity.isPassenger()) {
                        entity.unRide();
                    }

                    if (entity instanceof ServerPlayer serverplayer) {
                        if (serverplayer.connection.isAcceptingMessages()) {
                            var event = new unknownPearl(serverplayer, this.getX(), this.getY(), this.getZ(), this, 1.0F, pResult);
                            if (event.isCanceled()) {
                                this.discard();
                                return;
                            }

                            entity.changeDimension(
                                    new DimensionTransition(
                                            serverlevel, event.getTarget(), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), DimensionTransition.DO_NOTHING
                                    )
                            );
                            entity.resetFallDistance();
                            serverplayer.resetCurrentImpulseContext();
                            entity.hurt(this.damageSources().fall(), event.getAttackDamage());
                        }
                    } else {
                        entity.changeDimension(
                                new DimensionTransition(
                                        serverlevel, this.position(), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), DimensionTransition.DO_NOTHING
                                )
                        );
                        entity.resetFallDistance();
                    }

                    this.discard();
                    return;
                }

                this.discard();
            }
        }

        private static boolean isAllowedToTeleportOwner(Entity pEntity, Level pLevel) {
            if (pEntity.level().dimension() == pLevel.dimension()) {
                return !(pEntity instanceof LivingEntity livingentity) ? pEntity.isAlive() : livingentity.isAlive() && !livingentity.isSleeping();
            } else {
                return pEntity.canUsePortal(true);
            }
        }

        @Override
        public void tick() {
            Entity entity = this.getOwner();
            if (entity instanceof ServerPlayer && !entity.isAlive() && this.level().getGameRules().getBoolean(GameRules.RULE_ENDER_PEARLS_VANISH_ON_DEATH)) {
                this.discard();
            } else {
                super.tick();
            }
        }

        @Override
        public boolean canChangeDimensions(Level pOldLevel, Level pNewLevel) {
            return pOldLevel.dimension() == Level.END && this.getOwner() instanceof ServerPlayer serverplayer
                    ? super.canChangeDimensions(pOldLevel, pNewLevel) && serverplayer.seenCredits
                    : super.canChangeDimensions(pOldLevel, pNewLevel);
        }

        @Override
        protected void onInsideBlock(BlockState pState) {
            super.onInsideBlock(pState);
            if (pState.is(Blocks.END_GATEWAY) && this.getOwner() instanceof ServerPlayer serverplayer) {
                serverplayer.onInsideBlock(pState);
            }
        }
    }

    public class unknownPearl extends EntityTeleportEvent {
        private final ServerPlayer player;
        private final ThrownUnknownpearl pearlEntity;
        private float attackDamage;
        private final HitResult hitResult;

        public unknownPearl(ServerPlayer entity, double targetX, double targetY, double targetZ, ThrownUnknownpearl pearlEntity, float attackDamage, HitResult hitResult) {
            super(entity, targetX, targetY, targetZ);
            this.pearlEntity = pearlEntity;
            this.player = entity;
            this.attackDamage = attackDamage;
            this.hitResult = hitResult;
        }

        public float getAttackDamage() {
            return attackDamage;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.newengine.unknown_pearl.tooltip_shift_down"));
        } else {
            pTooltipComponents.add(Component.translatable("item.newengine.unknown_pearl.tooltip"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
