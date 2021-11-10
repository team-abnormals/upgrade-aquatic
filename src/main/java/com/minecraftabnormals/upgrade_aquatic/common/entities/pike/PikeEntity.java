package com.minecraftabnormals.upgrade_aquatic.common.entities.pike;

import com.google.common.collect.Lists;
import com.minecraftabnormals.abnormals_core.common.entity.BucketableWaterMobEntity;
import com.minecraftabnormals.upgrade_aquatic.api.util.UAEntityPredicates;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.PickerelweedBlock;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.PickerelweedDoublePlantBlock;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.PickerelweedPlantBlock;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai.HideInPickerelweedGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai.PikeAttackGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai.PikeSwimToItemsGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai.PikeTemptGoal;
import com.minecraftabnormals.upgrade_aquatic.core.other.UADataSerializers;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UASounds;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class PikeEntity extends BucketableWaterMobEntity {
	private static final DataParameter<PikeType> TYPE = EntityDataManager.defineId(PikeEntity.class, UADataSerializers.PIKE_TYPE);
	private static final DataParameter<Boolean> DROP_ITEM = EntityDataManager.defineId(PikeEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> MOVING = EntityDataManager.defineId(PikeEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LIT = EntityDataManager.defineId(PikeEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ATTACK_COOLDOWN = EntityDataManager.defineId(PikeEntity.class, DataSerializers.INT);
	public static final Predicate<ItemEntity> ITEM_SELECTOR = (entity) -> {
		return !entity.hasPickUpDelay() && entity.isAlive() && entity.isInWater() && entity.getItem().getItem().is(ItemTags.FISHES);
	};
	private int eatTicks;
	private int dropEatingLootCooldown;

	public PikeEntity(EntityType<? extends PikeEntity> type, World world) {
		super(type, world);
		this.moveControl = new MoveHelperController(this);
		this.startUsingItem(Hand.MAIN_HAND);
		this.setCanPickUpLoot(true);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 12.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.9D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		if (this.getPikeType() != PikeType.SPECTRAL) {
			this.goalSelector.addGoal(2, new AvoidEntityGoal<PikeEntity>(this, PikeEntity.class, 8.0F, 1.6D, 1.4D, UAEntityPredicates.IS_SPECTRAL::test) {

				@Override
				public boolean canUse() {
					return super.canUse() && this.mob != null && ((PikeEntity) this.mob).getPikeType() != PikeType.SPECTRAL;
				}

			});
		}
		this.goalSelector.addGoal(3, new PikeTemptGoal(this));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.1D, 40) {

			@Override
			public boolean canUse() {
				return ((PikeEntity) mob).isHidingInPickerelweed() ? super.canUse() && mob.getRandom().nextInt(6) == 0 : super.canUse();
			}

		});
		this.goalSelector.addGoal(4, new HideInPickerelweedGoal(this));
		this.goalSelector.addGoal(4, new PikeSwimToItemsGoal(this));
		this.goalSelector.addGoal(4, new PikeAttackGoal(this, 12D, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<AbstractFishEntity>(this, AbstractFishEntity.class, true) {

			@Override
			public boolean canUse() {
				return ((PikeEntity) this.mob).getAttackCooldown() <= 0 && super.canUse();
			}

		});
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<TurtleEntity>(this, TurtleEntity.class, 10, true, false, UAEntityPredicates.IS_CHILD::test) {

			@Override
			public boolean canUse() {
				return ((PikeEntity) this.mob).getAttackCooldown() <= 0 && super.canUse();
			}

		});

		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(TYPE, PikeType.AMUR);
		this.entityData.define(DROP_ITEM, true);
		this.entityData.define(MOVING, false);
		this.entityData.define(LIT, false);
		this.entityData.define(ATTACK_COOLDOWN, 0);
	}

	@Override
	public void onSyncedDataUpdated(DataParameter<?> key) {
		if (key.equals(TYPE)) {
			this.refreshDimensions();
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("PikeType", this.getPikeType().id);
		compound.putInt("AttackCooldown", this.getAttackCooldown());
		compound.putInt("EatingLootDropCooldown", this.dropEatingLootCooldown);
		compound.putBoolean("DoesDropItem", this.shouldDropItem());
		compound.putBoolean("Lit", this.isLit());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setPikeType(PikeType.getTypeById(MathHelper.clamp(compound.getInt("PikeType"), 1, 21)));
		this.dropEatingLootCooldown = compound.getInt("EatingLootDropCooldown");
		this.setAttackCooldown(compound.getInt("AttackCooldown"));
		this.setToDropItem(compound.getBoolean("DoesDropItem"));
		this.setLit(compound.getBoolean("Lit"));
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.eatTicks++;

		if (this.dropEatingLootCooldown > 0) this.dropEatingLootCooldown--;

		if (!this.isInWater() && this.onGround && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
			this.onGround = false;
			this.hasImpulse = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}

		if (this.level.getGameTime() % 20 == 0) {
			LivingEntity caughtEntity = this.getCaughtEntity();
			if (caughtEntity != null && (!this.isPickerelweedNearby() || this.isHidingInPickerelweed())) {
				this.level.playSound(null, this.blockPosition(), UASounds.ENTITY_PIKE_BITE.get(), SoundCategory.HOSTILE, 0.8F, 0.90F);
				if (this.level.isClientSide && caughtEntity.getHealth() <= 1 && this.getPikeType() == PikeType.SPECTRAL) {
					for (int i = 0; i < 3; ++i) {
						this.level.addParticle(UAParticles.SPECTRAL_CONSUME.get(), caughtEntity.getX() + (caughtEntity.getRandom().nextDouble() - 0.5D) * (double) caughtEntity.getBbWidth(), caughtEntity.getY() + caughtEntity.getRandom().nextDouble() * (double) caughtEntity.getBbHeight() - 0.25D, caughtEntity.getZ() + (caughtEntity.getRandom().nextDouble() - 0.5D) * (double) caughtEntity.getBbWidth(), (this.getCaughtEntity().getRandom().nextDouble() - 0.5D) * 2.0D, -caughtEntity.getRandom().nextDouble(), (caughtEntity.getRandom().nextDouble() - 0.5D) * 2.0D);
					}
				}
				caughtEntity.hurt(DamageSource.mobAttack(this), 1.0F);
			}
		}

		if (this.getAttackCooldown() > 0) {
			this.setAttackCooldown(this.getAttackCooldown() - 1);
		}

		ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.MAINHAND);
		if (!this.level.isClientSide && this.isAlive() && this.isEffectiveAi() && !itemstack.isEmpty() && this.canEatItem(itemstack) && this.isInWater()) {
			if (this.eatTicks > 600) {
				ItemStack itemstackFood = itemstack.finishUsingItem(this.level, this);
				if (!itemstack.isEmpty()) {
					this.setItemSlot(EquipmentSlotType.MAINHAND, itemstackFood);
				}

				if (this.dropEatingLootCooldown <= 0) {
					if (this.random.nextFloat() < 0.2F) {
						for (ItemStack stacks : this.generateFishingLoot((ServerWorld) this.level)) {
							if (stacks.getCount() > 0) {
								stacks.shrink(stacks.getCount() - 1);
							}
							this.spitOutItem(stacks);
						}
					} else {
						this.spitOutItem(new ItemStack(Items.BONE_MEAL));
					}
					this.dropEatingLootCooldown = 3600 + this.getRandom().nextInt(400);
				}

				this.heal(6);
				this.eatTicks = 0;
			} else if (this.eatTicks > 560 && this.random.nextFloat() < 0.1F) {
				this.playSound(this.getEatingSound(itemstack), 1.0F, 1.0F);
				this.level.broadcastEntityEvent(this, (byte) 45);
			}
		}

		if (this.isMoving() && this.isInWater() && this.getPikeType() == PikeType.SUPERCHARGED) {
			Vector3d vec3d1 = this.getViewVector(0.0F);

			for (int i = 0; i < 2; ++i) {
				this.level.addParticle(ParticleTypes.BUBBLE, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.x * 1.5D, this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - vec3d1.y * 1.5D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.z * 1.5D, 0.0D, 0.0D, 0.0D);
			}
		}

		if (this.getPikeType() == PikeType.OBSIDIAN && this.isLit()) {
			if (this.level.isClientSide) {
				for (int i = 0; i < 2; i++) {
					this.level.addParticle(ParticleTypes.PORTAL, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - 0.25D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
				}
			}
		}
	}

	@Override
	public void travel(Vector3d p_213352_1_) {
		if (this.isEffectiveAi() && this.isInWater()) {
			float speed = this.getPikeType() == PikeType.SUPERCHARGED ? 0.05F : 0.01F;
			this.moveRelative(speed, p_213352_1_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(p_213352_1_);
		}
	}

	public boolean hurt(DamageSource source, float amount) {
		Entity entitySource = source.getEntity();
		if (!this.hasCaughtEntity() && entitySource instanceof LivingEntity && !(entitySource instanceof PlayerEntity && ((PlayerEntity) entitySource).abilities.instabuild)) {
			this.setTarget((LivingEntity) source.getEntity());
			return super.hurt(source, amount);
		} else {
			return super.hurt(source, amount);
		}
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		int type = PikeType.getRandom(this.random, level.getBiome(this.blockPosition()).getBiomeCategory(), reason == SpawnReason.BUCKET).id;
		if (dataTag != null && dataTag.contains("BucketVariantTag", 3)) {
			this.setPikeType(PikeType.getTypeById(dataTag.getInt("BucketVariantTag")));
			this.dropEatingLootCooldown = dataTag.getInt("EatingLootDropCooldown");
			if (dataTag.contains("PikeHeldItem")) {
				this.startUsingItem(Hand.MAIN_HAND);
				this.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.of(dataTag.getCompound("PikeHeldItem")));
			}
			if (dataTag.contains("ShouldDropItem")) {
				this.setToDropItem(dataTag.getBoolean("ShouldDropItem"));
			}
			if (dataTag.contains("IsLit")) {
				this.setLit(dataTag.getBoolean("IsLit"));
			}
			return spawnDataIn;
		}
		if (spawnDataIn instanceof PikeEntity.PikeData) {
			type = ((PikeEntity.PikeData) spawnDataIn).typeData;
		} else {
			if (!this.isFromBucket()) {
				spawnDataIn = new PikeEntity.PikeData(type);
			}
		}

		this.setPikeType(PikeType.getTypeById(type));

		if (this.random.nextFloat() <= 0.10F && this.isEffectiveAi()) {
			List<ItemStack> generatedFishingLoot = this.generateFishingLoot((ServerWorld) this.level);
			for (ItemStack itemstack : generatedFishingLoot) {
				this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
			}
			this.setToDropItem(false);
		}

		this.refreshDimensions();
		return spawnDataIn;
	}

	public static boolean pickerelCondition(EntityType<? extends PikeEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if (((World) world).dimension() != World.OVERWORLD) return false;
		for (int yy = pos.getY() - 2; yy <= pos.getY() + 2; yy++) {
			for (int xx = pos.getX() - 6; xx <= pos.getX() + 6; xx++) {
				for (int zz = pos.getZ() - 6; zz <= pos.getZ() + 6; zz++) {
					if (world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() instanceof PickerelweedPlantBlock || world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() instanceof PickerelweedDoublePlantBlock) {
						if (random.nextFloat() <= 0.125F)
							if (world.getBiome(pos).getBiomeCategory() == Biome.Category.SWAMP) {
								return random.nextFloat() <= 0.25F;
							}
						return true;
					}
				}
			}
		}
		return random.nextFloat() <= 0.05F;
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
			itemstack.hurtAndBreak(1, player, (onBroken) -> {
				onBroken.broadcastBreakEvent(hand);
			});
			this.playSound(SoundEvents.FLINTANDSTEEL_USE, 1.0F, 1.0F);
			this.setLit(true);
			return ActionResultType.SUCCESS;
		} else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn) {

			@Override
			protected boolean canUpdatePath() {
				return super.canUpdatePath() || this.mob.getFeetBlockState().getBlock() instanceof PickerelweedBlock;
			}

			@Override
			public boolean isStableDestination(BlockPos pos) {
				return super.isStableDestination(pos) || this.mob.getFeetBlockState().getBlock() instanceof PickerelweedBlock;
			}

		};
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.PIKE_BUCKET.get());
	}

	@Override
	protected void setBucketData(ItemStack bucket) {
		if (this.hasCustomName()) {
			bucket.setHoverName(this.getCustomName());
		}
		CompoundNBT compoundnbt = bucket.getOrCreateTag();
		CompoundNBT compoundnbt1 = new CompoundNBT();

		compoundnbt.putInt("BucketVariantTag", this.getPikeType().id);
		compoundnbt.putInt("EatingLootDropCooldown", this.dropEatingLootCooldown);

		if (!this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty()) {
			this.getItemBySlot(EquipmentSlotType.MAINHAND).save(compoundnbt1);
		}

		compoundnbt.put("PikeHeldItem", compoundnbt1);
		compoundnbt.putBoolean("ShouldDropItem", this.shouldDropItem());
		compoundnbt.putBoolean("IsLit", this.isLit());
	}

	@Nullable
	public LivingEntity getCaughtEntity() {
		List<Entity> passengers = this.getPassengers();
		if (!passengers.isEmpty()) {
			Entity passenger = passengers.get(0);
			return passenger instanceof AbstractFishEntity || passenger instanceof AnimalEntity ? (LivingEntity) passenger : null;
		}
		return null;
	}

	public boolean hasCaughtEntity() {
		return this.getCaughtEntity() != null;
	}

	public void spitOutItem(ItemStack stackIn) {
		if (!stackIn.isEmpty()) {
			if (!this.level.isClientSide) {
				ItemEntity itementity = new ItemEntity(this.level, this.getX() + this.getLookAngle().x, this.getY() + 1.0D, this.getZ() + this.getLookAngle().z, stackIn);
				itementity.setPickUpDelay(40);
				itementity.setThrower(this.getUUID());
				this.playSound(UASounds.ENTITY_PIKE_SPIT.get(), 1.0F, 1.0F);
				this.level.addFreshEntity(itementity);
				this.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
			}
		}
	}

	private boolean canEatItem(ItemStack itemstack) {
		return itemstack.getItem().is(ItemTags.FISHES);
	}

	public boolean isPickerelweedNearby() {
		return this.getNearbyPickerelweeds().size() > 0;
	}

	public boolean isHidingInPickerelweed() {
		return this.getCommandSenderWorld().getBlockState(blockPosition()).getBlock() instanceof PickerelweedPlantBlock || this.getCommandSenderWorld().getBlockState(blockPosition()).getBlock() instanceof PickerelweedDoublePlantBlock;
	}

	public List<BlockPos> getNearbyPickerelweeds() {
		List<BlockPos> pickerelweeds = Lists.newArrayList();
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for (int yy = this.blockPosition().getY() - 6; yy <= this.getY() + 6; yy++) {
			for (int xx = this.blockPosition().getX() - 12; xx <= this.getX() + 12; xx++) {
				for (int zz = this.blockPosition().getZ() - 12; zz <= this.getZ() + 12; zz++) {
					mutable.set(xx, yy, zz);
					Block block = this.level.getBlockState(mutable).getBlock();
					if (block instanceof PickerelweedPlantBlock || block instanceof PickerelweedDoublePlantBlock) {
						pickerelweeds.add(mutable);
					}
				}
			}
		}
		return pickerelweeds;
	}

	private List<ItemStack> generateFishingLoot(ServerWorld world) {
		LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) this.level)).withParameter(LootParameters.ORIGIN, this.position()).withParameter(LootParameters.TOOL, new ItemStack(Items.FISHING_ROD)).withRandom(this.random).withLuck((float) 1 + 1);
		lootcontext$builder.withParameter(LootParameters.KILLER_ENTITY, this).withParameter(LootParameters.THIS_ENTITY, this);
		LootTable loottable = this.getRandom().nextFloat() >= 0.1F ? this.level.getServer().getLootTables().get(LootTables.FISHING_JUNK) : this.level.getServer().getLootTables().get(LootTables.FISHING_TREASURE);
		return loottable.getRandomItems(lootcontext$builder.create(LootParameterSets.FISHING));
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.6F;
	}

	@Override
	public boolean canPickUpLoot() {
		return this.isInWater() && super.canPickUpLoot();
	}

	@Override
	protected float getEquipmentDropChance(EquipmentSlotType p_205712_1_) {
		return this.shouldDropItem() ? 100 : 0;
	}

	@Override
	public boolean canTakeItem(ItemStack p_213365_1_) {
		EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(p_213365_1_);
		if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
			return false;
		} else {
			return equipmentslottype == EquipmentSlotType.MAINHAND && super.canTakeItem(p_213365_1_);
		}
	}

	@Override
	protected void pickUpItem(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		if (this.canHoldItem(itemstack) && !this.getItemBySlot(EquipmentSlotType.MAINHAND).getItem().is(ItemTags.FISHES)) {
			int i = itemstack.getCount();
			if (i > 1) {
				this.spawnItem(itemstack.split(i - 1));
			}

			this.spitOutItem(this.getItemBySlot(EquipmentSlotType.MAINHAND));
			this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack.split(1));
			this.take(itemEntity, itemstack.getCount());
			itemEntity.remove();
			this.eatTicks = 0;
		}
	}

	private void spawnItem(ItemStack stack) {
		this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), stack));
	}

	@Override
	public void take(Entity entityIn, int quantity) {
		super.take(entityIn, quantity);
		this.setToDropItem(true);
	}

	@Override
	public void positionRider(Entity passenger) {
		if (passenger instanceof AbstractFishEntity || passenger instanceof AnimalEntity) {
			float distance = 0.7F;

			double dx = Math.cos((this.yRot + 90) * Math.PI / 180.0D) * distance;
			double dz = Math.sin((this.yRot + 90) * Math.PI / 180.0D) * distance;

			Vector3d riderPos = new Vector3d(this.getX() + dx, this.getY() + this.getPassengersRidingOffset() + this.getPassengers().get(0).getMyRidingOffset(), this.getZ() + dz);
			passenger.setPos(riderPos.x, riderPos.y, riderPos.z);
		} else {
			super.positionRider(passenger);
		}
	}

	@Override
	public double getPassengersRidingOffset() {
		return this.getDimensions(this.getPose()).height * 0.075D;
	}

	@Override
	public boolean rideableUnderWater() {
		return true;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.PIKE_SPAWN_EGG.get());
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return super.getDimensions(poseIn).scale(this.getPikeType().pikeSize.boxSize);
	}

	public boolean isLit() {
		return this.entityData.get(LIT);
	}

	private void setLit(boolean lit) {
		this.entityData.set(LIT, lit);
	}

	public boolean isMoving() {
		return this.entityData.get(MOVING);
	}

	private void setMoving(boolean moving) {
		this.entityData.set(MOVING, moving);
	}

	public int getAttackCooldown() {
		return this.entityData.get(ATTACK_COOLDOWN);
	}

	public void setAttackCooldown(int ticks) {
		this.entityData.set(ATTACK_COOLDOWN, ticks);
	}

	public boolean shouldDropItem() {
		return this.entityData.get(DROP_ITEM);
	}

	public void setToDropItem(boolean bool) {
		this.entityData.set(DROP_ITEM, bool);
	}

	public PikeType getPikeType() {
		return this.entityData.get(TYPE);
	}

	public void setPikeType(PikeType type) {
		this.entityData.set(TYPE, type);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UASounds.ENTITY_PIKE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UASounds.ENTITY_PIKE_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return UASounds.ENTITY_PIKE_HURT.get();
	}

	protected SoundEvent getFlopSound() {
		return UASounds.ENTITY_PIKE_FLOP.get();
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	static class MoveHelperController extends MovementController {
		private final PikeEntity pike;

		MoveHelperController(PikeEntity pike) {
			super(pike);
			this.pike = pike;
		}

		public void tick() {
			if (this.pike.isEyeInFluid(FluidTags.WATER)) {
				this.pike.setDeltaMovement(this.pike.getDeltaMovement().add(0.0D, 0.001D, 0.0D));
			}

			if (this.operation == MovementController.Action.MOVE_TO && !this.pike.getNavigation().isDone()) {
				double d0 = this.wantedX - this.pike.getX();
				double d1 = this.wantedY - this.pike.getY();
				double d2 = this.wantedZ - this.pike.getZ();
				double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				d1 = d1 / d3;
				float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.pike.yRot = this.rotlerp(this.pike.yRot, f, 90.0F);
				this.pike.yBodyRot = this.pike.yRot;
				float f1 = (float) (this.speedModifier * this.pike.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				this.pike.setSpeed(MathHelper.lerp(0.125F, this.pike.getSpeed(), f1));
				this.pike.setDeltaMovement(this.pike.getDeltaMovement().add(0.0D, (double) this.pike.getSpeed() * d1 * 0.04D, 0.0D));
				this.pike.setMoving(true);
			} else {
				if (this.pike.getPikeType() == PikeType.SUPERCHARGED) {
					this.pike.setSpeed(0.0F);
				}
				this.pike.setMoving(false);
			}
		}
	}

	static class PikeData implements ILivingEntityData {
		public final int typeData;

		public PikeData(int type) {
			this.typeData = type;
		}
	}
}