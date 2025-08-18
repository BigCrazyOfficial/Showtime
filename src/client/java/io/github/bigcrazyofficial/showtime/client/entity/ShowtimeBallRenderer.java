package io.github.bigcrazyofficial.showtime.client.entity;

import io.github.bigcrazyofficial.showtime.Showtime;
import io.github.bigcrazyofficial.showtime.entity.ShowtimeBallEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;


public class ShowtimeBallRenderer extends EntityRenderer<ShowtimeBallEntity, ProjectileEntityRenderState> {
    private static final Identifier TEXTURE = Identifier.of(Showtime.MOD_ID,"textures/entity/showtime_ball.png");
    private final ShowtimeBallModel model;

    public ShowtimeBallRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model =  new ShowtimeBallModel(context.getPart(ShowtimeBallModel.BALL));
    }

    @Override
    public void render(ProjectileEntityRenderState state,
                       MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light) {
        matrices.push();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(TEXTURE));
        matrices.translate(0f, 0.2f, 0f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(state.age, state.yaw, state.yaw + 8)));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.lerp(state.age, state.pitch, state.pitch + 8)));
        matrices.scale(0.75f, 0.75f, 0.75f);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }
    @Override
    public ProjectileEntityRenderState createRenderState() {
        return new ProjectileEntityRenderState();
    }

}
