package com.teamabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.teamabnormals.abnormals_core.core.library.endimator.Endimation;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai.CassiopeaHideInSeagrassGoal;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai.CassiopeaJellyfishFlipGoal;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishBoostGoal;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishSwinIntoDirectionGoal;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UADamageSources;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class EntityCassiopeaJellyfish extends AbstractEntityJellyfish {
	public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntityCassiopeaJellyfish.class, DataSerializers.VARINT);
	public static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityCassiopeaJellyfish.class, DataSerializers.FLOAT);
	public static final Endimation SWIM_ANIMATION = new Endimation(20);
	public static final Endimation BOOST_ANIMATION = new Endimation(20);
	private RotationController rotationController;
	public int upsideDownCooldown;
	public int hideCooldown;
	
	public EntityCassiopeaJellyfish(EntityType<? extends AbstractEntityJellyfish> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
    	return MobEntity.func_233666_p_().
    			func_233815_a_(Attributes.ATTACK_DAMAGE, 1.0D);
    }
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(COLOR, 0);
		this.dataManager.register(SIZE, 0.85F);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new CassiopeaHideInSeagrassGoal(this));
		this.goalSelector.addGoal(2, new CassiopeaJellyfishFlipGoal(this));
		this.goalSelector.addGoal(2, new JellyfishSwinIntoDirectionGoal(this, SWIM_ANIMATION));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, BOOST_ANIMATION));
		
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
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
		
		if(this.hasUpsideDownCooldown()) {
			this.upsideDownCooldown--;
		}
		
		if(this.hasHideCooldown()) {
			this.hideCooldown--;
		}
		
		if(this.isEndimationPlaying(BOOST_ANIMATION) && this.isInWater()) {
			this.setMotion(this.getMotion().scale(1.15F));
		}
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		boolean updateSize = false;
		float size = this.getRNG().nextFloat() < 0.9F ? 1.0F : 0.425F;
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
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setColor(MathHelper.clamp(compound.getInt("JellyColor"), 0, 2));
		this.setSize(compound.getFloat("Size"), false);
		this.upsideDownCooldown = compound.getInt("UpsideDownCooldown");
		this.hideCooldown = compound.getInt("HideCooldown");
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("JellyColor", this.getColor());
		compound.putFloat("Size", this.getSize());
		compound.putInt("UpsideDownCooldown", this.upsideDownCooldown);
		compound.putInt("HideCooldown", this.hideCooldown);
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
	
	public float getSize() {
		return this.dataManager.get(SIZE);
	}
	
	public void setSize(float size, boolean updateHealth) {
		this.dataManager.set(SIZE, size);
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(6.0D * size);
		if(updateHealth) {
			this.setHealth(this.getMaxHealth());
		}
	}
	
	public boolean hasUpsideDownCooldown() {
		return this.upsideDownCooldown > 0;
	}
	
	public boolean hasHideCooldown() {
		return this.hideCooldown > 0;
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize size) {
		return size.height * 0.5F;
	}

	@Override
	public Endimation[] getEndimations() {
		return new Endimation[] {
			BOOST_ANIMATION,
			SWIM_ANIMATION
		};
	}
	
	@Override
	public void onEndimationStart(Endimation endimation) {
		float sizeForce = this.getSize() < 0.6F ? 0.85F : this.getSize();
		if(endimation == SWIM_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.3F, sizeForce);
		} else if(endimation == BOOST_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.2F, sizeForce);
		}
	}

	@Override
	public RotationController getRotationController() {
		return this.rotationController;
	}
	
	@Override
	protected void setBucketData(ItemStack bucket) {
		if(this.hasCustomName()) {
			bucket.setDisplayName(this.getCustomName());
		}
		CassiopeaJellyfishData data = new CassiopeaJellyfishData(this.getColor(), this.getSize());
		bucket.getOrCreateTag().put("JellyfishTag", CassiopeaJellyfishData.write(data));
	}

	@Override
	public void readBucketData(CompoundNBT compound) {
		CassiopeaJellyfishData jellyData = CassiopeaJellyfishData.read(compound);
		this.setColor(jellyData.color);
		this.setSize(jellyData.size, true);
	}

	@Override
	public String getBucketName() {
		switch(this.getColor()) {
			default:
			case 0: 
				return "cassiopea";
			case 1:
				return "blue_cassiopea";
			case 2:
				return "white_cassiopea";
		}
	}

	@Override
	public JellyTorchType getJellyTorchType() {
		switch(this.getColor()) {
			default:
			case 0: 
				return JellyTorchType.GREEN;
			case 1:
				return JellyTorchType.BLUE;
			case 2:
				return JellyTorchType.WHITE;
		}
	}
	
	@Override
	public float getCooldownChance() {
		return this.getSize() < 1.0F ? 0.9F : 0.8F;
	}
	
	@Override
	public boolean stingEntity(LivingEntity livingEntity) {
		if((this.getAttackTarget() == livingEntity || this.getRevengeTarget() == livingEntity) && this.getRNG().nextFloat() < 0.5F) {
			return livingEntity.attackEntityFrom(UADamageSources.causeJellyfishDamage(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
		}
		return false;
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
	
	public static class CassiopeaJellyfishData extends BucketData {
		private final int color;
		private final float size;
		
		public CassiopeaJellyfishData(int color, float size) {
			super("cassiopea_jellyfish");
			this.color = color;
			this.size = size;
		}
		
		public static CassiopeaJellyfishData read(CompoundNBT compound) {
			return new CassiopeaJellyfishData(compound.getInt("Color"), compound.getFloat("Size"));
		}
		
		public static CompoundNBT write(CassiopeaJellyfishData data) {
			CompoundNBT compound = new CompoundNBT();
			compound.putString("EntityId", data.entityId);
			compound.putInt("Color", data.color);
			compound.putFloat("Size", data.size);
			return compound;
		}
	}
}