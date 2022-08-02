package com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish;

import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.CassiopeaJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.layers.JellyfishEmissiveLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.CassiopeaJellyfish;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CassiopeaJellyfishRenderer extends AbstractJellyfishRenderer<CassiopeaJellyfish> {

	public CassiopeaJellyfishRenderer(EntityRendererProvider.Context context) {
		super(context, new CassiopeaJellyfishModel<>(context.bakeLayer(CassiopeaJellyfishModel.LOCATION)), 0.25F);
		this.addLayer(new JellyfishEmissiveLayer<>(this, this));
	}

	@Override
	public void render(CassiopeaJellyfish jellyfish, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		this.shadowRadius *= jellyfish.getSize();
		super.render(jellyfish, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(CassiopeaJellyfish jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/jellyfish/cassiopea/" + jellyfish.getVariantName() + "_jellyfish.png");
	}

	@Override
	public ResourceLocation getOverlayTexture(CassiopeaJellyfish jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/jellyfish/cassiopea/" + jellyfish.getVariantName() + "_jellyfish_overlay.png");
	}

	@Override
	protected RenderType getRenderType(CassiopeaJellyfish jellyfish, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
		return RenderType.entityTranslucent(this.getTextureLocation(jellyfish));
	}

	@Override
	protected void scale(CassiopeaJellyfish jellyfish, PoseStack matrixStack, float partialTickTime) {
		float size = jellyfish.getSize();
		matrixStack.scale(size, size, size);
	}

}