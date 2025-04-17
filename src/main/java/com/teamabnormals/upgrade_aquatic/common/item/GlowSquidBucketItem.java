package com.teamabnormals.upgrade_aquatic.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.item.ItemStack;

public class GlowSquidBucketItem extends SquidBucketItem {

	public GlowSquidBucketItem(Properties builder) {
		super(builder);
	}

	@Override
	protected void spawn(ServerLevel level, ItemStack stack, BlockPos pos) {
		Entity entity = EntityType.GLOW_SQUID.spawn(level, stack, null, pos, MobSpawnType.BUCKET, true, false);
		if (entity instanceof Squid squid) {
			Bucketable.loadDefaultDataFromBucketTag(squid, stack.getOrCreateTag());
			squid.setPersistenceRequired();
		}
	}
}