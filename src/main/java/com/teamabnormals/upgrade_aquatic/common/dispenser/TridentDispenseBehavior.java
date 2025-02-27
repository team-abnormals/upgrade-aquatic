package com.teamabnormals.upgrade_aquatic.common.dispenser;

import net.minecraft.core.BlockSource;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TridentDispenseBehavior extends AbstractProjectileDispenseBehavior {

	private boolean success = true;

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public ItemStack execute(BlockSource source, ItemStack stack) {
		if (stack.getDamageValue() < stack.getMaxDamage() - 1) {
			this.setSuccess(true);
			return super.execute(source, stack);
		} else {
			this.setSuccess(false);
			return stack;
		}
	}

	@Override
	protected Projectile getProjectile(Level level, Position pos, ItemStack itemStack) {
		ThrownTrident entity = new ThrownTrident(EntityType.TRIDENT, level);
		itemStack.hurt(1, level.random, (ServerPlayer) null);
		entity.tridentItem = itemStack.copy();
		entity.setPos(pos.x(), pos.y(), pos.z());
		entity.pickup = AbstractArrow.Pickup.ALLOWED;
		return entity;
	}

	@Override
	protected void playSound(BlockSource source) {
		if (this.isSuccess()) {
			source.getLevel().playSound((Player)null, source.getEntity().getBlockPos(), SoundEvents.TRIDENT_THROW, SoundSource.BLOCKS, 1.0F, 1.0F);
		} else {
			source.getLevel().levelEvent(1001, source.getPos(), 0);
		}
	}
}