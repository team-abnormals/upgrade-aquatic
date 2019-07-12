package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class UADispenseBehaviorRegistry {
	static IDispenseItemBehavior fishDispenseItemBehavior = new DefaultDispenseItemBehavior() {
		private final DefaultDispenseItemBehavior field_218405_b = new DefaultDispenseItemBehavior();

        /**
         * Dispense the specified stack, play the dispense sound and spawn particles.
         */
		public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
			BucketItem bucketitem = (BucketItem)stack.getItem();
			BlockPos blockpos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
			World world = source.getWorld();
			if (bucketitem.tryPlaceContainedLiquid((PlayerEntity)null, world, blockpos, (BlockRayTraceResult)null)) {
				bucketitem.onLiquidPlaced(world, stack, blockpos);
				return new ItemStack(Items.BUCKET);
			} else {
				return this.field_218405_b.dispense(source, stack);
			}
		}
	};
	public static void registerAll() {
		DispenserBlock.registerDispenseBehavior(UAItems.NAUTILUS_BUCKET, fishDispenseItemBehavior);
	}
}
