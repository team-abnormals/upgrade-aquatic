package com.teamabnormals.upgrade_aquatic.common.items;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.teamabnormals.abnormals_core.core.utils.ItemStackUtils;

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

public class ItemSquidBucket extends BucketItem {

	public ItemSquidBucket(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}
	
	public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos) {
		if(!worldIn.isRemote) {
			this.placeEntity(worldIn, p_203792_2_, pos);
		}
	}
	
	protected void playEmptySound(@Nullable PlayerEntity player, IWorld world, BlockPos pos) {
		world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}

	private void placeEntity(World worldIn, ItemStack stack, BlockPos pos) {
		Entity entity = EntityType.SQUID.spawn(worldIn, stack, null, pos, SpawnReason.BUCKET, true, false);
		if(entity instanceof SquidEntity) {
			((SquidEntity) entity).enablePersistence();
		}
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if(this.isInGroup(group)) {
			int targetIndex = ItemStackUtils.findIndexOfItem(Items.TROPICAL_FISH_BUCKET, items);
			if(targetIndex != -1) {
				items.add(targetIndex + 1, new ItemStack(this));
			} else {
				super.fillItemGroup(group, items);
			}
		}
	}

}