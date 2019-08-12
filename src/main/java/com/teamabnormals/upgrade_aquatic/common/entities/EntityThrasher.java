package com.teamabnormals.upgrade_aquatic.common.entities;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class EntityThrasher extends MonsterEntity {
	private static final DataParameter<Boolean> MOVING = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SONAR_ACTIVE = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> WATER_TIME = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.VARINT);
	protected float prevTailAnimation;
	protected float tailAnimation;
	protected float tailSpeed;
	
	public EntityThrasher(EntityType<? extends EntityThrasher> type, World world) {
		super(type, world);
		this.experienceValue = 30;
	}
	
	@Override
	protected void registerGoals() {
		
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOVING, false);
		this.dataManager.register(SONAR_ACTIVE, false);
		this.dataManager.register(WATER_TIME, 2500);
	}
	
	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}
	
	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.setAir(this.getMaxAir());
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	public boolean isMoving() {
		return this.dataManager.get(MOVING);
	}

	private void setMoving(boolean moving) {
		this.dataManager.set(MOVING, moving);
	}
	
	public boolean isSonarActive() {
		return this.dataManager.get(SONAR_ACTIVE);
	}

	private void setSonarActive(boolean active) {
		this.dataManager.set(SONAR_ACTIVE, active);
	}
	
	public int getWaterTime() {
		return this.dataManager.get(WATER_TIME);
	}

	public void setWaterTime(int ticks) {
		this.dataManager.set(WATER_TIME, ticks);
	}

	public void writeAdditional(CompoundNBT compound) {
    	super.writeAdditional(compound);
    	compound.putBoolean("IsMoving", this.isMoving());
    	compound.putBoolean("SonarActive", this.isSonarActive());
    	compound.putInt("WaterTicks", this.getWaterTime());
    }

    public void readAdditional(CompoundNBT compound) {
    	super.readAdditional(compound);
    	this.setMoving(compound.getBoolean("IsMoving"));
    	this.setSonarActive(compound.getBoolean("SonarActive"));
    	this.setWaterTime(compound.getInt("WaterTicks"));
    }
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}
	
	@Override
	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
		return worldIn.getFluidState(pos).isTagged(FluidTags.WATER) ? 10.0F + worldIn.getBrightness(pos) - 0.5F : super.getBlockPathWeight(pos, worldIn);
	}
	
	@Override
	public int getMaxAir() {
		return 4950;
	}

	@Override
	protected int determineNextAir(int currentAir) {
		return this.getMaxAir();
	}
	
	@Override
	public int getVerticalFaceSpeed() {
		return 1;
	}
	
	@Override
	public int getHorizontalFaceSpeed() {
		return 1;
	}
	
	public void travel(Vec3d p_213352_1_) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.1F, p_213352_1_);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (!this.isMoving() && this.getAttackTarget() == null) {
				this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(p_213352_1_);
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		if (!this.isAIDisabled()) {
			if (this.isInWaterRainOrBubbleColumn()) {
				this.setWaterTime(2400);
			} else {
				this.setWaterTime(this.getWaterTime() - 1);
				if (this.getWaterTime() <= 0) {
					this.attackEntityFrom(DamageSource.DRYOUT, 1.0F);
				}

				if (this.onGround) {
					this.setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F)));
					this.rotationYaw = this.rand.nextFloat() * 360.0F;
					this.onGround = false;
					this.isAirBorne = true;
				}
			}
		}
	}
	
}
