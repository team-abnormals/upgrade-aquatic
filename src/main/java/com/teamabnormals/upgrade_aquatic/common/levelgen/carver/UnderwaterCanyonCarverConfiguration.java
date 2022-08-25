package com.teamabnormals.upgrade_aquatic.common.levelgen.carver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class UnderwaterCanyonCarverConfiguration extends CanyonCarverConfiguration {
	public static final Codec<UnderwaterCanyonCarverConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(
				CarverConfiguration.CODEC.forGetter(configuration -> configuration),
				FloatProvider.CODEC.fieldOf("vertical_rotation").forGetter(configuration -> configuration.verticalRotation),
				CanyonCarverConfiguration.CanyonShapeConfiguration.CODEC.fieldOf("shape").forGetter(configuration -> configuration.shape),
				VerticalAnchor.CODEC.fieldOf("magma_and_obsidian_level").forGetter(configuration -> configuration.magmaAndObsidianLevel)
		).apply(instance, UnderwaterCanyonCarverConfiguration::new);
	});
	public final VerticalAnchor magmaAndObsidianLevel;

	public UnderwaterCanyonCarverConfiguration(float probability, HeightProvider p_190587_, FloatProvider p_190588_, VerticalAnchor p_190589_, CarverDebugSettings p_190590_, FloatProvider p_190591_, CanyonShapeConfiguration p_190592_, VerticalAnchor magmaAndObsidianLevel) {
		super(probability, p_190587_, p_190588_, p_190589_, p_190590_, p_190591_, p_190592_);
		this.magmaAndObsidianLevel = magmaAndObsidianLevel;
	}

	public UnderwaterCanyonCarverConfiguration(CarverConfiguration p_158980_, FloatProvider p_158981_, CanyonCarverConfiguration.CanyonShapeConfiguration p_158982_, VerticalAnchor magmaAndObsidianLevel) {
		this(p_158980_.probability, p_158980_.y, p_158980_.yScale, p_158980_.lavaLevel, p_158980_.debugSettings, p_158981_, p_158982_, magmaAndObsidianLevel);
	}
}
