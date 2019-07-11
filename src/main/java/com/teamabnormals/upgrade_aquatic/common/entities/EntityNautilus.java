package com.teamabnormals.upgrade_aquatic.common.entities;

import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.world.World;

public class EntityNautilus extends WaterMobEntity {

	public EntityNautilus(EntityType<? extends EntityNautilus> type, World worldIn) {
		super(type, worldIn);
	}
	
	public EntityNautilus(World world, double posX, double posY, double posZ) {
		this(UAEntities.NAUTILUS, world);
		this.setPosition(posX, posY, posZ);
	}
	
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}
	
}
