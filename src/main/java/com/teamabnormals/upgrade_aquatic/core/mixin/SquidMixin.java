package com.teamabnormals.upgrade_aquatic.core.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Squid.class)
public abstract class SquidMixin extends Entity {

	private SquidMixin(EntityType<?> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(at = @At("HEAD"), method = "spawnInk")
	private void spawnInk(CallbackInfo info) {
		for (LivingEntity entity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().expandTowards(2.5F, 2.5F, 2.5F))) {
			if (!(entity instanceof Squid))
				entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
		}
	}

}
