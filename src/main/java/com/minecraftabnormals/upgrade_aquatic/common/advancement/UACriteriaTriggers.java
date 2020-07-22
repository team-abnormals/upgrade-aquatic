package com.minecraftabnormals.upgrade_aquatic.common.advancement;

import com.minecraftabnormals.upgrade_aquatic.core.util.Reference;
import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Reference.MODID)
public class UACriteriaTriggers {
	public static final EmptyTrigger CONVERT_PHANTOM = CriteriaTriggers.register(new EmptyTrigger(prefix("convert_phantom")));
	public static final EmptyTrigger PICK_MULBERRIES = CriteriaTriggers.register(new EmptyTrigger(prefix("pick_mulberries")));
	public static final EmptyTrigger SLEEP_UNDERWATER = CriteriaTriggers.register(new EmptyTrigger(prefix("sleep_underwater")));

	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(Reference.MODID, name);
	}
}