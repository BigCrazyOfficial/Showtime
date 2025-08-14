package io.github.bigcrazyofficial.showtime.client;

import io.github.bigcrazyofficial.showtime.client.entity.ShowtimeBallModel;
import io.github.bigcrazyofficial.showtime.client.entity.ShowtimeBallRenderer;
import io.github.bigcrazyofficial.showtime.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ShowtimeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ShowtimeBallModel.BALL, ShowtimeBallModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SHOWTIME_BALL, ShowtimeBallRenderer::new);
    }
}
