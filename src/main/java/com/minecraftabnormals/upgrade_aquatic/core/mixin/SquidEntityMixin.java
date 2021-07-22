package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SquidEntity.class)
public abstract class SquidEntityMixin extends Entity {

	private SquidEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(at = @At("HEAD"), method = "spawnInk", cancellable = true)
	private void squirtInk(CallbackInfo info) {
		GlowingInkItem.createEffectCloud(new EffectInstance(Effects.BLINDNESS, 100), this.level, this.getBoundingBox().expandTowards(2.5F, 2.5F, 2.5F));
	}
}
