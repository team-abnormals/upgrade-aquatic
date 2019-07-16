package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class UADispenseBehaviorRegistry {
    static IDispenseItemBehavior fishDispenseItemBehavior = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior field_218405_b = new DefaultDispenseItemBehavior();

        @Override
        public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
            BucketItem bucketitem = (BucketItem) stack.getItem();
            BlockPos blockpos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
            World world = source.getWorld();
            if (bucketitem.tryPlaceContainedLiquid((PlayerEntity) null, world, blockpos, (BlockRayTraceResult) null)) {
                bucketitem.onLiquidPlaced(world, stack, blockpos);
                return new ItemStack(Items.BUCKET);
            } else {
                return this.field_218405_b.dispense(source, stack);
            }
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
                        world.playSound((PlayerEntity) null, source.getBlockPos(), SoundEvents.ITEM_BUCKET_FILL_FISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return bucket;
                    }
                    //TODO: Make Nautilus and other entities that are bucketable use an interface
                    if (mob instanceof EntityNautilus) {
                        ItemStack bucket = ((EntityNautilus) mob).getBucket();
                        mob.remove();
                        world.playSound(source.getX(), source.getY(), source.getZ(), SoundEvents.ITEM_BUCKET_FILL_FISH, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                        return bucket;
                    }
                }
            }
            return fishDispenseItemBehavior.dispense(source, stack);
        }
    };

    public static void registerAll() {
        DispenserBlock.registerDispenseBehavior(UAItems.NAUTILUS_BUCKET, fishDispenseItemBehavior);
        DispenserBlock.registerDispenseBehavior(Items.WATER_BUCKET, bucketFishItemBehavior);
    }
}
