package com.teamabnormals.upgrade_aquatic.common.entities;

import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityNautilus extends WaterMobEntity {

	public EntityNautilus(EntityType<? extends EntityNautilus> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new EntityNautilus.MoveHelperController(this);
	}
	
	public EntityNautilus(World world, double posX, double posY, double posZ) {
		this(UAEntities.NAUTILUS, world);
		this.setPosition(posX, posY, posZ);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, SquidEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
	}
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
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
	public void onEnterBubbleColumn(boolean downwards) {}
	
	@Override
	public void onEnterBubbleColumnWithAirAbove(boolean downwards) {}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.95F;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 4;
	}
	
	static class MoveHelperController extends MovementController {
		private final EntityNautilus nautilus;

		MoveHelperController(EntityNautilus nautilus) {
			super(nautilus);
			this.nautilus = nautilus;
		}

		public void tick() {
			if (this.nautilus.areEyesInFluid(FluidTags.WATER)) {
				this.nautilus.setMotion(this.nautilus.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.nautilus.getNavigator().noPath()) {
				double d0 = this.posX - this.nautilus.posX;
				double d1 = this.posY - this.nautilus.posY;
				double d2 = this.posZ - this.nautilus.posZ;
				double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				d1 = d1 / d3;
				float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
				this.nautilus.rotationYaw = this.limitAngle(this.nautilus.rotationYaw, f, 90.0F);
				this.nautilus.renderYawOffset = this.nautilus.rotationYaw;
				float f1 = (float)(this.speed * this.nautilus.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
				this.nautilus.setAIMoveSpeed(MathHelper.lerp(0.125F, this.nautilus.getAIMoveSpeed(), f1));
				this.nautilus.setMotion(this.nautilus.getMotion().add(0.0D, (double)this.nautilus.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
			} else {
				this.nautilus.setAIMoveSpeed(0.0F);
			}
		}
	}
}
