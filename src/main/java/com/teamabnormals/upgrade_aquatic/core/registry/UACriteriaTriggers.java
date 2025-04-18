package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class UACriteriaTriggers {
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, UpgradeAquatic.MOD_ID);

	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> CONVERT_PHANTOM = TRIGGERS.register("convert_phantom", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> PICK_MULBERRIES = TRIGGERS.register("pick_mulberries", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> SLEEP_UNDERWATER = TRIGGERS.register("sleep_underwater", PlayerTrigger::new);
}