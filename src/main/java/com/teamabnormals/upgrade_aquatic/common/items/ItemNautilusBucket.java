package com.teamabnormals.upgrade_aquatic.common.items;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;

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

public class ItemNautilusBucket extends BucketItem {
	private final EntityType<?> fishType;

	public ItemNautilusBucket(EntityType<?> fishTypeIn, Fluid p_i49022_2_, Item.Properties builder) {
		super(p_i49022_2_, builder);
		this.fishType = fishTypeIn;
	}

	public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos) {
		if (!worldIn.isRemote) {
			this.placeFish(worldIn, p_203792_2_, pos);
		}
	}
	
	protected void playEmptySound(@Nullable PlayerEntity player, IWorld worldIn, BlockPos pos) {
		worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}

	private void placeFish(World worldIn, ItemStack p_205357_2_, BlockPos pos) {
		Entity entity = this.fishType.spawn(worldIn, p_205357_2_, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
		if (entity != null) {
			((EntityNautilus)entity).setFromBucket(true);
		}
	}
}
