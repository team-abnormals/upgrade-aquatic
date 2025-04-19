package com.teamabnormals.upgrade_aquatic.common.entity.animal;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps.SpottedPikeVariant;
import com.teamabnormals.upgrade_aquatic.core.registry.UARegistries;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.biome.Biome;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record PikeVariant(ResourceLocation assetId, Component description, float size, int weight, Optional<HolderSet<Biome>> biomes, Optional<ResourceLocation> glowAssetId) implements WeightedEntry {

	@Override
	public Weight getWeight() {
		return Weight.of(this.weight());
	}

	public static final Codec<PikeVariant> DIRECT_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							ResourceLocation.CODEC.fieldOf("asset_id").forGetter(PikeVariant::assetId),
							ComponentSerialization.CODEC.fieldOf("description").forGetter(PikeVariant::description),
							Codec.FLOAT.fieldOf("size").forGetter(PikeVariant::size),
							Codec.INT.fieldOf("weight").forGetter(PikeVariant::weight),
							RegistryCodecs.homogeneousList(Registries.BIOME).optionalFieldOf("biomes").forGetter(PikeVariant::biomes),
							ResourceLocation.CODEC.optionalFieldOf("glow_asset_id").forGetter(PikeVariant::glowAssetId)
					)
					.apply(instance, PikeVariant::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, PikeVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC, PikeVariant::assetId,
			ComponentSerialization.STREAM_CODEC, PikeVariant::description,
			ByteBufCodecs.FLOAT, PikeVariant::size,
			ByteBufCodecs.INT, PikeVariant::weight,
			ByteBufCodecs.optional(ByteBufCodecs.holderSet(Registries.BIOME)), PikeVariant::biomes,
			ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC), PikeVariant::glowAssetId,
			PikeVariant::new
	);

	public static final Codec<Holder<PikeVariant>> CODEC = RegistryFileCodec.create(UARegistries.PIKE_VARIANT, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<PikeVariant>> STREAM_CODEC = ByteBufCodecs.holder(UARegistries.PIKE_VARIANT, DIRECT_STREAM_CODEC);

	public static Holder<PikeVariant> getSpawnVariant(RegistryAccess registryAccess, Holder<Biome> biome, boolean fromBucket, RandomSource random) {
		Registry<PikeVariant> registry = registryAccess.registryOrThrow(UARegistries.PIKE_VARIANT);
		Map<ResourceKey<PikeVariant>, SpottedPikeVariant> spotted = registry.getDataMap(UADataMaps.SPOTTED_PIKE_VARIANTS);

		WeightedRandomList<PikeVariant> variants = WeightedRandomList.create(getPossibleTypes(registry, biome, fromBucket));
		Holder<PikeVariant> variant = registry.wrapAsHolder(variants.getRandom(random).orElseThrow());
		if (spotted.get(variant.getKey()) != null) {
			SpottedPikeVariant spottedVariant = spotted.get(variant.getKey());
			if (random.nextFloat() < spottedVariant.chance()) {
				return spottedVariant.spottedVariant();
			}
		}

		return variant;
	}

	private static List<PikeVariant> getPossibleTypes(Registry<PikeVariant> registry, Holder<Biome> biome, boolean fromBucket) {
		Map<ResourceKey<PikeVariant>, SpottedPikeVariant> spotted = registry.getDataMap(UADataMaps.SPOTTED_PIKE_VARIANTS);
		List<Holder<PikeVariant>> spottedVariants = spotted.values().stream().map(SpottedPikeVariant::spottedVariant).toList();

		List<PikeVariant> validVariants = Lists.newArrayList();
		for (Holder<PikeVariant> holder : registry.holders().toList()) {
			PikeVariant variant = holder.value();
			if ((fromBucket || variant.biomes().isEmpty() || variant.biomes().get().contains(biome)) && !spottedVariants.contains(holder)) {
				validVariants.add(variant);
			}
		}

		return validVariants;
	}
}
