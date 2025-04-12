package com.teamabnormals.upgrade_aquatic.core.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.world.BiomeGenerationSettingsBuilder;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UABiomeModifierTypes {
	public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, UpgradeAquatic.MOD_ID);

	public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<AddCarversBiomeModifier>> ADD_CARVERS = BIOME_MODIFIER_SERIALIZERS.register("add_carvers", () ->
			RecordCodecBuilder.mapCodec(builder -> builder.group(
					Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddCarversBiomeModifier::biomes),
					ConfiguredWorldCarver.LIST_CODEC.fieldOf("carvers").forGetter(AddCarversBiomeModifier::carvers),
					Carving.CODEC.fieldOf("step").forGetter(AddCarversBiomeModifier::step)
			).apply(builder, AddCarversBiomeModifier::new))
	);

	public record AddCarversBiomeModifier(HolderSet<Biome> biomes, HolderSet<ConfiguredWorldCarver<?>> carvers, Carving step) implements BiomeModifier {
		
		@Override
		public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
			if (phase == Phase.ADD && this.biomes.contains(biome)) {
				BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
				this.carvers.forEach(holder -> generationSettings.addCarver(this.step, holder));
			}
		}
		
		@Override
		public MapCodec<? extends BiomeModifier> codec() {
			return UABiomeModifierTypes.ADD_CARVERS.get();
		}
	}
}