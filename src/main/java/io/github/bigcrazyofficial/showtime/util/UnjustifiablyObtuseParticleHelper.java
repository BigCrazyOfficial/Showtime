package io.github.bigcrazyofficial.showtime.util;

import io.github.bigcrazyofficial.showtime.Showtime;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UnjustifiablyObtuseParticleHelper {
    public static final SimpleParticleType CONFETTI =
            registerParticle("confetti", FabricParticleTypes.simple());
    public static final SimpleParticleType CONFETTI_EMITTER =
            registerParticle("confetti_emitter", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Showtime.MOD_ID, name), particleType);
    }

    public static void initialize() {
        Showtime.LOGGER.info("BAAH!");
    }
}
