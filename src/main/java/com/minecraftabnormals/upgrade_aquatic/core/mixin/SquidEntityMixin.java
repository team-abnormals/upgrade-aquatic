package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Squid.class)
public abstract class SquidEntityMixin extends Entity {

	private SquidEntityMixin(EntityType<?> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(at = @At("HEAD"), method = "spawnInk")
	private void squirtInk(CallbackInfo info) {
		GlowingInkItem.createEffectCloud(new MobEffectInstance(MobEffects.BLINDNESS, 100), this.level, this.getBoundingBox().expandTowards(2.5F, 2.5F, 2.5F));
	}
}
