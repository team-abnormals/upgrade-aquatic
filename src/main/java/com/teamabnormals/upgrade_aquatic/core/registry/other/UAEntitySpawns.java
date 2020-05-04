package com.teamabnormals.upgrade_aquatic.core.registry.other;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweed;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweedDouble;
import com.teamabnormals.upgrade_aquatic.common.entities.*;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.*;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.*;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.ForgeRegistries;

public class UAEntitySpawns {
	private static final List<EntitySpawn<? extends MobEntity>> SPAWNS = Util.make(Lists.newArrayList(), spawns -> {
		spawns.add(new EntitySpawn<>(() -> UAEntities.NAUTILUS.get(), new SpawnEntry(EntityClassification.WATER_CREATURE, 51, 1, 4), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UAEntitySpawns::ravineMobCondition, notColdOceanCondition()));
		spawns.add(new PikeEntitySpawn<>(() -> UAEntities.PIKE.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING));
		spawns.add(new EntitySpawn<>(() -> UAEntities.LIONFISH.get(), new SpawnEntry(EntityClassification.WATER_CREATURE, 15, 1, 1), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UAEntitySpawns::coralCondition, hotOceanCondition()));
		spawns.add(new ThrasherEntitySpawn<>(() -> UAEntities.THRASHER.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UAEntitySpawns.coldOceanCondition()));
	
		spawns.add(new EntitySpawn<>(() -> UAEntities.BOX_JELLYFISH.get(), new SpawnEntry(EntityClassification.WATER_CREATURE, 6, 1, 2), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractEntityJellyfish::defaultSpawnCondition, warmishOceanCondition()));
		spawns.add(new EntitySpawn<>(() -> UAEntities.CASSIOPEA_JELLYFISH.get(), new SpawnEntry(EntityClassification.WATER_CREATURE, 7, 1, 3), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractEntityJellyfish::defaultSpawnCondition, (biome) -> biome == Biomes.LUKEWARM_OCEAN));
	});
	
	public static void registerSpawnPlacements() {
		SPAWNS.forEach(entitySpawns -> {
			entitySpawns.registerSpawnPlacement();
		});
	}
	
	public static void processSpawnAdditions() {
		SPAWNS.forEach(entitySpawns -> {
			entitySpawns.processSpawnAddition();
		});
	}
	
	private static boolean ravineMobCondition(EntityType<? extends CreatureEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		return pos.getY() <= 30;
	}
	
	public static boolean pickerelCondition(EntityType<? extends EntityPike> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		for(int yy = pos.getY() - 2; yy <= pos.getY() + 2; yy++) {
			for(int xx = pos.getX() - 6; xx <= pos.getX() + 6; xx++) {
				for(int zz = pos.getZ() - 6; zz <= pos.getZ() + 6; zz++) {
					if(world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() instanceof BlockPickerelweed || world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() instanceof BlockPickerelweedDouble) {
						if(random.nextFloat() <= 0.125F)
							if(world.getBiome(pos).getCategory() == Category.SWAMP) {
								return random.nextFloat() <= 0.25 ? true : false;
							}
						return true;
					}
				}
			}
		}
		return random.nextFloat() <= 0.05F ? true : false;
	}
	
	private static boolean coralCondition(EntityType<? extends Entity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		for(int yy = pos.getY() - 2; yy <= pos.getY() + 2; yy++) {
			for(int xx = pos.getX() - 6; xx <= pos.getX() + 6; xx++) {
				for(int zz = pos.getZ() - 6; zz <= pos.getZ() + 6; zz++) {
					if(world.getBlockState(new BlockPos(xx, yy, zz)).getBlock().isIn(BlockTags.CORAL_BLOCKS)) {
						return true;
					}
				}
			}
		}
		return false;
    }
	
	private static boolean thrasherCondition(EntityType<? extends CreatureEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		return pos.getY() <= 30 ? world.getWorld().isNightTime() ? true : random.nextFloat() < 0.75F : false;
	}
	
	private static Predicate<Biome> coldOceanCondition() {
		return biome -> biome.getCategory() == Category.OCEAN && BiomeDictionary.hasType(biome, Type.COLD);
	}
	
	private static Predicate<Biome> notColdOceanCondition() {
		return biome -> biome.getCategory() == Category.OCEAN && !BiomeDictionary.hasType(biome, Type.COLD);
	}
	
	private static Predicate<Biome> hotOceanCondition() {
		return biome -> biome.getCategory() == Category.OCEAN && BiomeDictionary.hasType(biome, Type.HOT);
	}
	
	public static Predicate<Biome> warmishOceanCondition() {
		return biome -> biome == Biomes.WARM_OCEAN || biome == Biomes.LUKEWARM_OCEAN;
	}
	
	static class EntitySpawn<T extends MobEntity> {
		public final Supplier<EntityType<T>> entity;
		public final SpawnEntry spawnEntry;
		public final PlacementType placementType;
		public final Heightmap.Type heightmapType;
		public final EntitySpawnPlacementRegistry.IPlacementPredicate<T> placementPredicate;
		public final Predicate<Biome> biomePredicate;
		
		public EntitySpawn(Supplier<EntityType<T>> entity, SpawnEntry spawnEntry, PlacementType placementType, Heightmap.Type heightmapType, EntitySpawnPlacementRegistry.IPlacementPredicate<T> placementPredicate, Predicate<Biome> biomePredicate) {
			this.entity = entity;
			this.spawnEntry = spawnEntry;
			this.placementType = placementType;
			this.heightmapType = heightmapType;
			this.placementPredicate = placementPredicate;
			this.biomePredicate = biomePredicate;
		}
		
		public void registerSpawnPlacement() {
			EntitySpawnPlacementRegistry.register(this.entity.get(), this.placementType, this.heightmapType, this.placementPredicate);
		}
		
		public void processSpawnAddition() {
			ForgeRegistries.BIOMES.getEntries().stream().filter(biome -> this.biomePredicate.test(biome.getValue())).forEach((biome) -> {
				biome.getValue().addSpawn(this.spawnEntry.classification, new SpawnListEntry(this.entity.get(), this.spawnEntry.weight, this.spawnEntry.minGroup, this.spawnEntry.maxGroup));
			});
		}
	}
	
	static class PikeEntitySpawn<T extends EntityPike> extends EntitySpawn<T> {
		
		public PikeEntitySpawn(Supplier<EntityType<T>> entity, PlacementType placementType, Heightmap.Type heightmapType) {
			super(entity, null, placementType, heightmapType, UAEntitySpawns::pickerelCondition, null);
		}
		
		@Override
		public void registerSpawnPlacement() {
			EntitySpawnPlacementRegistry.register(this.entity.get(), this.placementType, this.heightmapType, this.placementPredicate);
		}
		
		@Override
		public void processSpawnAddition() {
			ForgeRegistries.BIOMES.getEntries().forEach(biomeEntry -> {
				Biome biome = biomeEntry.getValue();
				if(biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.RIVER) {
					if(biome.getCategory() == Category.SWAMP) {
						biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(UAEntities.PIKE.get(), 5, 1, 2));
						biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(EntityType.SQUID, 5, 1, 2));
						biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(EntityType.SALMON, 5, 1, 5));
		        	} else {
		        		biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(UAEntities.PIKE.get(), 11, 1, 2));
		        	}
		        }
			});
		}
		
	}
	
	static class ThrasherEntitySpawn<T extends EntityThrasher> extends EntitySpawn<T> {

		public ThrasherEntitySpawn(Supplier<EntityType<T>> thrasher, PlacementType placementType, Heightmap.Type heightmapType, Predicate<Biome> biomePredicate) {
			super(thrasher, null, placementType, heightmapType, null, biomePredicate);
		}
		
		@Override
		public void registerSpawnPlacement() {
			EntitySpawnPlacementRegistry.register(this.entity.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UAEntitySpawns::thrasherCondition);
			EntitySpawnPlacementRegistry.register(UAEntities.GREAT_THRASHER.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UAEntitySpawns::thrasherCondition);
		}
		
		@Override
		public void processSpawnAddition() {
			ForgeRegistries.BIOMES.getEntries().stream().filter(biome -> this.biomePredicate.test(biome.getValue())).forEach((biome) -> {
				biome.getValue().addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(this.entity.get(), 90, 1, 2));
			});
		}
		
	}
	
	static class SpawnEntry {
		public final EntityClassification classification;
		public final int weight, minGroup, maxGroup;
		
		public SpawnEntry(EntityClassification classification, int weight, int minGroup, int maxGroup) {
			this.classification = classification;
			this.weight = weight;
			this.minGroup = minGroup;
			this.maxGroup = maxGroup;
		}
	}
	
}