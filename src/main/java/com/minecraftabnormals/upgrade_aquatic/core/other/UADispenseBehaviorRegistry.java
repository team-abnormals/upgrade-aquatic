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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
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
		public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
			World world = source.getWorld();
            BlockPos pos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
            BlockState state = source.getWorld().getBlockState(pos);
            if (GlowingInkItem.DEAD_CORAL_CONVERSION_MAP.containsKey(state.getBlock())) {
    			Block livingCoral = GlowingInkItem.DEAD_CORAL_CONVERSION_MAP.get(state.getBlock());
    			world.setBlockState(pos, BlockUtil.transferAllBlockStates(state, livingCoral.getDefaultState()));
    			world.getPendingBlockTicks().scheduleTick(pos, livingCoral, 60 + world.getRandom().nextInt(40));
				stack.shrink(1);
            }
			return stack;
		}

		@Override
		protected void playDispenseSound(IBlockSource source) {
			source.getWorld().playSound(null, source.getBlockPos(), SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		
		@Override
		protected void spawnDispenseParticles(IBlockSource source, Direction facingIn) {
			GlowingInkItem.squirtInk(UAParticles.GLOW_SQUID_INK.get(), source.getBlockPos().offset(facingIn));
		}
	};

	static IDispenseItemBehavior bucketFishItemBehavior = new DefaultDispenseItemBehavior() {
    	
        @Override
        protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
            BlockPos blockPos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
            World world = source.getWorld();
            List<WaterMobEntity> entities = world.getEntitiesWithinAABB(WaterMobEntity.class, new AxisAlignedBB(blockPos));
            if (!entities.isEmpty()) {
                for (WaterMobEntity mob : entities) {
                    if (mob instanceof AbstractFishEntity) {
                        ItemStack bucket = ((AbstractFishEntity) mob).getFishBucket();
                        mob.remove();
                        world.playSound(null, blockPos, SoundEvents.ITEM_BUCKET_FILL_FISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
                        return bucket;
                    }
                    if (mob instanceof IBucketableEntity) {
                    	if(mob instanceof PikeEntity) {
                    		ItemStack bucket = ((IBucketableEntity) mob).getBucket();
                    		CompoundNBT nbt = bucket.getOrCreateTag();
                    		CompoundNBT compoundnbt1 = new CompoundNBT();
                    		nbt.putInt("BucketVariantTag", ((PikeEntity) mob).getPikeType().id);
                    		if (!mob.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
                    			mob.getItemStackFromSlot(EquipmentSlotType.MAINHAND).write(compoundnbt1);
                    		}
                    		nbt.put("PikeHeldItem", compoundnbt1);
                    		nbt.putBoolean("ShouldDropItem", ((PikeEntity) mob).shouldDropItem());
                    		mob.remove();
                            world.playSound(null, blockPos, SoundEvents.ITEM_BUCKET_FILL_FISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
                    		return bucket;
                    	}
                    	ItemStack bucket = ((IBucketableEntity) mob).getBucket();
                        mob.remove();
                        world.playSound(null, blockPos, SoundEvents.ITEM_BUCKET_FILL_FISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
                        return bucket;
                    }
                    if(mob instanceof SquidEntity) {
                    	ItemStack bucket = new ItemStack(UAItems.SQUID_BUCKET.get());
                    	if(mob.hasCustomName()) {
                    		bucket.setDisplayName(mob.getCustomName());
                    	}
                    	mob.remove();
                    	world.playSound(null, blockPos, SoundEvents.ITEM_BUCKET_FILL_FISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
                    	return bucket;
                    }
                }
            }
            return stack;
        }
    };

	public static void registerDispenseBehaviors() {
		DispenserBlock.registerDispenseBehavior(UAItems.NAUTILUS_BUCKET.get(), new FishBucketDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(UAItems.PIKE_BUCKET.get(), new FishBucketDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(UAItems.LIONFISH_BUCKET.get(), new FishBucketDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(UAItems.SQUID_BUCKET.get(), new FishBucketDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(UAItems.GLOW_SQUID_BUCKET.get(), new FishBucketDispenseBehavior());
		DataUtil.registerAlternativeDispenseBehavior(Items.WATER_BUCKET, (source, stack) -> !BlockUtil.getEntitiesAtOffsetPos(source, WaterMobEntity.class, entity -> entity instanceof AbstractFishEntity || entity instanceof IBucketableEntity || entity instanceof SquidEntity).isEmpty(), bucketFishItemBehavior);
		
		DispenserBlock.registerDispenseBehavior(UAItems.GLOWING_INK_SAC.get(), glowingInkDispenseBehavior);
	}
}
