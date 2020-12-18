package com.minecraftabnormals.upgrade_aquatic.common.items;

import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SquidBucketItem extends BucketItem {
	private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(() -> Items.TROPICAL_FISH_BUCKET);

	public SquidBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	public void onLiquidPlaced(World worldIn, ItemStack stack, BlockPos pos) {
		if (worldIn instanceof ServerWorld) {
			this.placeEntity((ServerWorld) worldIn, stack, pos);
		}
	}

	protected void playEmptySound(@Nullable PlayerEntity player, IWorld world, BlockPos pos) {
		world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}

	private void placeEntity(ServerWorld worldIn, ItemStack stack, BlockPos pos) {
		Entity entity = EntityType.SQUID.spawn(worldIn, stack, null, pos, SpawnReason.BUCKET, true, false);
		if (entity instanceof SquidEntity) {
			((SquidEntity) entity).enablePersistence();
		}
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}