package com.teamabnormals.upgrade_aquatic.common.entities;

import com.teamabnormals.upgrade_aquatic.api.entities.EntityBucketableWaterMob;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityLionfish extends EntityBucketableWaterMob {
	private static final DataParameter<Boolean> HUNGY = EntityDataManager.createKey(EntityLionfish.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TIME_TILL_HUNGRY = EntityDataManager.createKey(EntityLionfish.class, DataSerializers.VARINT);
	int lastTimeSinceHungry;

	public EntityLionfish(EntityType<? extends EntityLionfish> type, World world) {
		super(UAEntities.LIONFISH, world);
		this.moveController = new EntityLionfish.MoveHelperController(this);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.35D, 30) {
			
			@Override
			public boolean shouldExecute() {
				if (this.creature.isBeingRidden()) {
					return false;
				} else {
					if (!this.mustUpdate) {
						if (this.creature.getIdleTime() >= 100) {
							return false;
						}
						if(((EntityLionfish)this.creature).isHungry()) {
							if (this.creature.getRNG().nextInt(60) != 0) {
								return false;
							}
						} else {
							if (this.creature.getRNG().nextInt(30) != 0) {
								return false;
							}
						}
					}

					Vec3d vec3d = this.getPosition();
					if (vec3d == null) {
						return false;
					} else {
						this.x = vec3d.x;
						this.y = vec3d.y;
						this.z = vec3d.z;
						this.mustUpdate = false;
						return true;
					}
				}
			}
			
		});
		this.goalSelector.addGoal(4, new EntityLionfish.LionfishAttackGoal(this, 12D, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<TropicalFishEntity>(this, TropicalFishEntity.class, true) {
			
			@Override
			public boolean shouldExecute() {
				return ((EntityLionfish)this.goalOwner).isHungry() && super.shouldExecute();
			}
			
		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
	
	protected void registerData() {
		super.registerData();
		this.dataManager.register(HUNGY, true);
		this.dataManager.register(TIME_TILL_HUNGRY, 0);
    }

    public boolean isHungry() {
        return this.dataManager.get(HUNGY);
    }

    public void setHungry(boolean hungry) {
        this.dataManager.set(HUNGY, hungry);
    }
    
    public int getTimeTillHungry() {
        return this.dataManager.get(TIME_TILL_HUNGRY);
    }

    public void setTimeTillHungry(int ticks) {
        this.dataManager.set(TIME_TILL_HUNGRY, ticks);
    }
    
    public void writeAdditional(CompoundNBT compound) {
    	super.writeAdditional(compound);
    	compound.putBoolean("IsHungry", this.isHungry());
    	compound.putInt("TimeTillHungry", this.getTimeTillHungry());
    }

    public void readAdditional(CompoundNBT compound) {
    	super.readAdditional(compound);
    	this.setHungry(compound.getBoolean("IsHungry"));
    	this.setTimeTillHungry(compound.getInt("TimeTillHungry"));
    }
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.85F;
	}

    @Override
    public int getMaxSpawnedInChunk() {
        return 3;
    }

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.LIONFISH_BUCKET);
	}
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}
	
	@Override
	public void tick() {
		super.tick();
		if (!this.isInWater() && this.onGround && this.collidedVertically) {
			this.setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.035F), (double)0.4F, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.035F)));
			this.onGround = false;
			this.isAirBorne = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
		}
		if(!this.isHungry() && lastTimeSinceHungry < this.getTimeTillHungry()) {
			lastTimeSinceHungry++;
		}
		
		if(lastTimeSinceHungry >= this.getTimeTillHungry()) {
			this.setHungry(true);
			lastTimeSinceHungry = 0;
		}
	}
	
	private void attack(LivingEntity entity) {
		if (entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F) && entity.isInWater()) {
			entity.addPotionEffect(new EffectInstance(Effects.POISON, 70, 1));
			entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 180, 1));
			this.playSound(SoundEvents.ENTITY_PUFFER_FISH_STING, 1.0F, 1.0F);
			if(entity instanceof PlayerEntity) {
				this.setAttackTarget(entity);
			}
		}
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity entitySource = source.getTrueSource();
		if(entitySource instanceof LivingEntity && !(entitySource instanceof PlayerEntity && ((PlayerEntity) entitySource).abilities.isCreativeMode)) {
			this.attack((LivingEntity) entitySource);
			return super.attackEntityFrom(source, amount);
		} else {
			return super.attackEntityFrom(source, amount);
		}
	}
	
	public void travel(Vec3d p_213352_1_) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.01F, p_213352_1_);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (this.getAttackTarget() == null) {
				this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(p_213352_1_);
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PUFFER_FISH_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_PUFFER_FISH_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_PUFFER_FISH_HURT;
	}
	
	protected SoundEvent getFlopSound() {
		return SoundEvents.ENTITY_PUFFER_FISH_FLOP;
	}
	
	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_FISH_SWIM;
	}
	
	public static void addSpawn() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(EntityLionfish::processSpawning);
	}
	
	private static void processSpawning(Biome biome) {
		if(biome.getCategory() == Category.OCEAN && biome.getPrecipitation() != RainType.SNOW) {
			biome.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(UAEntities.LIONFISH, 15, 1, 1));
        }
	}
	
	static class MoveHelperController extends MovementController {
        private final EntityLionfish lionfish;

        MoveHelperController(EntityLionfish lionfish) {
            super(lionfish);
            this.lionfish = lionfish;
        }

        public void tick() {
            if (this.lionfish.areEyesInFluid(FluidTags.WATER)) {
                this.lionfish.setMotion(this.lionfish.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.lionfish.getNavigator().noPath()) {
            	double d0 = this.posX - this.lionfish.posX;
            	double d1 = this.posY - this.lionfish.posY;
            	double d2 = this.posZ - this.lionfish.posZ;
            	double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            	d1 = d1 / d3;
            	float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
            	this.lionfish.rotationYaw = this.limitAngle(this.lionfish.rotationYaw, f, 90.0F);
            	this.lionfish.renderYawOffset = this.lionfish.rotationYaw;
            	float f1 = (float) (this.speed * this.lionfish.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
            	this.lionfish.setAIMoveSpeed(MathHelper.lerp(0.125F, this.lionfish.getAIMoveSpeed(), f1));
            	this.lionfish.setMotion(this.lionfish.getMotion().add(0.0D, (double)this.lionfish.getAIMoveSpeed() * d1 * 0.03D, 0.0D));
            }
        }
    }
	
	static class LionfishAttackGoal extends MeleeAttackGoal {
		
		public LionfishAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return super.shouldContinueExecuting() && attacker.isInWater();
		}
		
		@Override
		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d0 = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d0 && this.attackTick <= 0) {
				this.attackTick = 20;
				((EntityLionfish)this.attacker).attack(enemy);
				((EntityLionfish)this.attacker).setHungry(false);
				((EntityLionfish)this.attacker).setTimeTillHungry(attacker.getRNG().nextInt(300) + 300);
				if(enemy instanceof PlayerEntity) {
					attacker.setAttackTarget(null);
					this.resetTask();
				}
			}
		}
		
	}

}