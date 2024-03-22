package com.teamabnormals.upgrade_aquatic.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class GlowSquidBucketItem extends SquidBucketItem {

	public GlowSquidBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	@Override
	protected void placeEntity(ServerLevel level, ItemStack stack, BlockPos pos) {
		Entity entity = EntityType.GLOW_SQUID.spawn(level, stack, null, pos, MobSpawnType.BUCKET, true, false);
		if (entity instanceof Squid squid) {
			squid.setPersistenceRequired();
		}
	}
}