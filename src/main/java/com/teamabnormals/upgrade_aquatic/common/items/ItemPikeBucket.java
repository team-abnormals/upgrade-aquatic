package com.teamabnormals.upgrade_aquatic.common.items;

import java.util.List;
import java.util.function.Supplier;

import com.teamabnormals.abnormals_core.common.entity.BucketableWaterMobEntity;
import com.teamabnormals.abnormals_core.common.items.MobBucketItem;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemPikeBucket extends MobBucketItem {

	public ItemPikeBucket(Supplier<EntityType<? extends BucketableWaterMobEntity>> entityType, Supplier<? extends Fluid> supplier, Properties builder) {
		super(entityType, supplier, builder);
		this.addPropertyOverride(new ResourceLocation("variant"), (stack, world, entity) -> {
			CompoundNBT compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
				return compoundnbt.getInt("BucketVariantTag");
			}
			return 2;
		});
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
			int i = compoundnbt.getInt("BucketVariantTag");
			TextFormatting[] atextformatting = new TextFormatting[] {TextFormatting.ITALIC, TextFormatting.GRAY};
			
			tooltip.add((new TranslationTextComponent("tooltip.upgrade_aquatic." + EntityPike.getNameById(i)).applyTextStyles(atextformatting)));
		}
	}
	
}
