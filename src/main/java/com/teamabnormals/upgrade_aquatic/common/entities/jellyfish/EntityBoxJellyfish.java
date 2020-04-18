package com.teamabnormals.upgrade_aquatic.common.entities.jellyfish;

import java.util.Random;

import com.teamabnormals.upgrade_aquatic.api.endimator.Endimation;
import com.teamabnormals.upgrade_aquatic.api.entity.ai.PredicateAttackGoal;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai.BoxJellyfishHuntGoal;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishBoostGoal;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishSwinIntoDirectionGoal;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityBoxJellyfish extends AbstractEntityJellyfish {
	public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntityBoxJellyfish.class, DataSerializers.VARINT);
	public static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityBoxJellyfish.class, DataSerializers.FLOAT);
	public static final Endimation SWIM_ANIMATION = new Endimation(20);
	public static final Endimation BOOST_ANIMATION = new Endimation(20);
	private RotationController rotationController;
	private int huntingCooldown;

	public EntityBoxJellyfish(EntityType<? extends EntityBoxJellyfish> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(COLOR, 0);
		this.dataManager.register(SIZE, 1.0F);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new BoxJellyfishHuntGoal(this));
		this.goalSelector.addGoal(2, new JellyfishSwinIntoDirectionGoal(this));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this));
		
		this.targetSelector.addGoal(1, new PredicateAttackGoal<>(this, AbstractFishEntity.class, true, owner -> !((EntityBoxJellyfish) owner).hasCooldown()));
	}
	
	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		super.notifyDataManagerChange(key);
		if(SIZE.equals(key)) {
			this.recalculateSize();
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(this.hasCooldown()) this.huntingCooldown--;
		
		this.rotationYaw = this.rotationYawHead = this.renderYawOffset = 0;
		
		if(this.isEndimationPlaying(BOOST_ANIMATION) && this.isInWater()) {
			this.setMotion(this.getMotion().scale(1.15F));
		}
	}
	
	@Override
	public void onEndimationStart(Endimation endimation) {
		float[] rotations = this.getRotationController().getRotations(1.0F);
		float yaw = (float) rotations[0] - this.rotationYaw;
		float pitch = (float) rotations[1] - 90.0F;
		
		if(endimation == SWIM_ANIMATION) {
			float x = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
			float y = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
			float z = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
			
			Vec3d motion = new Vec3d(x, y, z).normalize().scale(0.6F).scale(this.getSize());
			
			this.addVelocity(motion.x, motion.y, motion.z);
		} else if(endimation == BOOST_ANIMATION) {
			float x = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
			float y = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
			float z = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
			
			Vec3d motion = new Vec3d(x, y, z).normalize().scale(0.25F).scale(this.getSize());
			
			this.addVelocity(motion.x, motion.y, motion.z);
		}
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setColor(MathHelper.clamp(compound.getInt("JellyColor"), 0, 2));
		this.huntingCooldown = compound.getInt("HuntingCooldown");
		this.setSize(compound.getFloat("Size"), false);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("JellyColor", this.getColor());
		compound.putInt("HuntingCooldown", this.huntingCooldown);
		compound.putFloat("Size", this.getSize());
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		boolean updateSize = false;
		float size = this.getRNG().nextFloat() < 0.85F ? 1.0F : this.getRNG().nextBoolean() ? 0.5F : 0.65F;
		int color = this.getRNG().nextInt(3);
		if(dataTag != null && this.isFromBucket()) {
			
		} else {
			if(spawnDataIn instanceof SpawnData) {
				size = ((SpawnData) spawnDataIn).size;
				color = ((SpawnData) spawnDataIn).color;
			} else {
				if(!this.isFromBucket()) {
					spawnDataIn = new SpawnData(size, color);
					updateSize = true;
				}
			}
		}
		
		this.setSize(size, updateSize);
		this.setColor(color);
		return spawnDataIn;
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		float cooldownChance = this.getSize() >= 1.0F ? 0.3F : this.getSize() < 0.5F ? 0.65F : 0.75F;
		ItemStack itemstack = player.getHeldItem(hand);
		Item item = itemstack.getItem();
		if(item == UAItems.PRISMARINE_ROD.get() && !this.hasCooldown()) {
			Random rand = new Random();
			if(this.isServerWorld() && rand.nextFloat() < cooldownChance) {
				this.setCooldown(20 * (rand.nextInt(16) + 15));
			}
			itemstack.shrink(1);
			player.addItemStackToInventory(getTorchByType(this.getJellyTorchType()));
			return true;
		}
		return super.processInteract(player, hand);
	}
	
	@Override
	public EntitySize getSize(Pose pose) {
		return super.getSize(pose).scale(this.getSize());
	}
	
	public int getColor() {
		return this.dataManager.get(COLOR);
	}
	
	public void setColor(int color) {
		this.dataManager.set(COLOR, color);
	}
	
	public void setHuntingCooldown() {
		this.huntingCooldown = this.getRNG().nextInt(1600) + 1200;
	}
	
	public boolean hasHuntingCooldown() {
		return this.huntingCooldown > 0;
	}
	
	public float getSize() {
		return this.dataManager.get(SIZE);
	}
	
	public void setSize(float size, boolean updateHealth) {
		this.dataManager.set(SIZE, size);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D * size);
		if(updateHealth) {
			this.setHealth(this.getMaxHealth());
		}
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize size) {
		return size.height * 0.5F;
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.JELLYFISH_BUCKET.get());
	}
	
	@Override
	protected void setBucketData(ItemStack bucket) {
		if(this.hasCustomName()) {
			bucket.setDisplayName(this.getCustomName());
		}
		BoxJellyfishBucketData data = new BoxJellyfishBucketData(this.getColor(), this.getSize());
		bucket.getOrCreateTag().put("JellyfishTag", BoxJellyfishBucketData.write(data));
	}

	@Override
	public String getBucketName() {
		switch(this.getColor()) {
			default:
			case 0: 
				return "box";
			case 1:
				return "red_box";
			case 2:
				return "white_box";
		}
	}

	@Override
	public JellyTorchType getJellyTorchType() {
		switch(this.getColor()) {
			default:
			case 0: 
				return JellyTorchType.BLUE;
			case 1:
				return JellyTorchType.RED;
			case 2:
				return JellyTorchType.WHITE;
		}
	}
	
	@Override
	public boolean stingEntity(LivingEntity livingEntity) {
		if(super.stingEntity(livingEntity)) {
			livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, 600, 1));
			return true;
		}
		return false;
	}
	
	@Override
	public void readBucketData(CompoundNBT compound) {
		BoxJellyfishBucketData jellyData = BoxJellyfishBucketData.read(compound);
		this.tempBucketData = jellyData;
		this.setColor(jellyData.color);
		this.setSize(jellyData.size, true);
	}
	
	@Override
	public Endimation[] getEndimations() {
		return new Endimation[] {
			SWIM_ANIMATION,
			BOOST_ANIMATION
		};
	}

	@Override
	public RotationController getRotationController() {
		return this.rotationController;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}
	
	class SpawnData implements ILivingEntityData {
		public final float size;
		public final int color;

		public SpawnData(float size, int color) {
			this.size = size;
			this.color = color;
		}
	}
	
	public static class BoxJellyfishBucketData extends BucketData {
		private final int color;
		private final float size;
		
		public BoxJellyfishBucketData(int color, float size) {
			super("box_jellyfish");
			this.color = color;
			this.size = size;
		}
		
		public static BoxJellyfishBucketData read(CompoundNBT compound) {
			return new BoxJellyfishBucketData(compound.getInt("Color"), compound.getFloat("Size"));
		}
		
		public static CompoundNBT write(BoxJellyfishBucketData data) {
			CompoundNBT compound = new CompoundNBT();
			compound.putString("EntityId", data.entityId);
			compound.putInt("Color", data.color);
			compound.putFloat("Size", data.size);
			return compound;
		}
	}
}