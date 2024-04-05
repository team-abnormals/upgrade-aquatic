package com.teamabnormals.upgrade_aquatic.common.entity.animal;

import com.google.common.collect.Lists;
import com.teamabnormals.blueprint.common.entity.BucketableWaterAnimal;
import com.teamabnormals.upgrade_aquatic.api.util.UAEntityPredicates;
import com.teamabnormals.upgrade_aquatic.common.block.PickerelweedBlock;
import com.teamabnormals.upgrade_aquatic.common.block.PickerelweedDoublePlantBlock;
import com.teamabnormals.upgrade_aquatic.common.block.PickerelweedPlantBlock;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.pike.HideInPickerelweedGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.pike.PikeAttackGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.pike.PikeSwimToItemsGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.pike.PikeTemptGoal;
import com.teamabnormals.upgrade_aquatic.core.other.UADataSerializers;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UAParticleTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UASoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class Pike extends BucketableWaterAnimal {
	private static final EntityDataAccessor<PikeType> TYPE = SynchedEntityData.defineId(Pike.class, UADataSerializers.PIKE_TYPE);
	private static final EntityDataAccessor<Boolean> DROP_ITEM = SynchedEntityData.defineId(Pike.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> MOVING = SynchedEntityData.defineId(Pike.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> LIT = SynchedEntityData.defineId(Pike.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> ATTACK_COOLDOWN = SynchedEntityData.defineId(Pike.class, EntityDataSerializers.INT);
	public static final Predicate<ItemEntity> ITEM_SELECTOR = (entity) -> !entity.hasPickUpDelay() && entity.isAlive() && entity.isInWater() && entity.getItem().is(ItemTags.FISHES);
	private int eatTicks;
	private int dropEatingLootCooldown;

	public Pike(EntityType<? extends Pike> type, Level world) {
		super(type, world);
		this.moveControl = new MoveHelperController(this);
		this.startUsingItem(InteractionHand.MAIN_HAND);
		this.setCanPickUpLoot(true);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 12.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.9D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		if (this.getPikeType() != PikeType.SPECTRAL) {
			this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Pike.class, 8.0F, 1.6D, 1.4D, UAEntityPredicates.IS_SPECTRAL::test) {

				@Override
				public boolean canUse() {
					return super.canUse() && this.mob != null && ((Pike) this.mob).getPikeType() != PikeType.SPECTRAL;
				}

			});
		}
		this.goalSelector.addGoal(3, new PikeTemptGoal(this));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.1D, 40) {

			@Override
			public boolean canUse() {
				return ((Pike) mob).isHidingInPickerelweed() ? super.canUse() && mob.getRandom().nextInt(6) == 0 : super.canUse();
			}

		});
		this.goalSelector.addGoal(4, new HideInPickerelweedGoal(this));
		this.goalSelector.addGoal(4, new PikeSwimToItemsGoal(this));
		this.goalSelector.addGoal(4, new PikeAttackGoal(this, 12D, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, AbstractFish.class, true) {

			@Override
			public boolean canUse() {
				return ((Pike) this.mob).getAttackCooldown() <= 0 && super.canUse();
			}

		});
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, UAEntityPredicates.IS_CHILD::test) {

			@Override
			public boolean canUse() {
				return ((Pike) this.mob).getAttackCooldown() <= 0 && super.canUse();
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
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		if (key.equals(TYPE)) {
			this.refreshDimensions();
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("PikeType", this.getPikeType().id);
		compound.putInt("AttackCooldown", this.getAttackCooldown());
		compound.putInt("EatingLootDropCooldown", this.dropEatingLootCooldown);
		compound.putBoolean("DoesDropItem", this.shouldDropItem());
		compound.putBoolean("Lit", this.isLit());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setPikeType(PikeType.getTypeById(Mth.clamp(compound.getInt("PikeType"), 1, 21)));
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

		if (!this.isInWater() && this.onGround() && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
			this.setOnGround(false);
			this.hasImpulse = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}

		if (this.level().getGameTime() % 20 == 0) {
			LivingEntity caughtEntity = this.getCaughtEntity();
			if (caughtEntity != null && (!this.isPickerelweedNearby() || this.isHidingInPickerelweed())) {
				this.level().playSound(null, this.blockPosition(), UASoundEvents.PIKE_BITE.get(), SoundSource.HOSTILE, 0.8F, 0.90F);
				if (this.level().isClientSide && caughtEntity.getHealth() <= 1 && this.getPikeType() == PikeType.SPECTRAL) {
					for (int i = 0; i < 3; ++i) {
						this.level().addParticle(UAParticleTypes.SPECTRAL_CONSUME.get(), caughtEntity.getX() + (caughtEntity.getRandom().nextDouble() - 0.5D) * (double) caughtEntity.getBbWidth(), caughtEntity.getY() + caughtEntity.getRandom().nextDouble() * (double) caughtEntity.getBbHeight() - 0.25D, caughtEntity.getZ() + (caughtEntity.getRandom().nextDouble() - 0.5D) * (double) caughtEntity.getBbWidth(), (this.getCaughtEntity().getRandom().nextDouble() - 0.5D) * 2.0D, -caughtEntity.getRandom().nextDouble(), (caughtEntity.getRandom().nextDouble() - 0.5D) * 2.0D);
					}
				}
				caughtEntity.hurt(this.damageSources().mobAttack(this), 1.0F);
			}
		}

		if (this.getAttackCooldown() > 0) {
			this.setAttackCooldown(this.getAttackCooldown() - 1);
		}

		ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
		if (!this.level().isClientSide && this.isAlive() && this.isEffectiveAi() && !itemstack.isEmpty() && this.canEatItem(itemstack) && this.isInWater()) {
			if (this.eatTicks > 600) {
				ItemStack itemstackFood = itemstack.finishUsingItem(this.level(), this);
				if (!itemstack.isEmpty()) {
					this.setItemSlot(EquipmentSlot.MAINHAND, itemstackFood);
				}

				if (this.dropEatingLootCooldown <= 0) {
					if (this.random.nextFloat() < 0.2F) {
						for (ItemStack stacks : this.generateFishingLoot()) {
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
				this.level().broadcastEntityEvent(this, (byte) 45);
			}
		}

		if (this.isMoving() && this.isInWater() && this.getPikeType() == PikeType.SUPERCHARGED) {
			Vec3 vec3d1 = this.getViewVector(0.0F);

			for (int i = 0; i < 2; ++i) {
				this.level().addParticle(ParticleTypes.BUBBLE, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.x * 1.5D, this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - vec3d1.y * 1.5D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.z * 1.5D, 0.0D, 0.0D, 0.0D);
			}
		}

		if (this.getPikeType() == PikeType.OBSIDIAN && this.isLit()) {
			if (this.level().isClientSide) {
				for (int i = 0; i < 2; i++) {
					this.level().addParticle(ParticleTypes.PORTAL, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - 0.25D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
				}
			}
		}
	}

	@Override
	public void travel(Vec3 p_213352_1_) {
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
		if (!this.hasCaughtEntity() && entitySource instanceof LivingEntity && !(entitySource instanceof Player && ((Player) entitySource).getAbilities().instabuild)) {
			this.setTarget((LivingEntity) source.getEntity());
			return super.hurt(source, amount);
		} else {
			return super.hurt(source, amount);
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		int type = PikeType.getRandom(this.random, this.level().getBiome(this.blockPosition()), reason == MobSpawnType.BUCKET).id;
		if (dataTag != null && dataTag.contains("BucketVariantTag", 3)) {
			this.setPikeType(PikeType.getTypeById(dataTag.getInt("BucketVariantTag")));
			this.dropEatingLootCooldown = dataTag.getInt("EatingLootDropCooldown");
			if (dataTag.contains("PikeHeldItem")) {
				this.startUsingItem(InteractionHand.MAIN_HAND);
				this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.of(dataTag.getCompound("PikeHeldItem")));
			}
			if (dataTag.contains("ShouldDropItem")) {
				this.setToDropItem(dataTag.getBoolean("ShouldDropItem"));
			}
			if (dataTag.contains("IsLit")) {
				this.setLit(dataTag.getBoolean("IsLit"));
			}
			return spawnDataIn;
		}
		if (spawnDataIn instanceof Pike.PikeData) {
			type = ((Pike.PikeData) spawnDataIn).typeData;
		} else {
			if (!this.fromBucket()) {
				spawnDataIn = new Pike.PikeData(type);
			}
		}

		this.setPikeType(PikeType.getTypeById(type));

		if (this.random.nextFloat() <= 0.10F && this.isEffectiveAi()) {
			List<ItemStack> generatedFishingLoot = this.generateFishingLoot();
			for (ItemStack itemstack : generatedFishingLoot) {
				this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
			}
			this.setToDropItem(false);
		}

		this.refreshDimensions();
		return spawnDataIn;
	}

	public static boolean checkPikeSpawnRules(EntityType<? extends Pike> entityType, LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
		int vertRadius = 2;
		int horizRadius = 3;

		int count = 0;
		for (int y = pos.getY() - vertRadius; y <= pos.getY() + vertRadius; y++) {
			for (int x = pos.getX() - horizRadius; x <= pos.getX() + horizRadius; x++) {
				for (int z = pos.getZ() - horizRadius; z <= pos.getZ() + horizRadius; z++) {
					BlockState state = world.getBlockState(new BlockPos(x, y, z));
					if (state.getBlock() instanceof PickerelweedPlantBlock || state.getBlock() instanceof PickerelweedDoublePlantBlock) {
						count++;
					}
				}
			}
		}

		int i = world.getSeaLevel();
		return random.nextFloat() < (0.05F * count) && pos.getY() >= i - 13 && pos.getY() <= i;
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
			itemstack.hurtAndBreak(1, player, (onBroken) -> {
				onBroken.broadcastBreakEvent(hand);
			});
			this.playSound(SoundEvents.FLINTANDSTEEL_USE, 1.0F, 1.0F);
			this.setLit(true);
			return InteractionResult.SUCCESS;
		} else {
			return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
		}
	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		return new WaterBoundPathNavigation(this, worldIn) {

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
	public ItemStack getBucketItemStack() {
		return new ItemStack(UAItems.PIKE_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
	}

	@Override
	public void saveToBucketTag(ItemStack bucket) {
		super.saveToBucketTag(bucket);

		CompoundTag compoundnbt = bucket.getOrCreateTag();
		CompoundTag compoundnbt1 = new CompoundTag();

		compoundnbt.putInt("BucketVariantTag", this.getPikeType().id);
		compoundnbt.putInt("EatingLootDropCooldown", this.dropEatingLootCooldown);

		if (!this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
			this.getItemBySlot(EquipmentSlot.MAINHAND).save(compoundnbt1);
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
			return passenger instanceof AbstractFish || passenger instanceof Animal ? (LivingEntity) passenger : null;
		}
		return null;
	}

	public boolean hasCaughtEntity() {
		return this.getCaughtEntity() != null;
	}

	public void spitOutItem(ItemStack stackIn) {
		if (!stackIn.isEmpty()) {
			if (!this.level().isClientSide) {
				ItemEntity itementity = new ItemEntity(this.level(), this.getX() + this.getLookAngle().x, this.getY() + 1.0D, this.getZ() + this.getLookAngle().z, stackIn);
				itementity.setPickUpDelay(40);
				itementity.setThrower(this.getUUID());
				this.playSound(UASoundEvents.PIKE_SPIT.get(), 1.0F, 1.0F);
				this.level().addFreshEntity(itementity);
				this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
			}
		}
	}

	private boolean canEatItem(ItemStack itemstack) {
		return itemstack.is(ItemTags.FISHES);
	}

	public boolean isPickerelweedNearby() {
		return this.getNearbyPickerelweeds().size() > 0;
	}

	public boolean isHidingInPickerelweed() {
		return this.getCommandSenderWorld().getBlockState(blockPosition()).getBlock() instanceof PickerelweedPlantBlock || this.getCommandSenderWorld().getBlockState(blockPosition()).getBlock() instanceof PickerelweedDoublePlantBlock;
	}

	public List<BlockPos> getNearbyPickerelweeds() {
		List<BlockPos> pickerelweeds = Lists.newArrayList();
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
		for (int yy = this.blockPosition().getY() - 6; yy <= this.getY() + 6; yy++) {
			for (int xx = this.blockPosition().getX() - 12; xx <= this.getX() + 12; xx++) {
				for (int zz = this.blockPosition().getZ() - 12; zz <= this.getZ() + 12; zz++) {
					mutable.set(xx, yy, zz);
					Block block = this.level().getBlockState(mutable).getBlock();
					if (block instanceof PickerelweedPlantBlock || block instanceof PickerelweedDoublePlantBlock) {
						pickerelweeds.add(mutable);
					}
				}
			}
		}
		return pickerelweeds;
	}

	private List<ItemStack> generateFishingLoot() {
		LootParams.Builder builder = (new LootParams.Builder((ServerLevel) this.level())).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.TOOL, new ItemStack(Items.FISHING_ROD)).withLuck((float) 1 + 1);
		builder.withParameter(LootContextParams.KILLER_ENTITY, this).withParameter(LootContextParams.THIS_ENTITY, this);
		LootTable loottable = this.level().getServer().getLootData().getLootTable(this.getRandom().nextFloat() >= 0.1F ? BuiltInLootTables.FISHING_JUNK : BuiltInLootTables.FISHING_TREASURE);
		return loottable.getRandomItems(builder.create(LootContextParamSets.FISHING));
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return sizeIn.height * 0.6F;
	}

	@Override
	public boolean canPickUpLoot() {
		return this.isInWater() && super.canPickUpLoot();
	}

	@Override
	protected float getEquipmentDropChance(EquipmentSlot p_205712_1_) {
		return this.shouldDropItem() ? 100 : 0;
	}

	@Override
	public boolean canTakeItem(ItemStack p_213365_1_) {
		EquipmentSlot equipmentslottype = Mob.getEquipmentSlotForItem(p_213365_1_);
		if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
			return false;
		} else {
			return equipmentslottype == EquipmentSlot.MAINHAND && super.canTakeItem(p_213365_1_);
		}
	}

	@Override
	protected void pickUpItem(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		if (this.canHoldItem(itemstack) && !this.getItemBySlot(EquipmentSlot.MAINHAND).is(ItemTags.FISHES)) {
			int i = itemstack.getCount();
			if (i > 1) {
				this.spawnItem(itemstack.split(i - 1));
			}

			this.spitOutItem(this.getItemBySlot(EquipmentSlot.MAINHAND));
			this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
			this.take(itemEntity, itemstack.getCount());
			itemEntity.discard();
			this.eatTicks = 0;
		}
	}

	private void spawnItem(ItemStack stack) {
		this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), stack));
	}

	@Override
	public void take(Entity entityIn, int quantity) {
		super.take(entityIn, quantity);
		this.setToDropItem(true);
	}

	@Override
	public void positionRider(Entity passenger, Entity.MoveFunction function) {
		if (passenger instanceof AbstractFish || passenger instanceof Animal) {
			float distance = 0.7F;

			double dx = Math.cos((this.getYRot() + 90) * Math.PI / 180.0D) * distance;
			double dz = Math.sin((this.getYRot() + 90) * Math.PI / 180.0D) * distance;

			Vec3 riderPos = new Vec3(this.getX() + dx, this.getY() + this.getPassengersRidingOffset() + this.getPassengers().get(0).getMyRidingOffset(), this.getZ() + dz);
			function.accept(passenger, riderPos.x, riderPos.y, riderPos.z);
		} else {
			super.positionRider(passenger);
		}
	}

	@Override
	public double getPassengersRidingOffset() {
		return this.getDimensions(this.getPose()).height * 0.075D;
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(UAItems.PIKE_SPAWN_EGG.get());
	}

	@Override
	public EntityDimensions getDimensions(Pose poseIn) {
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
		return UASoundEvents.PIKE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UASoundEvents.PIKE_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return UASoundEvents.PIKE_HURT.get();
	}

	protected SoundEvent getFlopSound() {
		return UASoundEvents.PIKE_FLOP.get();
	}

	protected void playStepSound(BlockPos p_27482_, BlockState p_27483_) {
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	static class MoveHelperController extends MoveControl {
		private final Pike pike;

		MoveHelperController(Pike pike) {
			super(pike);
			this.pike = pike;
		}

		public void tick() {
			if (this.pike.isEyeInFluid(FluidTags.WATER)) {
				this.pike.setDeltaMovement(this.pike.getDeltaMovement().add(0.0D, 0.001D, 0.0D));
			}

			if (this.operation == MoveControl.Operation.MOVE_TO && !this.pike.getNavigation().isDone()) {
				double d0 = this.wantedX - this.pike.getX();
				double d1 = this.wantedY - this.pike.getY();
				double d2 = this.wantedZ - this.pike.getZ();
				double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
				d1 = d1 / d3;
				float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.pike.setYRot(this.rotlerp(this.pike.getYRot(), f, 90.0F));
				this.pike.yBodyRot = this.pike.getYRot();
				float f1 = (float) (this.speedModifier * this.pike.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				this.pike.setSpeed(Mth.lerp(0.125F, this.pike.getSpeed(), f1));
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

	static class PikeData implements SpawnGroupData {
		public final int typeData;

		public PikeData(int type) {
			this.typeData = type;
		}
	}
}