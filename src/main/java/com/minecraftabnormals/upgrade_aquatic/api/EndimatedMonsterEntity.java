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
		this.experienceValue = 5;
	}

	public SoundCategory getSoundCategory() {
		return SoundCategory.HOSTILE;
	}

	public void livingTick() {
		this.updateArmSwingProgress();
		this.func_213623_ec();
		super.livingTick();
	}

	protected void func_213623_ec() {
		float f = this.getBrightness();
		if (f > 0.5F) {
			this.idleTime += 2;
		}
	}

	public void tick() {
		super.tick();
		if (!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL) {
			this.remove();
		}
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_HOSTILE_SWIM;
	}

	protected SoundEvent getSplashSound() {
		return SoundEvents.ENTITY_HOSTILE_SPLASH;
	}

	public boolean attackEntityFrom(DamageSource source, float amount) {
		return this.isInvulnerableTo(source) ? false : super.attackEntityFrom(source, amount);
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_HOSTILE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_HOSTILE_DEATH;
	}

	protected SoundEvent getFallSound(int heightIn) {
		return heightIn > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
	}

	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
		return 0.5F - worldIn.getBrightness(pos);
	}

	public static boolean isValidLightLevel(IServerWorld p_223323_0_, BlockPos p_223323_1_, Random p_223323_2_) {
		if (p_223323_0_.getLightFor(LightType.SKY, p_223323_1_) > p_223323_2_.nextInt(32)) {
			return false;
		} else {
			int i = p_223323_0_.getWorld().isThundering() ? p_223323_0_.getNeighborAwareLightSubtracted(p_223323_1_, 10) : p_223323_0_.getLight(p_223323_1_);
			return i <= p_223323_2_.nextInt(8);
		}
	}

	protected boolean isDespawnPeaceful() {
		return true;
	}

	protected boolean canDropLoot() {
		return true;
	}

	protected boolean func_230282_cS_() {
		return true;
	}

	public boolean func_230292_f_(PlayerEntity playerIn) {
		return true;
	}

	public ItemStack findAmmo(ItemStack shootable) {
		if (shootable.getItem() instanceof ShootableItem) {
			Predicate<ItemStack> predicate = ((ShootableItem)shootable.getItem()).getAmmoPredicate();
			ItemStack itemstack = ShootableItem.getHeldAmmo(this, predicate);
			return itemstack.isEmpty() ? new ItemStack(Items.ARROW) : itemstack;
		} else {
			return ItemStack.EMPTY;
		}
	}
	
}