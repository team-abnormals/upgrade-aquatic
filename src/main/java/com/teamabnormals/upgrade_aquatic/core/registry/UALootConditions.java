package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class UALootConditions {
	public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, UpgradeAquatic.MOD_ID);

	//public static final DeferredHolder<LootItemConditionType, LootItemConditionType> CONFIG = LOOT_CONDITION_TYPES.register("config", () -> DataUtil.registerConfigCondition(UpgradeAquatic.MOD_ID, UAConfig.COMMON, UAConfig.CLIENT));
}