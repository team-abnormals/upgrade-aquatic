package com.teamabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.client.BlueprintRenderTypes;
import com.teamabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class UAGlowSquidModel<T extends Entity> extends SquidModel<T> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "glow_squid"), "main");
	private static final RenderType UNSHADED_CUTOUT = BlueprintRenderTypes.getUnshadedCutoutEntity(GlowSquidSpriteUploader.ATLAS_LOCATION, false);
	private static final RenderType SOLID = RenderType.entitySolid(GlowSquidSpriteUploader.ATLAS_LOCATION);
	private final boolean emissive;

	public UAGlowSquidModel(ModelPart root, boolean emissive) {
		super(root);
		this.emissive = emissive;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -8.0F, -6.0F, 12.0F, 16.0F, 12.0F), PartPose.offset(0.0F, 8.0F, 0.0F));
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F);

		for (int k = 0; k < 8; ++k) {
			double d0 = (double) k * Math.PI * 2.0D / 8.0D;
			float f = (float) Math.cos(d0) * 5.0F;
			float f2 = (float) Math.sin(d0) * 5.0F;
			d0 = (double) k * Math.PI * -2.0D / 8.0D + (Math.PI / 2D);
			float f3 = (float) d0;
			partdefinition.addOrReplaceChild("tentacle" + k, cubelistbuilder, PartPose.offsetAndRotation(f, 15.0F, f2, 0.0F, f3, 0.0F));
		}

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		TextureAtlasSprite sprite = this.emissive ? GlowSquidSpriteUploader.getGlowSprite() : GlowSquidSpriteUploader.getSprite();
		super.renderToBuffer(matrixStack, sprite.wrap(Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(this.emissive ? UNSHADED_CUTOUT : SOLID)), this.emissive ? 15728880 : packedLight, packedOverlay, red, green, blue, alpha);
	}
}
