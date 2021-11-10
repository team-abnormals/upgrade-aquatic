package com.minecraftabnormals.upgrade_aquatic.common.items;

import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UASounds;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class JellyfishBucketItem extends BucketItem {
	private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(() -> Items.TROPICAL_FISH_BUCKET);

	public JellyfishBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
	}

	public void checkExtraContent(World worldIn, ItemStack stack, BlockPos pos) {
		if (worldIn instanceof ServerWorld) {
			this.placeEntity((ServerWorld) worldIn, stack, pos);
		}
	}

	@Override
	protected void playEmptySound(@Nullable PlayerEntity player, IWorld worldIn, BlockPos pos) {
		worldIn.playSound(player, pos, UASounds.ITEM_BUCKET_EMPTY_JELLYFISH.get(), SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}

	private void placeEntity(ServerWorld world, ItemStack stack, BlockPos pos) {
		AbstractJellyfishEntity jellyfish = this.getEntityInStack(stack, world, pos);
		if (jellyfish != null) {
			jellyfish.setFromBucket(true);
		}
	}

	@Nullable
	public AbstractJellyfishEntity getEntityInStack(ItemStack stack, World world, @Nullable BlockPos pos) {
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
			CompoundNBT jellyfishTag = compoundnbt.getCompound("JellyfishTag");
			String entityId = jellyfishTag.getString("EntityId");
			EntityType<?> jellyfishType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(UpgradeAquatic.MOD_ID + ":" + entityId));
			Entity entity = pos != null ? jellyfishType.spawn((ServerWorld) world, stack, null, pos, SpawnReason.BUCKET, true, false) : jellyfishType.create(world);
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

	private AbstractJellyfishEntity getRandomJellyfish(ItemStack stack, World world, @Nullable BlockPos pos) {
		Random rand = new Random();
		List<JellyfishRegistry.JellyfishEntry<?>> commonJellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
		return (AbstractJellyfishEntity) commonJellies.get(rand.nextInt(commonJellies.size())).jellyfish.get().spawn((ServerWorld) world, stack, null, pos, SpawnReason.BUCKET, true, false);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
			AbstractJellyfishEntity jellyfish = this.getEntityInStack(stack, worldIn, null);

			if (jellyfish != null) {
				TextFormatting[] atextformatting = new TextFormatting[]{TextFormatting.ITALIC, TextFormatting.GRAY};
				tooltip.add((new TranslationTextComponent("tooltip.upgrade_aquatic." + jellyfish.getBucketName() + "_jellyfish").withStyle(atextformatting)));

				tooltip.add(jellyfish.getYieldingTorchMessage());
			}
		}
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}