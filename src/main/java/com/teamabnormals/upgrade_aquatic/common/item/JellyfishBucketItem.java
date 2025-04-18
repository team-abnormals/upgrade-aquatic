package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.teamabnormals.upgrade_aquatic.core.registry.UASoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class JellyfishBucketItem extends BucketItem {

	public JellyfishBucketItem(Fluid supplier, Properties builder) {
		super(supplier, builder);
	}

	@Override
	public void checkExtraContent(@Nullable Player player, Level level, ItemStack stack, BlockPos pos) {
		if (level instanceof ServerLevel serverLevel) {
			CompoundTag compoundTag = stack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag();
			AbstractJellyfish jellyfish;
			if (compoundTag.contains("EntityType")) {
				Optional<EntityType<?>> type = BuiltInRegistries.ENTITY_TYPE.getOptional(ResourceLocation.parse(compoundTag.getString("EntityType")));
				if (type.isEmpty()) return;
				Entity entity = type.get().spawn(serverLevel, stack, null, pos, MobSpawnType.BUCKET, true, false);
				if (!(entity instanceof AbstractJellyfish)) return;
				jellyfish = (AbstractJellyfish) entity;
			} else {
				List<JellyfishRegistry.JellyfishEntry<?>> jellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
				jellyfish = jellies.get(new Random().nextInt(jellies.size())).jellyfish().get().spawn(serverLevel, stack, null, pos, MobSpawnType.BUCKET, true, false);
				if (jellyfish == null) return;
			}
			jellyfish.loadFromBucketTag(compoundTag);
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
		CustomData data = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
		if (data.isEmpty()) {
			return;
		}

		CompoundTag tag = data.copyTag();
		if (tag.contains("JellyfishDisplayTag")) {
			AbstractJellyfish.BucketDisplayInfo.appendHoverText(tooltip, tag.getCompound("JellyfishDisplayTag"));
		}
	}
}