package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.minecraftabnormals.upgrade_aquatic.api.IGlowable;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignTileEntity.class)
public abstract class SignTileEntityMixin extends TileEntity implements IGlowable {
	private boolean glowing;

	public SignTileEntityMixin(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	@Inject(method = "write", at = @At("TAIL"))
	public void writeGlowing(CompoundNBT nbt, CallbackInfoReturnable<CompoundNBT> ci) {
		nbt.putBoolean("Glowing", this.glowing);
	}

	@Inject(method = "read", at = @At("TAIL"))
	public void readGlowing(BlockState state, CompoundNBT nbt, CallbackInfo ci) {
		this.glowing = nbt.getBoolean("Glowing");
	}

	@Override
	public boolean setGlowing(boolean glowing) {
		if (glowing != this.isGlowing()) {
			this.glowing = glowing;
			this.markDirty();
			this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
			return true;
		}
		return false;
	}

	@Override
	public boolean isGlowing() {
		return this.glowing;
	}
}
