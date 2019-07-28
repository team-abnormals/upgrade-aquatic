package com.teamabnormals.upgrade_aquatic.common.entities;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.api.entities.EntityBucketableWaterMob;
import com.teamabnormals.upgrade_aquatic.api.util.UAEntityPredicates;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelWeed;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelWeedDouble;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.storage.loot.*;

public class EntityPike extends EntityBucketableWaterMob {
	private static final DataParameter<Integer> PIKE_TYPE = EntityDataManager.createKey(EntityPike.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> CAUGHT_ENTITY = EntityDataManager.createKey(EntityPike.class, DataSerializers.VARINT);
	private static final Predicate<ItemEntity> CAN_PICKUP = (entity) -> {
		return entity.getItem().getItem().isIn(ItemTags.FISHES) && !entity.cannotPickup() && entity.isAlive();
	};
	private int eatTicks;
	
	public EntityPike(EntityType<? extends EntityPike> type, World world) {
		super(type, world);
		this.moveController = new MoveHelperController(this);
		this.setActiveHand(Hand.MAIN_HAND);
		this.setCanPickUpLoot(true);
	}
	
	public EntityPike(World world, double posX, double posY, double posZ) {
        this(UAEntities.PIKE, world);
        this.setPosition(posX, posY, posZ);
    }
	
	@Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9D);
    }
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NOT_SPECTATING::test));
		if(this.getPikeType() != 7) {
			this.goalSelector.addGoal(2, new AvoidEntityGoal<EntityPike>(this, EntityPike.class, 8.0F, 1.6D, 1.4D, UAEntityPredicates.IS_SPECTRAL::test) {
				
				@Override
				public boolean shouldExecute() {
					return super.shouldExecute() && entity != null && ((EntityPike)entity).getPikeType() != 7;
				}
				
			});
		}
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.1D, 40) {
			
			@Override
			public boolean shouldExecute() {
				return ((EntityPike)creature).isHidingInPickerelweed() ? super.shouldExecute() && creature.getRNG().nextInt(6) == 0 : super.shouldExecute();
			}
			
		});
		this.goalSelector.addGoal(4, new EntityPike.PikeAttackGoal(this, 12D, true));
		this.goalSelector.addGoal(4, new EntityPike.FindItemsGoal());
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, AbstractFishEntity.class, true));
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(PIKE_TYPE, 0);
		this.dataManager.register(CAUGHT_ENTITY, 0);
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.PIKE_BUCKET);
	}
	
	@Override
	protected void setBucketData(ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setDisplayName(this.getCustomName());
        }
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
        CompoundNBT compoundnbt1 = new CompoundNBT();
        compoundnbt.putInt("BucketVariantTag", this.getPikeType());
        if (!this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
        	this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).write(compoundnbt1);
        }
        compoundnbt.put("PikeHeldItem", compoundnbt1);
    }
	
	private void setCaughtEntity(int entityId) {
		this.dataManager.set(CAUGHT_ENTITY, entityId);
	}

	public boolean hasCaughtEntity() {
		return this.dataManager.get(CAUGHT_ENTITY) != 0;
	}
	
	public void notifyDataManagerChange(DataParameter<?> key) {
		super.notifyDataManagerChange(key);
		if (CAUGHT_ENTITY.equals(key)) {
			
		}
	}
	
	@Nullable
	public LivingEntity getCaughtEntity() {
		if (!this.hasCaughtEntity()) {
			return null;
		} else {
			Entity entity = this.world.getEntityByID(this.dataManager.get(CAUGHT_ENTITY));
			if (entity == null) {
				return null;
			} else if (entity != null && entity instanceof AbstractFishEntity) {
				return (AbstractFishEntity)entity;
			}
		}
		return null;
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
		eatTicks++;
		if (!this.isInWater() && this.onGround && this.collidedVertically) {
			this.setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F)));
			this.onGround = false;
			this.isAirBorne = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
		}
		
		if(this.world.getGameTime() % 20 == 0 && this.getCaughtEntity() != null) {
			this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_FOX_BITE, SoundCategory.HOSTILE, 0.8F, 0.90F);
			this.getCaughtEntity().attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
		}
		
		if(this.getCaughtEntity() != null) {
			this.getCaughtEntity().startRiding(this);
		}
		
		ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		if (this.canEatItem(itemstack)) {
			if (this.eatTicks > 600) {
				ItemStack itemstackFood = itemstack.onItemUseFinish(this.world, this);
				if (!itemstack.isEmpty()) {
					this.setItemStackToSlot(EquipmentSlotType.MAINHAND, itemstackFood);
				}
				this.eatTicks = 0;
			} else if (this.eatTicks > 560 && this.rand.nextFloat() < 0.1F) {
				this.playSound(this.getEatSound(itemstack), 1.0F, 1.0F);
				this.world.setEntityState(this, (byte)45);
			}
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
	
	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		int type = this.getRandomTypeForBiome(worldIn);
		if(dataTag != null && dataTag.contains("BucketVariantTag", 3)) {
			this.setPikeType(dataTag.getInt("BucketVariantTag"));
			if(dataTag.contains("PikeHeldItem")) {
				this.setActiveHand(Hand.MAIN_HAND);
				this.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.read(dataTag.getCompound("PikeHeldItem")));
			}
			return spawnDataIn;
		}
		if (spawnDataIn instanceof EntityPike.PikeData) {
			type = ((EntityPike.PikeData)spawnDataIn).typeData;
		} else {
			if(!this.isFromBucket()) {
				spawnDataIn = new EntityPike.PikeData(type);
			}
		}
		
		this.setPikeType(type);
		
		if(this.getRNG().nextFloat() <= 0.10F) {
			LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.world)).withParameter(LootParameters.POSITION, new BlockPos(this)).withParameter(LootParameters.TOOL, new ItemStack(Items.FISHING_ROD)).withRandom(this.rand).withLuck((float)1 + 1);
			lootcontext$builder.withParameter(LootParameters.KILLER_ENTITY, this).withParameter(LootParameters.THIS_ENTITY, this);
			LootTable loottable = this.getRNG().nextFloat() >= 0.1F ? this.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING_JUNK) : this.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING_TREASURE);
			List<ItemStack> list = loottable.generate(lootcontext$builder.build(LootParameterSets.FISHING));
			for(ItemStack itemstack : list) {
				this.setItemStackToSlot(EquipmentSlotType.MAINHAND, itemstack);
			}
		}
		
		return spawnDataIn;
	}
	
	private void spitOutItem(ItemStack stackIn) {
		if (!stackIn.isEmpty() && !this.world.isRemote) {
			ItemEntity itementity = new ItemEntity(this.world, this.posX + this.getLookVec().x, this.posY + 1.0D, this.posZ + this.getLookVec().z, stackIn);
			itementity.setPickupDelay(40);
			itementity.setThrowerId(this.getUniqueID());
			this.playSound(SoundEvents.ENTITY_FOX_SPIT, 1.0F, 1.0F);
			this.world.addEntity(itementity);
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
		}
	}
	
	private boolean canEatItem(ItemStack itemstack) {
		return itemstack.getItem().isIn(ItemTags.FISHES);
	}
	
	@Override
	public void updatePassenger(Entity passenger) {
		if(!this.getPassengers().isEmpty()) {
			float distance = 0.7F;
			
			double dx = Math.cos((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;
			double dz = Math.sin((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;
			
			Vec3d riderPos = new Vec3d(this.posX + dx, this.posY + this.getMountedYOffset() + this.getPassengers().get(0).getYOffset(), this.posZ + dz);
			
			this.getPassengers().get(0).setPosition(riderPos.x, riderPos.y, riderPos.z);
		} else {
			super.updatePassenger(passenger);
		}
	}
	
	@Override
	public double getMountedYOffset() {
		return this.getSize(getPose()).height * 0.075D;
	}
	
	@Override
	public boolean canBeRiddenInWater() {
		return true;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		float scale = 0F;
		if(this.getPikeType() == 0) {
			scale = 7F;
		} else if(this.getPikeType() == 1) {
			scale = 8F;
		} else {
			scale = 10F;
		}
		return super.getSize(poseIn).scale(0.225F * scale);
	}
	
	public static String getNameById(int id) {
		switch(id) {
			case 1:
				return "amur_pike";
			case 2:
				return "redfin_pickerel";
			case 3:
				return "brown_northern_pike";
			case 4:
				return "mahogany_northern_pike";
			case 5:
				return "jade_northern_pike";
			case 6:
				return "olive_northern_pike";
			case 7:
				return "spectral_pike";
			case 8:
				return "spotted_brown_northern_pike";
			case 9:
				return "spotted_mahogany_northern_pike";
			case 10:
				return "spotted_jade_northern_pike";
			case 11:
				return "spotted_olive_northern_pike";
		}
		return "";
	}
	
	public int getPikeType() {
		return this.dataManager.get(PIKE_TYPE);
	}
	
	public void setPikeType(int typeId) {
		this.dataManager.set(PIKE_TYPE, typeId);
	}
	
	private int getRandomTypeForBiome(IWorld world) {
		Biome biome = world.getBiome(new BlockPos(this));
		int probability = rand.nextInt(101);
		if(biome.getCategory() == Category.SWAMP) {
			int decidedVariant = probability >= 60 ? (rand.nextInt(20) <= 2 ? 7 : -1) : rand.nextInt(3) == 0 ? 2 : 1;
			if(decidedVariant == -1) {
				float chance = rand.nextFloat();
				if(chance <= 1 && chance >= 0.5) {
					decidedVariant = rand.nextInt(6) == 0 ? 8 : 3;
				} else if(chance < 0.5 && chance >= 0.35) {
					decidedVariant = rand.nextInt(6) == 0 ? 10 : 5;
				} else if(chance < 0.35 && chance > 0.25) {
					decidedVariant = rand.nextInt(6) == 0 ? 11 : 6;
				} else {
					decidedVariant = rand.nextInt(6) == 0 ? 9 : 4;
				}
			}
			return decidedVariant;
		} else if(biome.getCategory() == Category.RIVER) {
			int decidedVariant = probability >= 60 ? (rand.nextInt(20) <= 2 ? rand.nextBoolean() ? 7 : -1 : -1) : rand.nextInt(4) == 0 ? 2 : -1;
			if(decidedVariant == -1) {
				float chance = rand.nextFloat();
				if(chance <= 1 && chance >= 0.5) {
					decidedVariant = rand.nextInt(6) == 0 ? 8 : 3;
				} else if(chance < 0.5 && chance >= 0.35) {
					decidedVariant = rand.nextInt(6) == 0 ? 10 : 5;
				} else if(chance < 0.35 && chance > 0.25) {
					decidedVariant = rand.nextInt(6) == 0 ? 11 : 6;
				} else {
					decidedVariant = rand.nextInt(6) == 0 ? 9 : 4;
				}
			}
			return decidedVariant;
		}
		int decidedVariant = probability >= 60 ? (rand.nextInt(20) <= 2 ? rand.nextBoolean() ? 7 : - 1: -1) : rand.nextInt(4) == 0 ? 5 : -1;
		if(decidedVariant == -1) {
			float chance = rand.nextFloat();
			if(chance <= 1 && chance >= 0.5) {
				decidedVariant = rand.nextInt(6) == 0 ? 9 : 4;
			} else if(chance < 0.5 && chance >= 0.35) {
				decidedVariant = rand.nextInt(6) == 0 ? 10 : 5;
			} else if(chance < 0.35 && chance > 0.25) {
				decidedVariant = rand.nextInt(6) == 0 ? 11 : 6;
			} else {
				decidedVariant = rand.nextInt(6) == 0 ? 9 : 4;
			}
		}
		return decidedVariant;
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("PikeType", this.getPikeType());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setPikeType(compound.getInt("PikeType"));
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
	
	public boolean isHidingInPickerelweed() {
		return this.getEntityWorld().getBlockState(getPosition()).getBlock() instanceof BlockPickerelWeed || this.getEntityWorld().getBlockState(getPosition()).getBlock() instanceof BlockPickerelWeedDouble;
	}

	public static void addSpawn() {
        for (Biome biome : Biome.BIOMES) {
        	if(biome.getCategory() == Category.RIVER || biome.getCategory() == Category.SWAMP) {
        		biome.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(UAEntities.PIKE, 8, 2, 4));
        	}
        }
	}
	
	static class MoveHelperController extends MovementController {
		private final EntityPike pike;

		MoveHelperController(EntityPike pike) {
			super(pike);
			this.pike = pike;
		}

		public void tick() {
			if (this.pike.areEyesInFluid(FluidTags.WATER)) {
				this.pike.setMotion(this.pike.getMotion().add(0.0D, 0.001D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.pike.getNavigator().noPath()) {
				double d0 = this.posX - this.pike.posX;
				double d1 = this.posY - this.pike.posY;
				double d2 = this.posZ - this.pike.posZ;
				double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				d1 = d1 / d3;
				float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
				this.pike.rotationYaw = this.limitAngle(this.pike.rotationYaw, f, 90.0F);
				this.pike.renderYawOffset = this.pike.rotationYaw;
				float f1 = (float)(this.speed * this.pike.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
				this.pike.setAIMoveSpeed(MathHelper.lerp(0.125F, this.pike.getAIMoveSpeed(), f1));
				this.pike.setMotion(this.pike.getMotion().add(0.0D, (double)this.pike.getAIMoveSpeed() * d1 * 0.04D, 0.0D));
			}
		}
		
	}
	
	class FindItemsGoal extends Goal {
		
		public FindItemsGoal() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean shouldExecute() {
			if (!EntityPike.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
				return false;
			} else if (EntityPike.this.getAttackTarget() == null && EntityPike.this.getRevengeTarget() == null) {
				if (EntityPike.this.getRNG().nextInt(10) != 0) {
					return false;
				} else {
					List<ItemEntity> list = EntityPike.this.world.getEntitiesWithinAABB(ItemEntity.class, EntityPike.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityPike.CAN_PICKUP);
					return !list.isEmpty() && EntityPike.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty();
				}
			} else {
				return false;
			}
		}
		
		public void tick() {
			List<ItemEntity> list = EntityPike.this.world.getEntitiesWithinAABB(ItemEntity.class, EntityPike.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityPike.CAN_PICKUP);
			ItemStack itemstack = EntityPike.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			if (itemstack.isEmpty() && !list.isEmpty()) {
				EntityPike.this.getNavigator().tryMoveToEntityLiving(list.get(0), (double)1.2F);
			}

		}

		public void startExecuting() {
			List<ItemEntity> list = EntityPike.this.world.getEntitiesWithinAABB(ItemEntity.class, EntityPike.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityPike.CAN_PICKUP);
			if (!list.isEmpty()) {
				EntityPike.this.getNavigator().tryMoveToEntityLiving(list.get(0), (double)1.2F);
			}
		}
		
	}
	
	static class PikeAttackGoal extends MeleeAttackGoal {
		
		public PikeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}
		
		@Override
		public boolean shouldExecute() {
			return ((EntityPike)field_75441_b).getCaughtEntity() == null && !(field_75441_b.getAttackTarget() instanceof PufferfishEntity) && super.shouldExecute();
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return field_75441_b.isInWater() && super.shouldContinueExecuting();
		}
		
		@Override
		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d && this.attackTick <= 0) {
				this.attackTick = 20;
				((EntityPike)this.field_75441_b).setCaughtEntity(enemy.getEntityId());
				this.field_75441_b.setAttackTarget(null);
			}
		}
		
		@Override
		public void startExecuting() {
			if(field_75441_b.getItemStackFromSlot(EquipmentSlotType.MAINHAND) != null) {
				((EntityPike)this.field_75441_b).spitOutItem(field_75441_b.getItemStackFromSlot(EquipmentSlotType.MAINHAND));
			}
			super.startExecuting();
		}
		
	}
	
	public static class PikeData implements ILivingEntityData {
		public final int typeData;

		public PikeData(int type) {
			this.typeData = type;
		}
	}
}