package com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish;

import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.BoxJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.layers.JellyfishEmissiveLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.BoxJellyfishEntity;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoxJellyfishRenderer extends AbstractJellyfishRenderer<BoxJellyfishEntity> {

	public BoxJellyfishRenderer(EntityRenderDispatcher renderManager) {
		super(renderManager, new BoxJellyfishModel<>(), 0.5F);
		this.addLayer(new JellyfishEmissiveLayer<>(this, this));
	}

	@Override
	public void render(BoxJellyfishEntity jellyfish, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		this.shadowRadius *= jellyfish.getSize();
		super.render(jellyfish, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(BoxJellyfishEntity jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/jellyfish/box/" + jellyfish.getBucketName() + "_jellyfish.png");
	}

	@Override
	public ResourceLocation getOverlayTexture(BoxJellyfishEntity jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/jellyfish/box/" + jellyfish.getBucketName() + "_jellyfish_overlay.png");
	}

	@Override
	protected RenderType getRenderType(BoxJellyfishEntity jellyfish, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
		return RenderType.entityTranslucent(this.getTextureLocation(jellyfish));
	}

	@Override
	protected void scale(BoxJellyfishEntity jellyfish, PoseStack matrixStack, float partialTickTime) {
		float size = jellyfish.getSize();
		matrixStack.scale(size, size, size);
	}

}