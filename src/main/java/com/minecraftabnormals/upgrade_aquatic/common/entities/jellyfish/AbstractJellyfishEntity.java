package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import java.util.Random;
import java.util.function.Predicate;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.JellyTorchBlock.JellyTorchType;
import com.minecraftabnormals.upgrade_aquatic.common.network.RotateJellyfishMessage;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.other.UADamageSources;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.abnormals_core.common.entity.BucketableWaterMobEntity;
import com.teamabnormals.abnormals_core.core.library.endimator.Endimation;
import com.teamabnormals.abnormals_core.core.library.endimator.entity.IEndimatedEntity;
import com.teamabnormals.abnormals_core.core.utils.MathUtils;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * @author SmellyModder(Luke Tonon)
 */
public abstract class AbstractJellyfishEntity extends BucketableWaterMobEntity implements IEndimatedEntity {
	private static final Predicate<LivingEntity> CAN_STING = (entity) -> {
		if (entity instanceof PlayerEntity) {
			return !entity.isSpectator() && !((PlayerEntity) entity).isCreative();
		}
		return !entity.isSpectator() && !(entity instanceof AbstractJellyfishEntity || entity instanceof TurtleEntity);
	};
	public static final Endimation SWIM_ANIMATION = new Endimation(20);
	public static final Endimation BOOST_ANIMATION = new Endimation(20);
	protected static final DataParameter<Integer> COOLDOWN = EntityDataManager.createKey(AbstractJellyfishEntity.class, DataSerializers.VARINT);
	private Endimation playingEndimation = BLANK_ANIMATION;
	private int animationTick;
	public float[] lockedRotations = new float[2];
	
	public AbstractJellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, World world) {
		super(type, world);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
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
		
		this.rotationYaw = this.rotationYawHead = this.renderYawOffset = 0;
		
		if (this.isServerWorld()) {
			if (!this.isPassenger()) {
				this.getRotationController().rotate(this.lockedRotations[0], this.lockedRotations[1], 25);
			} else {
				this.getRotationController().rotate(0.0F, 0.0F, 1);
				this.setAir(this.getMaxAir());
			}
		}
		
		if (this.hasCooldown()) {
			if (this.isServerWorld()) this.setCooldown(this.getCooldown() - 1);
			
			if (this.world.isRemote && this.world.getGameTime() % 4 == 0) {
				for (int i = 0; i < 2; i++) {
					this.world.addParticle(this.getJellyTorchType().blobParticle.get(), this.getPosXRandom(0.5D), this.getPosY() + this.getEyeHeight(), this.getPosZRandom(0.5D), MathUtils.makeNegativeRandomly(this.rand.nextDouble() * 0.05F, this.getRNG()), -this.rand.nextDouble() * 0.05F, MathUtils.makeNegativeRandomly(this.rand.nextDouble() * 0.05F, this.getRNG()));
				}
			}
		}
		
		if (this.isAlive()) {
			for (LivingEntity entities : this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(0.15D), CAN_STING)) {
				if (entities.isAlive()) {
					this.stingEntity(entities);
				}
			}
			
			if (this.isInWater() && this.isNoEndimationPlaying()) {
				this.setMotion(this.getMotion().subtract(0.0F, 0.005F, 0.0F));
			}
		}
	}
	
	@Override
	public void travel(Vector3d Vector3d) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.01F, Vector3d);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.75D));
		} else {
			super.travel(Vector3d);
		}
	}
	
	public boolean willBeBoostedOutOfWater(float yaw, float pitch) {
		float xMotion = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
		float yMotion = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
		float zMotion = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
		
		Vector3d motion = new Vector3d(xMotion, yMotion, zMotion).normalize().scale(0.55F);
		if (this.world.isAirBlock(new BlockPos(this.getPositionVec().add(motion)))) {
			return true;
		}
		
		BlockPos pos = this.getPosition();
		BlockPos.Mutable blockpos = new BlockPos.Mutable();
		boolean up = pitch <= 75.0F && pitch >= -75.0F;
		int yOffset = up ? pos.getY() + 2 : pos.getY() - 2;
		for (int x = pos.getX() - 1; x < pos.getX() + 2; x++) {
			for (int y = pos.getY(); (up ? y < yOffset : y > yOffset); y++) {
				for (int z = pos.getZ() - 1; z < pos.getZ() + 2; z++) {
					blockpos.setPos(x, y, z);
					if (this.world.isAirBlock(blockpos.up()) && this.world.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public PathNavigator getNavigator() {
		return new SwimmerPathNavigator(this, this.world);
	}
	
	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		Item item = itemstack.getItem();
		if (itemstack.isEmpty() && this.getName().getString().toLowerCase().trim().equals("jellysox345")) {
			this.startRiding(player);
			return ActionResultType.SUCCESS;
		} else if (item == UAItems.PRISMARINE_ROD.get() && !this.hasCooldown()) {
			Random rand = new Random();
			if (this.isServerWorld() && rand.nextFloat() < this.getCooldownChance()) {
				this.setCooldown(20 * (rand.nextInt(16) + 15));
			}
			itemstack.shrink(1);
			player.addItemStackToInventory(this.getTorchByType(this.getJellyTorchType()));
			return ActionResultType.SUCCESS;
		}
		return super.func_230254_b_(player, hand);
	}
	
	@Override
	protected void setBucketData(ItemStack bucket) {
		if (this.hasCustomName()) {
			bucket.setDisplayName(this.getCustomName());
		}
		bucket.getOrCreateTag().put("JellyfishTag", this.getBucketProcessor().write());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setCooldown(compound.getInt("CooldownTicks"));
		this.lockedRotations[0] = compound.getFloat("LockedYaw");
		this.lockedRotations[1] = compound.getFloat("LockedPitch");
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("CooldownTicks", this.getCooldown());
		compound.putFloat("LockedYaw", this.lockedRotations[0]);
		compound.putFloat("LockedPitch", this.lockedRotations[1]);
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
	

	@Override
	public Endimation[] getEndimations() {
		return new Endimation[] {
			SWIM_ANIMATION,
			BOOST_ANIMATION
		};
	}
	
	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.JELLYFISH_BUCKET.get());
	}
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.JELLYFISH_SPAWN_EGG.get());
	}
	
	public abstract RotationController getRotationController();
	
	public abstract BucketProcessor<?> getBucketProcessor();
	
	public abstract String getBucketName();
	
	public abstract JellyTorchType getJellyTorchType();
	
	public abstract float getCooldownChance();
		
	public int getIdSuffix() {
		return this.getJellyTorchType().ordinal();
	}
	
	protected boolean stingEntity(LivingEntity livingEntity) {
		return livingEntity.attackEntityFrom(UADamageSources.causeJellyfishDamage(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
	}
	
	public ITextComponent getYieldingTorchMessage() {
		JellyTorchType torchType = this.getJellyTorchType();
		return (new TranslationTextComponent("tooltip.upgrade_aquatic.yielding_jelly_torch").mergeStyle(TextFormatting.GRAY)).append((new TranslationTextComponent("tooltip.upgrade_aquatic." + torchType.toString().toLowerCase() + "_jelly_torch")).mergeStyle(torchType.color));
	}
	
	protected ItemStack getTorchByType(JellyTorchType type) {
		return new ItemStack(type.torch.get());
	}
	
	@SuppressWarnings("deprecation")
	public static <J extends AbstractJellyfishEntity> boolean defaultSpawnCondition(EntityType<J> entity, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
		return pos.getY() > 45 && pos.getY() < world.getSeaLevel();
	}
	
	public static class RotationController {
		private AbstractJellyfishEntity jellyfish;
		private float prevYaw, yaw, startingYaw;
		private float prevPitch, pitch, startingPitch;
		private float setYaw, setPitch;
		private int tickLength;
		private int ticksSinceNotRotating;
		public boolean rotating;
		
		RotationController(AbstractJellyfishEntity jellyfish) {
			this.jellyfish = jellyfish;
		}
		
		protected void tick() {
			this.prevYaw = this.yaw;
			this.prevPitch = this.pitch;
			
			if (!this.rotating) {
				this.ticksSinceNotRotating++;
				
				if (this.ticksSinceNotRotating > 5) {
					if (this.setYaw != 0.0F) this.startingYaw = this.yaw;
					if (this.setPitch != 0.0F) this.startingPitch = this.pitch;
					
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
			if (invert) {
				return num > max ? num : max;
			} else {
				if (num < min) {
					return min;
				} else {
					return num > max ? max : num;
				}
			}
		}
		
		public void rotate(float yaw, float pitch, int tickLength) {
			if (this.setYaw != yaw) this.startingYaw = this.yaw;
			if (this.setPitch != pitch) this.startingPitch = this.pitch;
			
			this.setYaw = yaw;
			this.setPitch = pitch;
			this.tickLength = tickLength;
			this.rotating = true;
			this.ticksSinceNotRotating = 0;
			
			if (!this.jellyfish.world.isRemote) {
				UpgradeAquatic.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.jellyfish), new RotateJellyfishMessage(this.jellyfish.getEntityId(), tickLength, yaw, pitch));
			}
		}
		
		public float[] getRotations(float ptc) {
			return new float[] {MathHelper.lerp(ptc, this.prevYaw, this.yaw), MathHelper.lerp(ptc, this.prevPitch, this.pitch)}; 
		}
		
		public void addVelocityForLookDirection(float force, float sizeScale) {
			float[] rotations = this.getRotations(1.0F);
			float yaw = (float) rotations[0] + this.jellyfish.rotationYaw;
			float pitch = (float) rotations[1] - 90.0F;
			
			float x = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
			float y = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
			float z = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
			
			Vector3d motion = new Vector3d(x, y, z).normalize().scale(force).scale(sizeScale);
			
			this.jellyfish.addVelocity(motion.x, motion.y, motion.z);
		}
	}
	
	public static class BucketProcessor<J extends AbstractJellyfishEntity> {
		private final String entityId;
		protected final J jellyfish;
		
		public BucketProcessor(String entityId, J jellyfish) {
			this.entityId = entityId;
			this.jellyfish = jellyfish;
		}
		
		public CompoundNBT write() {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putString("EntityId", this.entityId);
			return nbt;
		}
		
		public void read(CompoundNBT nbt) {}
	}
}