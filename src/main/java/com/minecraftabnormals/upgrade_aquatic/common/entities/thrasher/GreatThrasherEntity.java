package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class GreatThrasherEntity extends ThrasherEntity {
	private static final EntitySize DEFAULT_SIZE = EntitySize.fixed(2.8F, 1.575F);

	public GreatThrasherEntity(EntityType<? extends ThrasherEntity> type, World world) {
		super(type, world);
		this.xpReward = 55;
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes()
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
	protected EntitySize getDefaultSize() {
		return DEFAULT_SIZE;
	}

	@Override
	protected float getVoicePitch() {
		return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.75F;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.GREAT_THRASHER_SPAWN_EGG.get());
	}
}