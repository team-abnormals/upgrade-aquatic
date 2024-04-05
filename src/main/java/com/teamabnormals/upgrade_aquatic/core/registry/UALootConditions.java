package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UALootConditions {
	public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<LootItemConditionType> CONFIG = LOOT_CONDITION_TYPES.register("config", () -> DataUtil.registerConfigCondition(UpgradeAquatic.MOD_ID, UAConfig.COMMON, UAConfig.CLIENT));
}