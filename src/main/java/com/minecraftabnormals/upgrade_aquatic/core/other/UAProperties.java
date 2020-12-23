package com.minecraftabnormals.upgrade_aquatic.core.other;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class UAProperties {
	public static final AbstractBlock.Properties DEAD_CORAL_BLOCK = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F);
	public static final AbstractBlock.Properties DEAD_CORAL = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0F, 0F);

	public static final AbstractBlock.Properties LUMINOUS_PRISMARINE = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.DIAMOND).hardnessAndResistance(1.5F, 6.0F).setLightLevel((unknown) -> (8)).setNeedsPostProcessing(UAProperties::needsPostProcessing).setEmmisiveRendering(UAProperties::needsPostProcessing);
	public static final AbstractBlock.Properties LUMINOUS_ELDER_PRISMARINE = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).setLightLevel((unknown) -> (8)).setNeedsPostProcessing(UAProperties::needsPostProcessing).setEmmisiveRendering(UAProperties::needsPostProcessing);

	public static final AbstractBlock.Properties LEAVES = AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().harvestTool(ToolType.HOE).setAllowsSpawn(UAProperties::allowsSpawnOnLeaves).setSuffocates(UAProperties::notSolid).setBlocksVision(UAProperties::notSolid);
	public static final AbstractBlock.Properties LEAF_CARPET = AbstractBlock.Properties.create(Material.CARPET).hardnessAndResistance(0.0F).sound(SoundType.PLANT).harvestTool(ToolType.HOE).notSolid();

	public static final AbstractBlock.Properties CORALSTONE = Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE).tickRandomly();

	public static final AbstractBlock.Properties DRIFTWOOD = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.STONE).sound(SoundType.WOOD).hardnessAndResistance(2);
	public static final AbstractBlock.Properties RIVER_WOOD = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).sound(SoundType.WOOD).hardnessAndResistance(2);
	public static final AbstractBlock.Properties SPINES = AbstractBlock.Properties.create(Material.ORGANIC).doesNotBlockMovement().hardnessAndResistance(1.5F);
	public static final AbstractBlock.Properties ELDER_EYE = AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.METAL).hardnessAndResistance(1.0F);
	public static final AbstractBlock.Properties PICKERELWEED = AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.GRASS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS);
	public static final AbstractBlock.Properties BEACHGRASS_THATCH = AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.PLANT).notSolid().harvestTool(ToolType.HOE);

	public static AbstractBlock.Properties createCoral(MaterialColor color) {
		return AbstractBlock.Properties.create(Material.CORAL, color).doesNotBlockMovement().sound(SoundType.WET_GRASS);
	}

	public static AbstractBlock.Properties createCoralBlock(MaterialColor color) {
		return AbstractBlock.Properties.create(Material.ROCK, color).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL);
	}

	public static AbstractBlock.Properties createCoralFan(MaterialColor color) {
		return AbstractBlock.Properties.create(Material.OCEAN_PLANT, color).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.WET_GRASS);
	}

	public static AbstractBlock.Properties createPrismarineCoral(boolean elder) {
		MaterialColor color = !elder ? MaterialColor.DIAMOND : MaterialColor.WHITE_TERRACOTTA;
		return AbstractBlock.Properties.create(Material.CORAL, color).doesNotBlockMovement().setLightLevel((unknown) -> (5)).sound(SoundType.GLASS);
	}

	public static AbstractBlock.Properties createPrismarineCoralBlock(boolean elder) {
		MaterialColor color = !elder ? MaterialColor.DIAMOND : MaterialColor.WHITE_TERRACOTTA;
		return AbstractBlock.Properties.create(Material.ROCK, color).hardnessAndResistance(1.5F, 6.0F).setLightLevel((unknown) -> (6)).sound(SoundType.GLASS);
	}

	public static final AbstractBlock.Properties createSearocket(boolean pink) {
		MaterialColor color = pink ? MaterialColor.PINK : MaterialColor.WHITE_TERRACOTTA;
		return AbstractBlock.Properties.create(Material.PLANTS, color).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT);
	}

	public static final AbstractBlock.Properties createPickerelweedBlock(boolean isBoiled) {
		return isBoiled ? AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.FOLIAGE).hardnessAndResistance(0.5F, 5).notSolid().sound(SoundType.PLANT).harvestTool(ToolType.HOE) : AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.GRASS).notSolid().hardnessAndResistance(0.5F, 5).sound(SoundType.WET_GRASS).harvestTool(ToolType.HOE);
	}

	private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	private static boolean notSolid(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}
}
