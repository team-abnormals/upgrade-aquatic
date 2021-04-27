package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public final class EntityMixin {

	@Inject(at = @At("HEAD"), method = "isInvulnerableTo", cancellable = true)
	private void preventThrasherSuffocationDamage(DamageSource source, CallbackInfoReturnable<Boolean> info) {
		if (source == DamageSource.IN_WALL) {
			Entity entity = ((Entity) (Object) this);
			if (entity.isAlive() && entity.isEntityInsideOpaqueBlock() && entity.getRidingEntity() instanceof ThrasherEntity) {
				info.setReturnValue(true);
			}
		}
	}

}
