package com.teamabnormals.upgrade_aquatic.core.mixin;

import com.teamabnormals.upgrade_aquatic.common.block.BedrollBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author SmellyModder (Luke Tonon)
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	private LivingEntityMixin(EntityType<?> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(at = @At("HEAD"), method = "setPosToBed", cancellable = true)
	private void setPosToBed(BlockPos pos, CallbackInfo info) {
		if (this.level.getBlockState(pos).getBlock() instanceof BedrollBlock) {
			this.setPos(pos.getX() + 0.5D, pos.getY() + 0.1875D, pos.getZ() + 0.5D);
			info.cancel();
		}
	}

}
