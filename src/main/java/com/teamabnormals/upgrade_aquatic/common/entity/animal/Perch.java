package com.teamabnormals.upgrade_aquatic.common.entity.animal;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.PerchHideInSeagrassGoal;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UASoundEvents;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.List;

public class Perch extends AbstractSchoolingFish {
	public int hideCooldown;

	public Perch(EntityType<? extends Perch> type, Level world) {
		super(type, world);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new PerchHideInSeagrassGoal(this));
	}

	public boolean isSeagrassNearby() {
		return !this.getNearbySeagrass().isEmpty();
	}

	public List<BlockPos> getNearbySeagrass() {
		List<BlockPos> seagrasses = Lists.newArrayList();
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
		for (int yy = this.blockPosition().getY() - 6; yy <= this.getY() + 6; yy++) {
			for (int xx = this.blockPosition().getX() - 12; xx <= this.getX() + 12; xx++) {
				for (int zz = this.blockPosition().getZ() - 12; zz <= this.getZ() + 12; zz++) {
					mutable.set(xx, yy, zz);
					BlockState block = this.level.getBlockState(mutable);
					if (block.is(Blocks.SEAGRASS) || block.is(Blocks.TALL_SEAGRASS)) {
						seagrasses.add(mutable);
					}
				}
			}
		}
		return seagrasses;
	}

	public ItemStack getBucketItemStack() {
		return new ItemStack(UAItems.PERCH_BUCKET.get());
	}

	protected SoundEvent getAmbientSound() {
		return UASoundEvents.ENTITY_PERCH_AMBIENT.get();
	}

	protected SoundEvent getDeathSound() {
		return UASoundEvents.ENTITY_PERCH_DEATH.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return UASoundEvents.ENTITY_PERCH_HURT.get();
	}

	protected SoundEvent getFlopSound() {
		return UASoundEvents.ENTITY_PERCH_FLOP.get();
	}
}