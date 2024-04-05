package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.*;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.BoxJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.CassiopeaJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ImmortalJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Flare;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.GreatThrasher;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.common.entity.projectile.SonarWave;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UAEntityTypes {
	public static final EntitySubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<SonarWave>> SONAR_WAVE = HELPER.createEntity("sonar_wave", SonarWave::new, SonarWave::new, MobCategory.MISC, 1.0F, 1.0F);
	public static final RegistryObject<EntityType<Nautilus>> NAUTILUS = HELPER.createLivingEntity("nautilus", Nautilus::new, MobCategory.WATER_AMBIENT, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<Pike>> PIKE = HELPER.createLivingEntity("pike", Pike::new, MobCategory.WATER_AMBIENT, 0.7F, 0.4F);
	public static final RegistryObject<EntityType<Perch>> PERCH = HELPER.createLivingEntity("perch", Perch::new, MobCategory.WATER_AMBIENT, 0.6F, 0.5F);
	public static final RegistryObject<EntityType<Lionfish>> LIONFISH = HELPER.createLivingEntity("lionfish", Lionfish::new, MobCategory.WATER_AMBIENT, 0.6F, 0.5F);
	public static final RegistryObject<EntityType<Thrasher>> THRASHER = HELPER.createLivingEntity("thrasher", Thrasher::new, MobCategory.MONSTER, 1.6F, 0.9F);
	public static final RegistryObject<EntityType<GreatThrasher>> GREAT_THRASHER = HELPER.createLivingEntity("great_thrasher", GreatThrasher::new, MobCategory.MONSTER, 2.8F, 1.575F);
	public static final RegistryObject<EntityType<Flare>> FLARE = HELPER.createLivingEntity("flare", Flare::new, MobCategory.MONSTER, 0.9F, 0.5F);
	public static final RegistryObject<EntityType<Goose>> GOOSE = HELPER.createLivingEntity("goose", Goose::new, MobCategory.CREATURE, 0.5F, 0.9F);

	public static final RegistryObject<EntityType<BoxJellyfish>> BOX_JELLYFISH = HELPER.createLivingEntity("box_jellyfish", BoxJellyfish::new, MobCategory.WATER_CREATURE, 0.75F, 0.625F);
	public static final RegistryObject<EntityType<CassiopeaJellyfish>> CASSIOPEA_JELLYFISH = HELPER.createLivingEntity("cassiopea_jellyfish", CassiopeaJellyfish::new, MobCategory.WATER_CREATURE, 0.6875F, 0.25F);
	public static final RegistryObject<EntityType<ImmortalJellyfish>> IMMORTAL_JELLYFISH = HELPER.createLivingEntity("immortal_jellyfish", ImmortalJellyfish::new, MobCategory.WATER_CREATURE, 0.625F, 0.5F);

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(NAUTILUS.get(), Nautilus.registerAttributes().build());
		event.put(PIKE.get(), Pike.registerAttributes().build());
		event.put(LIONFISH.get(), Lionfish.registerAttributes().build());
		event.put(PERCH.get(), Perch.createAttributes().build());
		event.put(THRASHER.get(), Thrasher.registerAttributes().build());
		event.put(GREAT_THRASHER.get(), GreatThrasher.registerAttributes().build());
		event.put(FLARE.get(), Flare.registerAttributes().build());
		event.put(GOOSE.get(), Goose.registerAttributes().build());

		event.put(BOX_JELLYFISH.get(), BoxJellyfish.registerAttributes().build());
		event.put(CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfish.registerAttributes().build());
		event.put(IMMORTAL_JELLYFISH.get(), ImmortalJellyfish.registerAttributes().build());
	}

	@SubscribeEvent
	public static void registerEntitySpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(UAEntityTypes.NAUTILUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Nautilus::checkNautilusSpawnRules, Operation.AND);
		event.register(UAEntityTypes.LIONFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Lionfish::checkLionfishSpawnRules, Operation.AND);
		event.register(UAEntityTypes.PIKE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Pike::checkPikeSpawnRules, Operation.AND);
		event.register(UAEntityTypes.PERCH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, Operation.AND);
		event.register(UAEntityTypes.THRASHER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Thrasher::checkThrasherSpawnRules, Operation.AND);
		event.register(UAEntityTypes.GREAT_THRASHER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, Thrasher::checkThrasherSpawnRules, Operation.AND);

		event.register(UAEntityTypes.BOX_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfish::checkJellyfishSpawnRules, Operation.AND);
		event.register(UAEntityTypes.CASSIOPEA_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfish::checkJellyfishSpawnRules, Operation.AND);
		event.register(UAEntityTypes.IMMORTAL_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractJellyfish::checkJellyfishSpawnRules, Operation.AND);
	}
}