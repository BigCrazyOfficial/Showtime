package io.github.bigcrazyofficial.showtime.client.particle;

import io.github.bigcrazyofficial.showtime.Showtime;
import io.github.bigcrazyofficial.showtime.client.mixin.ParticleMixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ConfettiParticle extends SpriteBillboardParticle {
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public float rotationSpeedX;
    public float rotationSpeedY;
    public float rotationSpeedZ;
    public float offsetY;


    public ConfettiParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);

        this.alpha = 1f;
        this.red = world.getRandom().nextFloat();
        this.blue = world.getRandom().nextFloat();
        this.green = world.getRandom().nextFloat();
        this.scale *= 0.55F + (world.getRandom().nextFloat() / 10);
        this.maxAge = 1000 + (world.getRandom().nextBetween(0, 60) * 5);
        this.gravityStrength = 0.1f + (world.getRandom().nextFloat() / 3);
        this.velocityMultiplier = 1.8f;
        this.setSprite(spriteProvider);

        this.rotationX = world.getRandom().nextFloat() * 360f;
        this.rotationY = world.getRandom().nextFloat() * 360f;
        this.rotationZ = world.getRandom().nextFloat() * 360f;
        this.rotationSpeedX = world.getRandom().nextFloat() * 10f * (random.nextBoolean() ? -1 : 1);
        this.rotationSpeedY = world.getRandom().nextFloat() * 10f * (random.nextBoolean() ? -1 : 1);
        this.rotationSpeedZ = world.getRandom().nextFloat() * 10f * (random.nextBoolean() ? -1 : 1);
        this.offsetY = 0.01f + (world.getRandom().nextFloat() / 100f);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    //i still don't really understand this but as long as it works that's fine
    public Quaternionf manifestQuaternion(float pitch, float yaw, float roll) {
        pitch *= ((float) Math.PI / 180F);
        yaw *= ((float) Math.PI / 180F);
        roll *= ((float) Math.PI / 180F);
        float sinPitch = MathHelper.sin(0.5F * pitch);
        float cosPitch = MathHelper.cos(0.5F * pitch);
        float sinYaw = MathHelper.sin(0.5F * yaw);
        float cosYaw = MathHelper.cos(0.5F * yaw);
        float sinRoll = MathHelper.sin(0.5F * roll);
        float cosRoll = MathHelper.cos(0.5F * roll);
        pitch = sinPitch * cosYaw * cosRoll + cosPitch * sinYaw * sinRoll;
        yaw = cosPitch * sinYaw * cosRoll - sinPitch * cosYaw * sinRoll;
        roll = sinPitch * sinYaw * cosRoll + cosPitch * cosYaw * sinRoll;
        float w = cosPitch * cosYaw * cosRoll - sinPitch * sinYaw * sinRoll;
        return new Quaternionf(pitch, yaw, roll, w);
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float tickProgress) {
        Vec3d cameraPos = camera.getPos();
        float camX = (float) (MathHelper.lerp(tickProgress, this.lastX, this.x) - cameraPos.getX());
        float camY = (float) (MathHelper.lerp(tickProgress, this.lastY, this.y) - cameraPos.getY());
        float camZ = (float) (MathHelper.lerp(tickProgress, this.lastZ, this.z) - cameraPos.getZ());
        Vector3f[] vec3f = new Vector3f[]{new Vector3f(-1, -1, 0), new Vector3f(-1, 1, 0), new Vector3f(1, 1, 0), new Vector3f(1, -1, 0)};
        float size = this.scale;
        float offsetY;
        if(this.onGround){
            rotationY = 0;
            rotationX = 90f;
            offsetY = this.offsetY;
        } else {
            rotationX += rotationSpeedX;
            rotationY += rotationSpeedY;
            rotationZ += rotationSpeedZ;
            offsetY = 0f;
        }
        for(int k = 0; k < 4; ++k) {
            Vector3f vec3f2 = vec3f[k];
            vec3f2.rotate(manifestQuaternion(rotationX, rotationY, rotationZ));
            vec3f2.normalize(size);
            vec3f2.add(camX, camY + offsetY, camZ);
        }

        float minU = this.getMinU();
        float maxU = this.getMaxU();
        float minV = this.getMinV();
        float maxV = this.getMaxV();
        int light = this.getBrightness(tickProgress);

        vertexConsumer.vertex(vec3f[0].x(), vec3f[0].y(), vec3f[0].z()).texture(maxU, maxV).color(red, green, blue, alpha).light(light);
        vertexConsumer.vertex(vec3f[1].x(), vec3f[1].y(), vec3f[1].z()).texture(maxU, minV).color(red, green, blue, alpha).light(light);
        vertexConsumer.vertex(vec3f[2].x(), vec3f[2].y(), vec3f[2].z()).texture(minU, minV).color(red, green, blue, alpha).light(light);
        vertexConsumer.vertex(vec3f[3].x(), vec3f[3].y(), vec3f[3].z()).texture(minU, maxV).color(red, green, blue, alpha).light(light);
        vertexConsumer.vertex(vec3f[0].x(), vec3f[0].y(), vec3f[0].z()).texture(maxU, maxV).color(red, green, blue, alpha).light(light);
        vertexConsumer.vertex(vec3f[3].x(), vec3f[3].y(), vec3f[3].z()).texture(maxU, minV).color(red, green, blue, alpha).light(light);
        vertexConsumer.vertex(vec3f[2].x(), vec3f[2].y(), vec3f[2].z()).texture(minU, minV).color(red, green, blue, alpha).light(light);
        vertexConsumer.vertex(vec3f[1].x(), vec3f[1].y(), vec3f[1].z()).texture(minU, maxV).color(red, green, blue, alpha).light(light);
    }

    @Override
    public void tick() {
        this.lastX = this.x;
        this.lastY = this.y;
        this.lastZ = this.z;
        if(this.age++ >= this.maxAge || this.alpha <= 0) {
            this.markDead();
        } else {
            if(this.world.getFluidState(BlockPos.ofFloored(this.x, (this.y + 0.2), this.z)).isEmpty()) {
                if(this.world.getFluidState(BlockPos.ofFloored(this.x, (this.y - 0.01), this.z)).isIn(FluidTags.WATER)) {
                    this.markDead();
                } else {
                    this.velocityY -= 0.06d * (double) this.gravityStrength;
                    this.move(this.velocityX, this.velocityY, this.velocityZ);
                    if(this.y == this.lastY && !this.onGround) {
                        ((ParticleMixin) this).setStopped(false);
                    }
                    this.velocityX *= this.velocityMultiplier;
                    this.velocityY *= this.velocityMultiplier;
                    this.velocityZ *= this.velocityMultiplier;

                    this.velocityMultiplier = Math.min(0.98f, this.velocityMultiplier * 1.15f);

                    if((double) this.maxAge / this.age <= 1.1){
                        this.setAlpha(this.alpha -= 0.03f);
                        if(this.alpha <= 0){
                            this.setAlpha(0);
                        }
                    }
                }
            } else {
                this.markDead();
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new ConfettiParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        }
    }
}
