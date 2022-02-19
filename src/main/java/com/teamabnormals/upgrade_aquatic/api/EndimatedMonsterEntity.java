package com.teamabnormals.upgrade_aquatic.api;

import com.teamabnormals.blueprint.core.endimator.entity.EndimatedEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.*;

import java.util.Random;
import java.util.function.Predicate;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class EndimatedMonsterEntity extends EndimatedEntity implements Enemy {

	protected EndimatedMonsterEntity(EntityType<? extends EndimatedMonsterEntity> type, Level p_i48553_2_) {
		super(type, p_i48553_2_);
		this.xpReward = 5;
	}

	public SoundSource getSoundSource() {
		return SoundSource.HOSTILE;
	}

	public void aiStep() {
		this.updateSwingTime();
		this.updateNoActionTime();
		super.aiStep();
	}

	protected void updateNoActionTime() {
		float f = this.getBrightness();
		if (f > 0.5F) {
			this.noActionTime += 2;
		}
	}

	public void tick() {
		super.tick();
		if (!this.level.isClientSide && this.level.getDifficulty() == Difficulty.PEACEFUL) {
			this.remove();
		}
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.HOSTILE_SWIM;
	}

	protected SoundEvent getSwimSplashSound() {
		return SoundEvents.HOSTILE_SPLASH;
	}

	public boolean hurt(DamageSource source, float amount) {
		return !this.isInvulnerableTo(source) && super.hurt(source, amount);
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.HOSTILE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.HOSTILE_DEATH;
	}

	protected SoundEvent getFallDamageSound(int heightIn) {
		return heightIn > 4 ? SoundEvents.HOSTILE_BIG_FALL : SoundEvents.HOSTILE_SMALL_FALL;
	}

	public float getWalkTargetValue(BlockPos pos, LevelReader worldIn) {
		return 0.5F - worldIn.getBrightness(pos);
	}

	public static boolean isValidLightLevel(ServerLevelAccessor p_223323_0_, BlockPos p_223323_1_, Random p_223323_2_) {
		if (p_223323_0_.getBrightness(LightLayer.SKY, p_223323_1_) > p_223323_2_.nextInt(32)) {
			return false;
		} else {
			int i = p_223323_0_.getLevel().isThundering() ? p_223323_0_.getMaxLocalRawBrightness(p_223323_1_, 10) : p_223323_0_.getMaxLocalRawBrightness(p_223323_1_);
			return i <= p_223323_2_.nextInt(8);
		}
	}

	protected boolean shouldDespawnInPeaceful() {
		return true;
	}

	protected boolean shouldDropExperience() {
		return true;
	}

	protected boolean shouldDropLoot() {
		return true;
	}

	public boolean isPreventingPlayerRest(Player playerIn) {
		return true;
	}

	public ItemStack getProjectile(ItemStack shootable) {
		if (shootable.getItem() instanceof ProjectileWeaponItem) {
			Predicate<ItemStack> predicate = ((ProjectileWeaponItem) shootable.getItem()).getSupportedHeldProjectiles();
			ItemStack itemstack = ProjectileWeaponItem.getHeldProjectile(this, predicate);
			return itemstack.isEmpty() ? new ItemStack(Items.ARROW) : itemstack;
		} else {
			return ItemStack.EMPTY;
		}
	}

}