package com.minecraftabnormals.upgrade_aquatic.common.entities;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class UluluEntity extends Mob {

	private static final EntityDataAccessor<Integer> ULULU_SIZE = SynchedEntityData.defineId(UluluEntity.class, EntityDataSerializers.INT);

	public float squishFactor;
	public float prevSquishFactor;

	public UluluEntity(EntityType<? extends UluluEntity> type, Level worldIn) {
		super(type, worldIn);
		this.moveControl = new UluluEntity.MoveHelperController(this);
	}

	public int getUluluSize() {
		return this.entityData.get(ULULU_SIZE);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ULULU_SIZE, 1);
	}

	public boolean isSmallUlulu() {
		return this.getUluluSize() <= 1;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return this.isSmallUlulu() ? BuiltInLootTables.EMPTY : this.getType().getDefaultLootTable();
	}

	public EntityDimensions getDimensions(Pose poseIn) {
		return super.getDimensions(poseIn);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 18.0D);
	}

	static class MoveHelperController extends MoveControl {
		@SuppressWarnings("unused")
		private final UluluEntity ululu;

		MoveHelperController(UluluEntity ululu) {
			super(ululu);
			this.ululu = ululu;
		}
	}

}
