package com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers;

import com.teamabnormals.blueprint.client.BlueprintRenderTypes;
import com.teamabnormals.upgrade_aquatic.client.model.ThrasherModel;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.GreatThrasher;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThrasherRenderLayer<T extends Thrasher, M extends ThrasherModel<T>> extends RenderLayer<T, M> {
	private static final ResourceLocation THRASHER_FROST = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/thrasher_emissive.png");
	private static final ResourceLocation GREAT_THRASHER_FROST = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/great_thrasher_emissive.png");

	public ThrasherRenderLayer(RenderLayerParent<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn, T thrasher, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		int stunnedAnimation = (int) (thrasher.stunAnimation.getProgress(partialTicks) * 240);
		this.getParentModel().setupAnim(thrasher, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getParentModel().renderToBuffer(poseStack, bufferIn.getBuffer(BlueprintRenderTypes.getUnshadedCutoutEntity(this.getThrasherFrostLayer(thrasher), false)), stunnedAnimation, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	public ResourceLocation getThrasherFrostLayer(Thrasher thrasher) {
		return thrasher instanceof GreatThrasher ? GREAT_THRASHER_FROST : THRASHER_FROST;
	}
}