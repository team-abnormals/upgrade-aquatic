package com.minecraftabnormals.upgrade_aquatic.api;

import com.minecraftabnormals.abnormals_core.core.endimator.entity.EndimatedEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import java.util.Random;
import java.util.function.Predicate;

public abstract class EndimatedMonsterEntity extends EndimatedEntity implements IMob {
	
	protected EndimatedMonsterEntity(EntityType<? extends EndimatedMonsterEntity> type, World p_i48553_2_) {
		super(type, p_i48553_2_);
		this.xpReward = 5;
	}

	public SoundCategory getSoundSource() {
		return SoundCategory.HOSTILE;
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
		return this.isInvulnerableTo(source) ? false : super.hurt(source, amount);
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

	public float getWalkTargetValue(BlockPos pos, IWorldReader worldIn) {
		return 0.5F - worldIn.getBrightness(pos);
	}

	public static boolean isValidLightLevel(IServerWorld p_223323_0_, BlockPos p_223323_1_, Random p_223323_2_) {
		if (p_223323_0_.getBrightness(LightType.SKY, p_223323_1_) > p_223323_2_.nextInt(32)) {
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

	public boolean isPreventingPlayerRest(PlayerEntity playerIn) {
		return true;
	}

	public ItemStack getProjectile(ItemStack shootable) {
		if (shootable.getItem() instanceof ShootableItem) {
			Predicate<ItemStack> predicate = ((ShootableItem)shootable.getItem()).getSupportedHeldProjectiles();
			ItemStack itemstack = ShootableItem.getHeldProjectile(this, predicate);
			return itemstack.isEmpty() ? new ItemStack(Items.ARROW) : itemstack;
		} else {
			return ItemStack.EMPTY;
		}
	}
	
}