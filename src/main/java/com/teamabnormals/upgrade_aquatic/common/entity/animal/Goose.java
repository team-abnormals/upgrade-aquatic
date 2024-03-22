package com.teamabnormals.upgrade_aquatic.common.entity.animal;

import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;

public class Goose extends Chicken {

	public Goose(EntityType<? extends Goose> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public Goose getBreedOffspring(ServerLevel world, AgeableMob ageable) {
		return UAEntityTypes.GOOSE.get().create(this.level());
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.3D);
	}
}
