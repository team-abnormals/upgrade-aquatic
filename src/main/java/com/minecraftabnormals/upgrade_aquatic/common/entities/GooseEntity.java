package com.minecraftabnormals.upgrade_aquatic.common.entities;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GooseEntity extends ChickenEntity {

	public GooseEntity(EntityType<? extends GooseEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public GooseEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
		return UAEntities.GOOSE.get().create(this.world);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 6.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
	}
}
