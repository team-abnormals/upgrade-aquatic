package com.minecraftabnormals.upgrade_aquatic.common.items;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UASounds;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
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

	public void checkExtraContent(Level worldIn, ItemStack stack, BlockPos pos) {
		if (worldIn instanceof ServerLevel) {
			this.placeEntity((ServerLevel) worldIn, stack, pos);
		}
	}

	@Override
	protected void playEmptySound(@Nullable Player player, LevelAccessor worldIn, BlockPos pos) {
		worldIn.playSound(player, pos, UASounds.ITEM_BUCKET_EMPTY_JELLYFISH.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
	}

	private void placeEntity(ServerLevel world, ItemStack stack, BlockPos pos) {
		AbstractJellyfishEntity jellyfish = this.getEntityInStack(stack, world, pos);
		if (jellyfish != null) {
			jellyfish.setFromBucket(true);
		}
	}

	@Nullable
	public AbstractJellyfishEntity getEntityInStack(ItemStack stack, Level world, @Nullable BlockPos pos) {
		CompoundTag compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
			CompoundTag jellyfishTag = compoundnbt.getCompound("JellyfishTag");
			String entityId = jellyfishTag.getString("EntityId");
			EntityType<?> jellyfishType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(UpgradeAquatic.MOD_ID + ":" + entityId));
			Entity entity = pos != null ? jellyfishType.spawn((ServerLevel) world, stack, null, pos, MobSpawnType.BUCKET, true, false) : jellyfishType.create(world);
			AbstractJellyfishEntity jellyfish = entity instanceof AbstractJellyfishEntity ? (AbstractJellyfishEntity) entity : null;

			if (jellyfish == null) {
				return null;
			}

			jellyfish.getBucketProcessor().read(jellyfishTag);
			return jellyfish;
		} else if (pos != null) {
			AbstractJellyfishEntity jellyfish = this.getRandomJellyfish(stack, world, pos);
			return jellyfish;
		}
		return null;
	}

	private AbstractJellyfishEntity getRandomJellyfish(ItemStack stack, Level world, @Nullable BlockPos pos) {
		Random rand = new Random();
		List<JellyfishRegistry.JellyfishEntry<?>> commonJellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
		return (AbstractJellyfishEntity) commonJellies.get(rand.nextInt(commonJellies.size())).jellyfish.get().spawn((ServerLevel) world, stack, null, pos, MobSpawnType.BUCKET, true, false);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		CompoundTag compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
			AbstractJellyfishEntity jellyfish = this.getEntityInStack(stack, worldIn, null);

			if (jellyfish != null) {
				ChatFormatting[] atextformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};
				tooltip.add((new TranslatableComponent("tooltip.upgrade_aquatic." + jellyfish.getBucketName() + "_jellyfish").withStyle(atextformatting)));

				tooltip.add(jellyfish.getYieldingTorchMessage());
			}
		}
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}