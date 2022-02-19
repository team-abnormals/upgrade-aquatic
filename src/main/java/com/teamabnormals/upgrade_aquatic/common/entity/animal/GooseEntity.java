package com.teamabnormals.upgrade_aquatic.common.entity.animal;

import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class GooseEntity extends Chicken {

	public GooseEntity(EntityType<? extends GooseEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public GooseEntity getBreedOffspring(ServerLevel world, AgableMob ageable) {
		return UAEntities.GOOSE.get().create(this.level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.3D);
	}
}
