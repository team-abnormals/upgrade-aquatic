package com.minecraftabnormals.upgrade_aquatic.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class MulberryJamBottleItem extends Item {
	public MulberryJamBottleItem(Item.Properties properties) {
		super(properties);
	}

	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayer serverplayerentity) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (stack.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (entityLiving instanceof Player playerentity && !((Player) entityLiving).getAbilities().instabuild) {
				ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
				if (!playerentity.getInventory().add(itemstack)) {
					playerentity.drop(itemstack, false);
				}
			}

			return stack;
		}
	}

	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	public SoundEvent getDrinkingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		playerIn.startUsingItem(handIn);
		return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
	}
}