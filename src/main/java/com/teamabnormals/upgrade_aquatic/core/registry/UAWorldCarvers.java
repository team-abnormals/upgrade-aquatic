package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.levelgen.carver.UnderwaterCanyonCarverConfiguration;
import com.teamabnormals.upgrade_aquatic.common.levelgen.carver.UnderwaterCanyonWorldCarver;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
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
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(Registry.CARVER_REGISTRY, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<WorldCarver<UnderwaterCanyonCarverConfiguration>> UNDERWATER_CANYON = WORLD_CARVERS.register("underwater_canyon", () -> new UnderwaterCanyonWorldCarver(UnderwaterCanyonCarverConfiguration.CODEC));

	public static final class UAConfiguredWorldCarvers {
		public static final DeferredRegister<ConfiguredWorldCarver<?>> CONFIGURED_WORLD_CARVERS = DeferredRegister.create(Registry.CONFIGURED_CARVER_REGISTRY, UpgradeAquatic.MOD_ID);

		public static final RegistryObject<ConfiguredWorldCarver<?>> UNDERWATER_CANYON = CONFIGURED_WORLD_CARVERS.register("underwater_canyon", () -> UAWorldCarvers.UNDERWATER_CANYON.get().configured(new UnderwaterCanyonCarverConfiguration(0.0333F, UniformHeight.of(VerticalAnchor.absolute(38), VerticalAnchor.absolute(58)), ConstantFloat.of(3.0F), VerticalAnchor.aboveBottom(8), CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()), UniformFloat.of(-0.125F, 0.125F), new CanyonCarverConfiguration.CanyonShapeConfiguration(UniformFloat.of(0.75F, 1.0F), TrapezoidFloat.of(5.0F, 8.0F, 2.0F), 3, UniformFloat.of(0.75F, 1.0F), 2.5F, 0.0F), VerticalAnchor.absolute(10))));
	}
}
