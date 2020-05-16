package com.teamabnormals.upgrade_aquatic.common.advancement;

import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Reference.MODID)
public class UACriteriaTriggers {
	public static final EmptyTrigger CONVERT_PHANTOM = CriteriaTriggers.register(new EmptyTrigger(prefix("convert_phantom")));
	
	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(Reference.MODID, name);
	}
}