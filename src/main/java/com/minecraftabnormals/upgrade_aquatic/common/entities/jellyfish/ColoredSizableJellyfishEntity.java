package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.google.common.collect.ImmutableMap;
import com.minecraftabnormals.abnormals_core.core.api.IAgeableEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.helper.JellyfishSizeMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import java.util.Random;
import java.util.TreeMap;

public abstract class ColoredSizableJellyfishEntity extends AbstractJellyfishEntity implements IAgeableEntity {
	protected static final DataParameter<Integer> COLOR = EntityDataManager.defineId(ColoredSizableJellyfishEntity.class, DataSerializers.INT);
	protected static final DataParameter<Float> SIZE = EntityDataManager.defineId(ColoredSizableJellyfishEntity.class, DataSerializers.FLOAT);
	private static final JellyfishSizeMap NATURAL_SIZES = new JellyfishSizeMap(new TreeMap<>(ImmutableMap.of(0.5F, 3, 0.65F, 3, 1.0F, 34)));
	private final ColoredSizableBucketProcessor bucketProcessor;

	public ColoredSizableJellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, World world) {
		super(type, world);
		this.bucketProcessor = new ColoredSizableBucketProcessor(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COLOR, 0);
		this.entityData.define(SIZE, this.getDefaultSize());
	}

	@Override
	public void onSyncedDataUpdated(DataParameter<?> key) {
		super.onSyncedDataUpdated(key);
		if (SIZE.equals(key)) {
			this.refreshDimensions();
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("JellyColor", this.getColor());
		compound.putFloat("Size", this.getSize());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setColor(compound.getInt("JellyColor"));
		this.setSize(compound.getFloat("Size"), false);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		boolean updateSize = false;

		Random rand = this.getRandom();
		int color = rand.nextInt(3);
		float size = this.getNaturalSizeMap().randomSize(rand);
		if (!(dataTag != null && this.isFromBucket())) {
			if (spawnDataIn instanceof SpawnData) {
				size = ((SpawnData) spawnDataIn).size;
				color = ((SpawnData) spawnDataIn).color;
			} else {
				if (!this.isFromBucket()) {
					spawnDataIn = new SpawnData(size, color);
					updateSize = true;
				}
			}
		}

		this.setSize(size, updateSize);
		this.setColor(color);
		return spawnDataIn;
	}

	public int getColor() {
		return this.entityData.get(COLOR);
	}

	public void setColor(int color) {
		this.entityData.set(COLOR, color);
	}

	public JellyfishSizeMap getNaturalSizeMap() {
		return NATURAL_SIZES;
	}

	public void setSize(float size, boolean updateHealth) {
		this.entityData.set(SIZE, size);
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getHealthSizeMultiplier() * size);
		if (updateHealth) {
			this.setHealth(this.getMaxHealth());
		}
	}

	public float getSize() {
		return this.entityData.get(SIZE);
	}

	@Override
	public boolean hasGrowthProgress() {
		return false;
	}

	@Override
	public void resetGrowthProgress() {
	}

	@Override
	public boolean canAge(boolean isGrowing) {
		float size = this.getSize();
		JellyfishSizeMap map = this.getNaturalSizeMap();
		if (map.containsKey(size)) {
			return (isGrowing ? map.higherKey(size) : map.lowerKey(size)) != null;
		}
		return false;
	}

	@Override
	public LivingEntity attemptAging(boolean isGrowing) {
		float size = this.getSize();
		JellyfishSizeMap map = this.getNaturalSizeMap();
		if (map.containsKey(size)) {
			Float newSize = isGrowing ? map.higherKey(size) : map.lowerKey(size);
			if (newSize != null) this.setSize(newSize, false);
		}
		return this;
	}

	@Override
	public BucketProcessor<?> getBucketProcessor() {
		return this.bucketProcessor;
	}

	protected abstract String getBucketEntityId();

	protected abstract float getDefaultSize();

	protected abstract float getHealthSizeMultiplier();

	protected static class ColoredSizableBucketProcessor extends BucketProcessor<ColoredSizableJellyfishEntity> {

		public ColoredSizableBucketProcessor(ColoredSizableJellyfishEntity jellyfish) {
			super(jellyfish.getBucketEntityId(), jellyfish);
		}

		@Override
		public CompoundNBT write() {
			CompoundNBT nbt = super.write();
			nbt.putInt("Color", this.jellyfish.getColor());
			nbt.putFloat("Size", this.jellyfish.getSize());
			return nbt;
		}

		@Override
		public void read(CompoundNBT nbt) {
			this.jellyfish.setColor(nbt.getInt("Color"));
			this.jellyfish.setSize(nbt.getFloat("Size"), true);
		}

	}

	static class SpawnData implements ILivingEntityData {
		private final float size;
		private final int color;

		private SpawnData(float size, int color) {
			this.size = size;
			this.color = color;
		}
	}
}
