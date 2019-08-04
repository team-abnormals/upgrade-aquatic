package com.teamabnormals.upgrade_aquatic.common.entities;

import com.teamabnormals.upgrade_aquatic.api.entities.EntityBucketableWaterMob;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityLionfish extends EntityBucketableWaterMob {

	public EntityLionfish(EntityType<? extends EntityLionfish> type, World world) {
		super(UAEntities.LIONFISH, world);
	}

	@Override
	public ItemStack getBucket() {
		return null;
	}

}
