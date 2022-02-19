package com.minecraftabnormals.upgrade_aquatic.common.items;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeType;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.blueprint.common.item.WaterAnimalBucketItem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Supplier;

public class PikeBucketItem extends WaterAnimalBucketItem {

	public PikeBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(UAEntities.PIKE::get, supplier, builder);
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