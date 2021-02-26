package com.minecraftabnormals.upgrade_aquatic.common.advancement;

import com.minecraftabnormals.abnormals_core.common.advancement.EmptyTrigger;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UACriteriaTriggers {
	public static final EmptyTrigger CONVERT_PHANTOM = CriteriaTriggers.register(new EmptyTrigger(prefix("convert_phantom")));
	public static final EmptyTrigger PICK_MULBERRIES = CriteriaTriggers.register(new EmptyTrigger(prefix("pick_mulberries")));
	public static final EmptyTrigger SLEEP_UNDERWATER = CriteriaTriggers.register(new EmptyTrigger(prefix("sleep_underwater")));

	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, name);
	}
}