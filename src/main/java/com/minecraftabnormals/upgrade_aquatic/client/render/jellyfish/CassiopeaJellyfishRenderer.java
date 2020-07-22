package com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish;

import com.minecraftabnormals.upgrade_aquatic.client.model.jellyfish.ModelCassiopeaJellyfish;
import com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish.layer.JellyfishEmissiveLayer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.EntityCassiopeaJellyfish;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CassiopeaJellyfishRenderer extends AbstractJellyfishRenderer<EntityCassiopeaJellyfish> {

	public CassiopeaJellyfishRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ModelCassiopeaJellyfish<>(), 0.25F);
		this.addLayer(new JellyfishEmissiveLayer<>(this, this));
	}
	
	@Override
	public void render(EntityCassiopeaJellyfish jellyfish, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		this.shadowSize *= jellyfish.getSize();
		super.render(jellyfish, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityCassiopeaJellyfish jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/jellyfish/cassiopea/" + jellyfish.getBucketName() + "_jellyfish.png");
	}
	
	@Override
	public ResourceLocation getOverlayTexture(EntityCassiopeaJellyfish jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/jellyfish/cassiopea/" + jellyfish.getBucketName() + "_jellyfish_overlay.png");
	}
	
	@Override
	protected RenderType func_230496_a_(EntityCassiopeaJellyfish jellyfish, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
		return RenderType.getEntityTranslucent(this.getEntityTexture(jellyfish));
	}
	
	@Override
	protected void preRenderCallback(EntityCassiopeaJellyfish jellyfish, MatrixStack matrixStack, float partialTickTime) {
		float size = jellyfish.getSize();
		matrixStack.scale(size, size, size);
	}

}