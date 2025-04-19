package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeVariant;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UARegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import java.util.Optional;

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
		if (tag.contains("BucketVariantTag", CompoundTag.TAG_STRING) && context.level() != null) {
			RegistryAccess registryAccess = context.level().registryAccess();
			if (registryAccess != null) {
				Optional<Holder<PikeVariant>> variant = Optional.ofNullable(ResourceLocation.tryParse(tag.getString("BucketVariantTag")))
						.map(loc -> ResourceKey.create(UARegistries.PIKE_VARIANT, loc))
						.flatMap(key -> registryAccess.registryOrThrow(UARegistries.PIKE_VARIANT).getHolder(key));

				if (variant.isPresent()) {
					PikeVariant pike = variant.get().value();
					tooltip.add(pike.description().copy().withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
				}
			}
		}
	}
}