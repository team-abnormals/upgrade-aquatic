package com.minecraftabnormals.upgrade_aquatic.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class MulberryJamBottleItem extends Item {
   public MulberryJamBottleItem(Item.Properties properties) {
      super(properties);
   }

   public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
      super.finishUsingItem(stack, worldIn, entityLiving);
      if (entityLiving instanceof ServerPlayerEntity) {
         ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
         CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
         serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
      }

      if (stack.isEmpty()) {
         return new ItemStack(Items.GLASS_BOTTLE);
      } else {
         if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.instabuild) {
            ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            if (!playerentity.inventory.add(itemstack)) {
               playerentity.drop(itemstack, false);
            }
         }

         return stack;
      }
   }

   public int getUseDuration(ItemStack stack) {
      return 40;
   }

   public UseAction getUseAnimation(ItemStack stack) {
      return UseAction.DRINK;
   }

   public SoundEvent getDrinkingSound() {
      return SoundEvents.HONEY_DRINK;
   }

   public SoundEvent getEatingSound() {
      return SoundEvents.HONEY_DRINK;
   }

   public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
      playerIn.startUsingItem(handIn);
      return ActionResult.success(playerIn.getItemInHand(handIn));
   }
}