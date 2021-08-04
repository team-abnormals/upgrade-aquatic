package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.abnormals_core.common.dispenser.FishBucketDispenseBehavior;
import com.minecraftabnormals.abnormals_core.core.api.IBucketableEntity;
import com.minecraftabnormals.abnormals_core.core.util.BlockUtil;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class UADispenseBehaviorRegistry {
	
	static IDispenseItemBehavior glowingInkDispenseBehavior = new DefaultDispenseItemBehavior() {
		
		@Override
		public ItemStack execute(IBlockSource source, ItemStack stack) {
			World world = source.getLevel();
            BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
            BlockState state = source.getLevel().getBlockState(pos);
			stack.shrink(1);
            if (GlowingInkItem.DEAD_CORAL_CONVERSION_MAP.containsKey(state.getBlock())) {
    			Block livingCoral = GlowingInkItem.DEAD_CORAL_CONVERSION_MAP.get(state.getBlock());
    			world.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(state, livingCoral.defaultBlockState()));
    			world.getBlockTicks().scheduleTick(pos, livingCoral, 60 + world.getRandom().nextInt(40));
            }
			return stack;
		}

		@Override
		protected void playSound(IBlockSource source) {
			source.getLevel().playSound(null, source.getPos(), SoundEvents.SQUID_SQUIRT, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		
		@Override
		protected void playAnimation(IBlockSource source, Direction facingIn) {
			GlowingInkItem.squirtInk(UAParticles.GLOW_SQUID_INK.get(), source.getPos().relative(facingIn));
		}
	};

	static IDispenseItemBehavior bucketFishItemBehavior = new DefaultDispenseItemBehavior() {
    	
        @Override
        protected ItemStack execute(IBlockSource source, ItemStack stack) {
            BlockPos blockPos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
            World world = source.getLevel();
            List<WaterMobEntity> entities = world.getEntitiesOfClass(WaterMobEntity.class, new AxisAlignedBB(blockPos));
            if (!entities.isEmpty()) {
                for (WaterMobEntity mob : entities) {
                    if (mob instanceof AbstractFishEntity) {
                        ItemStack bucket = ((AbstractFishEntity) mob).getBucketItemStack();
                        mob.remove();
                        world.playSound(null, blockPos, SoundEvents.BUCKET_FILL_FISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
                        return bucket;
                    }
                    if (mob instanceof IBucketableEntity) {
                    	if(mob instanceof PikeEntity) {
                    		ItemStack bucket = ((IBucketableEntity) mob).getBucket();
                    		CompoundNBT nbt = bucket.getOrCreateTag();
                    		CompoundNBT compoundnbt1 = new CompoundNBT();
                    		nbt.putInt("BucketVariantTag", ((PikeEntity) mob).getPikeType().id);
                    		if (!mob.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty()) {
                    			mob.getItemBySlot(EquipmentSlotType.MAINHAND).save(compoundnbt1);
                    		}
                    		nbt.put("PikeHeldItem", compoundnbt1);
                    		nbt.putBoolean("ShouldDropItem", ((PikeEntity) mob).shouldDropItem());
                    		mob.remove();
                            world.playSound(null, blockPos, SoundEvents.BUCKET_FILL_FISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
                    		return bucket;
                    	}
                    	ItemStack bucket = ((IBucketableEntity) mob).getBucket();
                        mob.remove();
                        world.playSound(null, blockPos, SoundEvents.BUCKET_FILL_FISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
                        return bucket;
                    }
                    if(mob instanceof SquidEntity) {
                    	ItemStack bucket = new ItemStack(UAItems.SQUID_BUCKET.get());
                    	if(mob.hasCustomName()) {
                    		bucket.setHoverName(mob.getCustomName());
                    	}
                    	mob.remove();
                    	world.playSound(null, blockPos, SoundEvents.BUCKET_FILL_FISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
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
		DataUtil.registerAlternativeDispenseBehavior(Items.WATER_BUCKET, (source, stack) -> !BlockUtil.getEntitiesAtOffsetPos(source, WaterMobEntity.class, entity -> entity instanceof AbstractFishEntity || entity instanceof IBucketableEntity || entity instanceof SquidEntity).isEmpty(), bucketFishItemBehavior);
		
		DispenserBlock.registerBehavior(UAItems.GLOWING_INK_SAC.get(), glowingInkDispenseBehavior);
	}
}
