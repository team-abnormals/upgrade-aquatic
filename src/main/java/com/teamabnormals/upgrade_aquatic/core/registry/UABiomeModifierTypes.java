package com.teamabnormals.upgrade_aquatic.core.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UABiomeModifierTypes {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<Codec<AddCarversBiomeModifier>> ADD_CARVERS = BIOME_MODIFIER_SERIALIZERS.register("add_carvers", () ->
			RecordCodecBuilder.create(builder -> builder.group(
					Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddCarversBiomeModifier::biomes),
					ConfiguredWorldCarver.LIST_CODEC.fieldOf("carvers").forGetter(AddCarversBiomeModifier::features),
					Carving.CODEC.fieldOf("step").forGetter(AddCarversBiomeModifier::step)
			).apply(builder, AddCarversBiomeModifier::new))
	);

	public record AddCarversBiomeModifier(HolderSet<Biome> biomes, HolderSet<ConfiguredWorldCarver<?>> features, Carving step) implements BiomeModifier {
		@Override
		public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
			if (phase == Phase.ADD && this.biomes.contains(biome)) {
				BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
				this.features.forEach(holder -> generationSettings.addCarver(this.step, holder));
			}
		}

		@Override
		public Codec<? extends BiomeModifier> codec() {
			return UABiomeModifierTypes.ADD_CARVERS.get();
		}
	}
}