package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.levelgen.carver.UnderwaterCanyonCarverConfiguration;
import com.teamabnormals.upgrade_aquatic.common.levelgen.carver.UnderwaterCanyonWorldCarver;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UAWorldCarvers {
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(Registries.CARVER, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<WorldCarver<UnderwaterCanyonCarverConfiguration>> UNDERWATER_CANYON = WORLD_CARVERS.register("underwater_canyon", () -> new UnderwaterCanyonWorldCarver(UnderwaterCanyonCarverConfiguration.CODEC));

	public static final class UAConfiguredWorldCarvers {
		public static final ResourceKey<ConfiguredWorldCarver<?>> UNDERWATER_CANYON = createKey("underwater_canyon");

		public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> context) {
			HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
			context.register(UNDERWATER_CANYON, UAWorldCarvers.UNDERWATER_CANYON.get().configured(new UnderwaterCanyonCarverConfiguration(0.0333F, UniformHeight.of(VerticalAnchor.absolute(38), VerticalAnchor.absolute(58)), ConstantFloat.of(3.0F), VerticalAnchor.aboveBottom(8), CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()), blocks.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES), UniformFloat.of(-0.125F, 0.125F), new CanyonCarverConfiguration.CanyonShapeConfiguration(UniformFloat.of(0.75F, 1.0F), TrapezoidFloat.of(5.0F, 8.0F, 2.0F), 3, UniformFloat.of(0.75F, 1.0F), 2.5F, 0.0F), VerticalAnchor.absolute(10))));
		}

		public static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name) {
			return ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(UpgradeAquatic.MOD_ID, name));
		}
	}
}
