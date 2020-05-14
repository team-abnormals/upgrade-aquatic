package com.teamabnormals.upgrade_aquatic.common.items;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.teamabnormals.abnormals_core.common.entity.BucketableWaterMobEntity;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractEntityJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractEntityJellyfish.BucketData;
import com.teamabnormals.upgrade_aquatic.core.registry.other.JellyfishRegistry;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemJellyfishBucket extends BucketItem {

	public ItemJellyfishBucket(Supplier<? extends Fluid> supplier, Properties builder) {
		super(supplier, builder);
		this.addPropertyOverride(new ResourceLocation("variant"), (stack, world, entity) -> {
			CompoundNBT compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
				AbstractEntityJellyfish jellyfish = this.getEntityInStack(stack, world, null);
				return (float) JellyfishRegistry.IDS.get(jellyfish.getClass()) + (0.1F * (float) jellyfish.getIdSuffix());
			}
			return 0.0F;
		});
	}
	
	public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos) {
		if(!worldIn.isRemote) {
			this.placeEntity(worldIn, p_203792_2_, pos);
		}
	}
	
	@Override
	protected void playEmptySound(@Nullable PlayerEntity player, IWorld worldIn, BlockPos pos) {
		worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}

	private void placeEntity(World world, ItemStack stack, BlockPos pos) {
		AbstractEntityJellyfish jellyfish = this.getEntityInStack(stack, world, pos);
		if(jellyfish != null) {
			((BucketableWaterMobEntity) jellyfish).setFromBucket(true);
		}
	}
	
	@Nullable
	private AbstractEntityJellyfish getEntityInStack(ItemStack stack, World world, @Nullable BlockPos pos) {
		CompoundNBT compoundnbt = stack.getTag();
		if(compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
			BucketData bucketData = BucketData.read(compoundnbt.getCompound("JellyfishTag"));
			Entity entity = pos != null ? ForgeRegistries.ENTITIES.getValue(new ResourceLocation(Reference.MODID + ":" + bucketData.entityId)).spawn(world, stack, null, pos, SpawnReason.BUCKET, true, false) : ForgeRegistries.ENTITIES.getValue(new ResourceLocation(Reference.MODID + ":" + bucketData.entityId)).create(world);
			AbstractEntityJellyfish jellyfish = entity instanceof AbstractEntityJellyfish ? (AbstractEntityJellyfish) entity : null;
			
			if(jellyfish == null) {
				return null;
			}
			
			jellyfish.readBucketData(compoundnbt.getCompound("JellyfishTag"));
			return jellyfish;
		} else if(pos != null) {
			AbstractEntityJellyfish jellyfish = this.getRandomJellyfish(stack, world, pos);
			return jellyfish != null ? jellyfish : null;
		}
		return null;
	}
	
	private AbstractEntityJellyfish getRandomJellyfish(ItemStack stack, World world, @Nullable BlockPos pos) {
		Random rand = new Random();
		List<JellyfishRegistry.JellyfishEntry<?>> commonJellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
		return (AbstractEntityJellyfish) commonJellies.get(rand.nextInt(commonJellies.size())).jellyfish.get().spawn(world, stack, null, pos, SpawnReason.BUCKET, true, false);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
			AbstractEntityJellyfish jellyfish = this.getEntityInStack(stack, worldIn, null);
			
			if(jellyfish != null) {
				TextFormatting[] atextformatting = new TextFormatting[] {TextFormatting.ITALIC, TextFormatting.GRAY};
				tooltip.add((new TranslationTextComponent("tooltip.upgrade_aquatic." + jellyfish.getBucketName() + "_jellyfish").applyTextStyles(atextformatting)));
				
				tooltip.add(jellyfish.getYieldingTorchMessage());
			}
		}
	}
	
}