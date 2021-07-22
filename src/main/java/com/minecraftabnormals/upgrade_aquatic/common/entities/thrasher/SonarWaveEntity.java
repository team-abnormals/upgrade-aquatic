package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher;

import com.minecraftabnormals.abnormals_core.client.ClientInfo;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class SonarWaveEntity extends Entity {
	private static final DataParameter<Integer> OWNER_ID = EntityDataManager.defineId(SonarWaveEntity.class, DataSerializers.INT);
	private float growProgress = 0;
	private float prevGrowProgress = 0;
	
	public SonarWaveEntity(EntityType<? extends SonarWaveEntity> type, World worldIn) {
		super(type, worldIn);
		this.blocksBuilding = true;
	}
	
	public SonarWaveEntity(World worldIn, double x, double y, double z) {
		this(UAEntities.SONAR_WAVE.get(), worldIn);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}
	
	public SonarWaveEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		this(UAEntities.SONAR_WAVE.get(), world);
	}

	@Override
	protected void defineSynchedData() {
		this.getEntityData().define(OWNER_ID, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		this.move(MoverType.SELF, this.getDeltaMovement());
		
		if(this.getThrasherOwner() != null) {
			List<Entity> entities = this.level.getEntities(this.getThrasherOwner(), this.getBoundingBox().inflate(this.growProgress), ThrasherEntity.ENEMY_MATCHER);
			
			for(Entity entity : entities) {
				if(entity instanceof LivingEntity && this.getThrasherOwner().getTarget() == null) {
					this.getThrasherOwner().setTarget((LivingEntity) entity);
				}
			}
		}
		
		Vector3d motion = this.getDeltaMovement();
		float horizontalMotionMagnitude = MathHelper.sqrt(getHorizontalDistanceSqr(motion));
		double motionX = motion.x();
		double motionY = motion.y();
		double motionZ = motion.z();
        
		if(this.xRotO == 0.0F && this.yRotO == 0.0F) {
			this.yRot = (float) (MathHelper.atan2(motionX, motionZ) * (double)(180F / (float)Math.PI));
			this.xRot = (float) (MathHelper.atan2(motionY, horizontalMotionMagnitude) * (double)(180F / (float)Math.PI));
			this.yRotO = this.yRot;
			this.xRotO = this.xRot;
		}
		
		this.yRot = (float)(MathHelper.atan2(motionX, motionZ) * (double)(180F / (float)Math.PI));
		
		for(this.xRot = (float)(MathHelper.atan2(motionY, horizontalMotionMagnitude) * (double)(180F / (float)Math.PI)); this.xRot - this.xRotO < -180.0F; this.xRotO -= 360.0F) {
			;
		}

		while(this.xRot - this.xRotO >= 180.0F) {
			this.xRotO += 360.0F;
		}

		while(this.yRot - this.yRotO < -180.0F) {
			this.yRotO -= 360.0F;
		}

		while(this.yRot - this.yRotO >= 180.0F) {
			this.yRotO += 360.0F;
		}

		this.xRot = MathHelper.lerp(0.2F, this.xRotO, this.xRot);
		this.yRot = MathHelper.lerp(0.2F, this.yRotO, this.yRot);
		
		this.prevGrowProgress = this.growProgress;
		
		if(this.growProgress < 0.1F) {
			this.growProgress += 0.025F;
		} else {
			this.growProgress += 0.1F;
		}
		
		if(this.tickCount > 40) {
			this.remove();
		}
	}
	
	@Override
	protected void moveTowardsClosestSpace(double x, double y, double z) {}
	
	public void fireSonarWave(ThrasherEntity thrasher) {
		float xMotion = -MathHelper.sin(thrasher.yRot * ((float) Math.PI / 180F)) * MathHelper.cos(thrasher.xRot * ((float) Math.PI / 180F));
		float yMotion = -MathHelper.sin(thrasher.xRot * ((float) Math.PI / 180F));
		float zMotion = MathHelper.cos(thrasher.yRot * ((float) Math.PI / 180F)) * MathHelper.cos(thrasher.xRot * ((float) Math.PI / 180F));
		
		Vector3d motion = new Vector3d(xMotion, yMotion, zMotion).normalize().scale(0.75D);
		
		this.setDeltaMovement(motion);
		this.setOwnerId(thrasher.getId());
		this.setPos(thrasher.getX() + xMotion, thrasher.getY(), thrasher.getZ() + zMotion);
		
		float motionSqrt = MathHelper.sqrt(getHorizontalDistanceSqr(motion));
		this.yRot = (float) (MathHelper.atan2(motion.x, motion.z) * (180F / Math.PI));
		this.xRot = (float) (MathHelper.atan2(motion.y, motionSqrt) * (180F / Math.PI));
		this.yRotO = this.yRot;
		this.xRotO = this.xRot;
	}
	
	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		return true;
	}
	
	@Override
	public void onInsideBubbleColumn(boolean downwards) {}

	@Override
	public void onAboveBubbleCol(boolean downwards) {}
	
	@Override
	protected void doWaterSplashEffect() {}
	
	@Override
	public boolean isPushedByFluid() {
		return false;
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getGrowProgress() {
		return MathHelper.lerp(ClientInfo.getPartialTicks(), this.prevGrowProgress, this.growProgress);
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		this.setOwnerId(compound.getInt("OwnerId"));
		this.growProgress = compound.getFloat("GrowProgress");
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
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
	public ThrasherEntity getThrasherOwner() {
		Entity owner = this.level.getEntity(this.getOwnerId());
		if(owner instanceof ThrasherEntity) {
			return (ThrasherEntity) owner;
		}
		return null;
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}