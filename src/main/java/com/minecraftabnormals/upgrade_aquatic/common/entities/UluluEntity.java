package com.minecraftabnormals.upgrade_aquatic.common.entities;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.loot.LootTables;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class UluluEntity extends MobEntity {

	private static final DataParameter<Integer> ULULU_SIZE = EntityDataManager.defineId(UluluEntity.class, DataSerializers.INT);

	public float squishFactor;
	public float prevSquishFactor;

	public UluluEntity(EntityType<? extends UluluEntity> type, World worldIn) {
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
	      return this.isSmallUlulu() ? LootTables.EMPTY : this.getType().getDefaultLootTable();
	   }
	
	public EntitySize getDimensions(Pose poseIn) {
	      return super.getDimensions(poseIn);
	   }

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 18.0D);
	}

	static class MoveHelperController extends MovementController {
		@SuppressWarnings("unused")
		private final UluluEntity ululu;

		MoveHelperController(UluluEntity ululu) {
			super(ululu);
			this.ululu = ululu;
		}
	}

}
