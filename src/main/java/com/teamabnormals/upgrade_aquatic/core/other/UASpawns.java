package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.Lionfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Random;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UASpawns {

	public static void registerSpawns() {
		SpawnPlacements.register(UAEntityTypes.NAUTILUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, UASpawns::ravineMobCondition);
		SpawnPlacements.register(UAEntityTypes.LIONFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Lionfish::coralCondition);
		SpawnPlacements.register(UAEntityTypes.PIKE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Pike::pickerelCondition);
		SpawnPlacements.register(UAEntityTypes.PERCH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(UAEntityTypes.THRASHER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Thrasher::thrasherCondition);
		SpawnPlacements.register(UAEntityTypes.GREAT_THRASHER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Thrasher::thrasherCondition);

		SpawnPlacements.register(UAEntityTypes.BOX_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfish::defaultSpawnCondition);
		SpawnPlacements.register(UAEntityTypes.CASSIOPEA_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfish::defaultSpawnCondition);
		SpawnPlacements.register(UAEntityTypes.IMMORTAL_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfish::defaultSpawnCondition);
	}

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		if (event.getName() == null) return;
		ResourceLocation biome = event.getName();
		MobSpawnSettingsBuilder spawns = event.getSpawns();
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, biome);

		if (event.getCategory() == Biome.BiomeCategory.OCEAN) {
			if (BiomeDictionary.hasType(key, BiomeDictionary.Type.COLD)) {
				if (UAConfig.COMMON.thrasherWeight.get() > 0) {
					spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(UAEntityTypes.THRASHER.get(), UAConfig.COMMON.thrasherWeight.get(), 1, 2));
				}
			} else if (UAConfig.COMMON.nautilusWeight.get() > 0) {
				spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntityTypes.NAUTILUS.get(), UAConfig.COMMON.nautilusWeight.get(), 1, 4));
			}

			if (BiomeDictionary.hasType(key, BiomeDictionary.Type.HOT) && UAConfig.COMMON.lionfishWeight.get() > 0) {
				spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntityTypes.LIONFISH.get(), UAConfig.COMMON.lionfishWeight.get(), 1, 1));
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
			spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntityTypes.PIKE.get(), UAConfig.COMMON.pikeWeight.get(), 1, 2));
		}

		if (event.getCategory() == Biome.BiomeCategory.SWAMP) {
			if (UAConfig.COMMON.pikeSwampWeight.get() > 0)
				spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntityTypes.PIKE.get(), UAConfig.COMMON.pikeSwampWeight.get(), 1, 2));
			if (UAConfig.COMMON.squidSwampWeight.get() > 0)
				spawns.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SQUID, UAConfig.COMMON.squidSwampWeight.get(), 1, 2));
			if (UAConfig.COMMON.perchWeight.get() > 0)
				spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(UAEntityTypes.PERCH.get(), UAConfig.COMMON.perchWeight.get(), 1, 6));
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