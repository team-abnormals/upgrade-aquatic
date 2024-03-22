package com.teamabnormals.upgrade_aquatic.common.entity.projectile;

import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;
import java.util.List;

public class SonarWave extends Entity {
	private static final EntityDataAccessor<Integer> OWNER_ID = SynchedEntityData.defineId(SonarWave.class, EntityDataSerializers.INT);
	private float growProgress = 0;
	private float prevGrowProgress = 0;

	public SonarWave(EntityType<? extends SonarWave> type, Level worldIn) {
		super(type, worldIn);
		this.blocksBuilding = true;
	}

	public SonarWave(Level worldIn, double x, double y, double z) {
		this(UAEntityTypes.SONAR_WAVE.get(), worldIn);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public SonarWave(PlayMessages.SpawnEntity spawnEntity, Level world) {
		this(UAEntityTypes.SONAR_WAVE.get(), world);
	}

	@Override
	protected void defineSynchedData() {
		this.getEntityData().define(OWNER_ID, 0);
	}

	@Override
	public void tick() {
		super.tick();

		this.move(MoverType.SELF, this.getDeltaMovement());

		if (this.getThrasherOwner() != null) {
			List<Entity> entities = this.level().getEntities(this.getThrasherOwner(), this.getBoundingBox().inflate(this.growProgress), Thrasher.ENEMY_MATCHER);

			for (Entity entity : entities) {
				if (entity instanceof LivingEntity && this.getThrasherOwner().getTarget() == null) {
					this.getThrasherOwner().setTarget((LivingEntity) entity);
				}
			}
		}

		Vec3 motion = this.getDeltaMovement();
		float horizontalMotionMagnitude = Mth.sqrt((float) motion.horizontalDistanceSqr());
		double motionX = motion.x();
		double motionY = motion.y();
		double motionZ = motion.z();

		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			this.setYRot((float) (Mth.atan2(motionX, motionZ) * (double) (180F / (float) Math.PI)));
			this.setXRot((float) (Mth.atan2(motionY, horizontalMotionMagnitude) * (double) (180F / (float) Math.PI)));
			this.yRotO = this.getYRot();
			this.xRotO = this.getXRot();
		}

		this.setYRot((float) (Mth.atan2(motionX, motionZ) * (double) (180F / (float) Math.PI)));

		for (this.setXRot((float) (Mth.atan2(motionY, horizontalMotionMagnitude) * (double) (180F / (float) Math.PI))); this.getXRot() - this.xRotO < -180.0F; this.xRotO -= 360.0F) {
		}

		while (this.getXRot() - this.xRotO >= 180.0F) {
			this.xRotO += 360.0F;
		}

		while (this.getYRot() - this.yRotO < -180.0F) {
			this.yRotO -= 360.0F;
		}

		while (this.getYRot() - this.yRotO >= 180.0F) {
			this.yRotO += 360.0F;
		}

		this.setXRot(Mth.lerp(0.2F, this.xRotO, this.getXRot()));
		this.setYRot(Mth.lerp(0.2F, this.yRotO, this.getYRot()));

		this.prevGrowProgress = this.growProgress;

		if (this.growProgress < 0.1F) {
			this.growProgress += 0.025F;
		} else {
			this.growProgress += 0.1F;
		}

		if (this.tickCount > 40) {
			this.discard();
		}
	}

	@Override
	protected void moveTowardsClosestSpace(double x, double y, double z) {
	}

	public void fireSonarWave(Thrasher thrasher) {
		float xMotion = -Mth.sin(thrasher.getYRot() * ((float) Math.PI / 180F)) * Mth.cos(thrasher.getXRot() * ((float) Math.PI / 180F));
		float yMotion = -Mth.sin(thrasher.getXRot() * ((float) Math.PI / 180F));
		float zMotion = Mth.cos(thrasher.getYRot() * ((float) Math.PI / 180F)) * Mth.cos(thrasher.getXRot() * ((float) Math.PI / 180F));

		Vec3 motion = new Vec3(xMotion, yMotion, zMotion).normalize().scale(0.75D);

		this.setDeltaMovement(motion);
		this.setOwnerId(thrasher.getId());
		this.setPos(thrasher.getX() + xMotion, thrasher.getY(), thrasher.getZ() + zMotion);

		float motionSqrt = Mth.sqrt((float) motion.horizontalDistanceSqr());
		this.setYRot((float) (Mth.atan2(motion.x, motion.z) * (180F / Math.PI)));
		this.setYRot((float) (Mth.atan2(motion.y, motionSqrt) * (180F / Math.PI)));
		this.yRotO = this.getYRot();
		this.xRotO = this.getXRot();
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		return true;
	}

	@Override
	public void onInsideBubbleColumn(boolean downwards) {
	}

	@Override
	public void onAboveBubbleCol(boolean downwards) {
	}

	@Override
	protected void doWaterSplashEffect() {
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	public float getGrowProgress() {
		return Mth.lerp(ClientInfo.getPartialTicks(), this.prevGrowProgress, this.growProgress);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		this.setOwnerId(compound.getInt("OwnerId"));
		this.growProgress = compound.getFloat("GrowProgress");
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putInt("OwnerId", this.getOwnerId());
		compound.putFloat("GrowProgress", this.growProgress);
	}

	public void setOwnerId(int id) {
		this.getEntityData().set(OWNER_ID, id);
	}

	public int getOwnerId() {
		return this.getEntityData().get(OWNER_ID);
	}

	@Nullable
	public Thrasher getThrasherOwner() {
		Entity owner = this.level().getEntity(this.getOwnerId());
		if (owner instanceof Thrasher) {
			return (Thrasher) owner;
		}
		return null;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}