package com.teamabnormals.upgrade_aquatic.common.entity.monster;

import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class GreatThrasher extends Thrasher {
	private static final EntityDimensions DEFAULT_SIZE = EntityDimensions.fixed(2.8F, 1.575F);

	public GreatThrasher(EntityType<? extends Thrasher> type, Level world) {
		super(type, world);
		this.xpReward = 55;
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 125.0D)
				.add(Attributes.ATTACK_DAMAGE, 8.0D)
				.add(Attributes.ARMOR, 16.0D);
	}

	@Override
	public float getMountDistance() {
		return 2.1F;
	}

	@Override
	public double getPassengersRidingOffset() {
		return 0.875F;
	}

	@Override
	protected double getStunDamageThreshold() {
		return 8.0F;
	}

	@Override
	protected EntityDimensions getDefaultSize() {
		return DEFAULT_SIZE;
	}

	@Override
	public float getVoicePitch() {
		return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.75F;
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(UAItems.GREAT_THRASHER_SPAWN_EGG.get());
	}
}