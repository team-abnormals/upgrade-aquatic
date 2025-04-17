package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABiomeTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class UABiomeTagsProvider extends BiomeTagsProvider {

	public UABiomeTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(UABiomeTags.HAS_NAUTILUS).add(Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);
		this.tag(UABiomeTags.HAS_THRASHER).add(Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.FROZEN_OCEAN).addTag(UABiomeTags.HAS_GREAT_THRASHER);
		this.tag(UABiomeTags.HAS_GREAT_THRASHER).add(Biomes.DEEP_FROZEN_OCEAN);
		this.tag(UABiomeTags.HAS_LIONFISH).add(Biomes.WARM_OCEAN);

		this.tag(UABiomeTags.HAS_PIKE).add(Biomes.RIVER).addTag(Tags.Biomes.IS_SWAMP);
		this.tag(UABiomeTags.HAS_SQUID).add(Biomes.SWAMP);
		this.tag(UABiomeTags.HAS_PERCH).addTag(Tags.Biomes.IS_SWAMP);

		this.tag(UABiomeTags.HAS_CASSIOPEA_JELLYFISH).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);
		this.tag(UABiomeTags.HAS_JELLYFISH).add(Biomes.WARM_OCEAN).addTag(UABiomeTags.HAS_CASSIOPEA_JELLYFISH);

		this.tag(UABiomeTags.HAS_BEACHGRASS).add(Biomes.BEACH);
		this.tag(UABiomeTags.HAS_SEAROCKET).add(Biomes.BEACH);
		this.tag(UABiomeTags.HAS_FLOWERING_RUSH).add(Biomes.RIVER);
		this.tag(UABiomeTags.HAS_BEACHGRASS).addTag(BiomeTags.IS_RIVER);
		this.tag(UABiomeTags.HAS_PICKERELWEED).addTag(BiomeTags.IS_JUNGLE).add(Biomes.RIVER).addTag(Tags.Biomes.IS_SWAMP);
		this.tag(UABiomeTags.HAS_EXTRA_PICKERELWEED).add(Biomes.FLOWER_FOREST);
		this.tag(UABiomeTags.HAS_AMMONITE_ORE).addTag(BiomeTags.IS_BEACH).addTag(BiomeTags.IS_OCEAN).add(Biomes.STONY_SHORE);
		this.tag(UABiomeTags.HAS_PRISMARINE_CORAL).addTag(BiomeTags.IS_OCEAN);
		this.tag(UABiomeTags.HAS_RIVER_TREE).addTag(BiomeTags.IS_RIVER);

		this.tag(UABiomeTags.HAS_DRIFTWOOD).addTag(BiomeTags.IS_OCEAN);
		this.tag(UABiomeTags.HAS_BEACH_DRIFTWOOD).addTag(BiomeTags.IS_BEACH);
		this.tag(UABiomeTags.HAS_RIVER_DRIFTWOOD).addTag(BiomeTags.IS_RIVER);
		this.tag(UABiomeTags.HAS_SWAMP_DRIFTWOOD).addTag(Tags.Biomes.IS_SWAMP);
		this.tag(UABiomeTags.HAS_EXTRA_DRIFTWOOD).addOptional(new ResourceLocation("atmospheric", "rainforest_basin")).addOptional(new ResourceLocation("atmospheric", "sparse_rainforest_basin"));

		this.tag(UABiomeTags.HAS_UNDERWATER_CANYON).addTag(BiomeTags.IS_OCEAN);
	}
}