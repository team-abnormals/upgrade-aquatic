package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class UADecoratedPotPatterns {
	public static final DeferredRegister<DecoratedPotPattern> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERN, UpgradeAquatic.MOD_ID);

	public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> PREDATOR = register("predator_pottery_pattern");

	public static DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> register(String name) {
		return DECORATED_POT_PATTERNS.register(name, () -> new DecoratedPotPattern(ResourceLocation.fromNamespaceAndPath(UpgradeAquatic.MOD_ID, name)));
	}

	public static void registerDecoratedPotPatterns() {
		DataUtil.registerDecoratedPotPattern(Pair.of(UAItems.PREDATOR_POTTERY_SHERD.get(), PREDATOR));
	}
}