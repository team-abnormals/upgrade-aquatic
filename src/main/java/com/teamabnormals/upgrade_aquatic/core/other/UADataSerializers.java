package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeType;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.entity.EntityDimensions;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class UADataSerializers {
	public static final DeferredRegister<EntityDataSerializer<?>> SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, UpgradeAquatic.MOD_ID);

	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<PikeType>> PIKE_TYPE = SERIALIZERS.register("pike_type", () -> new EntityDataSerializer<PikeType>() {
		@Override
		public StreamCodec<? super RegistryFriendlyByteBuf, PikeType> codec() {
			return null;
		}

		public PikeType copy(PikeType type) {
			return type;
		}
	});

	public static final StreamCodec<RegistryFriendlyByteBuf, EntityDimensions> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.FLOAT, EntityDimensions::width,
			ByteBufCodecs.FLOAT, EntityDimensions::height,
			ByteBufCodecs.BOOL, EntityDimensions::fixed,
			EntityDimensions::new
	);

	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<EntityDimensions>> ENTITY_SIZE = SERIALIZERS.register("entity_size", () -> EntityDataSerializer.forValueType(DIRECT_STREAM_CODEC));
}