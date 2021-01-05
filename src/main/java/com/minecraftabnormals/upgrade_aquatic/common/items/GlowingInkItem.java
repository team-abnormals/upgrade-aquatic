package com.minecraftabnormals.upgrade_aquatic.common.items;

import com.google.common.collect.Maps;
import com.minecraftabnormals.abnormals_core.core.util.BlockUtil;
import com.minecraftabnormals.abnormals_core.core.util.NetworkUtil;
import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.item.*;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

public class GlowingInkItem extends Item {
	private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(() -> Items.INK_SAC);

	public GlowingInkItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState state = world.getBlockState(pos);

		if (context.getPlayer() != null && context.getPlayer().isSecondaryUseActive())
			return super.onItemUse(context);

		if (DEAD_CORAL_CONVERSION_MAP.containsKey(state.getBlock())) {
			Block livingCoral = DEAD_CORAL_CONVERSION_MAP.get(state.getBlock());
			world.setBlockState(pos, BlockUtil.transferAllBlockStates(state, livingCoral.getDefaultState()));
			world.getPendingBlockTicks().scheduleTick(pos, livingCoral, 60 + world.getRandom().nextInt(40));
			world.playSound(context.getPlayer(), pos, SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.BLOCKS, 1.0F, 1.0F);
			createEffectCloud(Effects.NIGHT_VISION, world, pos);
			if (world.isRemote())
				squirtInk(UAParticles.GLOW_SQUID_INK.get(), world, pos);
		} else {
			BlockPos offset = world.getBlockState(pos).isSolid() ? pos.offset(context.getFace()) : pos;
			world.playSound(context.getPlayer(), offset, SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.BLOCKS, 1.0F, 1.0F);
			createEffectCloud(Effects.NIGHT_VISION, world, offset);
			if (world.isRemote())
				squirtInk(UAParticles.GLOW_SQUID_INK.get(), world, offset);
		}
		if (context.getPlayer() != null && !context.getPlayer().abilities.isCreativeMode)
			context.getItem().shrink(1);

		return ActionResultType.SUCCESS;
	}

	@OnlyIn(Dist.CLIENT)
	public static void squirtInk(IParticleData particle, IWorld worldIn, BlockPos posIn) {
		worldIn.addParticle(particle, (double) posIn.getX() + 0.5D, (double) posIn.getY() + 0.5D, (double) posIn.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
		for (int i = 0; i < 15; ++i) {
			double d1 = random.nextGaussian() * 0.02D;
			double d6 = (double) posIn.getX() + random.nextDouble();
			double d7 = (double) posIn.getY() + random.nextDouble();
			double d8 = (double) posIn.getZ() + random.nextDouble();
			worldIn.addParticle(particle, d6, d7, d8, d1, d1, d1);
		}
	}

	public static void squirtInkServer(IParticleData particle, BlockPos posIn) {
		String particleRegistryName = particle.getType().getRegistryName().toString();
		NetworkUtil.spawnParticle(particleRegistryName, (double) posIn.getX() + 0.5D, (double) posIn.getY() + 0.5D, (double) posIn.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
		for (int i = 0; i < 15; ++i) {
			double d1 = random.nextGaussian() * 0.02D;
			double d6 = (double) posIn.getX() + random.nextDouble();
			double d7 = (double) posIn.getY() + random.nextDouble();
			double d8 = (double) posIn.getZ() + random.nextDouble();
			NetworkUtil.spawnParticle(particleRegistryName, d6, d7, d8, d1, d1, d1);
		}
	}

	public static void createEffectCloud(Effect effect, World world, BlockPos pos) {
		for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos))) {
			entity.addPotionEffect(new EffectInstance(effect, 300));
		}
	}

	public static void createEffectCloud(Effect effect, World world, AxisAlignedBB aabb) {
		for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, aabb)) {
			if (!(entity instanceof SquidEntity))
				entity.addPotionEffect(new EffectInstance(effect, 200, 1));
		}
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}

	public static final Map<Block, Block> DEAD_CORAL_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK);
		conversions.put(Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK);
		conversions.put(Blocks.DEAD_TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK);
		conversions.put(Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK);
		conversions.put(Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK);
		conversions.put(UABlocks.DEAD_ACAN_CORAL_BLOCK.get(), UABlocks.ACAN_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_FINGER_CORAL_BLOCK.get(), UABlocks.FINGER_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_STAR_CORAL_BLOCK.get(), UABlocks.STAR_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_MOSS_CORAL_BLOCK.get(), UABlocks.MOSS_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_PETAL_CORAL_BLOCK.get(), UABlocks.PETAL_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_BRANCH_CORAL_BLOCK.get(), UABlocks.BRANCH_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_ROCK_CORAL_BLOCK.get(), UABlocks.ROCK_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_PILLOW_CORAL_BLOCK.get(), UABlocks.PILLOW_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_SILK_CORAL_BLOCK.get(), UABlocks.SILK_CORAL_BLOCK.get());
		conversions.put(UABlocks.DEAD_CHROME_CORAL_BLOCK.get(), UABlocks.CHROME_CORAL_BLOCK.get());
		conversions.put(UABlocks.ELDER_PRISMARINE_CORAL_BLOCK.get(), UABlocks.PRISMARINE_CORAL_BLOCK.get());
		conversions.put(Blocks.DEAD_BUBBLE_CORAL, Blocks.BUBBLE_CORAL);
		conversions.put(Blocks.DEAD_HORN_CORAL, Blocks.HORN_CORAL);
		conversions.put(Blocks.DEAD_TUBE_CORAL, Blocks.TUBE_CORAL);
		conversions.put(Blocks.DEAD_BRAIN_CORAL, Blocks.BRAIN_CORAL);
		conversions.put(Blocks.DEAD_FIRE_CORAL, Blocks.FIRE_CORAL);
		conversions.put(UABlocks.DEAD_ACAN_CORAL.get(), UABlocks.ACAN_CORAL.get());
		conversions.put(UABlocks.DEAD_FINGER_CORAL.get(), UABlocks.FINGER_CORAL.get());
		conversions.put(UABlocks.DEAD_STAR_CORAL.get(), UABlocks.STAR_CORAL.get());
		conversions.put(UABlocks.DEAD_MOSS_CORAL.get(), UABlocks.MOSS_CORAL.get());
		conversions.put(UABlocks.DEAD_PETAL_CORAL.get(), UABlocks.PETAL_CORAL.get());
		conversions.put(UABlocks.DEAD_BRANCH_CORAL.get(), UABlocks.BRANCH_CORAL.get());
		conversions.put(UABlocks.DEAD_ROCK_CORAL.get(), UABlocks.ROCK_CORAL.get());
		conversions.put(UABlocks.DEAD_PILLOW_CORAL.get(), UABlocks.PILLOW_CORAL.get());
		conversions.put(UABlocks.DEAD_SILK_CORAL.get(), UABlocks.SILK_CORAL.get());
		conversions.put(UABlocks.DEAD_CHROME_CORAL.get(), UABlocks.CHROME_CORAL.get());
		conversions.put(UABlocks.ELDER_PRISMARINE_CORAL.get(), UABlocks.PRISMARINE_CORAL.get());
		conversions.put(Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN);
		conversions.put(Blocks.DEAD_HORN_CORAL_FAN, Blocks.HORN_CORAL_FAN);
		conversions.put(Blocks.DEAD_TUBE_CORAL_FAN, Blocks.TUBE_CORAL_FAN);
		conversions.put(Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_FAN);
		conversions.put(Blocks.DEAD_FIRE_CORAL_FAN, Blocks.FIRE_CORAL_FAN);
		conversions.put(UABlocks.DEAD_ACAN_CORAL_FAN.get(), UABlocks.ACAN_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_FINGER_CORAL_FAN.get(), UABlocks.FINGER_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_STAR_CORAL_FAN.get(), UABlocks.STAR_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_MOSS_CORAL_FAN.get(), UABlocks.MOSS_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_PETAL_CORAL_FAN.get(), UABlocks.PETAL_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_BRANCH_CORAL_FAN.get(), UABlocks.BRANCH_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_ROCK_CORAL_FAN.get(), UABlocks.ROCK_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_PILLOW_CORAL_FAN.get(), UABlocks.PILLOW_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_SILK_CORAL_FAN.get(), UABlocks.SILK_CORAL_FAN.get());
		conversions.put(UABlocks.DEAD_CHROME_CORAL_FAN.get(), UABlocks.CHROME_CORAL_FAN.get());
		conversions.put(UABlocks.ELDER_PRISMARINE_CORAL_FAN.get(), UABlocks.PRISMARINE_CORAL_FAN.get());
		conversions.put(Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN);
		conversions.put(Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN);
		conversions.put(Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN);
		conversions.put(Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN);
		conversions.put(Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN);
		conversions.put(UABlocks.DEAD_ACAN_CORAL_WALL_FAN.get(), UABlocks.ACAN_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_FINGER_CORAL_WALL_FAN.get(), UABlocks.FINGER_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_STAR_CORAL_WALL_FAN.get(), UABlocks.STAR_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_MOSS_CORAL_WALL_FAN.get(), UABlocks.MOSS_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_PETAL_CORAL_WALL_FAN.get(), UABlocks.PETAL_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_BRANCH_CORAL_WALL_FAN.get(), UABlocks.BRANCH_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_ROCK_CORAL_WALL_FAN.get(), UABlocks.ROCK_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_PILLOW_CORAL_WALL_FAN.get(), UABlocks.PILLOW_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_SILK_CORAL_WALL_FAN.get(), UABlocks.SILK_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.DEAD_CHROME_CORAL_WALL_FAN.get(), UABlocks.CHROME_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get(), UABlocks.PRISMARINE_CORAL_WALL_FAN.get());
		conversions.put(UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get(), UABlocks.PRISMARINE_CORAL_SHOWER.get());
	});
}
