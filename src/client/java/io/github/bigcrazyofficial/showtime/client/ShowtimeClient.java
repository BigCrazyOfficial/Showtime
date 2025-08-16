package io.github.bigcrazyofficial.showtime.client;

import io.github.bigcrazyofficial.showtime.Showtime;
import io.github.bigcrazyofficial.showtime.client.entity.ShowtimeBallModel;
import io.github.bigcrazyofficial.showtime.client.entity.ShowtimeBallRenderer;
import io.github.bigcrazyofficial.showtime.client.particle.ConfettiParticle;
import io.github.bigcrazyofficial.showtime.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ShowtimeClient implements ClientModInitializer {
    public static SimpleParticleType CONFETTI;

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ShowtimeBallModel.BALL, ShowtimeBallModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SHOWTIME_BALL, ShowtimeBallRenderer::new);


        SimpleParticleType CONFETTI = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Showtime.MOD_ID, "confetti"), FabricParticleTypes.simple(true));
        ParticleFactoryRegistry.getInstance().register(CONFETTI, ConfettiParticle.DefaultFactory::new);
    }
}
