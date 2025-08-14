// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package io.github.bigcrazyofficial.showtime.client.entity;

import io.github.bigcrazyofficial.showtime.Showtime;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.util.Identifier;

public class ShowtimeBallModel extends EntityModel<EntityRenderState> {
	public static final EntityModelLayer BALL = new EntityModelLayer(Identifier.of(Showtime.MOD_ID, "ball"), "main");
	private final ModelPart bb_main;
	public ShowtimeBallModel(ModelPart root) {
		super(root.getChild("bb_main"));
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, -2.42F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

}