package com.minecraftabnormals.upgrade_aquatic.common.items;

import java.util.function.Supplier;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlowSquidBucketItem extends SquidBucketItem {

	public GlowSquidBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	@Override
	public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos) {
		if (!worldIn.isRemote) {
			this.placeEntity(worldIn, p_203792_2_, pos);
		}
	}

	private void placeEntity(World worldIn, ItemStack stack, BlockPos pos) {
		Entity entity = UAEntities.GLOW_SQUID.get().spawn(worldIn, stack, null, pos, SpawnReason.BUCKET, true, false);
		if (entity instanceof SquidEntity) {
			((SquidEntity) entity).enablePersistence();
		}
	}
}