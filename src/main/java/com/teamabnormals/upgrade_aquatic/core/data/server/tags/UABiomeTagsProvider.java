package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABiomeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

public class UABiomeTagsProvider extends BiomeTagsProvider {

	public UABiomeTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, UpgradeAquatic.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(UABiomeTags.HAS_NAUTILUS).add(Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);
		this.tag(UABiomeTags.HAS_THRASHER).add(Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.FROZEN_OCEAN).addTag(UABiomeTags.HAS_GREAT_THRASHER);
		this.tag(UABiomeTags.HAS_GREAT_THRASHER).add(Biomes.DEEP_FROZEN_OCEAN);
		this.tag(UABiomeTags.HAS_LIONFISH).add(Biomes.WARM_OCEAN);

		this.tag(UABiomeTags.HAS_PIKE).addTag(BiomeTags.IS_RIVER);
		this.tag(UABiomeTags.HAS_EXTRA_PIKE).add(Biomes.SWAMP);
		this.tag(UABiomeTags.HAS_SQUID).add(Biomes.SWAMP);
		this.tag(UABiomeTags.HAS_PERCH).add(Biomes.SWAMP);

		this.tag(UABiomeTags.HAS_CASSIOPEA_JELLYFISH).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);
		this.tag(UABiomeTags.HAS_JELLYFISH).add(Biomes.WARM_OCEAN).addTag(UABiomeTags.HAS_CASSIOPEA_JELLYFISH);

		this.tag(UABiomeTags.HAS_AMMONITE_ORE).addTag(BiomeTags.IS_BEACH).addTag(BiomeTags.IS_OCEAN).add(Biomes.STONY_SHORE);
		this.tag(UABiomeTags.HAS_PICKERELWEED).addTag(BiomeTags.IS_JUNGLE).addTag(BiomeTags.IS_RIVER).add(Biomes.SWAMP);
		this.tag(UABiomeTags.HAS_EXTRA_DRIFTWOOD).addOptional(new ResourceLocation("atmospheric", "rainforest_basin"));
		this.tag(UABiomeTags.HAS_EXTRA_PICKERELWEED).add(Biomes.FLOWER_FOREST);
	}
}