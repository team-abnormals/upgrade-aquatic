package com.minecraftabnormals.upgrade_aquatic.common.entities;

import com.minecraftabnormals.abnormals_core.core.api.IBucketableEntity;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GlowSquidEntity extends SquidEntity implements IBucketableEntity {

	public GlowSquidEntity(EntityType<? extends GlowSquidEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.GLOW_SQUID_SPAWN_EGG.get());
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.GLOW_SQUID_BUCKET.get());
	}

	@Override
	public void push(Entity entityIn) {
		super.push(entityIn);
		if (entityIn instanceof LivingEntity) {
			((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.NIGHT_VISION, 600));
		}
	}

	@Override
	protected void spawnInk() {
		GlowingInkItem.createEffectCloud(new EffectInstance(Effects.NIGHT_VISION, 300, 1), this.level, this.getBoundingBox().expandTowards(2.5F, 2.5F, 2.5F));
		this.playSound(SoundEvents.SQUID_SQUIRT, this.getSoundVolume(), this.getVoicePitch());
		Vector3d vector3d = this.rotateVector(new Vector3d(0.0D, -1.0D, 0.0D)).add(this.getX(), this.getY(), this.getZ());

		for (int i = 0; i < 30; ++i) {
			Vector3d vector3d1 = this.rotateVector(new Vector3d((double) this.random.nextFloat() * 0.6D - 0.3D, -1.0D, (double) this.random.nextFloat() * 0.6D - 0.3D));
			Vector3d vector3d2 = vector3d1.scale(0.3D + (double) (this.random.nextFloat() * 2.0F));
			((ServerWorld) this.level).sendParticles(UAParticles.GLOW_SQUID_INK.get(), vector3d.x, vector3d.y + 0.5D, vector3d.z, 0, vector3d2.x, vector3d2.y, vector3d2.z, (double) 0.1F);
		}
	}

	private Vector3d rotateVector(Vector3d vec3d) {
		Vector3d vector3d = vec3d.xRot(this.xBodyRotO * ((float) Math.PI / 180F));
		return vector3d.yRot(-this.yBodyRotO * ((float) Math.PI / 180F));
	}
}
