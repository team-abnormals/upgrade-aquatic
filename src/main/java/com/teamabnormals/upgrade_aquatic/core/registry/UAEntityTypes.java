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
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UAEntityTypes {
	public static final EntitySubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getEntitySubHelper();

	public static final DeferredHolder<EntityType<?>, EntityType<SonarWave>> SONAR_WAVE = HELPER.createEntity("sonar_wave", SonarWave::new, MobCategory.MISC, builder -> builder
			.sized(1.0F, 1.0F).clientTrackingRange(4).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Nautilus>> NAUTILUS = HELPER.createEntity("nautilus", Nautilus::new, MobCategory.WATER_AMBIENT, builder -> builder
			.sized(0.5F, 0.5F).eyeHeight(0.325F).clientTrackingRange(8));
	public static final DeferredHolder<EntityType<?>, EntityType<Pike>> PIKE = HELPER.createEntity("pike", Pike::new, MobCategory.WATER_AMBIENT, builder -> builder
			.sized(0.7F, 0.4F).eyeHeight(0.25F).ridingOffset(0.03F).clientTrackingRange(8));
	public static final DeferredHolder<EntityType<?>, EntityType<Perch>> PERCH = HELPER.createEntity("perch", Perch::new, MobCategory.WATER_AMBIENT, builder -> builder
			.sized(0.6F, 0.5F).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<Lionfish>> LIONFISH = HELPER.createEntity("lionfish", Lionfish::new, MobCategory.WATER_AMBIENT, builder -> builder
			.sized(0.6F, 0.5F).eyeHeight(0.425F).clientTrackingRange(4));
	public static final DeferredHolder<EntityType<?>, EntityType<Thrasher>> THRASHER = HELPER.createEntity("thrasher", Thrasher::new, MobCategory.MONSTER, builder -> builder
			.sized(1.6F, 0.9F).eyeHeight(0.65F).ridingOffset(0.5F).clientTrackingRange(8));
	public static final DeferredHolder<EntityType<?>, EntityType<GreatThrasher>> GREAT_THRASHER = HELPER.createEntity("great_thrasher", GreatThrasher::new, MobCategory.MONSTER, builder -> builder
			.sized(2.8F, 1.575F).eyeHeight(0.65F).ridingOffset(0.875F).clientTrackingRange(8));
	public static final DeferredHolder<EntityType<?>, EntityType<Flare>> FLARE = HELPER.createEntity("flare", Flare::new, MobCategory.MONSTER, builder -> builder
			.sized(0.9F, 0.5F).eyeHeight(0.175F).passengerAttachments(0.3375F).ridingOffset(-0.125F).clientTrackingRange(8));
	public static final DeferredHolder<EntityType<?>, EntityType<Goose>> GOOSE = HELPER.createEntity("goose", Goose::new, MobCategory.CREATURE, builder -> builder
			.sized(0.5F, 0.9F).eyeHeight(0.644F).passengerAttachments(new Vec3(0.0, 0.7, -0.1)).clientTrackingRange(10));

	public static final DeferredHolder<EntityType<?>, EntityType<BoxJellyfish>> BOX_JELLYFISH = HELPER.createEntity("box_jellyfish", BoxJellyfish::new, MobCategory.WATER_CREATURE, builder -> builder
			.sized(0.75F, 0.625F).eyeHeight(0.3125F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<CassiopeaJellyfish>> CASSIOPEA_JELLYFISH = HELPER.createEntity("cassiopea_jellyfish", CassiopeaJellyfish::new, MobCategory.WATER_CREATURE, builder -> builder
			.sized(0.6875F, 0.25F).eyeHeight(0.125F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ImmortalJellyfish>> IMMORTAL_JELLYFISH = HELPER.createEntity("immortal_jellyfish", ImmortalJellyfish::new, MobCategory.WATER_CREATURE, builder -> builder
			.sized(0.625F, 0.5F).eyeHeight(0.4F).clientTrackingRange(10));

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
	public static void registerEntitySpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(UAEntityTypes.NAUTILUS.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Nautilus::checkNautilusSpawnRules, Operation.AND);
		event.register(UAEntityTypes.LIONFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Lionfish::checkLionfishSpawnRules, Operation.AND);
		event.register(UAEntityTypes.PIKE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pike::checkPikeSpawnRules, Operation.AND);
		event.register(UAEntityTypes.PERCH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, Operation.AND);
		event.register(UAEntityTypes.THRASHER.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Thrasher::checkThrasherSpawnRules, Operation.AND);
		event.register(UAEntityTypes.GREAT_THRASHER.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Thrasher::checkThrasherSpawnRules, Operation.AND);

		event.register(UAEntityTypes.BOX_JELLYFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfish::checkJellyfishSpawnRules, Operation.AND);
		event.register(UAEntityTypes.CASSIOPEA_JELLYFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfish::checkJellyfishSpawnRules, Operation.AND);
		event.register(UAEntityTypes.IMMORTAL_JELLYFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfish::checkJellyfishSpawnRules, Operation.AND);
	}
}