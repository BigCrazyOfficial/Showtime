package io.github.bigcrazyofficial.showtime.client.entity;

import io.github.bigcrazyofficial.showtime.Showtime;
import io.github.bigcrazyofficial.showtime.entity.ShowtimeBallEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ShowtimeBallRenderer extends EntityRenderer<ShowtimeBallEntity, EntityRenderState> {
    private static final Identifier TEXTURE = Identifier.of(Showtime.MOD_ID,"textures/entity/showtime_ball.png");
    private final ShowtimeBallModel model;

    public ShowtimeBallRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model =  new ShowtimeBallModel(context.getPart(ShowtimeBallModel.BALL));
    }

    @Override
    public void render(EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(TEXTURE));
        this.model.setAngles(state);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }
    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

}
