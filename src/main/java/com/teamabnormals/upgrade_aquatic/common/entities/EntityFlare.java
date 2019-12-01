package com.teamabnormals.upgrade_aquatic.common.entities;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Why not extend the Phantom they said.... :pensive:
public class EntityFlare extends FlyingEntity {
	private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(EntityFlare.class, DataSerializers.VARINT);
	private Vec3d orbitOffset = Vec3d.ZERO;
	private BlockPos orbitPosition = BlockPos.ZERO;
	private EntityFlare.AttackPhase attackPhase = EntityFlare.AttackPhase.CIRCLE;
	
	public EntityFlare(EntityType<? extends EntityFlare> type, World world) {
		super(type, world);
		this.experienceValue = 5;
		this.moveController = new EntityFlare.MoveHelperController(this);
		this.lookController = new EntityFlare.LookHelperController(this);
	}

	protected BodyController createBodyController() {
		return new EntityFlare.BodyHelperController(this);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new EntityFlare.PickAttackGoal());
		this.goalSelector.addGoal(2, new EntityFlare.SweepAttackGoal());
		this.goalSelector.addGoal(3, new EntityFlare.OrbitPointGoal());
		this.targetSelector.addGoal(1, new EntityFlare.AttackPlayerGoal());
	}

	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(SIZE, 0);
	}

	public void setPhantomSize(int sizeIn) {
		this.dataManager.set(SIZE, MathHelper.clamp(sizeIn, 0, 64));
	}

	private void updatePhantomSize() {
	    this.recalculateSize();
	    this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((double)(6 + this.getPhantomSize()));
	}

	public int getPhantomSize() {
	    return this.dataManager.get(SIZE);
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.35F;
	}

	public void notifyDataManagerChange(DataParameter<?> key) {
		if(SIZE.equals(key)) {
			this.updatePhantomSize();
		}

		super.notifyDataManagerChange(key);
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.UNDEFINED;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if(entity instanceof LivingEntity) {
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, 60, 1));
		}
		return super.attackEntityAsMob(entity);
	}
	
	public void tick() {
	    super.tick();
	    if(this.world.isRemote) {
	        float f = MathHelper.cos((float)(this.getEntityId() * 3 + this.ticksExisted) * 0.13F + (float) Math.PI);
	        float f1 = MathHelper.cos((float)(this.getEntityId() * 3 + this.ticksExisted + 1) * 0.13F + (float) Math.PI);
	        if(f > 0.0F && f1 <= 0.0F) {
	            this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 0.95F + this.rand.nextFloat() * 0.05F, 0.95F + this.rand.nextFloat() * 0.05F, false);
	        }

	        int i = this.getPhantomSize();
	        float f2 = MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F)) * (1.3F + 0.21F * (float) i);
	        float f3 = MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)) * (1.3F + 0.21F * (float) i);
	        float f4 = (0.3F + f * 0.45F) * ((float) i * 0.2F + 1.0F);
	        this.world.addParticle(ParticleTypes.PORTAL, this.posX + (double) f2, this.posY + (double) f4, this.posZ + (double) f3, 0.0D, 0.0D, 0.0D);
	        this.world.addParticle(ParticleTypes.PORTAL, this.posX - (double) f2, this.posY + (double) f4, this.posZ - (double) f3, 0.0D, 0.0D, 0.0D);
	    }

	    if(!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL) {
	        this.remove();
	    }
	}

	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	    this.orbitPosition = (new BlockPos(this)).up(5);
	    this.setPhantomSize(0);
	    return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
	    super.readAdditional(compound);
	    if (compound.contains("AX")) {
	        this.orbitPosition = new BlockPos(compound.getInt("AX"), compound.getInt("AY"), compound.getInt("AZ"));
	    }

	    this.setPhantomSize(compound.getInt("Size"));
	}

	public void writeAdditional(CompoundNBT compound) {
	    super.writeAdditional(compound);
	    compound.putInt("AX", this.orbitPosition.getX());
	    compound.putInt("AY", this.orbitPosition.getY());
	    compound.putInt("AZ", this.orbitPosition.getZ());
	    compound.putInt("Size", this.getPhantomSize());
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
	    return true;
	}

	public SoundCategory getSoundCategory() {
	    return SoundCategory.HOSTILE;
	}

	protected SoundEvent getAmbientSound() {
	    return SoundEvents.ENTITY_PHANTOM_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	    return SoundEvents.ENTITY_PHANTOM_HURT;
	}

	protected SoundEvent getDeathSound() {
	    return SoundEvents.ENTITY_PHANTOM_DEATH;
	}
	
	protected float getSoundVolume() {
	    return 1.0F;
	}

	public boolean canAttack(EntityType<?> typeIn) {
	    return true;
	}

	public EntitySize getSize(Pose poseIn) {
	    int i = this.getPhantomSize();
	    EntitySize entitysize = super.getSize(poseIn);
	    float f = (entitysize.width + 0.2F * (float) i) / entitysize.width;
	    return entitysize.scale(f);
	}

	static enum AttackPhase {
	    CIRCLE,
	    SWOOP;
	}
	
	public class AttackPlayerGoal extends Goal {
	    private final EntityPredicate field_220842_b = (new EntityPredicate()).setDistance(64.0D);
	    private int tickDelay = 20;

	    private AttackPlayerGoal() {}

	    public boolean shouldExecute() {
	        if(this.tickDelay > 0) {
	            --this.tickDelay;
	            return false;
	        } else {
	            this.tickDelay = 60;
	            List<PlayerEntity> list = EntityFlare.this.world.getTargettablePlayersWithinAABB(this.field_220842_b, EntityFlare.this, EntityFlare.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
	            if (!list.isEmpty()) {
	                list.sort((p_203140_0_, p_203140_1_) -> {
	                    return p_203140_0_.posY > p_203140_1_.posY ? -1 : 1;
	                });

	                for(PlayerEntity playerentity : list) {
	                    if(EntityFlare.this.func_213344_a(playerentity, EntityPredicate.DEFAULT)) {
	                    	if(playerentity instanceof ServerPlayerEntity) {
	                    		StatisticsManager statisticsManager = ((ServerPlayerEntity) playerentity).getStats();
	                    		if(statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 72000) {
	                    			EntityFlare.this.setAttackTarget(playerentity);
	                    			return true;
	                    		}
	                    	}
	                    }
	                }
	            }

	            return false;
	        }
	    }

	    public boolean shouldContinueExecuting() {
	        LivingEntity livingentity = EntityFlare.this.getAttackTarget();
	        if(livingentity instanceof ServerPlayerEntity) {
	        	StatisticsManager statisticsManager = ((ServerPlayerEntity) livingentity).getStats();
        		if(statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 72000) {
        			return EntityFlare.this.func_213344_a(livingentity, EntityPredicate.DEFAULT);
        		}
	        }
	        return livingentity != null ? EntityFlare.this.func_213344_a(livingentity, EntityPredicate.DEFAULT) : false;
	    }
	}
	
	class BodyHelperController extends BodyController {
	    public BodyHelperController(MobEntity p_i49925_2_) {
	        super(p_i49925_2_);
	    }

	    /**
	     * Update the Head and Body rendenring angles
	     */
	    public void updateRenderAngles() {
	    	EntityFlare.this.rotationYawHead = EntityFlare.this.renderYawOffset;
	    	EntityFlare.this.renderYawOffset = EntityFlare.this.rotationYaw;
	    }
	}

	class LookHelperController extends LookController {
	    public LookHelperController(MobEntity entityIn) {
	        super(entityIn);
	    }

	    /**
	     * Updates look
	     */
	    public void tick() {}
	}
	
	abstract class MoveGoal extends Goal {
	    public MoveGoal() {
	        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	    }

	    protected boolean func_203146_f() {
	        return EntityFlare.this.orbitOffset.squareDistanceTo(EntityFlare.this.posX, EntityFlare.this.posY, EntityFlare.this.posZ) < 4.0D;
	    }
	}
	
	class MoveHelperController extends MovementController {
	    private float speedFactor = 0.1F;

	    public MoveHelperController(MobEntity entityIn) {
	        super(entityIn);
	    }

	    public void tick() {
	        if (EntityFlare.this.collidedHorizontally) {
	            EntityFlare.this.rotationYaw += 180.0F;
	            this.speedFactor = 0.1F;
	        }

	        float f = (float)(EntityFlare.this.orbitOffset.x - EntityFlare.this.posX);
	        float f1 = (float)(EntityFlare.this.orbitOffset.y - EntityFlare.this.posY);
	        float f2 = (float)(EntityFlare.this.orbitOffset.z - EntityFlare.this.posZ);
	        double d0 = (double) MathHelper.sqrt(f * f + f2 * f2);
	        double d1 = 1.0D - (double) MathHelper.abs(f1 * 0.7F) / d0;
	        f = (float)((double) f * d1);
	        f2 = (float)((double) f2 * d1);
	        d0 = (double) MathHelper.sqrt(f * f + f2 * f2);
	        double d2 = (double) MathHelper.sqrt(f * f + f2 * f2 + f1 * f1);
	        float f3 = EntityFlare.this.rotationYaw;
	        float f4 = (float) MathHelper.atan2((double) f2, (double) f);
	        float f5 = MathHelper.wrapDegrees(EntityFlare.this.rotationYaw + 90.0F);
	        float f6 = MathHelper.wrapDegrees(f4 * (180F / (float) Math.PI));
	        EntityFlare.this.rotationYaw = MathHelper.approachDegrees(f5, f6, 4.0F) - 90.0F;
	        EntityFlare.this.renderYawOffset = EntityFlare.this.rotationYaw;
	        if (MathHelper.degreesDifferenceAbs(f3, EntityFlare.this.rotationYaw) < 3.0F) {
	            this.speedFactor = MathHelper.approach(this.speedFactor, 1.8F, 0.005F * (1.8F / this.speedFactor));
	        } else {
	            this.speedFactor = MathHelper.approach(this.speedFactor, 0.2F, 0.025F);
	        }

	        float f7 = (float)(-(MathHelper.atan2((double)(-f1), d0) * (double)(180F / (float) Math.PI)));
	        EntityFlare.this.rotationPitch = f7;
	        float f8 = EntityFlare.this.rotationYaw + 90.0F;
	        double d3 = (double)(this.speedFactor * MathHelper.cos(f8 * ((float) Math.PI / 180F))) * Math.abs((double) f / d2);
	        double d4 = (double)(this.speedFactor * MathHelper.sin(f8 * ((float) Math.PI / 180F))) * Math.abs((double) f2 / d2);
	        double d5 = (double)(this.speedFactor * MathHelper.sin(f7 * ((float) Math.PI / 180F))) * Math.abs((double) f1 / d2);
	        Vec3d vec3d = EntityFlare.this.getMotion();
	        EntityFlare.this.setMotion(vec3d.add((new Vec3d(d3, d5, d4)).subtract(vec3d).scale(0.2D)));
	    }
	}
	
	class OrbitPointGoal extends EntityFlare.MoveGoal {
	    private float field_203150_c;
	    private float field_203151_d;
	    private float field_203152_e;
	    private float field_203153_f;

	    private OrbitPointGoal() {}

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute() {
	        return EntityFlare.this.getAttackTarget() == null || EntityFlare.this.attackPhase == EntityFlare.AttackPhase.CIRCLE;
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting() {
	        this.field_203151_d = 5.0F + EntityFlare.this.rand.nextFloat() * 10.0F;
	        this.field_203152_e = -4.0F + EntityFlare.this.rand.nextFloat() * 9.0F;
	        this.field_203153_f = EntityFlare.this.rand.nextBoolean() ? 1.0F : -1.0F;
	        this.func_203148_i();
	    }

	    /**
	     * Keep ticking a continuous task that has already been started
	     */
	    public void tick() {
	        if (EntityFlare.this.rand.nextInt(350) == 0) {
	            this.field_203152_e = -4.0F + EntityFlare.this.rand.nextFloat() * 9.0F;
	        }

	        if (EntityFlare.this.rand.nextInt(250) == 0) {
	            ++this.field_203151_d;
	            if (this.field_203151_d > 15.0F) {
	                this.field_203151_d = 5.0F;
	                this.field_203153_f = -this.field_203153_f;
	            }
	        }

	        if (EntityFlare.this.rand.nextInt(450) == 0) {
	            this.field_203150_c = EntityFlare.this.rand.nextFloat() * 2.0F * (float) Math.PI;
	            this.func_203148_i();
	        }

	        if (this.func_203146_f()) {
	            this.func_203148_i();
	        }

	        if (EntityFlare.this.orbitOffset.y < EntityFlare.this.posY && !EntityFlare.this.world.isAirBlock((new BlockPos(EntityFlare.this)).down(1))) {
	            this.field_203152_e = Math.max(1.0F, this.field_203152_e);
	            this.func_203148_i();
	        }

	        if (EntityFlare.this.orbitOffset.y > EntityFlare.this.posY && !EntityFlare.this.world.isAirBlock((new BlockPos(EntityFlare.this)).up(1))) {
	            this.field_203152_e = Math.min(-1.0F, this.field_203152_e);
	            this.func_203148_i();
	        }

	    }

	    private void func_203148_i() {
	        if(BlockPos.ZERO.equals(EntityFlare.this.orbitPosition)) {
	            EntityFlare.this.orbitPosition = new BlockPos(EntityFlare.this);
	        }

	        this.field_203150_c += this.field_203153_f * 15.0F * ((float) Math.PI / 180F);
	        EntityFlare.this.orbitOffset = (new Vec3d(EntityFlare.this.orbitPosition)).add((double)(this.field_203151_d * MathHelper.cos(this.field_203150_c)), (double)(-4.0F + this.field_203152_e), (double)(this.field_203151_d * MathHelper.sin(this.field_203150_c)));
	    }
	}
	
	class PickAttackGoal extends Goal {
	    private int tickDelay;

	    private PickAttackGoal() {}

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute() {
	        LivingEntity livingentity = EntityFlare.this.getAttackTarget();
	        return livingentity != null ? EntityFlare.this.func_213344_a(EntityFlare.this.getAttackTarget(), EntityPredicate.DEFAULT) : false;
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting() {
	        this.tickDelay = 10;
	        EntityFlare.this.attackPhase = EntityFlare.AttackPhase.CIRCLE;
	        this.func_203143_f();
	    }

	    /**
	     * Reset the task's internal state. Called when this task is interrupted by another one
	     */
	    public void resetTask() {
	        EntityFlare.this.orbitPosition = EntityFlare.this.world.getHeight(Heightmap.Type.MOTION_BLOCKING, EntityFlare.this.orbitPosition).up(10 + EntityFlare.this.rand.nextInt(20));
	    }

	    /**
	     * Keep ticking a continuous task that has already been started
	     */
	    public void tick() {
	        if(EntityFlare.this.attackPhase == EntityFlare.AttackPhase.CIRCLE) {
	            --this.tickDelay;
	            if(this.tickDelay <= 0) {
	                EntityFlare.this.attackPhase = EntityFlare.AttackPhase.SWOOP;
	                this.func_203143_f();
	                this.tickDelay = (8 + EntityFlare.this.rand.nextInt(4)) * 20;
	                EntityFlare.this.playSound(SoundEvents.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + EntityFlare.this.rand.nextFloat() * 0.1F);
	            }
	        }

	    }

	    private void func_203143_f() {
	        EntityFlare.this.orbitPosition = (new BlockPos(EntityFlare.this.getAttackTarget())).up(20 + EntityFlare.this.rand.nextInt(20));
	        if(EntityFlare.this.orbitPosition.getY() < EntityFlare.this.world.getSeaLevel()) {
	            EntityFlare.this.orbitPosition = new BlockPos(EntityFlare.this.orbitPosition.getX(), EntityFlare.this.world.getSeaLevel() + 1, EntityFlare.this.orbitPosition.getZ());
	        }

	    }
	}
	
	class SweepAttackGoal extends EntityFlare.MoveGoal {
	    private SweepAttackGoal() {}

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute() {
	        return EntityFlare.this.getAttackTarget() != null && EntityFlare.this.attackPhase == EntityFlare.AttackPhase.SWOOP;
	    }

	    /**
	     * Returns whether an in-progress EntityAIBase should continue executing
	     */
	    public boolean shouldContinueExecuting() {
	        LivingEntity livingentity = EntityFlare.this.getAttackTarget();
	        if(livingentity == null) {
	            return false;
	        } else if(!livingentity.isAlive()) {
	            return false;
	        } else if(!(livingentity instanceof PlayerEntity) || !((PlayerEntity) livingentity).isSpectator() && !((PlayerEntity) livingentity).isCreative()) {
	            if(!this.shouldExecute()) {
	                return false;
	            } else {
	                if (EntityFlare.this.ticksExisted % 20 == 0) {
	                    List<CatEntity> list = EntityFlare.this.world.getEntitiesWithinAABB(CatEntity.class, EntityFlare.this.getBoundingBox().grow(16.0D), EntityPredicates.IS_ALIVE);
	                    if (!list.isEmpty()) {
	                        for(CatEntity catentity: list) {
	                            catentity.func_213420_ej();
	                        }

	                        return false;
	                    }
	                }

	                return true;
	            }
	        } else {
	            return false;
	        }
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting() {}

	    /**
	     * Reset the task's internal state. Called when this task is interrupted by another one
	     */
	    public void resetTask() {
	        EntityFlare.this.setAttackTarget((LivingEntity) null);
	        EntityFlare.this.attackPhase = EntityFlare.AttackPhase.CIRCLE;
	    }

	    /**
	     * Keep ticking a continuous task that has already been started
	     */
	    public void tick() {
	        LivingEntity livingentity = EntityFlare.this.getAttackTarget();
	        EntityFlare.this.orbitOffset = new Vec3d(livingentity.posX, livingentity.posY + (double) livingentity.getHeight() * 0.5D, livingentity.posZ);
	        if (EntityFlare.this.getBoundingBox().grow((double) 0.2F).intersects(livingentity.getBoundingBox())) {
	            EntityFlare.this.attackEntityAsMob(livingentity);
	            EntityFlare.this.attackPhase = EntityFlare.AttackPhase.CIRCLE;
	            EntityFlare.this.world.playEvent(1039, new BlockPos(EntityFlare.this), 0);
	        } else if (EntityFlare.this.collidedHorizontally || EntityFlare.this.hurtTime > 0) {
	            EntityFlare.this.attackPhase = EntityFlare.AttackPhase.CIRCLE;
	        }

	    }
	}
}