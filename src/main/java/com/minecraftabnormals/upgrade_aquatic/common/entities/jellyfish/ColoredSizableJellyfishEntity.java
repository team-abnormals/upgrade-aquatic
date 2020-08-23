package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class ColoredSizableJellyfishEntity extends AbstractJellyfishEntity {
	protected static final DataParameter<Integer> COLOR = EntityDataManager.createKey(ColoredSizableJellyfishEntity.class, DataSerializers.VARINT);
	protected static final DataParameter<Float> SIZE = EntityDataManager.createKey(ColoredSizableJellyfishEntity.class, DataSerializers.FLOAT);
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
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		boolean updateSize = false;
		float size = this.getRNG().nextFloat() < 0.85F ? 1.0F : this.getRNG().nextBoolean() ? 0.5F : 0.65F;
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
	
	public void setColor(int color) {
		this.dataManager.set(COLOR, color);
	}

	public int getColor() {
		return this.dataManager.get(COLOR);
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