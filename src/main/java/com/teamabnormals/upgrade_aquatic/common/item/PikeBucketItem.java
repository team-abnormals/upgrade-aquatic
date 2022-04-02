package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeType;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class PikeBucketItem extends MobBucketItem {

	public PikeBucketItem(Properties builder) {
		super(UAEntityTypes.PIKE::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, builder);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		CompoundTag compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
			PikeType type = PikeType.getTypeById(compoundnbt.getInt("BucketVariantTag"));
			tooltip.add((new TranslatableComponent(String.format("tooltip.upgrade_aquatic.%s_pike", type.toString().toLowerCase())).withStyle(ChatFormatting.ITALIC, type.rarity.formatting)));
		}
	}
}