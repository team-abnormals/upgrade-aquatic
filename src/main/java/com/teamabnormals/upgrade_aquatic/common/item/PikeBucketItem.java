package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeType;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Locale;

public class PikeBucketItem extends MobBucketItem {

	public PikeBucketItem(Properties builder) {
		super(UAEntityTypes.PIKE.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, builder);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		CustomData data = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
		if (data.isEmpty()) {
			return;
		}

		CompoundTag tag = data.copyTag();
		if (tag.contains("BucketVariantTag", 3)) {
			PikeType type = PikeType.getTypeById(tag.getInt("BucketVariantTag"));
			tooltip.add((Component.translatable(String.format("tooltip.upgrade_aquatic.%s_pike", type.toString().toLowerCase(Locale.ROOT))).withStyle(ChatFormatting.ITALIC, type.rarity.formatting)));
		}
	}
}