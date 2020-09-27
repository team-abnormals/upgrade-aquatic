package com.minecraftabnormals.upgrade_aquatic.common.entities;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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

	protected void registerData() {
		super.registerData();
		this.dataManager.register(ULULU_SIZE, 1);
	}

	public boolean isSmallUlulu() {
		return this.getUluluSize() <= 1;
	}

	@Override
	protected ResourceLocation getLootTable() {
	      return this.isSmallUlulu() ? LootTables.EMPTY : this.getType().getLootTable();
	   }
	
	public EntitySize getSize(Pose poseIn) {
	      return super.getSize(poseIn);
	   }

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 18.0D);
	}

	static class MoveHelperController extends MovementController {
		private final UluluEntity ululu;

		MoveHelperController(UluluEntity ululu) {
			super(ululu);
			this.ululu = ululu;
		}
	}

}
