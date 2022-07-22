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

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		TextureAtlasSprite sprite = this.emissive ? GlowSquidSpriteUploader.getGlowSprite() : GlowSquidSpriteUploader.getSprite();
		super.renderToBuffer(matrixStack, sprite.wrap(Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(this.emissive ? UNSHADED_CUTOUT : SOLID)), this.emissive ? 15728880 : packedLight, packedOverlay, red, green, blue, alpha);
	}
}
