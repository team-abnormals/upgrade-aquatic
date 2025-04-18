package com.teamabnormals.upgrade_aquatic.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;

public class SquidBucketItem extends MobBucketItem {

	public SquidBucketItem(Properties builder) {
		super(EntityType.SQUID, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, builder);
	}

	@Override
	public void checkExtraContent(@Nullable Player player, Level level, ItemStack stack, BlockPos pos) {
		if (level instanceof ServerLevel) {
			this.spawn((ServerLevel) level, stack, pos);
			level.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
		}
	}

	protected void spawn(ServerLevel level, ItemStack stack, BlockPos pos) {
		Squid squid = EntityType.SQUID.spawn(level, stack, null, pos, MobSpawnType.BUCKET, true, false);
		if (squid != null) {
			Bucketable.loadDefaultDataFromBucketTag(squid, stack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag());
			squid.setPersistenceRequired();
		}
	}
}