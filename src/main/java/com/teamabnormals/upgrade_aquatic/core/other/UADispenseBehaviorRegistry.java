package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.DataUtil.AlternativeDispenseBehavior;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class UADispenseBehaviorRegistry {

	public static DispenseItemBehavior BUCKET_FISH_ITEM_BEHAVIOR = new DefaultDispenseItemBehavior() {

		@Override
		protected ItemStack execute(BlockSource source, ItemStack stack) {
			BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
			Level level = source.level();
			List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos), entity -> entity instanceof Bucketable);
			if (!entities.isEmpty()) {
				LivingEntity entity = entities.getFirst();
				if (entity instanceof Bucketable bucketable) {
					if (stack.getItem() == Items.WATER_BUCKET && entity.isAlive()) {
						entity.playSound(bucketable.getPickupSound(), 1.0F, 1.0F);
						ItemStack newStack = bucketable.getBucketItemStack();
						bucketable.saveToBucketTag(newStack);
						entity.discard();
						return newStack;
					}

				}
			}
			return stack;
		}
	};

	public static void registerDispenseBehaviors() {
		DispenseItemBehavior fishBucketDispenseBehavior = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource source, ItemStack stack) {
				DispensibleContainerItem item = (DispensibleContainerItem) stack.getItem();
				BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
				Level level = source.level();
				if (item.emptyContents(null, level, blockpos, null)) {
					item.checkExtraContent(null, level, stack, blockpos);
					return new ItemStack(Items.BUCKET);
				} else {
					return this.defaultDispenseItemBehavior.dispense(source, stack);
				}
			}
		};

		DispenserBlock.registerBehavior(UAItems.NAUTILUS_BUCKET.get(), fishBucketDispenseBehavior);
		DispenserBlock.registerBehavior(UAItems.PIKE_BUCKET.get(), fishBucketDispenseBehavior);
		DispenserBlock.registerBehavior(UAItems.LIONFISH_BUCKET.get(), fishBucketDispenseBehavior);
		DispenserBlock.registerBehavior(UAItems.SQUID_BUCKET.get(), fishBucketDispenseBehavior);
		DispenserBlock.registerBehavior(UAItems.GLOW_SQUID_BUCKET.get(), fishBucketDispenseBehavior);
		DataUtil.registerAlternativeDispenseBehavior(new AlternativeDispenseBehavior(UpgradeAquatic.MOD_ID, Items.WATER_BUCKET, (source, stack) -> !BlockUtil.getEntitiesAtOffsetPos(source, LivingEntity.class, entity -> entity instanceof Bucketable).isEmpty(), BUCKET_FISH_ITEM_BEHAVIOR));
	}
}
