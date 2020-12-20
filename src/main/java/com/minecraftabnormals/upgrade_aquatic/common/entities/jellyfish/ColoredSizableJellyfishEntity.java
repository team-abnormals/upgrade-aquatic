package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.google.common.collect.ImmutableMap;
import com.minecraftabnormals.abnormals_core.core.api.IAgeableEntity;
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

import java.util.TreeMap;

public abstract class ColoredSizableJellyfishEntity extends AbstractJellyfishEntity implements IAgeableEntity {
	protected static final DataParameter<Integer> COLOR = EntityDataManager.createKey(ColoredSizableJellyfishEntity.class, DataSerializers.VARINT);
	protected static final DataParameter<Float> SIZE = EntityDataManager.createKey(ColoredSizableJellyfishEntity.class, DataSerializers.FLOAT);
	protected TreeMap<Float, Integer> sizes = new TreeMap<>(ImmutableMap.of(0.5F, 3, 0.65F, 3, 1.0F, 34));
	private final ColoredSizableBucketProcessor bucketProcessor;

	public ColoredSizableJellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, World world) {
		super(type, world);
		this.bucketProcessor = new ColoredSizableBucketProcessor(this);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(COLOR, 0);
		this.dataManager.register(SIZE, this.getDefaultSize());
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		super.notifyDataManagerChange(key);
		if (SIZE.equals(key)) {
			this.recalculateSize();
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("JellyColor", this.getColor());
		compound.putFloat("Size", this.getSize());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setColor(compound.getInt("JellyColor"));
		this.setSize(compound.getFloat("Size"), false);
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		boolean updateSize = false;
		float size = 0;
		int denominator = 0;
		for (int weight : sizes.values())
			denominator += weight;
		for (float currentSize : sizes.keySet()) {
			int weight = sizes.get(currentSize);
			if (this.getRNG().nextInt(denominator) < weight) {
				size = currentSize;
				break;
			}
			denominator = denominator - weight;
		}
		int color = this.getRNG().nextInt(3);

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
		return this.dataManager.get(COLOR);
	}

	public void setColor(int color) {
		this.dataManager.set(COLOR, color);
	}

	public void setSize(float size, boolean updateHealth) {
		this.dataManager.set(SIZE, size);
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getHealthSizeMultiplier() * size);
		if (updateHealth) {
			this.setHealth(this.getMaxHealth());
		}
	}

	public float getSize() {
		return this.dataManager.get(SIZE);
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
		if (sizes.containsKey(this.getSize())) {
			Float newSize = isGrowing ? sizes.higherKey(this.getSize()) : sizes.lowerKey(this.getSize());
			return newSize != null;
		}
		return false;
	}

	@Override
	public LivingEntity attemptAging(boolean isGrowing) {
		if (sizes.containsKey(this.getSize())) {
			Float newSize = isGrowing ? sizes.higherKey(this.getSize()) : sizes.lowerKey(this.getSize());
			if(newSize != null) this.setSize(newSize, false);
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