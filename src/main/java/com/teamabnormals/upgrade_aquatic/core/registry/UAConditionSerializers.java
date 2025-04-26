package com.teamabnormals.upgrade_aquatic.core.registry;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class UAConditionSerializers {
	public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, UpgradeAquatic.MOD_ID);

	public static final DeferredHolder<MapCodec<? extends ICondition>, ConfigValueCondition.Serializer> CONFIG = CONDITION_SERIALIZERS.register("config", () -> new ConfigValueCondition.Serializer(DataUtil.getConfigValues(UAConfig.COMMON, UAConfig.CLIENT)));

	public static class UAConditions {
		public static final ConfigValueCondition KELPY_OCEAN_RUINS = config(UAConfig.COMMON.kelpyOceanRuins, "kelpy_ocean_ruins");

		public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
			return new ConfigValueCondition(CONFIG.get(), value, key, Maps.newHashMap(), inverted);
		}

		public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
			return config(value, key, false);
		}
	}
}