package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.teamabnormals.upgrade_aquatic.core.registry.UASoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class JellyfishBucketItem extends BucketItem {
	private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.TROPICAL_FISH_BUCKET);

	public JellyfishBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	@Override
	public void checkExtraContent(@Nullable Player player, Level level, ItemStack stack, BlockPos pos) {
		if (level instanceof ServerLevel) {
			CompoundTag compoundTag = stack.getTag();
			AbstractJellyfish jellyfish;
			if (compoundTag != null && compoundTag.contains("EntityType")) {
				EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(compoundTag.getString("EntityType")));
				if (type == null) return;
				Entity entity = type.spawn((ServerLevel) level, stack, null, pos, MobSpawnType.BUCKET, true, false);
				if (!(entity instanceof AbstractJellyfish)) return;
				jellyfish = (AbstractJellyfish) entity;
			} else {
				List<JellyfishRegistry.JellyfishEntry<?>> jellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
				jellyfish = (AbstractJellyfish) jellies.get(new Random().nextInt(jellies.size())).jellyfish().get().spawn((ServerLevel) level, stack, null, pos, MobSpawnType.BUCKET, true, false);
				if (jellyfish == null) return;
			}
			jellyfish.loadFromBucketTag(stack.getOrCreateTag());
			jellyfish.setFromBucket(true);
		}
	}

	@Override
	protected void playEmptySound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos pos) {
		levelAccessor.playSound(player, pos, UASoundEvents.ITEM_BUCKET_EMPTY_JELLYFISH.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		CompoundTag compoundTag = stack.getTag();
		if (compoundTag != null && compoundTag.contains("JellyfishDisplayTag")) {
			AbstractJellyfish.BucketDisplayInfo.appendHoverText(tooltip, compoundTag.getCompound("JellyfishDisplayTag"));
		}
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}