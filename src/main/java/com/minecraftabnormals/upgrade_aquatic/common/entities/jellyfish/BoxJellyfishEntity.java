package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.JellyTorchBlock.JellyTorchType;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.BoxJellyfishHuntGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishBoostGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishSwinIntoDirectionGoal;
import com.minecraftabnormals.abnormals_core.common.entity.ai.PredicateAttackGoal;
import com.minecraftabnormals.abnormals_core.core.library.endimator.Endimation;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class BoxJellyfishEntity extends AbstractJellyfishEntity {
	public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(BoxJellyfishEntity.class, DataSerializers.VARINT);
	public static final DataParameter<Float> SIZE = EntityDataManager.createKey(BoxJellyfishEntity.class, DataSerializers.FLOAT);
	public static final Endimation SWIM_ANIMATION = new Endimation(20);
	public static final Endimation BOOST_ANIMATION = new Endimation(20);
	private RotationController rotationController;
	private int huntingCooldown;

	public BoxJellyfishEntity(EntityType<? extends BoxJellyfishEntity> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
    	return MobEntity.func_233666_p_().
    			func_233815_a_(Attributes.ATTACK_DAMAGE, 5.0D);
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
		this.goalSelector.addGoal(2, new JellyfishSwinIntoDirectionGoal(this, SWIM_ANIMATION));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, BOOST_ANIMATION));
		
		this.targetSelector.addGoal(1, new PredicateAttackGoal<>(this, AbstractFishEntity.class, 150, true, true, null, owner -> !((BoxJellyfishEntity) owner).hasCooldown() && !((BoxJellyfishEntity) owner).hasHuntingCooldown()));
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
		
		if(this.hasHuntingCooldown()) this.huntingCooldown--;
		
		if(this.isEndimationPlaying(BOOST_ANIMATION) && this.isInWater()) {
			this.setMotion(this.getMotion().scale(1.15F));
		}
	}
	
	@Override
	public void onEndimationStart(Endimation endimation) {
		if(endimation == SWIM_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.6F, this.getSize());
		} else if(endimation == BOOST_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.25F, this.getSize());
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
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D * size);
		if(updateHealth) {
			this.setHealth(this.getMaxHealth());
		}
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize size) {
		return size.height * 0.5F;
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
	public float getCooldownChance() {
		return this.getSize() >= 1.0F ? 0.05F : this.getSize() < 0.5F ? 0.1F : 0.15F;
	}
	
	@Override
	public boolean stingEntity(LivingEntity livingEntity) {
		if(super.stingEntity(livingEntity)) {
			livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, 600, 1));
			if(this.getAttackTarget() == null) {
				this.setAttackTarget(livingEntity);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(super.attackEntityFrom(source, amount)) {
			Entity entity = source.getTrueSource();
			if(entity instanceof LivingEntity && this.getAttackTarget() == null && !((LivingEntity) entity).isSpectator()) {
				if(!(entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative())) {
					this.setAttackTarget((LivingEntity) entity);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void readBucketData(CompoundNBT compound) {
		BoxJellyfishBucketData jellyData = BoxJellyfishBucketData.read(compound);
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