package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.teamabnormals.upgrade_aquatic.core.registry.UASoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class JellyfishBucketItem extends BucketItem {

	public JellyfishBucketItem(Fluid supplier, Properties builder) {
		super(supplier, builder);
	}

	@Override
	public void checkExtraContent(@Nullable Player player, Level level, ItemStack stack, BlockPos pos) {
		if (level instanceof ServerLevel) {
			CompoundTag compoundTag = stack.getTag();
			AbstractJellyfish jellyfish;
			if (compoundTag != null && compoundTag.contains("EntityType")) {
				EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse(compoundTag.getString("EntityType")));
				if (type == null) return;
				Entity entity = type.spawn((ServerLevel) level, stack, null, pos, MobSpawnType.BUCKET, true, false);
				if (!(entity instanceof AbstractJellyfish)) return;
				jellyfish = (AbstractJellyfish) entity;
			} else {
				List<JellyfishRegistry.JellyfishEntry<?>> jellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
				jellyfish = jellies.get(new Random().nextInt(jellies.size())).jellyfish().get().spawn((ServerLevel) level, stack, null, pos, MobSpawnType.BUCKET, true, false);
				if (jellyfish == null) return;
			}
			jellyfish.loadFromBucketTag(stack.getOrCreateTag());
			jellyfish.setFromBucket(true);
		}
	}

	@Override
	protected void playEmptySound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos pos) {
		levelAccessor.playSound(player, pos, UASoundEvents.BUCKET_EMPTY_JELLYFISH.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		CompoundTag compoundTag = stack.getTag();
		if (compoundTag != null && compoundTag.contains("JellyfishDisplayTag")) {
			AbstractJellyfish.BucketDisplayInfo.appendHoverText(tooltip, compoundTag.getCompound("JellyfishDisplayTag"));
		}
	}
}