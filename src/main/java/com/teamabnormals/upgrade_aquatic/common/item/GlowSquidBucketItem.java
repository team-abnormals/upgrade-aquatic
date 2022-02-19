package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class GlowSquidBucketItem extends SquidBucketItem {
	private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(UAItems.SQUID_BUCKET);

	public GlowSquidBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	public void checkExtraContent(Level worldIn, ItemStack stack, BlockPos pos) {
		if (worldIn instanceof ServerLevel) {
			this.placeEntity((ServerLevel) worldIn, stack, pos);
		}
	}

	private void placeEntity(ServerLevel worldIn, ItemStack stack, BlockPos pos) {
		Entity entity = UAEntities.GLOW_SQUID.get().spawn(worldIn, stack, null, pos, MobSpawnType.BUCKET, true, false);
		if (entity instanceof Squid) {
			((Squid) entity).setPersistenceRequired();
		}
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}