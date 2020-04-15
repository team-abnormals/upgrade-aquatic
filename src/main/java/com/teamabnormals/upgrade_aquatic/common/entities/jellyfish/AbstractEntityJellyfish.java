package com.teamabnormals.upgrade_aquatic.common.entities.jellyfish;

import java.util.Map;
import java.util.function.Predicate;

import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.api.endimator.Endimation;
import com.teamabnormals.upgrade_aquatic.api.endimator.entity.IEndimatedEntity;
import com.teamabnormals.upgrade_aquatic.api.entity.EntityBucketableWaterMob;
import com.teamabnormals.upgrade_aquatic.api.util.MathUtil;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.common.network.MessageRotateJellyfish;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UADamageSources;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.PooledMutable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * @author SmellyModder(Luke Tonon)
 */
public abstract class AbstractEntityJellyfish extends EntityBucketableWaterMob implements IEndimatedEntity {
	private static final Predicate<LivingEntity> CAN_STING = (entity) -> {
		if(entity instanceof PlayerEntity) {
			return !entity.isSpectator() && !((PlayerEntity) entity).isCreative();
		}
		return !entity.isSpectator() && !(entity instanceof AbstractEntityJellyfish);
	};
	protected static final DataParameter<Integer> COOLDOWN = EntityDataManager.createKey(AbstractEntityJellyfish.class, DataSerializers.VARINT);
	private Endimation playingEndimation = BLANK_ANIMATION;
	public BucketData tempBucketData;
	private int animationTick;
	public float[] lockedRotations = new float[2];
	
	public AbstractEntityJellyfish(EntityType<? extends AbstractEntityJellyfish> type, World world) {
		super(type, world);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(COOLDOWN, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.endimateTick();
		this.getRotationController().tick();
		
		if(this.hasCooldown()) {
			if(this.isServerWorld()) this.setCooldown(this.getCooldown() - 1);
			
			if(this.world.isRemote && this.world.getGameTime() % 4 == 0) {
				for(int i = 0; i < 2; i++) {
					this.world.addParticle(BlockJellyTorch.getBlobParticleType(this.getJellyTorchType()), this.getPosXRandom(0.5D), this.getPosY() + this.getEyeHeight(), this.getPosZRandom(0.5D), MathUtil.makeNegativeRandomly(this.rand.nextDouble() * 0.05F, this.getRNG()), -this.rand.nextDouble() * 0.05F, MathUtil.makeNegativeRandomly(this.rand.nextDouble() * 0.05F, this.getRNG()));
				}
			}
		}
		
		if(this.isAlive()) {
			for(LivingEntity entities : this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(0.15D), CAN_STING)) {
				if(entities.isAlive()) {
					this.stingEntity(entities);
				}
			}
			
			if(this.isInWater() && this.isNoEndimationPlaying()) {
				this.setMotion(this.getMotion().subtract(0.0F, 0.005F, 0.0F));
			}
		}
	}
	
	@Override
	public void travel(Vec3d vec3d) {
		if(this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.01F, vec3d);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.75D));
		} else {
			super.travel(vec3d);
		}
	}
	
	public boolean willBeBoostedOutOfWater(float yaw, float pitch) {
		float xMotion = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
		float yMotion = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
		float zMotion = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
		
		Vec3d motion = new Vec3d(xMotion, yMotion, zMotion).normalize().scale(0.55F);
		if(this.world.isAirBlock(new BlockPos(this.getPositionVec().add(motion)))) {
			return true;
		}
		
		if(pitch <= 75.0F && pitch >= -75.0F) {
			BlockPos pos = this.getPosition();
			PooledMutable blockpos = PooledMutable.retain();
			for(int x = pos.getX() - 1; x < pos.getX() + 2; x++) {
				for(int y = pos.getY(); y < pos.getY() + 2; y++) {
					for(int z = pos.getZ() - 1; z < pos.getZ() + 2; z++) {
						blockpos.setPos(x, y, z);
						if(this.world.isAirBlock(blockpos.up()) && this.world.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
							return true;
						}
					}
				}
			}
		} else if(pitch <= 180.0F && (pitch >= -180.0F && pitch < -75.0F)) {
			BlockPos pos = this.getPosition();
			PooledMutable blockpos = PooledMutable.retain();
			for(int x = pos.getX() - 1; x < pos.getX() + 2; x++) {
				for(int y = pos.getY(); y < pos.getY() - 2; y++) {
					for(int z = pos.getZ() - 1; z < pos.getZ() + 2; z++) {
						blockpos.setPos(x, y, z);
						if(this.world.isAirBlock(blockpos.down()) && this.world.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setCooldown(compound.getInt("CooldownTicks"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("CooldownTicks", this.getCooldown());
	}
	
	public int getCooldown() {
		return this.dataManager.get(COOLDOWN);
	}
	
	public void setCooldown(int ticks) {
		this.dataManager.set(COOLDOWN, ticks);
	}
	
	public boolean hasCooldown() {
		return this.getCooldown() > 0;
	}
	
	@Override
	public Endimation getPlayingEndimation() {
		return this.playingEndimation;
	}

	@Override
	public int getAnimationTick() {
		return this.animationTick;
	}

	@Override
	public void setAnimationTick(int animationTick) {
		this.animationTick = animationTick;
	}

	@Override
	public void setPlayingEndimation(Endimation endimationToPlay) {
		this.onEndimationEnd(this.playingEndimation);
		this.playingEndimation = endimationToPlay;
		this.setAnimationTick(0);
	}
	
	public abstract RotationController getRotationController();
	
	public abstract void readBucketData(CompoundNBT compound);
	
	public abstract String getBucketName();
	
	public abstract JellyTorchType getJellyTorchType();
	
	public void stingEntity(LivingEntity livingEntity) {
		livingEntity.attackEntityFrom(UADamageSources.causeJellyfishDamage(this), 2.0F);
	}
	
	public static ItemStack getTorchByType(JellyTorchType type) {
		switch(type) {
			default:
			case RED:
				return new ItemStack(UABlocks.RED_JELLY_TORCH.get());
			case ORANGE:
				return new ItemStack(UABlocks.ORANGE_JELLY_TORCH.get());
			case YELLOW:
				return new ItemStack(UABlocks.YELLOW_JELLY_TORCH.get());
			case GREEN:
				return new ItemStack(UABlocks.GREEN_JELLY_TORCH.get());
				case BLUE:
				return new ItemStack(UABlocks.BLUE_JELLY_TORCH.get());
			case PINK:
				return new ItemStack(UABlocks.PINK_JELLY_TORCH.get());
			case PURPLE:
				return new ItemStack(UABlocks.PURPLE_JELLY_TORCH.get());
			case WHITE:
				return new ItemStack(UABlocks.WHITE_JELLY_TORCH.get());
		}
	}
	
	public static final Map<Class<? extends AbstractEntityJellyfish>, Float> IDS = Util.make(Maps.newHashMap(), (entries) -> {
		entries.put(EntityBoxJellyfish.class, 1.0F);
	});
	
	public static class RotationController {
		private AbstractEntityJellyfish jellyfish;
		private float prevYaw, yaw, startingYaw;
		private float prevPitch, pitch, startingPitch;
		private float setYaw, setPitch;
		private int tickLength;
		private int ticksSinceNotRotating;
		public boolean rotating;
		
		RotationController(AbstractEntityJellyfish jellyfish) {
			this.jellyfish = jellyfish;
		}
		
		protected void tick() {
			this.prevYaw = this.yaw;
			this.prevPitch = this.pitch;
			
			if(!this.rotating) {
				this.ticksSinceNotRotating++;
				
				if(this.ticksSinceNotRotating > 5) {
					if(this.setYaw != 0.0F) {
						this.startingYaw = this.yaw;
					}
				
					if(this.setPitch != 0.0F) {
						this.startingPitch = this.pitch;
					}
					
					this.setYaw = 0.0F;
					this.setPitch = 0.0F;
					this.tickLength = 20;
				}
			}
			
			this.yaw = this.clamp((this.setYaw - this.startingYaw) <= 0, this.yaw + ((this.setYaw - this.startingYaw) / this.tickLength), this.startingYaw, this.setYaw);
			this.pitch = this.clamp((this.setPitch - this.startingPitch) <= 0, this.pitch + ((this.setPitch - this.startingPitch) / this.tickLength), this.startingPitch, this.setPitch);
			
			this.rotating = false;
		}
		
		private float clamp(boolean invert, float num, float min, float max) {
			if(invert) {
				return num > max ? num : max;
			} else {
				if(num < min) {
					return min;
				} else {
					return num > max ? max : num;
				}
			}
		}
		
		public void rotate(float yaw, float pitch, int tickLength) {
			if(this.setYaw != yaw) {
				this.startingYaw = this.yaw;
			}
			
			if(this.setPitch != pitch) {
				this.startingPitch = this.pitch;
			}
			
			this.setYaw = yaw;
			this.setPitch = pitch;
			this.tickLength = tickLength;
			this.rotating = true;
			this.ticksSinceNotRotating = 0;
			
			if(!this.jellyfish.world.isRemote) {
				UpgradeAquatic.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.jellyfish), new MessageRotateJellyfish(this.jellyfish.getEntityId(), tickLength, yaw, pitch));
			}
		}
		
		public float[] getRotations(float ptc) {
			return new float[] {MathHelper.lerp(ptc, this.prevYaw, this.yaw), MathHelper.lerp(ptc, this.prevPitch, this.pitch)}; 
		}
	}
	
	public static class BucketData {
		public final String entityId;
		
		public BucketData(String entityId) {
			this.entityId = entityId;
		}
		
		public static BucketData read(CompoundNBT compound) {
			return new BucketData(compound.getString("EntityId"));
		}
		
		public static CompoundNBT write(BucketData data) {
			CompoundNBT compound = new CompoundNBT();
			compound.putString("EntityId", data.entityId);
			return compound;
		}
	}
}