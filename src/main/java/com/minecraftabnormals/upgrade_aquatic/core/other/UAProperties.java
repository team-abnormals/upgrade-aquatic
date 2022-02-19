package com.minecraftabnormals.upgrade_aquatic.core.other;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class UAProperties {
	public static final BlockBehaviour.Properties DEAD_CORAL_BLOCK = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(1.5F, 6.0F);
	public static final BlockBehaviour.Properties DEAD_CORAL = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).noCollission().strength(0F, 0F);

	public static final BlockBehaviour.Properties LUMINOUS_PRISMARINE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DIAMOND).strength(1.5F, 6.0F).lightLevel((unknown) -> (8)).hasPostProcess(UAProperties::needsPostProcessing).emissiveRendering(UAProperties::needsPostProcessing);
	public static final BlockBehaviour.Properties LUMINOUS_ELDER_PRISMARINE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(1.5F, 6.0F).lightLevel((unknown) -> (8)).hasPostProcess(UAProperties::needsPostProcessing).emissiveRendering(UAProperties::needsPostProcessing);

	public static final BlockBehaviour.Properties LEAVES = BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(UAProperties::allowsSpawnOnLeaves).isSuffocating(UAProperties::notSolid).isViewBlocking(UAProperties::notSolid);
	public static final BlockBehaviour.Properties LEAF_CARPET = BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.0F).sound(SoundType.GRASS).noOcclusion();

	public static final BlockBehaviour.Properties CORALSTONE = Properties.copy(Blocks.STONE).randomTicks();

	public static final BlockBehaviour.Properties DRIFTWOOD = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).sound(SoundType.WOOD).strength(2);
	public static final BlockBehaviour.Properties RIVER_WOOD = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2);
	public static final BlockBehaviour.Properties SPINES = BlockBehaviour.Properties.of(Material.GRASS).noCollission().strength(1.5F);
	public static final BlockBehaviour.Properties ELDER_EYE = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1.0F);
	public static final BlockBehaviour.Properties PICKERELWEED = BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.GRASS).randomTicks().noCollission().strength(0.0F).sound(SoundType.WET_GRASS);
	public static final BlockBehaviour.Properties BEACHGRASS_THATCH = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.GRASS).noOcclusion();

	public static BlockBehaviour.Properties createCoral(MaterialColor color) {
		return BlockBehaviour.Properties.of(Material.WATER_PLANT, color).noCollission().sound(SoundType.WET_GRASS);
	}

	public static BlockBehaviour.Properties createCoralBlock(MaterialColor color) {
		return BlockBehaviour.Properties.of(Material.STONE, color).strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK);
	}

	public static BlockBehaviour.Properties createCoralFan(MaterialColor color) {
		return BlockBehaviour.Properties.of(Material.WATER_PLANT, color).noCollission().strength(0).sound(SoundType.WET_GRASS);
	}

	public static BlockBehaviour.Properties createPrismarineCoral(boolean elder) {
		MaterialColor color = !elder ? MaterialColor.DIAMOND : MaterialColor.TERRACOTTA_WHITE;
		return BlockBehaviour.Properties.of(Material.WATER_PLANT, color).noCollission().lightLevel((unknown) -> (5)).sound(SoundType.GLASS);
	}

	public static BlockBehaviour.Properties createPrismarineCoralBlock(boolean elder) {
		MaterialColor color = !elder ? MaterialColor.DIAMOND : MaterialColor.TERRACOTTA_WHITE;
		return BlockBehaviour.Properties.of(Material.STONE, color).strength(1.5F, 6.0F).lightLevel((unknown) -> (6)).sound(SoundType.GLASS);
	}

	public static BlockBehaviour.Properties createSearocket(boolean pink) {
		MaterialColor color = pink ? MaterialColor.COLOR_PINK : MaterialColor.TERRACOTTA_WHITE;
		return BlockBehaviour.Properties.of(Material.PLANT, color).randomTicks().noCollission().strength(0.0F).sound(SoundType.GRASS);
	}

	public static BlockBehaviour.Properties createPickerelweedBlock(boolean isBoiled) {
		return isBoiled ? BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.PLANT).strength(0.5F, 5).noOcclusion().sound(SoundType.GRASS) : BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.GRASS).noOcclusion().strength(0.5F, 5).sound(SoundType.WET_GRASS);
	}

	private static Boolean allowsSpawnOnLeaves(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean needsPostProcessing(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	private static boolean notSolid(BlockState state, BlockGetter reader, BlockPos pos) {
		return false;
	}
}
