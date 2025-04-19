package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeVariant;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UARegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.Tags.Biomes;

import java.util.Optional;

public final class UAPikeVariants {
	public static final ResourceKey<PikeVariant> AMUR = create("amur");
	public static final ResourceKey<PikeVariant> REDFIN_PICKEREL = create("redfin_pickerel");
	public static final ResourceKey<PikeVariant> BROWN_NORTHERN = create("brown_northern");
	public static final ResourceKey<PikeVariant> MAHOGANY_NORTHERN = create("mahogany_northern");
	public static final ResourceKey<PikeVariant> JADE_NORTHERN = create("jade_northern");
	public static final ResourceKey<PikeVariant> OLIVE_NORTHERN = create("olive_northern");
	public static final ResourceKey<PikeVariant> SPECTRAL = create("spectral");
	public static final ResourceKey<PikeVariant> SPOTTED_BROWN_NORTHERN = create("spotted_brown_northern");
	public static final ResourceKey<PikeVariant> SPOTTED_MAHOGANY_NORTHERN = create("spotted_mahogany_northern");
	public static final ResourceKey<PikeVariant> SPOTTED_JADE_NORTHERN = create("spotted_jade_northern");
	public static final ResourceKey<PikeVariant> SPOTTED_OLIVE_NORTHERN = create("spotted_olive_northern");
	public static final ResourceKey<PikeVariant> SUPERCHARGED = create("supercharged");
	public static final ResourceKey<PikeVariant> OBSIDIAN = create("obsidian");
	public static final ResourceKey<PikeVariant> MUSKELLUNGE = create("muskellunge");
	public static final ResourceKey<PikeVariant> CHAIN_PICKEREL = create("chain_pickerel");
	public static final ResourceKey<PikeVariant> GRASS_PICKEREL = create("grass_pickerel");
	public static final ResourceKey<PikeVariant> BLACK_SOUTHERN = create("black_southern");
	public static final ResourceKey<PikeVariant> EBONY_SOUTHERN = create("ebony_southern");
	public static final ResourceKey<PikeVariant> MUSTARD_SOUTHERN = create("mustard_southern");
	public static final ResourceKey<PikeVariant> LEMON_SOUTHERN = create("lemon_southern");
	public static final ResourceKey<PikeVariant> GOLDEN_SOUTHERN = create("golden_southern");
	public static final ResourceKey<PikeVariant> DEFAULT = REDFIN_PICKEREL;

	public static void bootstrap(BootstrapContext<PikeVariant> context) {
		float small = 1.2F;
		float medium = 1.5F;
		float large = 1.7F;
		float huge = 2.3F;

		int common = 33;
		int uncommon = 25;
		int rare = 15;
		int epic = 5;
		int legendary = 1;

		register(context, AMUR, small, uncommon, Biomes.IS_SWAMP);
		register(context, MUSKELLUNGE, huge, epic, BiomeTags.IS_RIVER);

		register(context, REDFIN_PICKEREL, small, common);
		register(context, CHAIN_PICKEREL, small, common, BiomeTags.IS_RIVER);
		register(context, GRASS_PICKEREL, small, common, Biomes.IS_SWAMP);

		register(context, BLACK_SOUTHERN, medium, common);
		register(context, EBONY_SOUTHERN, medium, uncommon);
		register(context, MUSTARD_SOUTHERN, medium, rare);
		register(context, LEMON_SOUTHERN, medium, rare);
		register(context, GOLDEN_SOUTHERN, medium, epic);

		register(context, BROWN_NORTHERN, large, common);
		register(context, MAHOGANY_NORTHERN, large, uncommon);
		register(context, JADE_NORTHERN, large, rare);
		register(context, OLIVE_NORTHERN, large, epic);

		register(context, SPOTTED_BROWN_NORTHERN, large, 0);
		register(context, SPOTTED_MAHOGANY_NORTHERN, large, 0);
		register(context, SPOTTED_JADE_NORTHERN, large, 0);
		register(context, SPOTTED_OLIVE_NORTHERN, large, 0);

		register(context, SPECTRAL, large, legendary, true);
		register(context, SUPERCHARGED, large, legendary, true);
		register(context, OBSIDIAN, large, legendary, true);
	}

	private static ResourceKey<PikeVariant> create(String name) {
		return ResourceKey.create(UARegistries.PIKE_VARIANT, UpgradeAquatic.location(name));
	}

	public static void register(BootstrapContext<PikeVariant> context, ResourceKey<PikeVariant> key, float size, int weight, TagKey<Biome> biomes) {
		register(context, key, size, weight, Optional.of(context.lookup(Registries.BIOME).getOrThrow(biomes)), false);
	}

	public static void register(BootstrapContext<PikeVariant> context, ResourceKey<PikeVariant> key, float size, int weight) {
		register(context, key, size, weight, Optional.empty(), false);
	}

	public static void register(BootstrapContext<PikeVariant> context, ResourceKey<PikeVariant> key, float size, int weight, boolean glow) {
		register(context, key, size, weight, Optional.empty(), glow);
	}

	public static void register(BootstrapContext<PikeVariant> context, ResourceKey<PikeVariant> key, float size, int weight, Optional<HolderSet<Biome>> biomes, boolean glow) {
		ResourceLocation texture = key.location().withPrefix("entity/pike/");
		context.register(key, new PikeVariant(
				texture,
				Component.translatable(Util.makeDescriptionId("pike_variant", key.location())).withStyle(getColorForWeight(weight)),
				size, weight, biomes, glow ? Optional.of(texture.withSuffix("_glow")) : Optional.empty()
		));
	}

	public static ChatFormatting getColorForWeight(int weight) {
		return switch (weight) {
			case 1 -> ChatFormatting.GOLD;
			case 5 -> ChatFormatting.LIGHT_PURPLE;
			case 15 -> ChatFormatting.BLUE;
			case 25 -> ChatFormatting.GREEN;
			default -> ChatFormatting.GRAY;
		};
	}
}