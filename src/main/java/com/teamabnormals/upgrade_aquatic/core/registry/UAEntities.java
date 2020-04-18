package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.function.BiFunction;

import com.teamabnormals.upgrade_aquatic.common.entities.*;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.*;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.*;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UAEntitySpawns;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Reference.MODID);
	
	public static final RegistryObject<EntityType<EntityUABoat>> BOAT 		              = ENTITY_TYPES.register("boat", () -> createEntity(EntityUABoat::new, EntityUABoat::new, EntityClassification.MISC, "boat", 1.375F, 0.5625F));
	public static final RegistryObject<EntityType<EntitySonarWave>> SONAR_WAVE            = ENTITY_TYPES.register("sonar_wave", () -> createEntity(EntitySonarWave::new, EntitySonarWave::new, EntityClassification.AMBIENT, "sonar_wave", 1.0F, 1.0F));
	public static final RegistryObject<EntityType<EntityNautilus>> NAUTILUS               = ENTITY_TYPES.register("nautilus", () -> createLivingEntity(EntityNautilus::new, EntityClassification.CREATURE, "nautilus", 0.5F, 0.5F));
	public static final RegistryObject<EntityType<EntityPike>> PIKE                       = ENTITY_TYPES.register("pike", () -> createLivingEntity(EntityPike::new, EntityClassification.CREATURE, "pike", 0.7F, 0.4F));
	public static final RegistryObject<EntityType<EntityLionfish>> LIONFISH               = ENTITY_TYPES.register("lionfish", () -> createLivingEntity(EntityLionfish::new, EntityClassification.CREATURE, "lionfish", 0.6F, 0.5F));
	public static final RegistryObject<EntityType<EntityThrasher>> THRASHER               = ENTITY_TYPES.register("thrasher", () -> createLivingEntity(EntityThrasher::new, EntityClassification.MONSTER, "thrasher", 1.6F, 0.9F));
	public static final RegistryObject<EntityType<EntityGreatThrasher>> GREAT_THRASHER    = ENTITY_TYPES.register("great_thrasher", () -> createLivingEntity(EntityGreatThrasher::new, EntityClassification.MONSTER, "great_thrasher", 2.8F, 1.575F));
	public static final RegistryObject<EntityType<EntityFlare>> FLARE                     = ENTITY_TYPES.register("flare", () -> createLivingEntity(EntityFlare::new, EntityClassification.MONSTER, "flare", 0.9F, 0.5F));
	
	public static final RegistryObject<EntityType<EntityBoxJellyfish>> BOX_JELLYFISH      = ENTITY_TYPES.register("box_jellyfish", () -> createLivingEntity(EntityBoxJellyfish::new, EntityClassification.WATER_CREATURE, "box_jellyfish", 0.75F, 0.625F));
	
	private static <T extends LivingEntity> EntityType<T> createLivingEntity(EntityType.IFactory<T> factory, EntityClassification entityClassification, String name, float width, float height){
		ResourceLocation location = new ResourceLocation(Reference.MODID, name);
		EntityType<T> entity = EntityType.Builder.create(factory, entityClassification)
			.size(width, height)
			.setTrackingRange(64)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(3)
			.build(location.toString()
		);
		return entity;
	}
	
	private static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> factory, BiFunction<FMLPlayMessages.SpawnEntity, World, T> clientFactory, EntityClassification entityClassification, String name, float width, float height) {
		ResourceLocation location = new ResourceLocation(Reference.MODID, name);
		EntityType<T> entity = EntityType.Builder.create(factory, entityClassification)
			.size(width, height)
			.setTrackingRange(64)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(3)
			.setCustomClientFactory(clientFactory)
			.build(location.toString()
		);
		return entity;
	}
    
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void registerEntitySpawns(RegistryEvent.Register<EntityType<?>> event) {
		UAEntitySpawns.registerSpawnPlacements();
	}
}