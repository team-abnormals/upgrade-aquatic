package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.Lionfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

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

	public static boolean ravineMobCondition(EntityType<? extends PathfinderMob> entityType, LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
		if (((Level) world).dimension() != Level.OVERWORLD) return false;
		return pos.getY() <= UAConfig.COMMON.deepOceanMobMaxHeight.get();
	}
}