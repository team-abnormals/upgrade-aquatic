package com.teamabnormals.upgrade_aquatic.common.entities;

import com.teamabnormals.upgrade_aquatic.api.entities.EntityBucketableWaterMob;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;

public class EntityPickerel extends EntityBucketableWaterMob {

	public EntityPickerel(EntityType<? extends EntityPickerel> type, World world) {
		super(type, world);
		this.moveController = new MoveHelperController(this);
	}
	
	public EntityPickerel(World world, double posX, double posY, double posZ) {
        this(UAEntities.PICKEREL, world);
        this.setPosition(posX, posY, posZ);
    }
	
	@Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
    }
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.1D, 40));
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.PICKEREL_BUCKET);
	}
	
	@Override
    public int getMaxSpawnedInChunk() {
        return 8;
    }
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.6F;
	}
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}
	
	@Override
	public void livingTick() {
		if (!this.isInWater() && this.onGround && this.collidedVertically) {
			this.setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F)));
			this.onGround = false;
			this.isAirBorne = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
		}
		super.livingTick();
	}
	
	@Override
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
		return SoundEvents.ENTITY_SALMON_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SALMON_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SALMON_HURT;
	}
	
	protected SoundEvent getFlopSound() {
		return SoundEvents.ENTITY_SALMON_FLOP;
	}
	
	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_FISH_SWIM;
	}

	public static void addSpawn() {
        for (Biome biome : Biome.BIOMES) {
        	if(biome.getCategory() == Category.RIVER || biome.getCategory() == Category.SWAMP) {
        		biome.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(UAEntities.PICKEREL, 8, 2, 4));
        	}
        }
	}
	
	static class MoveHelperController extends MovementController {
		private final EntityPickerel pickerel;

		MoveHelperController(EntityPickerel pickerel) {
			super(pickerel);
			this.pickerel = pickerel;
		}

		public void tick() {
			if (this.pickerel.areEyesInFluid(FluidTags.WATER)) {
				this.pickerel.setMotion(this.pickerel.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.pickerel.getNavigator().noPath()) {
				double d0 = this.posX - this.pickerel.posX;
				double d1 = this.posY - this.pickerel.posY;
				double d2 = this.posZ - this.pickerel.posZ;
				double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				d1 = d1 / d3;
				float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
				this.pickerel.rotationYaw = this.limitAngle(this.pickerel.rotationYaw, f, 90.0F);
				this.pickerel.renderYawOffset = this.pickerel.rotationYaw;
				float f1 = (float)(this.speed * this.pickerel.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
				this.pickerel.setAIMoveSpeed(MathHelper.lerp(0.125F, this.pickerel.getAIMoveSpeed(), f1));
				this.pickerel.setMotion(this.pickerel.getMotion().add(0.0D, (double)this.pickerel.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
			} else {
				this.pickerel.setAIMoveSpeed(0.0F);
			}
		}
	}
}
