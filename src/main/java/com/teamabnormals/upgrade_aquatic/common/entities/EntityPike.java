package com.teamabnormals.upgrade_aquatic.common.entities;

import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.api.entities.EntityBucketableWaterMob;
import com.teamabnormals.upgrade_aquatic.api.util.UAEntityPredicates;
import com.teamabnormals.upgrade_aquatic.client.particle.UAParticles;
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
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityPike extends EntityBucketableWaterMob {
	private static final DataParameter<Integer> PIKE_TYPE = EntityDataManager.createKey(EntityPike.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> CAUGHT_ENTITY = EntityDataManager.createKey(EntityPike.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> DROP_ITEM = EntityDataManager.createKey(EntityPike.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ATTACK_COOLDOWN = EntityDataManager.createKey(EntityPike.class, DataSerializers.VARINT);
	private int eatTicks;
	
	public EntityPike(EntityType<? extends EntityPike> type, World world) {
		super(type, world);
		this.moveController = new MoveHelperController(this);
		this.setActiveHand(Hand.MAIN_HAND);
		this.setCanPickUpLoot(true);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
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
		this.goalSelector.addGoal(4, new EntityPike.HideInPickerelweedGoal(this));
		this.goalSelector.addGoal(4, new EntityPike.PikeAttackGoal(this, 12D, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<AbstractFishEntity>(this, AbstractFishEntity.class, true) {
			
			@Override
			public boolean shouldExecute() {
				return ((EntityPike)this.goalOwner).getAttackCooldown() <= 0 && super.shouldExecute();
			}
			
		});
		this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(PIKE_TYPE, 0);
		this.dataManager.register(CAUGHT_ENTITY, 0);
		this.dataManager.register(DROP_ITEM, true);
		this.dataManager.register(ATTACK_COOLDOWN, 0);
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
		compoundnbt.putBoolean("ShouldDropItem", this.shouldDropItem());
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
		return 3;
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
			if(this.isPickerelweedNearby()) {
				if(this.isHidingInPickerelweed()) {
					this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_FOX_BITE, SoundCategory.HOSTILE, 0.8F, 0.90F);
					if(this.getCaughtEntity().getHealth() <= 1) {
						if(this.getPikeType() == 7) {
							if (this.world.isRemote) {
								for(int i = 0; i < 3; ++i) {
									UAParticles.SPECTRAL_CONSUME.spawn(this.world, this.getCaughtEntity().posX + (this.getCaughtEntity().getRNG().nextDouble() - 0.5D) * (double)this.getCaughtEntity().getWidth(), this.getCaughtEntity().posY + this.getCaughtEntity().getRNG().nextDouble() * (double)this.getCaughtEntity().getHeight() - 0.25D, this.getCaughtEntity().posZ + (this.getCaughtEntity().getRNG().nextDouble() - 0.5D) * (double)this.getCaughtEntity().getWidth(), (this.getCaughtEntity().getRNG().nextDouble() - 0.5D) * 2.0D, -this.getCaughtEntity().getRNG().nextDouble(), (this.getCaughtEntity().getRNG().nextDouble() - 0.5D) * 2.0D);
								}
							}
						}
					}
					this.getCaughtEntity().attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
				}
			} else {
				this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_FOX_BITE, SoundCategory.HOSTILE, 0.8F, 0.90F);
				if(this.getCaughtEntity().getHealth() <= 1) {
					if(this.getPikeType() == 7) {
						if (this.world.isRemote) {
							for(int i = 0; i < 3; ++i) {
								UAParticles.SPECTRAL_CONSUME.spawn(this.world, this.getCaughtEntity().posX + (this.getCaughtEntity().getRNG().nextDouble() - 0.5D) * (double)this.getCaughtEntity().getWidth(), this.getCaughtEntity().posY + this.getCaughtEntity().getRNG().nextDouble() * (double)this.getCaughtEntity().getHeight() - 0.25D, this.getCaughtEntity().posZ + (this.getCaughtEntity().getRNG().nextDouble() - 0.5D) * (double)this.getCaughtEntity().getWidth(), (this.getCaughtEntity().getRNG().nextDouble() - 0.5D) * 2.0D, -this.getCaughtEntity().getRNG().nextDouble(), (this.getCaughtEntity().getRNG().nextDouble() - 0.5D) * 2.0D);
							}
						}
					}
				}
				this.getCaughtEntity().attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
			}
		}
		
		if(this.getAttackCooldown() > 0) {
			this.setAttackCooldown(this.getAttackCooldown() - 1);
		}
		
		if(this.getCaughtEntity() != null) {
			this.getCaughtEntity().startRiding(this);
		}
		
		ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		if (!this.world.isRemote && this.isAlive() && this.isServerWorld() && !itemstack.isEmpty() && this.canEatItem(itemstack) && this.isInWater()) {
			if (this.eatTicks > 600) {
				ItemStack itemstackFood = itemstack.onItemUseFinish(this.world, this);
				if (!itemstack.isEmpty()) {
					this.setItemStackToSlot(EquipmentSlotType.MAINHAND, itemstackFood);
				}
				this.heal(6);
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
			if(dataTag.contains("ShouldDropItem")) {
				this.setToDropItem(dataTag.getBoolean("ShouldDropItem"));
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
		
		if(this.getRNG().nextFloat() <= 0.10F && !world.isRemote && this.isServerWorld()) {
			LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.world)).withParameter(LootParameters.POSITION, new BlockPos(this)).withParameter(LootParameters.TOOL, new ItemStack(Items.FISHING_ROD)).withRandom(this.rand).withLuck((float)1 + 1);
			lootcontext$builder.withParameter(LootParameters.KILLER_ENTITY, this).withParameter(LootParameters.THIS_ENTITY, this);
			LootTable loottable = this.getRNG().nextFloat() >= 0.1F ? this.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING_JUNK) : this.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING_TREASURE);
			List<ItemStack> list = loottable.generate(lootcontext$builder.build(LootParameterSets.FISHING));
			for(ItemStack itemstack : list) {
				this.setItemStackToSlot(EquipmentSlotType.MAINHAND, itemstack);
			}
			this.setToDropItem(false);
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
	
	private boolean isPickerelweedNearby() {
		return this.getNearbyPickerelweeds().size() > 0;
	}
	
	private List<BlockPos> getNearbyPickerelweeds() {
		List<BlockPos> pickerelweeds = Lists.newArrayList();
		for (int yy = this.getPosition().getY() - 6; yy <= this.getPosition().getY() + 6; yy++) {
			for (int xx = this.getPosition().getX() - 12; xx <= this.getPosition().getX() + 12; xx++) {
				for (int zz = this.getPosition().getZ() - 12; zz <= this.getPosition().getZ() + 12; zz++) {
					if(world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() instanceof BlockPickerelWeed || world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() instanceof BlockPickerelWeedDouble) {
						pickerelweeds.add(new BlockPos(xx, yy, zz));
					}
				}
			}
		}
		return pickerelweeds;
	}
	
	@Override
	public boolean canPickUpLoot() {
		return this.isInWater() && super.canPickUpLoot();
	}
	
	@Override
	protected float getDropChance(EquipmentSlotType p_205712_1_) {
		if(this.shouldDropItem()) {
			return 100;
		} else {
			return 0;
		}
	}
	
	@Override
	protected void updateEquipmentIfNeeded(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		if (!this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem().isIn(ItemTags.FISHES) && this.canEquipItem(itemstack)) {
			int i = itemstack.getCount();
		    if (i > 1) {
		    	this.spawnItem(itemstack.split(i - 1));
		    }

		    this.spitOutItem(this.getItemStackFromSlot(EquipmentSlotType.MAINHAND));
		    this.setItemStackToSlot(EquipmentSlotType.MAINHAND, itemstack.split(1));
		    this.onItemPickup(itemEntity, itemstack.getCount());
		    itemEntity.remove();
		    this.eatTicks = 0;
		}
	}
	
	private void spawnItem(ItemStack stackIn) {
		ItemEntity itementity = new ItemEntity(this.world, this.posX, this.posY, this.posZ, stackIn);
		this.world.addEntity(itementity);
	}
	
	@Override
	public void onItemPickup(Entity entityIn, int quantity) {
		super.onItemPickup(entityIn, quantity);
		this.setToDropItem(true);
	}
	
	@Override
	public void updatePassenger(Entity passenger) {
		if(!this.getPassengers().isEmpty() && passenger instanceof AbstractFishEntity) {
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
	
	private void setCaughtEntity(int entityId) {
		this.dataManager.set(CAUGHT_ENTITY, entityId);
	}

	public boolean hasCaughtEntity() {
		return this.dataManager.get(CAUGHT_ENTITY) != 0;
	}
	
	public int getAttackCooldown() {
		return this.dataManager.get(ATTACK_COOLDOWN);
	}
	
	public void setAttackCooldown(int ticks) {
		this.dataManager.set(ATTACK_COOLDOWN, ticks);
	}
	
	public boolean shouldDropItem() {
		return this.dataManager.get(DROP_ITEM);
	}
	
	public void setToDropItem(boolean bool) {
		this.dataManager.set(DROP_ITEM, bool);
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
		compound.putBoolean("DoesDropItem", this.shouldDropItem());
		compound.putInt("AttackCooldown", this.getAttackCooldown());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setPikeType(compound.getInt("PikeType"));
		this.setToDropItem(compound.getBoolean("DoesDropItem"));
		this.setAttackCooldown(compound.getInt("AttackCooldown"));
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
		ForgeRegistries.BIOMES.getValues().stream().forEach(EntityPike::processSpawning);
	}
	
	private static void processSpawning(Biome biome) {
		if(biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.RIVER) {
        	if(biome.getCategory() == Category.SWAMP) {
        		biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(UAEntities.PIKE, 5, 1, 2));
        		biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(EntityType.SQUID, 5, 1, 2));
        		biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(EntityType.SALMON, 5, 1, 2));
        	} else {
        		biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(UAEntities.PIKE, 11, 1, 2));
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
	
	static class HideInPickerelweedGoal extends RandomWalkingGoal {
		
		public HideInPickerelweedGoal(EntityPike pike) {
			super(pike, 1.1D, 25);
		}
		
		@Override
		public boolean shouldExecute() {
			if (!this.mustUpdate) {
				if (this.creature.getIdleTime() >= 100) {
					return false;
				}

				if (this.creature.getRNG().nextInt(this.executionChance) != 0) {
					return false;
				}
			}

			Vec3d vec3d = this.getPosition();
			if (vec3d == null) {
				return false;
			} else {
				if(((EntityPike)creature).isPickerelweedNearby()) {
					this.x = vec3d.x;
					this.y = vec3d.y;
					this.z = vec3d.z;
					this.mustUpdate = false;
					return true;
				}
			}
			return false;
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return ((EntityPike)creature).isPickerelweedNearby() && super.shouldContinueExecuting();
		}

		@Nullable
		protected Vec3d getPosition() {
			if(((EntityPike)creature).isPickerelweedNearby()) {
				int pickedWeed = creature.getRNG().nextInt(((EntityPike)creature).getNearbyPickerelweeds().size());
				return new Vec3d(((EntityPike)creature).getNearbyPickerelweeds().get(pickedWeed));
			}
			return null;
		}
		
	}
	
	static class PikeAttackGoal extends MeleeAttackGoal {
		
		public PikeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}
		
		@Override
		public boolean shouldExecute() {
			return ((EntityPike)this.attacker).getAttackCooldown() <= 0 && attacker.getAttackTarget() != null && !attacker.getAttackTarget().isRidingOrBeingRiddenBy(attacker) && !attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem().isIn(ItemTags.FISHES) && attacker.isInWater() && ((EntityPike)attacker).getCaughtEntity() == null && !(attacker.getAttackTarget() instanceof PufferfishEntity) && super.shouldExecute();
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return !attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem().isIn(ItemTags.FISHES) && attacker.isInWater() && super.shouldContinueExecuting();
		}
		
		@Override
		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d = this.getAttackReachSqr(enemy);
			Vector3f difference = new Vector3f(
				enemy.getPosition().getX() - attacker.getPosition().getX(),
				enemy.getPosition().getY() - attacker.getPosition().getY(),
				enemy.getPosition().getZ() - attacker.getPosition().getZ()
			);
			boolean isCloseToEntity = difference.length() <= 2;
			if (isCloseToEntity && distToEnemySqr <= d && this.attackTick <= 0) {
				this.attackTick = 20;
				if(attacker.getAttackTarget() != null) {
					((EntityPike)this.attacker).setAttackCooldown(attacker.getRNG().nextInt(551) + 50);
					AxisAlignedBB bb = new AxisAlignedBB(attacker.getPosition()).grow(16.0D);
					List<Entity> entities = attacker.world.getEntitiesWithinAABB(Entity.class, bb);
					for(int i = 0; i < entities.size(); i++) {
						Entity entity = entities.get(i);
						if(entity instanceof EntityPike) {
							if(((EntityPike)entity).getCaughtEntity() != null && ((EntityPike)entity).getCaughtEntity().getEntityId() == enemy.getEntityId()) {
								((EntityPike)entity).setCaughtEntity(0);
							}
						}
					}
					((EntityPike)this.attacker).setCaughtEntity(enemy.getEntityId());
				}
				this.attacker.setAttackTarget(null);
			}
		}
		
		@Override
		public void startExecuting() {
			if(attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND) != null) {
				((EntityPike)this.attacker).spitOutItem(attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND));
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