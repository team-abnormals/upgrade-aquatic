package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

@Mixin(SquidEntity.class)
public abstract class SquidEntityMixin extends Entity {

	private SquidEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(at = @At("HEAD"), method = "squirtInk", cancellable = true)
	private void squirtInk(CallbackInfo info) {
		GlowingInkItem.createEffectCloud(Effects.BLINDNESS, this.world, this.getBoundingBox().expand(2.5F, 2.5F, 2.5F));
	}

}
