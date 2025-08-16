package io.github.bigcrazyofficial.showtime.client.particle;

import io.github.bigcrazyofficial.showtime.util.UnjustifiablyObtuseParticleHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;

public class ConfettiEmitterParticle extends NoRenderParticle {
    private int emitterAge;
    private final int maxEmitterAge;
    private final ParticleEffect parameters;

    private ConfettiEmitterParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
        this.maxEmitterAge = 2;
        this.parameters = UnjustifiablyObtuseParticleHelper.CONFETTI;
        this.tick();
    }

    public void tick() {
        if(this.emitterAge == 0){
            this.world.addParticleClient(ParticleTypes.FLASH,
                    this.x,
                    this.y,
                    this.z,
                    0, 0, 0);

        }
        for(int i = 0; i < 64; ++i) {
            this.world.addParticleClient(this.parameters,
                    this.x,
                    this.y,
                    this.z,
                    random.nextGaussian() / 6f,
                    Math.abs(random.nextGaussian() / 10f),
                    random.nextGaussian() / 6f);
        }

        ++this.emitterAge;
        if (this.emitterAge >= this.maxEmitterAge) {
            this.markDead();
        }

    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        //apparently, despite the particle not rendering, we still have to give it a SpriteProvider or it won't register
        private final SpriteProvider spriteProvider;
        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;
        }
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new ConfettiEmitterParticle(world, x, y, z);
        }
    }
}
