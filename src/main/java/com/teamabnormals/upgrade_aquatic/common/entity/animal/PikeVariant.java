package com.teamabnormals.upgrade_aquatic.common.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.upgrade_aquatic.core.registry.UARegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;

public record PikeVariant(ResourceLocation assetId, Component description, float size, int weight, Optional<HolderSet<Biome>> biomes) {

	public static final Codec<PikeVariant> DIRECT_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							ResourceLocation.CODEC.fieldOf("asset_id").forGetter(PikeVariant::assetId),
							ComponentSerialization.CODEC.fieldOf("description").forGetter(PikeVariant::description),
							Codec.FLOAT.fieldOf("size").forGetter(PikeVariant::size),
							Codec.INT.fieldOf("weight").forGetter(PikeVariant::weight),
							RegistryCodecs.homogeneousList(Registries.BIOME).optionalFieldOf("biomes").forGetter(PikeVariant::biomes)
					)
					.apply(instance, PikeVariant::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, PikeVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC, PikeVariant::assetId,
			ComponentSerialization.STREAM_CODEC, PikeVariant::description,
			ByteBufCodecs.FLOAT, PikeVariant::size,
			ByteBufCodecs.INT, PikeVariant::weight,
			ByteBufCodecs.optional(ByteBufCodecs.holderSet(Registries.BIOME)), PikeVariant::biomes,
			PikeVariant::new
	);

	public static final Codec<Holder<PikeVariant>> CODEC = RegistryFileCodec.create(UARegistries.PIKE_VARIANT, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<PikeVariant>> STREAM_CODEC = ByteBufCodecs.holder(UARegistries.PIKE_VARIANT, DIRECT_STREAM_CODEC);
}
