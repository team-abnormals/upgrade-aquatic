package com.teamabnormals.upgrade_aquatic.core.mixin;

import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Shadow
	public abstract DamageSources damageSources();

	@Inject(at = @At("HEAD"), method = "isInvulnerableTo", cancellable = true)
	private void preventThrasherSuffocationDamage(DamageSource source, CallbackInfoReturnable<Boolean> info) {
		if (source == this.damageSources().inWall()) {
			Entity entity = ((Entity) (Object) this);
			if (entity.isAlive() && entity.isInWall() && entity.getVehicle() instanceof Thrasher) {
				info.setReturnValue(true);
			}
		}
	}
}