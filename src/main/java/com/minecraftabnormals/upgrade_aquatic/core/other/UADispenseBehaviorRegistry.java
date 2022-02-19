package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.blueprint.common.dispenser.FishBucketDispenseBehavior;
import com.teamabnormals.blueprint.core.api.IBucketableEntity;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.List;

public class UADispenseBehaviorRegistry {

	static DispenseItemBehavior glowingInkDispenseBehavior = new DefaultDispenseItemBehavior() {

		@Override
		public ItemStack execute(BlockSource source, ItemStack stack) {
			Level world = source.getLevel();
			BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
			BlockState state = source.getLevel().getBlockState(pos);
			stack.shrink(1);
			if (GlowingInkItem.DEAD_CORAL_CONVERSION_MAP.containsKey(state.getBlock())) {
				Block livingCoral = GlowingInkItem.DEAD_CORAL_CONVERSION_MAP.get(state.getBlock());
				world.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(state, livingCoral.defaultBlockState()));
				world.scheduleTick(pos, livingCoral, 60 + world.getRandom().nextInt(40));
			}
			return stack;
		}

		@Override
		protected void playSound(BlockSource source) {
			source.getLevel().playSound(null, source.getPos(), SoundEvents.SQUID_SQUIRT, SoundSource.BLOCKS, 1.0F, 1.0F);
		}

		@Override
		protected void playAnimation(BlockSource source, Direction facingIn) {
			GlowingInkItem.squirtInk(UAParticles.GLOW_SQUID_INK.get(), source.getPos().relative(facingIn));
		}
	};

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
					if (mob instanceof IBucketableEntity) {
						if (mob instanceof PikeEntity) {
							ItemStack bucket = ((IBucketableEntity) mob).getBucket();
							CompoundTag nbt = bucket.getOrCreateTag();
							CompoundTag compoundnbt1 = new CompoundTag();
							nbt.putInt("BucketVariantTag", ((PikeEntity) mob).getPikeType().id);
							if (!mob.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
								mob.getItemBySlot(EquipmentSlot.MAINHAND).save(compoundnbt1);
							}
							nbt.put("PikeHeldItem", compoundnbt1);
							nbt.putBoolean("ShouldDropItem", ((PikeEntity) mob).shouldDropItem());
							mob.discard();
							world.playSound(null, blockPos, SoundEvents.BUCKET_FILL_FISH, SoundSource.BLOCKS, 0.5F, 1.0F);
							return bucket;
						}
						ItemStack bucket = ((IBucketableEntity) mob).getBucket();
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
		DispenserBlock.registerBehavior(UAItems.NAUTILUS_BUCKET.get(), new FishBucketDispenseBehavior());
		DispenserBlock.registerBehavior(UAItems.PIKE_BUCKET.get(), new FishBucketDispenseBehavior());
		DispenserBlock.registerBehavior(UAItems.LIONFISH_BUCKET.get(), new FishBucketDispenseBehavior());
		DispenserBlock.registerBehavior(UAItems.SQUID_BUCKET.get(), new FishBucketDispenseBehavior());
		DispenserBlock.registerBehavior(UAItems.GLOW_SQUID_BUCKET.get(), new FishBucketDispenseBehavior());
		DataUtil.registerAlternativeDispenseBehavior(Items.WATER_BUCKET, (source, stack) -> !BlockUtil.getEntitiesAtOffsetPos(source, WaterAnimal.class, entity -> entity instanceof AbstractFish || entity instanceof IBucketableEntity || entity instanceof Squid).isEmpty(), bucketFishItemBehavior);

		DispenserBlock.registerBehavior(UAItems.GLOWING_INK_SAC.get(), glowingInkDispenseBehavior);
	}
}
