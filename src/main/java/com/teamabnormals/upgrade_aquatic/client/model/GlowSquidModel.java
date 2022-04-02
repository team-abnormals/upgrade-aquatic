package com.teamabnormals.upgrade_aquatic.client.model;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.blueprint.client.ACRenderTypes;
import com.teamabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.GlowSquid;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.util.Arrays;

/**
 * @author Ocelot
 */
public class GlowSquidModel extends ListModel<GlowSquid> {
	private final boolean emissive;
	private final ModelPart body;
	private final ModelPart[] legs = new ModelPart[8];
	private final ImmutableList<ModelPart> parts;

	public GlowSquidModel(boolean emissive) {
		this.texWidth = 64;
		this.texHeight = 64;

		this.emissive = emissive;

		this.body = new ModelPart(this, 0, 0);
		this.body.addBox(-6.0F, -8.0F, -6.0F, 12.0F, 16.0F, 12.0F);
		this.body.y += 8.0F;

		for (int j = 0; j < this.legs.length; ++j) {
			this.legs[j] = new ModelPart(this, 48, 0);
			double d0 = (double) j * Math.PI * 2.0D / (double) this.legs.length;
			float f = (float) Math.cos(d0) * 5.0F;
			float f1 = (float) Math.sin(d0) * 5.0F;
			this.legs[j].addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F);
			this.legs[j].x = f;
			this.legs[j].z = f1;
			this.legs[j].y = 15.0F;
			d0 = (double) j * Math.PI * -2.0D / (double) this.legs.length + (Math.PI / 2D);
			this.legs[j].yRot = (float) d0;
		}

		ImmutableList.Builder<ModelPart> builder = ImmutableList.builder();
		builder.add(this.body);
		builder.addAll(Arrays.asList(this.legs));
		this.parts = builder.build();
	}

	@Override
	public void setupAnim(GlowSquid entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		for (ModelPart modelrenderer : this.legs) {
			modelrenderer.xRot = ageInTicks;
		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		TextureAtlasSprite sprite = this.emissive ? GlowSquidSpriteUploader.getGlowSprite() : GlowSquidSpriteUploader.getSprite();
		RenderType render = this.emissive ? ACRenderTypes.getEmissiveEntity(GlowSquidSpriteUploader.ATLAS_LOCATION) : RenderType.entitySolid(GlowSquidSpriteUploader.ATLAS_LOCATION);
		super.renderToBuffer(matrixStack, sprite.wrap(Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(render)), this.emissive ? 15728880 : packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public Iterable<ModelPart> parts() {
		return this.parts;
	}
}
