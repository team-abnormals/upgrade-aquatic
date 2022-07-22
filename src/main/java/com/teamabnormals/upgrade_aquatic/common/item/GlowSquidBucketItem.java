package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class GlowSquidBucketItem extends SquidBucketItem {
	private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(UAItems.SQUID_BUCKET);

	public GlowSquidBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	@Override
	protected void placeEntity(ServerLevel level, ItemStack stack, BlockPos pos) {
		Entity entity = EntityType.GLOW_SQUID.spawn(level, stack, null, pos, MobSpawnType.BUCKET, true, false);
		if (entity instanceof Squid) {
			((Squid) entity).setPersistenceRequired();
		}
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}