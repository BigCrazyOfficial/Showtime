package io.github.bigcrazyofficial.showtime.entity;

import io.github.bigcrazyofficial.showtime.util.Sounds;
import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

public class ShowtimeBallEntity extends AbstractWindChargeEntity {
    private static final ExplosionBehavior EXPLOSION_BEHAVIOR;
    private int deflectCooldown = 5;

    public ShowtimeBallEntity(EntityType<? extends AbstractWindChargeEntity> entityType, World world) {
        super(entityType, world);
    }

    public ShowtimeBallEntity(World world, PlayerEntity player, double x, double y, double z) {
        super(ModEntities.SHOWTIME_BALL, world, player, x, y, z);
    }

    @Override
    public void tick() {
        super.tick();
        this.applyGravity();
        if (!this.getWorld().isClient){
            ProjectileUtil.setRotationFromVelocity(this, 0.2F);
        }
        if (this.deflectCooldown > 0) {
            --this.deflectCooldown;
        }
    }

    @Override
    protected void createExplosion(Vec3d pos) {
        this.getWorld().createExplosion(this, (DamageSource)null, EXPLOSION_BEHAVIOR, pos.getX(), pos.getY(), pos.getZ(), 1.6F, false, World.ExplosionSourceType.TRIGGER, ParticleTypes.FLASH, ParticleTypes.GUST_EMITTER_SMALL, Sounds.BALL_DETONATE);
    }

    /*@Override
    public boolean deflect(ProjectileDeflection deflection, @Nullable Entity deflector, @Nullable Entity owner, boolean fromAttack) {
        return this.deflectCooldown <= 0 && super.deflect(deflection, deflector, owner, fromAttack);
    }*/

    protected double getGravity() { return 0.055; }

    @Override
    public DoubleDoubleImmutablePair getKnockback(LivingEntity target, DamageSource source) {
        double d = this.getVelocity().x * 2;
        double e = this.getVelocity().z * 2;
        return DoubleDoubleImmutablePair.of(d, e);
    }

    @Nullable
    @Override
    protected ParticleEffect getParticleType() {
        return ParticleTypes.FIREWORK;
    }

    static {
        EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(true, false, Optional.of(1.4F), Registries.BLOCK.getOptional(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));
    }
}
