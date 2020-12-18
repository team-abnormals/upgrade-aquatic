package com.minecraftabnormals.upgrade_aquatic.common.entities;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

//Why not extend the Phantom they said.... :pensive:
public class FlareEntity extends FlyingEntity {
	private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(FlareEntity.class, DataSerializers.VARINT);
	private Vector3d orbitOffset = Vector3d.ZERO;
	private BlockPos orbitPosition = BlockPos.ZERO;
	private FlareEntity.AttackPhase attackPhase = FlareEntity.AttackPhase.CIRCLE;
	
	public FlareEntity(EntityType<? extends FlareEntity> type, World world) {
		super(type, world);
		this.experienceValue = 5;
		this.moveController = new FlareEntity.MoveHelperController(this);
		this.lookController = new FlareEntity.LookHelperController(this);
	}

	protected BodyController createBodyController() {
		return new FlareEntity.BodyHelperController(this);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FlareEntity.PickAttackGoal());
		this.goalSelector.addGoal(2, new FlareEntity.SweepAttackGoal());
		this.goalSelector.addGoal(3, new FlareEntity.OrbitPointGoal());
		this.targetSelector.addGoal(1, new FlareEntity.AttackLivingEntityGoal());
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
    	return MobEntity.func_233666_p_().createMutableAttribute(Attributes.ATTACK_DAMAGE);
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
	    this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((12 + this.getPhantomSize()));
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
	public boolean canDespawn(double distanceToClosestPlayer) {
	      return false;
	}
	
	public void tick() {
	    super.tick();
	    if(this.world.isRemote) {
	        float f = MathHelper.cos((float)(this.getEntityId() * 3 + this.ticksExisted) * 0.13F + (float) Math.PI);
	        float f1 = MathHelper.cos((float)(this.getEntityId() * 3 + this.ticksExisted + 1) * 0.13F + (float) Math.PI);
	        if(f > 0.0F && f1 <= 0.0F) {
	            this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 0.95F + this.rand.nextFloat() * 0.05F, 0.95F + this.rand.nextFloat() * 0.05F, false);
	        }

	        int i = this.getPhantomSize();
	        float f2 = MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F)) * (1.3F + 0.21F * (float) i);
	        float f3 = MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)) * (1.3F + 0.21F * (float) i);
	        float f4 = (0.3F + f * 0.45F) * ((float) i * 0.2F + 1.0F);
	        this.world.addParticle(ParticleTypes.PORTAL, this.getPosX() + (double) f2, this.getPosY() + (double) f4, this.getPosZ() + (double) f3, 0.0D, 0.0D, 0.0D);
	        this.world.addParticle(ParticleTypes.PORTAL, this.getPosX() - (double) f2, this.getPosY() + (double) f4, this.getPosZ() - (double) f3, 0.0D, 0.0D, 0.0D);
	    }

	    if(!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL) {
	        this.remove();
	    }
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	    this.orbitPosition = (new BlockPos(this.getPositionVec())).up(5);
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
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.FLARE_SPAWN_EGG.get());
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
	
	public class AttackLivingEntityGoal extends Goal {
	    private final EntityPredicate field_220842_b = (new EntityPredicate()).setDistance(64.0D);
	    private int tickDelay = 20;

	    private AttackLivingEntityGoal() {}

	    public boolean shouldExecute() {
	        if(this.tickDelay > 0) {
	            --this.tickDelay;
	            return false;
	        } else {
	            this.tickDelay = 60;
	            List<LivingEntity> list = FlareEntity.this.world.getTargettableEntitiesWithinAABB(LivingEntity.class, this.field_220842_b, FlareEntity.this, FlareEntity.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
	            if (!list.isEmpty()) {
	                for(LivingEntity mob : list) {
	                    if(FlareEntity.this.canAttack(mob, EntityPredicate.DEFAULT)) {
	                    	if(mob instanceof ServerPlayerEntity) {
	                    		StatisticsManager statisticsManager = ((ServerPlayerEntity) mob).getStats();
	                    		if(statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 72000) {
	                    			FlareEntity.this.setAttackTarget(mob);
	                    			return true;
	                    		}
	                    	} else {
	                    		if(!(mob instanceof FlareEntity)) {
	                    			FlareEntity.this.setAttackTarget(mob);
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
	        LivingEntity livingentity = FlareEntity.this.getAttackTarget();
	        if(livingentity instanceof ServerPlayerEntity) {
	        	StatisticsManager statisticsManager = ((ServerPlayerEntity) livingentity).getStats();
        		if(statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 72000) {
        			return FlareEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT);
        		}
	        }
	        return livingentity != null ? true : false;
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
	    	FlareEntity.this.rotationYawHead = FlareEntity.this.renderYawOffset;
	    	FlareEntity.this.renderYawOffset = FlareEntity.this.rotationYaw;
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
	        return FlareEntity.this.orbitOffset.squareDistanceTo(FlareEntity.this.getPosX(), FlareEntity.this.getPosY(), FlareEntity.this.getPosZ()) < 4.0D;
	    }
	}
	
	class MoveHelperController extends MovementController {
	    private float speedFactor = 0.1F;

	    public MoveHelperController(MobEntity entityIn) {
	        super(entityIn);
	    }

	    public void tick() {
	        if (FlareEntity.this.collidedHorizontally) {
	            FlareEntity.this.rotationYaw += 180.0F;
	            this.speedFactor = 0.1F;
	        }

	        float f = (float)(FlareEntity.this.orbitOffset.x - FlareEntity.this.getPosX());
	        float f1 = (float)(FlareEntity.this.orbitOffset.y - FlareEntity.this.getPosY());
	        float f2 = (float)(FlareEntity.this.orbitOffset.z - FlareEntity.this.getPosZ());
	        double d0 = (double) MathHelper.sqrt(f * f + f2 * f2);
	        double d1 = 1.0D - (double) MathHelper.abs(f1 * 0.7F) / d0;
	        f = (float)((double) f * d1);
	        f2 = (float)((double) f2 * d1);
	        d0 = (double) MathHelper.sqrt(f * f + f2 * f2);
	        double d2 = (double) MathHelper.sqrt(f * f + f2 * f2 + f1 * f1);
	        float f3 = FlareEntity.this.rotationYaw;
	        float f4 = (float) MathHelper.atan2((double) f2, (double) f);
	        float f5 = MathHelper.wrapDegrees(FlareEntity.this.rotationYaw + 90.0F);
	        float f6 = MathHelper.wrapDegrees(f4 * (180F / (float) Math.PI));
	        FlareEntity.this.rotationYaw = MathHelper.approachDegrees(f5, f6, 4.0F) - 90.0F;
	        FlareEntity.this.renderYawOffset = FlareEntity.this.rotationYaw;
	        if (MathHelper.degreesDifferenceAbs(f3, FlareEntity.this.rotationYaw) < 3.0F) {
	            this.speedFactor = MathHelper.approach(this.speedFactor, 1.8F, 0.005F * (1.8F / this.speedFactor));
	        } else {
	            this.speedFactor = MathHelper.approach(this.speedFactor, 0.2F, 0.025F);
	        }

	        float f7 = (float)(-(MathHelper.atan2((double)(-f1), d0) * (double)(180F / (float) Math.PI)));
	        FlareEntity.this.rotationPitch = f7;
	        float f8 = FlareEntity.this.rotationYaw + 90.0F;
	        double d3 = (double)(this.speedFactor * MathHelper.cos(f8 * ((float) Math.PI / 180F))) * Math.abs((double) f / d2);
	        double d4 = (double)(this.speedFactor * MathHelper.sin(f8 * ((float) Math.PI / 180F))) * Math.abs((double) f2 / d2);
	        double d5 = (double)(this.speedFactor * MathHelper.sin(f7 * ((float) Math.PI / 180F))) * Math.abs((double) f1 / d2);
	        Vector3d vec3d = FlareEntity.this.getMotion();
	        FlareEntity.this.setMotion(vec3d.add((new Vector3d(d3, d5, d4)).subtract(vec3d).scale(0.2D)));
	    }
	}
	
	class OrbitPointGoal extends FlareEntity.MoveGoal {
	    private float field_203150_c;
	    private float field_203151_d;
	    private float field_203152_e;
	    private float field_203153_f;

	    private OrbitPointGoal() {}

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute() {
	        return FlareEntity.this.getAttackTarget() == null || FlareEntity.this.attackPhase == FlareEntity.AttackPhase.CIRCLE;
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting() {
	        this.field_203151_d = 5.0F + FlareEntity.this.rand.nextFloat() * 10.0F;
	        this.field_203152_e = -4.0F + FlareEntity.this.rand.nextFloat() * 9.0F;
	        this.field_203153_f = FlareEntity.this.rand.nextBoolean() ? 1.0F : -1.0F;
	        this.func_203148_i();
	    }

	    /**
	     * Keep ticking a continuous task that has already been started
	     */
	    public void tick() {
	        if (FlareEntity.this.rand.nextInt(350) == 0) {
	            this.field_203152_e = -4.0F + FlareEntity.this.rand.nextFloat() * 9.0F;
	        }

	        if (FlareEntity.this.rand.nextInt(250) == 0) {
	            ++this.field_203151_d;
	            if (this.field_203151_d > 15.0F) {
	                this.field_203151_d = 5.0F;
	                this.field_203153_f = -this.field_203153_f;
	            }
	        }

	        if (FlareEntity.this.rand.nextInt(450) == 0) {
	            this.field_203150_c = FlareEntity.this.rand.nextFloat() * 2.0F * (float) Math.PI;
	            this.func_203148_i();
	        }

	        if (this.func_203146_f()) {
	            this.func_203148_i();
	        }

	        if (FlareEntity.this.orbitOffset.y < FlareEntity.this.getPosY() && !FlareEntity.this.world.isAirBlock((new BlockPos(FlareEntity.this.getPositionVec())).down(1))) {
	            this.field_203152_e = Math.max(1.0F, this.field_203152_e);
	            this.func_203148_i();
	        }

	        if (FlareEntity.this.orbitOffset.y > FlareEntity.this.getPosY() && !FlareEntity.this.world.isAirBlock((new BlockPos(FlareEntity.this.getPositionVec())).up(1))) {
	            this.field_203152_e = Math.min(-1.0F, this.field_203152_e);
	            this.func_203148_i();
	        }

	    }

	    private void func_203148_i() {
	        if(BlockPos.ZERO.equals(FlareEntity.this.orbitPosition)) {
	            FlareEntity.this.orbitPosition = new BlockPos(FlareEntity.this.getPositionVec());
	        }

	        this.field_203150_c += this.field_203153_f * 15.0F * ((float) Math.PI / 180F);
	        FlareEntity.this.orbitOffset = (new Vector3d(FlareEntity.this.orbitPosition.getX(), FlareEntity.this.orbitPosition.getY(), FlareEntity.this.orbitPosition.getZ())).add((double)(this.field_203151_d * MathHelper.cos(this.field_203150_c)), (double)(-4.0F + this.field_203152_e), (double)(this.field_203151_d * MathHelper.sin(this.field_203150_c)));
	    }
	}
	
	class PickAttackGoal extends Goal {
	    private int tickDelay;

	    private PickAttackGoal() {}

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute() {
	        LivingEntity livingentity = FlareEntity.this.getAttackTarget();
	        return livingentity != null ? FlareEntity.this.canAttack(FlareEntity.this.getAttackTarget(), EntityPredicate.DEFAULT) : false;
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting() {
	        this.tickDelay = 10;
	        FlareEntity.this.attackPhase = FlareEntity.AttackPhase.CIRCLE;
	        this.func_203143_f();
	    }

	    /**
	     * Reset the task's internal state. Called when this task is interrupted by another one
	     */
	    public void resetTask() {
	        FlareEntity.this.orbitPosition = FlareEntity.this.world.getHeight(Heightmap.Type.MOTION_BLOCKING, FlareEntity.this.orbitPosition).up(10 + FlareEntity.this.rand.nextInt(20));
	    }

	    /**
	     * Keep ticking a continuous task that has already been started
	     */
	    public void tick() {
	        if(FlareEntity.this.attackPhase == FlareEntity.AttackPhase.CIRCLE) {
	            --this.tickDelay;
	            if(this.tickDelay <= 0) {
	                FlareEntity.this.attackPhase = FlareEntity.AttackPhase.SWOOP;
	                this.func_203143_f();
	                this.tickDelay = (8 + FlareEntity.this.rand.nextInt(4)) * 20;
	                FlareEntity.this.playSound(SoundEvents.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + FlareEntity.this.rand.nextFloat() * 0.1F);
	            }
	        }

	    }

	    private void func_203143_f() {
	        FlareEntity.this.orbitPosition = (new BlockPos(FlareEntity.this.getAttackTarget().getPositionVec())).up(20 + FlareEntity.this.rand.nextInt(20));
	        if(FlareEntity.this.orbitPosition.getY() < FlareEntity.this.world.getSeaLevel()) {
	            FlareEntity.this.orbitPosition = new BlockPos(FlareEntity.this.orbitPosition.getX(), FlareEntity.this.world.getSeaLevel() + 1, FlareEntity.this.orbitPosition.getZ());
	        }

	    }
	}
	
	class SweepAttackGoal extends FlareEntity.MoveGoal {
	    private SweepAttackGoal() {}

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute() {
	        return FlareEntity.this.getAttackTarget() != null && FlareEntity.this.attackPhase == FlareEntity.AttackPhase.SWOOP;
	    }

	    /**
	     * Returns whether an in-progress EntityAIBase should continue executing
	     */
	    public boolean shouldContinueExecuting() {
	        LivingEntity livingentity = FlareEntity.this.getAttackTarget();
	        if(livingentity == null) {
	            return false;
	        } else if(!livingentity.isAlive()) {
	            return false;
	        } else if(!(livingentity instanceof PlayerEntity) || !((PlayerEntity) livingentity).isSpectator() && !((PlayerEntity) livingentity).isCreative()) {
	            if(!this.shouldExecute()) {
	                return false;
	            } else {
	                if (FlareEntity.this.ticksExisted % 20 == 0) {
	                    List<CatEntity> list = FlareEntity.this.world.getEntitiesWithinAABB(CatEntity.class, FlareEntity.this.getBoundingBox().grow(16.0D), EntityPredicates.IS_ALIVE);
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
	    	if(FlareEntity.this.getAttackTarget() instanceof PlayerEntity) {
	    		FlareEntity.this.setAttackTarget((LivingEntity) null);
	    	}
	        FlareEntity.this.attackPhase = FlareEntity.AttackPhase.CIRCLE;
	    }

	    /**
	     * Keep ticking a continuous task that has already been started
	     */
	    public void tick() {
	        LivingEntity livingentity = FlareEntity.this.getAttackTarget();
	        FlareEntity.this.orbitOffset = new Vector3d(livingentity.getPosX(), livingentity.getPosY() + (double) livingentity.getHeight() * 0.5D, livingentity.getPosZ());
	        if (FlareEntity.this.getBoundingBox().grow((double) 0.2F).intersects(livingentity.getBoundingBox())) {
	            FlareEntity.this.attackEntityAsMob(livingentity);
	            FlareEntity.this.attackPhase = FlareEntity.AttackPhase.CIRCLE;
	            FlareEntity.this.world.playEvent(1039, new BlockPos(FlareEntity.this.getPositionVec()), 0);
	        } else if (FlareEntity.this.collidedHorizontally || FlareEntity.this.hurtTime > 0) {
	            FlareEntity.this.attackPhase = FlareEntity.AttackPhase.CIRCLE;
	        }

	    }
	}
}
