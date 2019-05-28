package com.teamabnormals.upgrade_aquatic.client.render;

import net.minecraftforge.api.distmarker.OnlyIn;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityDrownedVillager;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelZombieVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;

@OnlyIn(Dist.CLIENT)
public class RenderDrownedVillager extends RenderLiving<EntityDrownedVillager> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/villager_drowned.png");
	
	public RenderDrownedVillager(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombieVillager(), 0.5F);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityDrownedVillager arg0) {
		return TEXTURE;
	}

	protected void applyRotations(EntityDrownedVillager entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
		//if (entityLiving.isConverting()) {
			//rotationYaw += (float)(Math.cos((double)entityLiving.ticksExisted * 3.25D) * Math.PI * 0.25D);
	    //}
		super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
	}
	
}
