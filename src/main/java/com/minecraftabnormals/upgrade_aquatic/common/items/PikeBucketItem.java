package com.minecraftabnormals.upgrade_aquatic.common.items;

import java.util.List;
import java.util.function.Supplier;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeType;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.abnormals_core.common.items.MobBucketItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PikeBucketItem extends MobBucketItem {

	public PikeBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(() -> UAEntities.PIKE.get(), supplier, builder);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
			PikeType type = PikeType.getTypeById(compoundnbt.getInt("BucketVariantTag"));
			tooltip.add((new TranslationTextComponent(String.format("tooltip.upgrade_aquatic.%s_pike", type.toString().toLowerCase())).mergeStyle(TextFormatting.ITALIC, type.rarity.formatting)));
		}
	}
	
}