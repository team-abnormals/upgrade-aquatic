package com.minecraftabnormals.upgrade_aquatic.common.entities;

import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.blueprint.core.api.IBucketableEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class GlowSquidEntity extends Squid implements IBucketableEntity {

	public GlowSquidEntity(EntityType<? extends GlowSquidEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
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
			((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600));
		}
	}

	@Override
	protected void spawnInk() {
		GlowingInkItem.createEffectCloud(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 1), this.level, this.getBoundingBox().expandTowards(2.5F, 2.5F, 2.5F));
		this.playSound(SoundEvents.SQUID_SQUIRT, this.getSoundVolume(), this.getVoicePitch());
		Vec3 vector3d = this.rotateVector(new Vec3(0.0D, -1.0D, 0.0D)).add(this.getX(), this.getY(), this.getZ());

		for (int i = 0; i < 30; ++i) {
			Vec3 vector3d1 = this.rotateVector(new Vec3((double) this.random.nextFloat() * 0.6D - 0.3D, -1.0D, (double) this.random.nextFloat() * 0.6D - 0.3D));
			Vec3 vector3d2 = vector3d1.scale(0.3D + (double) (this.random.nextFloat() * 2.0F));
			((ServerLevel) this.level).sendParticles(UAParticles.GLOW_SQUID_INK.get(), vector3d.x, vector3d.y + 0.5D, vector3d.z, 0, vector3d2.x, vector3d2.y, vector3d2.z, 0.1F);
		}
	}

	private Vec3 rotateVector(Vec3 vec3d) {
		Vec3 vector3d = vec3d.xRot(this.xBodyRotO * ((float) Math.PI / 180F));
		return vector3d.yRot(-this.yBodyRotO * ((float) Math.PI / 180F));
	}
}
