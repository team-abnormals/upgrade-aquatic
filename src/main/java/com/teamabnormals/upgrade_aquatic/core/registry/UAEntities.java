package com.teamabnormals.upgrade_aquatic.core.registry;
import com.teamabnormals.upgrade_aquatic.common.entities.*;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.*;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.*;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UAEntitySpawns;
import com.teamabnormals.upgrade_aquatic.core.registry.util.UARegistryHelper;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAEntities {
	public static final UARegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER;
	
	public static final RegistryObject<EntityType<EntitySonarWave>> SONAR_WAVE            = HELPER.createEntity("sonar_wave", EntitySonarWave::new, EntitySonarWave::new, EntityClassification.AMBIENT, 1.0F, 1.0F);
	public static final RegistryObject<EntityType<EntityNautilus>> NAUTILUS               = HELPER.createLivingEntity("nautilus", EntityNautilus::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<EntityPike>> PIKE                       = HELPER.createLivingEntity("pike", EntityPike::new, EntityClassification.CREATURE, 0.7F, 0.4F);
	public static final RegistryObject<EntityType<EntityLionfish>> LIONFISH               = HELPER.createLivingEntity("lionfish", EntityLionfish::new, EntityClassification.CREATURE, 0.6F, 0.5F);
	public static final RegistryObject<EntityType<EntityThrasher>> THRASHER               = HELPER.createLivingEntity("thrasher", EntityThrasher::new, EntityClassification.MONSTER, 1.6F, 0.9F);
	public static final RegistryObject<EntityType<EntityGreatThrasher>> GREAT_THRASHER    = HELPER.createLivingEntity("great_thrasher", EntityGreatThrasher::new, EntityClassification.MONSTER, 2.8F, 1.575F);
	public static final RegistryObject<EntityType<EntityFlare>> FLARE                     = HELPER.createLivingEntity("flare", EntityFlare::new, EntityClassification.MONSTER, 0.9F, 0.5F);
	public static final RegistryObject<EntityType<EntityUlulu>> ULULU					  = HELPER.createLivingEntity("ululu", EntityUlulu::new, EntityClassification.MONSTER, 2.04F, 2.04F);
	
	public static final RegistryObject<EntityType<EntityBoxJellyfish>> BOX_JELLYFISH             = HELPER.createLivingEntity("box_jellyfish", EntityBoxJellyfish::new, EntityClassification.WATER_CREATURE, 0.75F, 0.625F);
	public static final RegistryObject<EntityType<EntityCassiopeaJellyfish>> CASSIOPEA_JELLYFISH = HELPER.createLivingEntity("cassiopea_jellyfish", EntityCassiopeaJellyfish::new, EntityClassification.WATER_CREATURE, 0.6875F, 0.25F);
	public static final RegistryObject<EntityType<EntityImmortalJellyfish>> IMMORTAL_JELLYFISH   = HELPER.createLivingEntity("immortal_jellyfish", EntityImmortalJellyfish::new, EntityClassification.WATER_CREATURE, 0.625F, 0.5F);
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void registerEntitySpawns(RegistryEvent.Register<EntityType<?>> event) {
		UAEntitySpawns.registerSpawnPlacements();
	}
}