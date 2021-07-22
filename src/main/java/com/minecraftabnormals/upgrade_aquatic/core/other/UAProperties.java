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
	public static final AbstractBlock.Properties DEAD_CORAL_BLOCK = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(1.5F, 6.0F);
	public static final AbstractBlock.Properties DEAD_CORAL = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).noCollission().strength(0F, 0F);

	public static final AbstractBlock.Properties LUMINOUS_PRISMARINE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIAMOND).strength(1.5F, 6.0F).lightLevel((unknown) -> (8)).hasPostProcess(UAProperties::needsPostProcessing).emissiveRendering(UAProperties::needsPostProcessing);
	public static final AbstractBlock.Properties LUMINOUS_ELDER_PRISMARINE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(1.5F, 6.0F).lightLevel((unknown) -> (8)).hasPostProcess(UAProperties::needsPostProcessing).emissiveRendering(UAProperties::needsPostProcessing);

	public static final AbstractBlock.Properties LEAVES = AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().harvestTool(ToolType.HOE).isValidSpawn(UAProperties::allowsSpawnOnLeaves).isSuffocating(UAProperties::notSolid).isViewBlocking(UAProperties::notSolid);
	public static final AbstractBlock.Properties LEAF_CARPET = AbstractBlock.Properties.of(Material.CLOTH_DECORATION).strength(0.0F).sound(SoundType.GRASS).harvestTool(ToolType.HOE).noOcclusion();

	public static final AbstractBlock.Properties CORALSTONE = Properties.copy(Blocks.STONE).harvestTool(ToolType.PICKAXE).randomTicks();

	public static final AbstractBlock.Properties DRIFTWOOD = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.STONE).sound(SoundType.WOOD).strength(2);
	public static final AbstractBlock.Properties RIVER_WOOD = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2);
	public static final AbstractBlock.Properties SPINES = AbstractBlock.Properties.of(Material.GRASS).noCollission().strength(1.5F);
	public static final AbstractBlock.Properties ELDER_EYE = AbstractBlock.Properties.of(Material.GRASS, MaterialColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1.0F);
	public static final AbstractBlock.Properties PICKERELWEED = AbstractBlock.Properties.of(Material.PLANT, MaterialColor.GRASS).randomTicks().noCollission().strength(0.0F).sound(SoundType.WET_GRASS);
	public static final AbstractBlock.Properties BEACHGRASS_THATCH = AbstractBlock.Properties.of(Material.GRASS, MaterialColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.GRASS).noOcclusion().harvestTool(ToolType.HOE);

	public static AbstractBlock.Properties createCoral(MaterialColor color) {
		return AbstractBlock.Properties.of(Material.CORAL, color).noCollission().sound(SoundType.WET_GRASS);
	}

	public static AbstractBlock.Properties createCoralBlock(MaterialColor color) {
		return AbstractBlock.Properties.of(Material.STONE, color).strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK);
	}

	public static AbstractBlock.Properties createCoralFan(MaterialColor color) {
		return AbstractBlock.Properties.of(Material.WATER_PLANT, color).noCollission().strength(0).sound(SoundType.WET_GRASS);
	}

	public static AbstractBlock.Properties createPrismarineCoral(boolean elder) {
		MaterialColor color = !elder ? MaterialColor.DIAMOND : MaterialColor.TERRACOTTA_WHITE;
		return AbstractBlock.Properties.of(Material.CORAL, color).noCollission().lightLevel((unknown) -> (5)).sound(SoundType.GLASS);
	}

	public static AbstractBlock.Properties createPrismarineCoralBlock(boolean elder) {
		MaterialColor color = !elder ? MaterialColor.DIAMOND : MaterialColor.TERRACOTTA_WHITE;
		return AbstractBlock.Properties.of(Material.STONE, color).strength(1.5F, 6.0F).lightLevel((unknown) -> (6)).sound(SoundType.GLASS);
	}

	public static AbstractBlock.Properties createSearocket(boolean pink) {
		MaterialColor color = pink ? MaterialColor.COLOR_PINK : MaterialColor.TERRACOTTA_WHITE;
		return AbstractBlock.Properties.of(Material.PLANT, color).randomTicks().noCollission().strength(0.0F).sound(SoundType.GRASS);
	}

	public static AbstractBlock.Properties createPickerelweedBlock(boolean isBoiled) {
		return isBoiled ? AbstractBlock.Properties.of(Material.PLANT, MaterialColor.PLANT).strength(0.5F, 5).noOcclusion().sound(SoundType.GRASS).harvestTool(ToolType.HOE) : AbstractBlock.Properties.of(Material.PLANT, MaterialColor.GRASS).noOcclusion().strength(0.5F, 5).sound(SoundType.WET_GRASS).harvestTool(ToolType.HOE);
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
