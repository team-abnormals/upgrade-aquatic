package com.minecraftabnormals.upgrade_aquatic.common.entities;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class UluluEntity extends MobEntity {

	private static final DataParameter<Integer> ULULU_SIZE = EntityDataManager.createKey(UluluEntity.class, DataSerializers.VARINT);

	public float squishFactor;
	public float prevSquishFactor;

	public UluluEntity(EntityType<? extends UluluEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new UluluEntity.MoveHelperController(this);
	}

	public int getUluluSize() {
		return this.dataManager.get(ULULU_SIZE);
	}

	public boolean isSmallUlulu() {
		return this.getUluluSize() <= 1;
	}

	@Override
	protected ResourceLocation getLootTable() {
	      return this.isSmallUlulu() ? LootTables.EMPTY : this.getType().getLootTable();
	   }
	
	public EntitySize getSize(Pose poseIn) {
	      return super.getSize(poseIn).scale(0.255F * (float)this.getUluluSize());
	   }

	static class MoveHelperController extends MovementController {
		private final UluluEntity ululu;

		MoveHelperController(UluluEntity ululu) {
			super(ululu);
			this.ululu = ululu;
		}
	}

}
