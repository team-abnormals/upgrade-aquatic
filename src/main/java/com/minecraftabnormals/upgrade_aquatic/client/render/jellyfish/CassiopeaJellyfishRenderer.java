package com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish;

import com.minecraftabnormals.upgrade_aquatic.client.model.jellyfish.CassiopeaJellyfishModel;
import com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish.layer.JellyfishEmissiveLayer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.CassiopeaJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CassiopeaJellyfishRenderer extends AbstractJellyfishRenderer<CassiopeaJellyfishEntity> {

	public CassiopeaJellyfishRenderer(EntityRenderDispatcher renderManager) {
		super(renderManager, new CassiopeaJellyfishModel<>(), 0.25F);
		this.addLayer(new JellyfishEmissiveLayer<>(this, this));
	}

	@Override
	public void render(CassiopeaJellyfishEntity jellyfish, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		this.shadowRadius *= jellyfish.getSize();
		super.render(jellyfish, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(CassiopeaJellyfishEntity jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/jellyfish/cassiopea/" + jellyfish.getBucketName() + "_jellyfish.png");
	}

	@Override
	public ResourceLocation getOverlayTexture(CassiopeaJellyfishEntity jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/jellyfish/cassiopea/" + jellyfish.getBucketName() + "_jellyfish_overlay.png");
	}

	@Override
	protected RenderType getRenderType(CassiopeaJellyfishEntity jellyfish, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
		return RenderType.entityTranslucent(this.getTextureLocation(jellyfish));
	}

	@Override
	protected void scale(CassiopeaJellyfishEntity jellyfish, PoseStack matrixStack, float partialTickTime) {
		float size = jellyfish.getSize();
		matrixStack.scale(size, size, size);
	}

}