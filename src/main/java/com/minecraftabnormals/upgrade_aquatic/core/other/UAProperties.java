package com.minecraftabnormals.upgrade_aquatic.core.other;

import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class UAProperties {
	public static final Block.Properties DEAD_CORAL_BLOCK = Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F);
	public static final Block.Properties DEAD_CORAL = Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0F, 0F);

	public static Block.Properties createCoral(MaterialColor color) {
		return Block.Properties.create(Material.CORAL, color).doesNotBlockMovement().sound(SoundType.WET_GRASS);
	}

	public static Block.Properties createCoralBlock(MaterialColor color) {
		return Block.Properties.create(Material.ROCK, color).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL);
	}

	public static Block.Properties createCoralFan(MaterialColor color) {
		return Block.Properties.create(Material.OCEAN_PLANT, color).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.WET_GRASS);
	}

	public static Block.Properties createPrismarineCoral(boolean elder) {
		MaterialColor color = !elder ? MaterialColor.DIAMOND : MaterialColor.WHITE_TERRACOTTA;
		return Block.Properties.create(Material.CORAL, color).doesNotBlockMovement().setLightLevel((unknown) -> (5)).sound(SoundType.GLASS);
	}

	public static Block.Properties createPrismarineCoralBlock(boolean elder) {
		MaterialColor color = !elder ? MaterialColor.DIAMOND : MaterialColor.WHITE_TERRACOTTA;
		return Block.Properties.create(Material.ROCK, color).hardnessAndResistance(1.5F, 6.0F).setLightLevel((unknown) -> (6)).sound(SoundType.GLASS);
	}

	public static final Block.Properties ELDER_PRISMARINE_CORAL = Block.Properties.create(Material.CORAL, MaterialColor.WHITE_TERRACOTTA).setLightLevel((unknown) -> (3)).sound(SoundType.GLASS);

	public static final Block.Properties LUMINOUS_PRISMARINE = Block.Properties.create(Material.ROCK, MaterialColor.DIAMOND).hardnessAndResistance(1.5F, 6.0F).setLightLevel((unknown) -> (8)).setNeedsPostProcessing(UAProperties::needsPostProcessing).setEmmisiveRendering(UAProperties::needsPostProcessing);
	public static final Block.Properties LUMINOUS_ELDER_PRISMARINE = Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).setLightLevel((unknown) -> (8)).setNeedsPostProcessing(UAProperties::needsPostProcessing).setEmmisiveRendering(UAProperties::needsPostProcessing);

	public static final Block.Properties createSearocket(boolean pink) {
		MaterialColor color = pink ? MaterialColor.PINK : MaterialColor.WHITE_TERRACOTTA;
		return Block.Properties.create(Material.PLANTS, color).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT);
	}

	public static Block.Properties createLeaves() {
		return AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().harvestTool(ToolType.HOE).setAllowsSpawn(UAProperties::allowsSpawnOnLeaves).setSuffocates(UAProperties::isntSolid).setBlocksVision(UAProperties::isntSolid);
	}

	private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}

	public static final Block.Properties CORALSTONE = Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE).tickRandomly();

	public static final Block.Properties DRIFTWOOD = Block.Properties.create(Material.WOOD, MaterialColor.STONE).sound(SoundType.WOOD).hardnessAndResistance(2);
	public static final Block.Properties RIVER_WOOD = Block.Properties.create(Material.WOOD, MaterialColor.BROWN).sound(SoundType.WOOD).hardnessAndResistance(2);
	public static final Block.Properties SPINES = Block.Properties.create(Material.ORGANIC).doesNotBlockMovement().hardnessAndResistance(1.5F);
	public static final Block.Properties ELDER_EYE = Block.Properties.create(Material.ORGANIC, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.METAL).hardnessAndResistance(1.0F);
	public static final Block.Properties BEDROLL = Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH);
	public static final Block.Properties PICKERELWEED = Block.Properties.create(Material.PLANTS, MaterialColor.GRASS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS);
	public static final Block.Properties BEACHGRASS_THATCH = Block.Properties.create(Material.ORGANIC, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.PLANT).notSolid().harvestTool(ToolType.HOE);

	public static final Block.Properties createPickerelweedBlock(boolean isBoiled) {
		return isBoiled ? Block.Properties.create(Material.PLANTS, MaterialColor.FOLIAGE).hardnessAndResistance(0.5F, 5).notSolid().sound(SoundType.PLANT).harvestTool(ToolType.HOE) : Block.Properties.create(Material.PLANTS, MaterialColor.GRASS).notSolid().hardnessAndResistance(0.5F, 5).sound(SoundType.WET_GRASS).harvestTool(ToolType.HOE);
	}
}
