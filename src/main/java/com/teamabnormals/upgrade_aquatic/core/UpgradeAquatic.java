package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.gallery.core.data.client.GalleryItemModelProvider;
import com.teamabnormals.upgrade_aquatic.core.data.client.UABlockStateProvider;
import com.teamabnormals.upgrade_aquatic.core.data.client.UAItemModelProvider;
import com.teamabnormals.upgrade_aquatic.core.data.client.UASpriteSourceProvider;
import com.teamabnormals.upgrade_aquatic.core.data.server.*;
import com.teamabnormals.upgrade_aquatic.core.data.server.tags.*;
import com.teamabnormals.upgrade_aquatic.core.other.UAClientCompat;
import com.teamabnormals.upgrade_aquatic.core.other.UACompat;
import com.teamabnormals.upgrade_aquatic.core.other.UAConstants;
import com.teamabnormals.upgrade_aquatic.core.other.UADataSerializers;
import com.teamabnormals.upgrade_aquatic.core.registry.*;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UADecoratedPotPatterns;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAWorldCarvers;
import com.teamabnormals.upgrade_aquatic.core.registry.helper.UAItemSubRegistryHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(UpgradeAquatic.MOD_ID)
public class UpgradeAquatic {
	public static final String MOD_ID = "upgrade_aquatic";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(Registries.ITEM, new UAItemSubRegistryHelper(helper)));

	public UpgradeAquatic(IEventBus bus, ModContainer container) {
		UABlocks.BLOCKS.register(bus);
		UAItems.ITEMS.register(bus);
		UAEntityTypes.ENTITY_TYPES.register(bus);
		UABlockEntityTypes.BLOCK_ENTITY_TYPES.register(bus);
		UASoundEvents.SOUND_EVENTS.register(bus);
		UAMobEffects.MOB_EFFECTS.register(bus);
		UAMobEffects.POTIONS.register(bus);
		UAFeatures.FEATURES.register(bus);
		UAWorldCarvers.WORLD_CARVERS.register(bus);
		UAParticleTypes.PARTICLES.register(bus);
		UADataSerializers.SERIALIZERS.register(bus);
		UABiomeModifierTypes.BIOME_MODIFIER_SERIALIZERS.register(bus);
		UAConditionSerializers.CONDITION_SERIALIZERS.register(bus);
		UAFeatures.TREE_DECORATORS.register(bus);
		UADecoratedPotPatterns.DECORATED_POT_PATTERNS.register(bus);
		UACriteriaTriggers.TRIGGERS.register(bus);

		bus.addListener(UARegistries::registerRegistries);

		this.registerCCCompat(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		container.registerConfig(ModConfig.Type.COMMON, UAConfig.COMMON_SPEC);
		container.registerConfig(ModConfig.Type.CLIENT, UAConfig.CLIENT_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(UACompat::register);
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(UAClientCompat::register);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();
		UADatapackProvider datapack = new UADatapackProvider(output, provider);
		generator.addProvider(server, datapack);
		provider = datapack.getRegistryProvider();

		UABlockTagsProvider blockTags = new UABlockTagsProvider(output, provider, helper);
		generator.addProvider(server, blockTags);
		generator.addProvider(server, new UAItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(server, new UAEntityTypeTagsProvider(output, provider, helper));
		generator.addProvider(server, new UABiomeTagsProvider(output, provider, helper));
		generator.addProvider(server, new UAPaintingVariantTagsProvider(output, provider, helper));
		generator.addProvider(server, new UATrimMaterialTagsProvider(output, provider, helper));
		generator.addProvider(server, new UADataRemolderProvider(output, provider));
		generator.addProvider(server, new UAAdvancementModifierProvider(output, provider));
		generator.addProvider(server, new UARecipeProvider(output, provider));
		generator.addProvider(server, new UADataMapProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new UABlockStateProvider(output, helper));
		generator.addProvider(client, new UASpriteSourceProvider(output, provider, helper));
		generator.addProvider(client, new UAItemModelProvider(output, helper));

		generator.addProvider(client, new GalleryItemModelProvider(MOD_ID, output, helper, provider));
	}

	private void registerCCCompat(IEventBus bus) {
		if ("true".equals(System.getProperty("blueprint.indev")) && !ModList.get().isLoaded(UAConstants.CAVERNS_AND_CHASMS)) {
			UAConstants.CAVERNS_AND_CHASMS_ITEMS.register(bus);
		}
	}

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}