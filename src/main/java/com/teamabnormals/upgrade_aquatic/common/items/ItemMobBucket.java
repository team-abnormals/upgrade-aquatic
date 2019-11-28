package com.teamabnormals.upgrade_aquatic.common.items;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.api.entities.EntityBucketableWaterMob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class ItemMobBucket extends BucketItem {
	private final EntityType<?> entityType;

	public ItemMobBucket(EntityType<? extends EntityBucketableWaterMob> entityType, Supplier<? extends Fluid> supplier, Item.Properties builder) {
		super(supplier, builder);
		this.entityType = entityType;
	}

	public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos) {
		if (!worldIn.isRemote) {
			this.placeEntity(worldIn, p_203792_2_, pos);
		}
	}
	
	protected void playEmptySound(@Nullable PlayerEntity player, IWorld worldIn, BlockPos pos) {
		worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}

	private void placeEntity(World worldIn, ItemStack p_205357_2_, BlockPos pos) {
		Entity entity = this.entityType.spawn(worldIn, p_205357_2_, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
		if (entity != null) {
			((EntityBucketableWaterMob)entity).setFromBucket(true);
		}
	}
}
