package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.BedrollBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author SmellyModder (Luke Tonon)
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	private LivingEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(at = @At("HEAD"), method = "setPosToBed", cancellable = true)
	private void setBedrollSleepingPosition(BlockPos pos, CallbackInfo info) {
		if (this.level.getBlockState(pos).getBlock() instanceof BedrollBlock) {
			this.setPos(pos.getX() + 0.5D, pos.getY() + 0.1875D, pos.getZ() + 0.5D);
			info.cancel();
		}
	}

}
