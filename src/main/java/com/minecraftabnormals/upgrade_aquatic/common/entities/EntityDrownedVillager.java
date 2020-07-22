package com.minecraftabnormals.upgrade_aquatic.common.entities;

/*
 * MAJOR WIP
 */
public class EntityDrownedVillager {
//	private boolean targetingUnderwater;
//	protected final PathNavigateSwimmer waterNavigation;
//	protected final PathNavigateGround landNavigation;
//	    
//	public EntityDrownedVillager(World world) {
//		super(UAEntities.DROWNED_VILLAGER_ENTITY_TYPE, world);
//		this.stepHeight = 1;
//        this.setPathPriority(PathNodeType.WATER, 0.0F);
//        this.waterNavigation = new PathNavigateSwimmer(this, world);
//        this.landNavigation = new PathNavigateGround(this, world);
//	}
//	
//	@Override
//	protected void applyEntityAI() {
//		this.tasks.addTask(1, new EntityDrownedVillager.EntityAIWanderOnSurface(this, 1.0D));
//        this.tasks.addTask(2, new EntityDrownedVillager.EntityAITridentAttack(this, 1.0D, 40, 10.0F));
//        this.tasks.addTask(2, new EntityDrownedVillager.EntityAIDrownedAttack(this, 1.0D, false));
//        this.tasks.addTask(5, new EntityDrownedVillager.EntityAILeaveWater(this, 1.0D));
//        //this.tasks.addTask(6, new EntityDrownedVillager.class_1557(this, 1.0D, this.world.getSeaLevel()));
//		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
//		this.targetTasks.addTask(1, (new EntityAIHurtByTarget(this, true, new Class[]{EntityDrownedVillager.class})));
//		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
//		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, false));
//		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityIronGolem>(this, EntityIronGolem.class, false));
//		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<EntityTurtle>(this, EntityTurtle.class, 10, true, false, EntityTurtle.TARGET_DRY_BABY));
//	}
//	
//	@Override
//	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData entityLivingData, NBTTagCompound itemNbt) {
//		IEntityLivingData data = super.onInitialSpawn(difficulty, entityLivingData, itemNbt);
//		if (this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).isEmpty() && this.world.rand.nextFloat() < 0.03F) {
//            this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.NAUTILUS_SHELL));
//            this.setDropChance(EntityEquipmentSlot.OFFHAND, 2.0F);
//        }
//		return data;
//	}
//	
//	public boolean canSpawn(IWorld iWorld_1, boolean spawnType_1) {
//        Biome biome_1 = iWorld_1.getBiome(new BlockPos(this.posX, this.posY, this.posZ));
//        if (biome_1 != Biomes.RIVER && biome_1 != Biomes.FROZEN_RIVER) {
//            return this.world.rand.nextInt(40) == 0 && this.method_7015() && super.canSpawn(iWorld_1, spawnType_1);
//        } else {
//            return this.world.rand.nextInt(15) == 0 && super.canSpawn(iWorld_1, spawnType_1);
//        }
//    }
//	
//	protected boolean canSpawnAt(IWorld iWorld_1, SpawnerEntityTypes spawnType_1, BlockPos blockPos_1) {
//        return iWorld_1.getFluidState(blockPos_1).getFluidState() == FluidTags.WATER;
//    }
//	
//	private boolean method_7015() {
//        return this.getBoundingBox().minY < (double)(this.world.getSeaLevel() - 5);
//    }
//	
//	protected boolean shouldBreakDoors() {
//        return false;
//    }
//
//    protected SoundEvent getAmbientSound() {
//        return this.isInWater() ? SoundEvents.ENTITY_DROWNED_AMBIENT_WATER : SoundEvents.ENTITY_DROWNED_AMBIENT;
//    }
//
//    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
//        return this.isInWater() ? SoundEvents.ENTITY_DROWNED_HURT_WATER : SoundEvents.ENTITY_DROWNED_HURT;
//    }
//
//    protected SoundEvent getDeathSound() {
//        return this.isInWater() ? SoundEvents.ENTITY_DROWNED_DEATH_WATER : SoundEvents.ENTITY_DROWNED_DEATH;
//    }
//
//    protected SoundEvent getStepSound() {
//        return SoundEvents.ENTITY_DROWNED_STEP;
//    }
//
//    protected SoundEvent getSwimSound() {
//        return SoundEvents.ENTITY_DROWNED_SWIM;
//    }
//
//    protected ItemStack getSkull() {
//        return ItemStack.EMPTY;
//    }
//    
//    @Override
//    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
//    	if ((double)this.world.rand.nextFloat() > 0.9D) {
//            int int_1 = this.world.rand.nextInt(16);
//            if (int_1 < 10) {
//                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.TRIDENT));
//            } else {
//                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.FISHING_ROD));
//            }
//        }
//    }
//    
//    @Override
//    protected void onDrowned() {}
//    
//    public boolean canFly() {
//        return !this.isSwimming();
//    }
//    
//    public boolean isTargetingUnderwater() {
//    	if (this.targetingUnderwater) {
//            return true;
//        } else {
//            LivingEntity livingEntity_1 = this.getAttackTarget();
//            return livingEntity_1 != null && livingEntity_1.isInWater();
//        }
//	}
//    
//    @Override
//    public void travel(float strafe, float vertical, float forward) {
//    	if (this.isNotColliding() && this.isInWater() && this.isTargetingUnderwater()) {
//            this.addVelocity(0.01F, 0.01F, 0.01F);
//            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
//            this.setVelocity(this.motionX * 0.9D, this.motionY * 0.9D, this.motionZ * 0.9D);
//        } else {
//            super.travel(strafe, vertical, forward);
//        }
//    	super.travel(strafe, vertical, forward);
//    }
//    
//    @Override
//    public void updateSwimming() {
//        if (!this.world.isRemote) {
//            if (this.isNotColliding() && this.isInWater() && this.isTargetingUnderwater()) {
//                this.navigator = this.waterNavigation;
//                this.setSwimming(true);
//            } else {
//                this.navigator = this.landNavigation;
//                this.setSwimming(false);
//            }
//        }
//    }
//    
//    protected boolean method_7016() {
//        Path path_1 = this.getNavigator().getPath();
//        if (path_1 != null) {
//            PathPoint pathNode_1 = path_1.getTarget();
//            if (pathNode_1 != null) {
//                double double_1 = this.getDistanceSq((double)pathNode_1.x, (double)pathNode_1.y, (double)pathNode_1.z);
//                if (double_1 < 4.0D) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    
//    public void setTargetingUnderwater(boolean boolean_1) {
//        this.targetingUnderwater = boolean_1;
//    }
//    
//    public boolean method_7012(@Nullable LivingEntity livingEntity_1) {
//        if (livingEntity_1 != null) {
//            return !this.world.isDaytime() || livingEntity_1.isInWater();
//        } else {
//            return false;
//        }
//    }
//    
//	@Override
//	public void attackEntityWithRangedAttack(LivingEntity arg0, float arg1) {
//		EntityTrident tridentEntity_1 = new EntityTrident(this.world, this, new ItemStack(Items.TRIDENT));
//        double double_1 = arg0.posX - this.posX;
//        double double_2 = arg0.getBoundingBox().minY + (double)(arg0.getEyeHeight() / 3.0F) - tridentEntity_1.posY;
//        double double_3 = arg0.posZ - this.posZ;
//        double double_4 = (double)MathHelper.sqrt(double_1 * double_1 + double_3 * double_3);
//        tridentEntity_1.setVelocity(double_1, double_2 + double_4 * 0.20000000298023224D, double_3);
//        tridentEntity_1.setDamage((14 - this.world.getDifficulty().getId() * 4));
//        this.playSound(SoundEvents.ENTITY_DROWNED_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
//        this.world.spawnEntity(tridentEntity_1);
//	}
//	
//	static class EntityAIDrownedMoveHelper extends EntityMoveHelper {
//		private final EntityDrownedVillager drowned;
//		
//		public EntityAIDrownedMoveHelper(EntityDrownedVillager drownedEntity_1) {
//            super(drownedEntity_1);
//            this.drowned = drownedEntity_1;
//        }
//		
//		public void tick() {
//            LivingEntity livingEntity_1 = this.drowned.getAttackTarget();
//            if (this.drowned.isTargetingUnderwater() && this.drowned.isInWater()) {
//                if (livingEntity_1 != null && livingEntity_1.getPosition().getY() > this.drowned.posY || this.drowned.targetingUnderwater) {
//                    this.drowned.addVelocity(0.0D, 0.002D, 0.0D);
//                }
//
//                if (this.action != Action.MOVE_TO || this.drowned.getNavigator().noPath()) {
//                    this.drowned.setAIMoveSpeed(0.0F);
//                    return;
//                }
//
//                double double_1 = this.getX() - this.drowned.posX;
//                double double_2 = this.getY() - this.drowned.posY;
//                double double_3 = this.getZ() - this.drowned.posZ;
//                double double_4 = (double)MathHelper.sqrt(double_1 * double_1 + double_2 * double_2 + double_3 * double_3);
//                double_2 /= double_4;
//                float float_1 = (float)(MathHelper.atan2(double_3, double_1) * 57.2957763671875D) - 90.0F;
//                this.drowned.rotationYaw = this.limitAngle(this.drowned.rotationYaw, float_1, 90.0F);
//                this.drowned.cameraPitch = this.drowned.rotationYaw;
//                float float_2 = (float)(this.speed * this.drowned.getAIMoveSpeed());
//                float float_3 = (float) MathHelper.clampedLerp(0.125F, this.drowned.getAIMoveSpeed(), float_2);
//                this.drowned.setAIMoveSpeed(float_3);
//                this.drowned.addVelocity((double)float_3 * double_1 * 0.005D, (double)float_3 * double_2 * 0.1D, (double)float_3 * double_3 * 0.005D);
//            } else {
//                if (!this.drowned.onGround) {
//                    this.drowned.addVelocity(0.0D, -0.008D, 0.0D);
//                }
//                super.tick();
//            }
//        }
//	}
//	
//	static class EntityAIDrownedAttack extends EntityAIZombieAttack {
//		private final EntityDrownedVillager drowned;
//		
//		public EntityAIDrownedAttack(EntityDrownedVillager drownedEntity, double p_i46803_2_, boolean p_i46803_4_) {
//			super(drownedEntity, p_i46803_2_, p_i46803_4_);
//			this.drowned = drownedEntity;
//		}
//		
//		public boolean canStart() {
//			return super.shouldExecute() && this.drowned.method_7012(this.drowned.getAttackTarget());
//	    }
//
//	    public boolean shouldContinue() {
//	    	return super.shouldContinueExecuting() && this.drowned.method_7012(this.drowned.getAttackTarget());
//	    }
//	}
//	
//	static class EntityAIWanderOnSurface extends EntityAIBase {
//		private final EntityMob mob;
//		private double x;
//        private double y;
//        private double z;
//        private final double speed;
//        private final World world;
//
//        public EntityAIWanderOnSurface(EntityMob mobEntityWithAi_1, double double_1) {
//            this.mob = mobEntityWithAi_1;
//            this.speed = double_1;
//            this.world = mobEntityWithAi_1.world;
//        }
//        
//        @Override
//        public boolean shouldContinueExecuting() {
//        	return !this.mob.getNavigator().noPath();
//        }
//        
//        @Override
//        public void startExecuting() {
//        	this.mob.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);
//        }
//        
//        @Override
//        public boolean shouldExecute() {
//        	if (!this.world.isDaytime()) {
//                return false;
//            } else if (this.mob.isInWater()) {
//                return false;
//            } else {
//                Vector3d vec3d_1 = this.getWanderTarget();
//                if (vec3d_1 == null) {
//                    return false;
//                } else {
//                    this.x = vec3d_1.x;
//                    this.y = vec3d_1.y;
//                    this.z = vec3d_1.z;
//                    return true;
//                }
//            }
//        }
//        
//        @Nullable
//        private Vector3d getWanderTarget() {
//            Random random_1 = this.mob.getRNG();
//            BlockPos blockPos_1 = new BlockPos(this.mob.posX, this.mob.getBoundingBox().minY, this.mob.posZ);
//            for(int int_1 = 0; int_1 < 10; ++int_1) {
//                BlockPos blockPos_2 = blockPos_1.add(random_1.nextInt(20) - 10, 2 - random_1.nextInt(8), random_1.nextInt(20) - 10);
//                if (this.world.getBlockState(blockPos_2).getBlock() == Blocks.WATER) {
//                    return new Vector3d((double)blockPos_2.getX(), (double)blockPos_2.getY(), (double)blockPos_2.getZ());
//                }
//            }
//            return null;
//        }
//	}
//	
//	static class EntityAILeaveWater extends EntityAIMoveToBlock {
//        private final EntityDrownedVillager drowned;
//
//        public EntityAILeaveWater(EntityDrownedVillager drownedEntity_1, double double_1) {
//            super(drownedEntity_1, double_1, 8, 2);
//            this.drowned = drownedEntity_1;
//        }
//
//        @Override
//        public boolean shouldExecute() {
//            return super.shouldExecute() && !this.drowned.world.isDaytime() && this.drowned.isInWater() && this.drowned.posY >= (double)(this.drowned.world.getSeaLevel() - 3);
//        }
//
//        public boolean shouldContinueExecuting() {
//            return super.shouldContinueExecuting();
//        }
//
//        public void startExecuting() {
//            this.drowned.setTargetingUnderwater(false);
//            this.drowned.navigator = this.drowned.landNavigation;
//            super.startExecuting();
//        }
//        
//		@Override
//		protected boolean shouldMoveTo(IWorldReaderBase arg0, BlockPos arg1) {
//			BlockPos blockPos_2 = arg1.up();
//            return arg0.isAirBlock(blockPos_2) && arg0.isAirBlock(blockPos_2.up()) ? arg0.getBlockState(arg1).isTopSolid((IWorldReader) arg0, arg1) : false;
//		}
//    }
//	
//	static class class_1557 extends EntityAIBase {
//        private final EntityDrownedVillager drowned;
//        private final double speed;
//        private final int minY;
//        private boolean field_7248;
//
//        public class_1557(EntityDrownedVillager drownedEntity_1, double double_1, int int_1) {
//            this.drowned = drownedEntity_1;
//            this.speed = double_1;
//            this.minY = int_1;
//        }
//
//        public boolean canExecute() {
//            return !this.drowned.world.isDaytime() && this.drowned.isInWater() && this.drowned.posY < (double)(this.minY - 2);
//        }
//
//        public void tick() {
//            if (this.drowned.posY < (double)(this.minY - 1) && (this.drowned.getNavigator().noPath() || this.drowned.method_7016())) {
//                Vector3d vec3d_1 = new Vector3d(this.drowned.posX, (double)(this.minY - 1), this.drowned.posZ);
//                
//                this.drowned.getNavigator().tryMoveToXYZ(vec3d_1.x, vec3d_1.y, vec3d_1.z, this.speed);
//            }
//        }
//        
//        @Override
//        public void startExecuting() {
//        	this.drowned.setTargetingUnderwater(true);
//            this.field_7248 = false;
//        }
//        
//        @Override
//        public void resetTask() {
//        	this.drowned.setTargetingUnderwater(false);
//        }
//
//		@Override
//		public boolean shouldExecute() {
//			return true;
//		}
//    }
//	
//	static class EntityAITridentAttack extends EntityAIAttackRanged {
//        private final EntityDrownedVillager drowned;
//
//        public EntityAITridentAttack(IRangedAttackMob rangedAttackMob_1, double double_1, int int_1, float float_1) {
//        	super(rangedAttackMob_1, double_1, int_1, float_1);
//            this.drowned = (EntityDrownedVillager)rangedAttackMob_1;
//        }
//
//        public boolean shouldExecute() {
//            return super.shouldExecute() && this.drowned.getActiveItemStack().getItem() == Items.TRIDENT;
//        }
//
//        public void startExecuting() {
//            super.startExecuting();
//        }
//
//        @Override
//        public void resetTask() {
//        	super.resetTask();
//        	this.drowned.activeItemStack = null;
//        }
//    }
}
