package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.teamabnormals.upgrade_aquatic.client.model.*;
import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.BoxJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.CassiopeaJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.ImmortalJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.*;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.BoxJellyfishRenderer;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.CassiopeaJellyfishRenderer;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.ImmortalJellyfishRenderer;
import com.teamabnormals.upgrade_aquatic.core.data.client.UABlockStateProvider;
import com.teamabnormals.upgrade_aquatic.core.data.server.UADatapackBuiltinEntriesProvider;
import com.teamabnormals.upgrade_aquatic.core.data.server.modifiers.UAAdvancementModifierProvider;
import com.teamabnormals.upgrade_aquatic.core.data.server.modifiers.UALootModifierProvider;
import com.teamabnormals.upgrade_aquatic.core.data.server.tags.*;
import com.teamabnormals.upgrade_aquatic.core.other.UAClientCompat;
import com.teamabnormals.upgrade_aquatic.core.other.UACompat;
import com.teamabnormals.upgrade_aquatic.core.other.UADataSerializers;
import com.teamabnormals.upgrade_aquatic.core.other.UADispenseBehaviorRegistry;
import com.teamabnormals.upgrade_aquatic.core.registry.*;
import com.teamabnormals.upgrade_aquatic.core.registry.util.UAItemSubRegistryHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.CompletableFuture;

@Mod(value = UpgradeAquatic.MOD_ID)
public class UpgradeAquatic {
	public static final String MOD_ID = "upgrade_aquatic";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(ForgeRegistries.ITEMS, new UAItemSubRegistryHelper(helper)));

	public UpgradeAquatic() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();

		REGISTRY_HELPER.register(bus);
		UAMobEffects.MOB_EFFECTS.register(bus);
		UAMobEffects.POTIONS.register(bus);
		UAFeatures.FEATURES.register(bus);
		UAWorldCarvers.WORLD_CARVERS.register(bus);
		UAParticleTypes.PARTICLES.register(bus);
		UADataSerializers.SERIALIZERS.register(bus);
		UABiomeModifierTypes.BIOME_MODIFIER_SERIALIZERS.register(bus);
		UALootConditions.LOOT_CONDITION_TYPES.register(bus);
		UAPaintingVariants.PAINTING_VARIANTS.register(bus);
		UAFeatures.TREE_DECORATORS.register(bus);
		UADecoratedPotPatterns.DECORATED_POT_PATTERNS.register(bus);

		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);
		bus.addListener(this::clientSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			UABlocks.setupTabEditors();
			UAItems.setupTabEditors();
			GlowSquidSpriteUploader.init(bus);
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		});

		context.registerConfig(ModConfig.Type.COMMON, UAConfig.COMMON_SPEC);
		context.registerConfig(ModConfig.Type.CLIENT, UAConfig.CLIENT_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			UACompat.registerCompat();
			UAMobEffects.registerBrewingRecipes();
			UADispenseBehaviorRegistry.registerDispenseBehaviors();
			ObfuscationReflectionHelper.setPrivateValue(BlockBehaviour.class, Blocks.BUBBLE_COLUMN, true, "f_60445_");
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		UABlockTagsProvider blockTags = new UABlockTagsProvider(output, provider, helper);
		generator.addProvider(includeServer, blockTags);
		generator.addProvider(includeServer, new UAItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(includeServer, new UAEntityTypeTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new UABiomeTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new UAPaintingVariantTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new UALootModifierProvider(output, provider));
		generator.addProvider(includeServer, new UAAdvancementModifierProvider(output, provider));
		generator.addProvider(includeServer, new UADatapackBuiltinEntriesProvider(output, provider));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new UABlockStateProvider(output, helper));
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			UAItems.registerItemProperties();
			UAClientCompat.registerClientCompat();
		});
	}

	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(NautilusModel.LOCATION, NautilusModel::createBodyLayer);
		event.registerLayerDefinition(PikeModel.LOCATION, PikeModel::createBodyLayer);
		event.registerLayerDefinition(LionfishModel.LOCATION, LionfishModel::createBodyLayer);
		event.registerLayerDefinition(PerchModel.LOCATION, PerchModel::createBodyLayer);
		event.registerLayerDefinition(ThrasherModel.LOCATION, ThrasherModel::createBodyLayer);
		event.registerLayerDefinition(FlareModel.LOCATION, FlareModel::createBodyLayer);
		event.registerLayerDefinition(SonarWaveModel.LOCATION, SonarWaveModel::createBodyLayer);
		event.registerLayerDefinition(UAGlowSquidModel.LOCATION, UAGlowSquidModel::createBodyLayer);
		event.registerLayerDefinition(GooseModel.LOCATION, GooseModel::createBodyLayer);
		event.registerLayerDefinition(BoxJellyfishModel.LOCATION, BoxJellyfishModel::createBodyLayer);
		event.registerLayerDefinition(CassiopeaJellyfishModel.LOCATION, CassiopeaJellyfishModel::createBodyLayer);
		event.registerLayerDefinition(ImmortalJellyfishModel.LOCATION, ImmortalJellyfishModel::createBodyLayer);
	}

	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(UAEntityTypes.NAUTILUS.get(), NautilusRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.PIKE.get(), PikeRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.LIONFISH.get(), LionfishRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.PERCH.get(), PerchRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.THRASHER.get(), ThrasherRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.GREAT_THRASHER.get(), GreatThrasherRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.FLARE.get(), FlareRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.SONAR_WAVE.get(), SonarWaveRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.GOOSE.get(), GooseRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.BOX_JELLYFISH.get(), BoxJellyfishRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfishRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.IMMORTAL_JELLYFISH.get(), ImmortalJellyfishRenderer::new);
		event.registerEntityRenderer(EntityType.GLOW_SQUID, UAGlowSquidRenderer::new);
	}
}