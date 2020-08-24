package com.minecraftabnormals.upgrade_aquatic.core.other;

import java.util.List;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.abnormals_core.common.items.AbnormalsSpawnEggItem;
import com.teamabnormals.abnormals_core.core.library.api.IBucketableEntity;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UADispenseBehaviorRegistry {
	static IDispenseItemBehavior fishDispenseItemBehavior = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior field_218405_b = new DefaultDispenseItemBehavior();

        @Override
        public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
            BucketItem bucketitem = (BucketItem) stack.getItem();
            BlockPos blockpos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
            World world = source.getWorld();
            if (bucketitem.tryPlaceContainedLiquid(null, world, blockpos, null)) {
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
            return fishDispenseItemBehavior.dispense(source, stack);
        }
    };
    static DefaultDispenseItemBehavior spawnEggItemBehavior = new DefaultDispenseItemBehavior() {
    	
        public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
           Direction direction = source.getBlockState().get(DispenserBlock.FACING);
           EntityType<?> entitytype = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
           entitytype.spawn(source.getWorld(), stack, (PlayerEntity)null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
           stack.shrink(1);
           return stack;
        }
        
    };

    public static void registerDispenseBehaviors() {
    	DispenserBlock.registerDispenseBehavior(UAItems.NAUTILUS_BUCKET.get(), fishDispenseItemBehavior);
    	DispenserBlock.registerDispenseBehavior(UAItems.PIKE_BUCKET.get(), fishDispenseItemBehavior);
    	DispenserBlock.registerDispenseBehavior(UAItems.LIONFISH_BUCKET.get(), fishDispenseItemBehavior);
    	DispenserBlock.registerDispenseBehavior(UAItems.SQUID_BUCKET.get(), fishDispenseItemBehavior);
    	DispenserBlock.registerDispenseBehavior(Items.WATER_BUCKET, bucketFishItemBehavior);
    	
    	UpgradeAquatic.REGISTRY_HELPER.getDeferredItemRegister().getEntries().stream().filter((entry) -> entry.get() instanceof AbnormalsSpawnEggItem).forEach((item) -> {
    		Item items = item.get();
    		DispenserBlock.registerDispenseBehavior(items, spawnEggItemBehavior);
    	});
    }
}