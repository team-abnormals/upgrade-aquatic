package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.DataUtil.AlternativeDispenseBehavior;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class UADispenseBehaviorRegistry {

	static DispenseItemBehavior bucketFishItemBehavior = new DefaultDispenseItemBehavior() {

		@Override
		protected ItemStack execute(BlockSource source, ItemStack stack) {
			BlockPos blockPos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
			Level world = source.getLevel();
			List<WaterAnimal> entities = world.getEntitiesOfClass(WaterAnimal.class, new AABB(blockPos));
			if (!entities.isEmpty()) {
				for (WaterAnimal mob : entities) {
					if (mob instanceof AbstractFish) {
						ItemStack bucket = ((AbstractFish) mob).getBucketItemStack();
						mob.discard();
						world.playSound(null, blockPos, SoundEvents.BUCKET_FILL_FISH, SoundSource.BLOCKS, 0.5F, 1.0F);
						return bucket;
					}
					if (mob instanceof Bucketable bucketable) {
						ItemStack bucket = bucketable.getBucketItemStack();
						if (mob instanceof Pike) {
							CompoundTag nbt = bucket.getOrCreateTag();
							CompoundTag compoundnbt1 = new CompoundTag();
							nbt.putInt("BucketVariantTag", ((Pike) mob).getPikeType().id);
							if (!mob.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
								mob.getItemBySlot(EquipmentSlot.MAINHAND).save(compoundnbt1);
							}
							nbt.put("PikeHeldItem", compoundnbt1);
							nbt.putBoolean("ShouldDropItem", ((Pike) mob).shouldDropItem());
						}
						mob.discard();
						world.playSound(null, blockPos, SoundEvents.BUCKET_FILL_FISH, SoundSource.BLOCKS, 0.5F, 1.0F);
						return bucket;
					}
					if (mob instanceof Squid) {
						ItemStack bucket = new ItemStack(UAItems.SQUID_BUCKET.get());
						if (mob.hasCustomName()) {
							bucket.setHoverName(mob.getCustomName());
						}
						mob.discard();
						world.playSound(null, blockPos, SoundEvents.BUCKET_FILL_FISH, SoundSource.BLOCKS, 0.5F, 1.0F);
						return bucket;
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
				BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
				Level level = source.getLevel();
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
		DataUtil.registerAlternativeDispenseBehavior(new AlternativeDispenseBehavior(UpgradeAquatic.MOD_ID, Items.WATER_BUCKET, (source, stack) -> !BlockUtil.getEntitiesAtOffsetPos(source, WaterAnimal.class, entity -> entity instanceof AbstractFish || entity instanceof Bucketable || entity instanceof Squid).isEmpty(), bucketFishItemBehavior));
	}
}
