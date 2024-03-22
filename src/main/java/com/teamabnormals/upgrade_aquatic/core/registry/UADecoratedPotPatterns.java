package com.teamabnormals.upgrade_aquatic.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UADecoratedPotPatterns {
	public static final DeferredRegister<String> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERNS, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<String> PREDATOR = register("predator_pottery_pattern");

	public static RegistryObject<String> register(String name) {
		return DECORATED_POT_PATTERNS.register(name, () -> name);
	}

	public static void registerDecoratedPotPatterns() {
		DataUtil.registerDecoratedPotPattern(Pair.of(UAItems.PREDATOR_POTTERY_SHERD.get(), PREDATOR));
	}
}