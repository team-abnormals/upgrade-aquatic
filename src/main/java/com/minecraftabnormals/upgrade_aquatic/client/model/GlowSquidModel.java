package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.abnormals_core.client.ACRenderTypes;
import com.minecraftabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.minecraftabnormals.upgrade_aquatic.common.entities.GlowSquidEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.util.Arrays;

/**
 * @author Ocelot
 */
public class GlowSquidModel extends SegmentedModel<GlowSquidEntity>
{
    private final boolean emissive;
    private final ModelRenderer body;
    private final ModelRenderer[] legs = new ModelRenderer[8];
    private final ImmutableList<ModelRenderer> field_228296_f_;

    public GlowSquidModel(boolean emissive)
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.emissive = emissive;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-6.0F, -8.0F, -6.0F, 12.0F, 16.0F, 12.0F);
        this.body.rotationPointY += 8.0F;

        for (int j = 0; j < this.legs.length; ++j)
        {
            this.legs[j] = new ModelRenderer(this, 48, 0);
            double d0 = (double) j * Math.PI * 2.0D / (double) this.legs.length;
            float f = (float) Math.cos(d0) * 5.0F;
            float f1 = (float) Math.sin(d0) * 5.0F;
            this.legs[j].addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F);
            this.legs[j].rotationPointX = f;
            this.legs[j].rotationPointZ = f1;
            this.legs[j].rotationPointY = 15.0F;
            d0 = (double) j * Math.PI * -2.0D / (double) this.legs.length + (Math.PI / 2D);
            this.legs[j].rotateAngleY = (float) d0;
        }

        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        builder.add(this.body);
        builder.addAll(Arrays.asList(this.legs));
        this.field_228296_f_ = builder.build();
    }

    @Override
    public void setRotationAngles(GlowSquidEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        for (ModelRenderer modelrenderer : this.legs)
        {
            modelrenderer.rotateAngleX = ageInTicks;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        TextureAtlasSprite sprite = this.emissive ? GlowSquidSpriteUploader.getGlowSprite() : GlowSquidSpriteUploader.getSprite();
        RenderType render = this.emissive ? ACRenderTypes.getEmissiveEntity(GlowSquidSpriteUploader.ATLAS_LOCATION) : RenderType.getEntitySolid(GlowSquidSpriteUploader.ATLAS_LOCATION);
        super.render(matrixStack, sprite.wrapBuffer(Minecraft.getInstance().getRenderTypeBuffers().getBufferSource().getBuffer(render)), this.emissive ? 15728880 : packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public Iterable<ModelRenderer> getParts()
    {
        return this.field_228296_f_;
    }
}
