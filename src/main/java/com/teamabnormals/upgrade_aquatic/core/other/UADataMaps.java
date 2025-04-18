package com.teamabnormals.upgrade_aquatic.core.other;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeVariant;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UARegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UADataMaps {
	public static final DataMapType<PikeVariant, SpottedPikeVariant> SPOTTED_PIKE_VARIANTS = DataMapType.builder(UpgradeAquatic.location("spotted_pike_variants"), UARegistries.PIKE_VARIANT, SpottedPikeVariant.CODEC).synced(SpottedPikeVariant.CODEC, false).build();

	public static final DataMapType<Block, BubbleColumnRenewable> BUBBLE_COLUMN_RENEWABLES = DataMapType.builder(UpgradeAquatic.location("bubble_column_renewables"), Registries.BLOCK, BubbleColumnRenewable.CODEC).synced(BubbleColumnRenewable.CODEC, false).build();
	public static final DataMapType<Block, CoralstoneConversions> CORALSTONE_CONVERSIONS = DataMapType.builder(UpgradeAquatic.location("coralstone_conversions"), Registries.BLOCK, CoralstoneConversions.CODEC).synced(CoralstoneConversions.CODEC, false).build();

	@SubscribeEvent
	public static void registerDataMaps(RegisterDataMapTypesEvent event) {
		event.register(SPOTTED_PIKE_VARIANTS);
		event.register(BUBBLE_COLUMN_RENEWABLES);
		event.register(CORALSTONE_CONVERSIONS);
	}

	public record SpottedPikeVariant(Holder<PikeVariant> spottedVariant, float chance) {
		public static final Codec<SpottedPikeVariant> CODEC = RecordCodecBuilder.create(in -> in.group(
				RegistryFixedCodec.create(UARegistries.PIKE_VARIANT).fieldOf("spotted_variant").forGetter(SpottedPikeVariant::spottedVariant),
				Codec.FLOAT.fieldOf("chance").forGetter(SpottedPikeVariant::chance)
		).apply(in, SpottedPikeVariant::new));
	}

	public record BubbleColumnRenewable(Holder<Block> fallingBlock) {
		public static final Codec<BubbleColumnRenewable> CODEC = RecordCodecBuilder.create(in -> in.group(
				RegistryFixedCodec.create(Registries.BLOCK).fieldOf("falling_block").forGetter(BubbleColumnRenewable::fallingBlock)
		).apply(in, BubbleColumnRenewable::new));
	}

	public record CoralstoneConversions(Holder<Block> coralstone, Holder<Block> coralstoneStairs, Holder<Block> coralstoneSlab, Holder<Block> coralstoneWall, Holder<Block> chiseledCoralstone) {

		public static final Codec<CoralstoneConversions> CODEC = RecordCodecBuilder.create(in -> in.group(
				RegistryFixedCodec.create(Registries.BLOCK).fieldOf("coralstone").forGetter(CoralstoneConversions::coralstone),
				RegistryFixedCodec.create(Registries.BLOCK).fieldOf("coralstone_stairs").forGetter(CoralstoneConversions::coralstoneStairs),
				RegistryFixedCodec.create(Registries.BLOCK).fieldOf("coralstone_slab").forGetter(CoralstoneConversions::coralstoneSlab),
				RegistryFixedCodec.create(Registries.BLOCK).fieldOf("coralstone_wall").forGetter(CoralstoneConversions::coralstoneWall),
				RegistryFixedCodec.create(Registries.BLOCK).fieldOf("chiseled_coralstone").forGetter(CoralstoneConversions::chiseledCoralstone)
		).apply(in, CoralstoneConversions::new));
	}
}
