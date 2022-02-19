package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.minecraftabnormals.upgrade_aquatic.api.IGlowable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignBlockEntity.class)
public abstract class SignBlockEntityMixin extends BlockEntity implements IGlowable {
	private boolean glowing;

	public SignBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
		super(blockEntityType, pos, state);
	}

	@Inject(method = "saveAdditional", at = @At("TAIL"))
	public void writeGlowing(CompoundTag tag, CallbackInfo ci) {
		tag.putBoolean("Glowing", this.glowing);
	}

	@Inject(method = "load", at = @At("TAIL"))
	public void readGlowing(CompoundTag nbt, CallbackInfo ci) {
		this.glowing = nbt.getBoolean("Glowing");
	}

	@Override
	public boolean setGlowing(boolean glowing) {
		if (glowing != this.isGlowing()) {
			this.glowing = glowing;
			this.setChanged();
			this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
			return true;
		}
		return false;
	}

	@Override
	public boolean isGlowing() {
		return this.glowing;
	}
}
