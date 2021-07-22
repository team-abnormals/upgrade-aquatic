package com.minecraftabnormals.upgrade_aquatic.common.items;

import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Supplier;

import net.minecraft.item.Item.Properties;

public class GlowSquidBucketItem extends SquidBucketItem {
	private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(UAItems.SQUID_BUCKET);

	public GlowSquidBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	public void checkExtraContent(World worldIn, ItemStack stack, BlockPos pos) {
		if (worldIn instanceof ServerWorld) {
			this.placeEntity((ServerWorld) worldIn, stack, pos);
		}
	}

	private void placeEntity(ServerWorld worldIn, ItemStack stack, BlockPos pos) {
		Entity entity = UAEntities.GLOW_SQUID.get().spawn(worldIn, stack, null, pos, SpawnReason.BUCKET, true, false);
		if (entity instanceof SquidEntity) {
			((SquidEntity) entity).setPersistenceRequired();
		}
	}


	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}