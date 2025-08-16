package io.github.bigcrazyofficial.showtime.client.mixin;

import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Particle.class)
public interface ParticleMixin {
    @Accessor
    void setStopped(boolean stopped);
}
