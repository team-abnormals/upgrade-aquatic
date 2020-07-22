package com.minecraftabnormals.upgrade_aquatic.core.registry;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityFlare;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityGoose;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityUlulu;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.EntityBoxJellyfish;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.EntityCassiopeaJellyfish;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.EntityImmortalJellyfish;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntityGreatThrasher;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntitySonarWave;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.other.UAEntitySpawns;
import com.minecraftabnormals.upgrade_aquatic.core.registry.util.UARegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAEntities {
	public static final UARegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER;
	
	public static final RegistryObject<EntityType<EntitySonarWave>> SONAR_WAVE            = HELPER.createEntity("sonar_wave", EntitySonarWave::new, EntitySonarWave::new, EntityClassification.MISC, 1.0F, 1.0F);
	public static final RegistryObject<EntityType<EntityNautilus>> NAUTILUS               = HELPER.createLivingEntity("nautilus", EntityNautilus::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<EntityPike>> PIKE                       = HELPER.createLivingEntity("pike", EntityPike::new, EntityClassification.CREATURE, 0.7F, 0.4F);
	public static final RegistryObject<EntityType<EntityLionfish>> LIONFISH               = HELPER.createLivingEntity("lionfish", EntityLionfish::new, EntityClassification.CREATURE, 0.6F, 0.5F);
	public static final RegistryObject<EntityType<EntityThrasher>> THRASHER               = HELPER.createLivingEntity("thrasher", EntityThrasher::new, EntityClassification.MONSTER, 1.6F, 0.9F);
	public static final RegistryObject<EntityType<EntityGreatThrasher>> GREAT_THRASHER    = HELPER.createLivingEntity("great_thrasher", EntityGreatThrasher::new, EntityClassification.MONSTER, 2.8F, 1.575F);
	public static final RegistryObject<EntityType<EntityFlare>> FLARE                     = HELPER.createLivingEntity("flare", EntityFlare::new, EntityClassification.MONSTER, 0.9F, 0.5F);
	public static final RegistryObject<EntityType<EntityUlulu>> ULULU					  = HELPER.createLivingEntity("ululu", EntityUlulu::new, EntityClassification.MONSTER, 2.04F, 2.04F);
	public static final RegistryObject<EntityType<EntityGoose>> GOOSE 				  	  = HELPER.createLivingEntity("goose", EntityGoose::new, EntityClassification.CREATURE, 0.5F, 0.9F);
	
	public static final RegistryObject<EntityType<EntityBoxJellyfish>> BOX_JELLYFISH             = HELPER.createLivingEntity("box_jellyfish", EntityBoxJellyfish::new, EntityClassification.WATER_CREATURE, 0.75F, 0.625F);
	public static final RegistryObject<EntityType<EntityCassiopeaJellyfish>> CASSIOPEA_JELLYFISH = HELPER.createLivingEntity("cassiopea_jellyfish", EntityCassiopeaJellyfish::new, EntityClassification.WATER_CREATURE, 0.6875F, 0.25F);
	public static final RegistryObject<EntityType<EntityImmortalJellyfish>> IMMORTAL_JELLYFISH   = HELPER.createLivingEntity("immortal_jellyfish", EntityImmortalJellyfish::new, EntityClassification.WATER_CREATURE, 0.625F, 0.5F);
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void registerEntitySpawns(RegistryEvent.Register<EntityType<?>> event) {
		UAEntitySpawns.registerSpawnPlacements();
	}
	
	public static void registerAttributes() {
		GlobalEntityTypeAttributes.put(NAUTILUS.get(), EntityNautilus.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(PIKE.get(), EntityPike.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(LIONFISH.get(), EntityLionfish.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(THRASHER.get(), EntityThrasher.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(GREAT_THRASHER.get(), EntityGreatThrasher.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(FLARE.get(), EntityFlare.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(ULULU.get(), EntityUlulu.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(GOOSE.get(), EntityGoose.registerAttributes().func_233813_a_());
		
		GlobalEntityTypeAttributes.put(BOX_JELLYFISH.get(), EntityBoxJellyfish.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(CASSIOPEA_JELLYFISH.get(), EntityCassiopeaJellyfish.registerAttributes().func_233813_a_());
		GlobalEntityTypeAttributes.put(IMMORTAL_JELLYFISH.get(), EntityImmortalJellyfish.registerAttributes().func_233813_a_());
	}
}