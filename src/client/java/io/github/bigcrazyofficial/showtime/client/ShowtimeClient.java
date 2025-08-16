package io.github.bigcrazyofficial.showtime.client;

import io.github.bigcrazyofficial.showtime.client.entity.ShowtimeBallModel;
import io.github.bigcrazyofficial.showtime.client.entity.ShowtimeBallRenderer;
import io.github.bigcrazyofficial.showtime.client.particle.ConfettiEmitterParticle;
import io.github.bigcrazyofficial.showtime.client.particle.ConfettiParticle;
import io.github.bigcrazyofficial.showtime.entity.ModEntities;
import io.github.bigcrazyofficial.showtime.util.UnjustifiablyObtuseParticleHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ShowtimeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ShowtimeBallModel.BALL, ShowtimeBallModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SHOWTIME_BALL, ShowtimeBallRenderer::new);

        ParticleFactoryRegistry.getInstance().register(UnjustifiablyObtuseParticleHelper.CONFETTI, ConfettiParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(UnjustifiablyObtuseParticleHelper.CONFETTI_EMITTER, ConfettiEmitterParticle.Factory::new);

    }
}
