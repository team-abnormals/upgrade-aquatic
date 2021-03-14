package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.common.entities.LionfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UAConfig;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UASpawns {

	public static void registerSpawns() {
		EntitySpawnPlacementRegistry.register(UAEntities.NAUTILUS.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UASpawns::ravineMobCondition);
		EntitySpawnPlacementRegistry.register(UAEntities.LIONFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, LionfishEntity::coralCondition);
		EntitySpawnPlacementRegistry.register(UAEntities.PIKE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, PikeEntity::pickerelCondition);
		EntitySpawnPlacementRegistry.register(UAEntities.PERCH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractFishEntity::func_223363_b);
		EntitySpawnPlacementRegistry.register(UAEntities.GLOW_SQUID.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UASpawns::ravineMobCondition);
		EntitySpawnPlacementRegistry.register(UAEntities.THRASHER.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, ThrasherEntity::thrasherCondition);
		EntitySpawnPlacementRegistry.register(UAEntities.GREAT_THRASHER.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, ThrasherEntity::thrasherCondition);

		EntitySpawnPlacementRegistry.register(UAEntities.BOX_JELLYFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractJellyfishEntity::defaultSpawnCondition);
		EntitySpawnPlacementRegistry.register(UAEntities.CASSIOPEA_JELLYFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractJellyfishEntity::defaultSpawnCondition);
		EntitySpawnPlacementRegistry.register(UAEntities.IMMORTAL_JELLYFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractJellyfishEntity::defaultSpawnCondition);
	}

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		if (event.getName() == null) return;
		ResourceLocation biome = event.getName();
		MobSpawnInfoBuilder spawns = event.getSpawns();
		RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, biome);

		if (event.getCategory() == Biome.Category.OCEAN && UAConfig.COMMON.glowSquidWeight.get() > 0) {
			spawns.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(UAEntities.GLOW_SQUID.get(), UAConfig.COMMON.glowSquidWeight.get(), 1, 1));

			if (BiomeDictionary.hasType(key, BiomeDictionary.Type.COLD) && UAConfig.COMMON.thrasherWeight.get() > 0) {
				spawns.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(UAEntities.THRASHER.get(), UAConfig.COMMON.thrasherWeight.get(), 1, 2));
			} else if (UAConfig.COMMON.nautilusWeight.get() > 0) {
				spawns.withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(UAEntities.NAUTILUS.get(), UAConfig.COMMON.nautilusWeight.get(), 1, 4));
			}

			if (BiomeDictionary.hasType(key, BiomeDictionary.Type.HOT) && UAConfig.COMMON.lionfishWeight.get() > 0) {
				spawns.withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(UAEntities.LIONFISH.get(), UAConfig.COMMON.lionfishWeight.get(), 1, 1));
			}

//			if (isWarmOcean(biome) || isLukewarmOcean(biome)) {
//				spawns.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(UAEntities.BOX_JELLYFISH.get(), 6, 1, 2));
//				spawns.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(UAEntities.IMMORTAL_JELLYFISH.get(), 7, 1, 3));
//				if (isLukewarmOcean(biome)) {
//					spawns.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(UAEntities.CASSIOPEA_JELLYFISH.get(), 7, 1, 3));
//				}
//			}
		}

		if (event.getCategory() == Biome.Category.RIVER && UAConfig.COMMON.pikeWeight.get() > 0) {
			spawns.withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(UAEntities.PIKE.get(), UAConfig.COMMON.pikeWeight.get(), 1, 2));
		}

		if (event.getCategory() == Biome.Category.SWAMP) {
			if (UAConfig.COMMON.pikeSwampWeight.get() > 0) spawns.withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(UAEntities.PIKE.get(), UAConfig.COMMON.pikeSwampWeight.get(), 1, 2));
			if (UAConfig.COMMON.squidSwampWeight.get() > 0) spawns.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.SQUID, UAConfig.COMMON.squidSwampWeight.get(), 1, 2));
			if (UAConfig.COMMON.perchWeight.get() > 0) spawns.withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(UAEntities.PERCH.get(), UAConfig.COMMON.perchWeight.get(), 1, 5));
		}
	}

	public static boolean ravineMobCondition(EntityType<? extends CreatureEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if (((World) world).getDimensionKey() != World.OVERWORLD) return false;
		return pos.getY() <= UAConfig.COMMON.deepOceanMobMaxHeight.get();
	}

	public static boolean isLukewarmOcean(ResourceLocation biome) {
		return biome.equals(Biomes.LUKEWARM_OCEAN.getLocation()) || biome.equals(Biomes.DEEP_LUKEWARM_OCEAN.getLocation());
	}

	public static boolean isWarmOcean(ResourceLocation biome) {
		return biome.equals(Biomes.WARM_OCEAN.getLocation()) || biome.equals(Biomes.DEEP_WARM_OCEAN.getLocation());
	}
}