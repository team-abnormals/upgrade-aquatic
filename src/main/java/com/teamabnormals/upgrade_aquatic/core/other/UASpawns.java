package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.LionfishEntity;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfishEntity;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.pike.PikeEntity;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.thrasher.ThrasherEntity;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UASpawns {

	public static void registerSpawns() {
		SpawnPlacements.register(UAEntities.NAUTILUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, UASpawns::ravineMobCondition);
		SpawnPlacements.register(UAEntities.LIONFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, LionfishEntity::coralCondition);
		SpawnPlacements.register(UAEntities.PIKE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, PikeEntity::pickerelCondition);
		SpawnPlacements.register(UAEntities.PERCH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(UAEntities.GLOW_SQUID.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, UASpawns::ravineMobCondition);
		SpawnPlacements.register(UAEntities.THRASHER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, ThrasherEntity::thrasherCondition);
		SpawnPlacements.register(UAEntities.GREAT_THRASHER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, ThrasherEntity::thrasherCondition);

		SpawnPlacements.register(UAEntities.BOX_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfishEntity::defaultSpawnCondition);
		SpawnPlacements.register(UAEntities.CASSIOPEA_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfishEntity::defaultSpawnCondition);
		SpawnPlacements.register(UAEntities.IMMORTAL_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfishEntity::defaultSpawnCondition);
	}

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		if (event.getName() == null) return;
		ResourceLocation biome = event.getName();
		MobSpawnSettingsBuilder spawns = event.getSpawns();
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, biome);

		if (event.getCategory() == Biome.BiomeCategory.OCEAN && UAConfig.COMMON.glowSquidWeight.get() > 0) {
			spawns.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(UAEntities.GLOW_SQUID.get(), UAConfig.COMMON.glowSquidWeight.get(), 1, 1));

			if (BiomeDictionary.hasType(key, BiomeDictionary.Type.COLD) && UAConfig.COMMON.thrasherWeight.get() > 0) {
				spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UAEntities.THRASHER.get(), UAConfig.COMMON.thrasherWeight.get(), 1, 2));
			} else if (UAConfig.COMMON.nautilusWeight.get() > 0) {
				spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntities.NAUTILUS.get(), UAConfig.COMMON.nautilusWeight.get(), 1, 4));
			}

			if (BiomeDictionary.hasType(key, BiomeDictionary.Type.HOT) && UAConfig.COMMON.lionfishWeight.get() > 0) {
				spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntities.LIONFISH.get(), UAConfig.COMMON.lionfishWeight.get(), 1, 1));
			}

//			if (isWarmOcean(biome) || isLukewarmOcean(biome)) {
//				spawns.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(UAEntities.BOX_JELLYFISH.get(), 6, 1, 2));
//				spawns.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(UAEntities.IMMORTAL_JELLYFISH.get(), 7, 1, 3));
//				if (isLukewarmOcean(biome)) {
//					spawns.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(UAEntities.CASSIOPEA_JELLYFISH.get(), 7, 1, 3));
//				}
//			}
		}

		if (event.getCategory() == Biome.BiomeCategory.RIVER && UAConfig.COMMON.pikeWeight.get() > 0) {
			spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntities.PIKE.get(), UAConfig.COMMON.pikeWeight.get(), 1, 2));
		}

		if (event.getCategory() == Biome.BiomeCategory.SWAMP) {
			if (UAConfig.COMMON.pikeSwampWeight.get() > 0)
				spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntities.PIKE.get(), UAConfig.COMMON.pikeSwampWeight.get(), 1, 2));
			if (UAConfig.COMMON.squidSwampWeight.get() > 0)
				spawns.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SQUID, UAConfig.COMMON.squidSwampWeight.get(), 1, 2));
			if (UAConfig.COMMON.perchWeight.get() > 0)
				spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntities.PERCH.get(), UAConfig.COMMON.perchWeight.get(), 1, 6));
		}
	}

	public static boolean ravineMobCondition(EntityType<? extends PathfinderMob> entityType, LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, Random random) {
		if (((Level) world).dimension() != Level.OVERWORLD) return false;
		return pos.getY() <= UAConfig.COMMON.deepOceanMobMaxHeight.get();
	}

	public static boolean isLukewarmOcean(ResourceLocation biome) {
		return biome.equals(Biomes.LUKEWARM_OCEAN.location()) || biome.equals(Biomes.DEEP_LUKEWARM_OCEAN.location());
	}

	public static boolean isWarmOcean(ResourceLocation biome) {
		return biome.equals(Biomes.WARM_OCEAN.location());
	}
}