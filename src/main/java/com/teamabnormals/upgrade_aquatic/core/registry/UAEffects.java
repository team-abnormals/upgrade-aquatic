package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.List;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAEffects {
	private static List<Potion> potions = Lists.newArrayList();
	
	@SubscribeEvent
    public static void onRegisterPotion(RegistryEvent.Register<Potion> event) {
		final IForgeRegistry<Potion> registry = event.getRegistry();
		for(Potion potion : potions) {
			registry.register(potion);
		}
	}
	
	@SubscribeEvent
    public static void onRegisterPotionType(RegistryEvent.Register<Effect> event) {
		final IForgeRegistry<Effect> registry = event.getRegistry();
	}
	
}
