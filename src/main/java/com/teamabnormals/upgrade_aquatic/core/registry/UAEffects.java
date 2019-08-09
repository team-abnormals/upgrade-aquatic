package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.List;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.common.effects.EffectInsomnia;
import com.teamabnormals.upgrade_aquatic.common.effects.EffectRepellence;
import com.teamabnormals.upgrade_aquatic.common.effects.EffectRestfulness;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAEffects {
	private static List<Effect> effects = Lists.newArrayList();
	private static List<Potion> potions = Lists.newArrayList();
	
	public static final Effect INSOMNIA    = registerEffect(new EffectInsomnia("insomnia"));
	public static final Effect RESTFULNESS = registerEffect(new EffectRestfulness("restfulness"));
	public static final Effect REPELLENCE  = registerEffect(new EffectRepellence("repellence"));
	
	public static final Potion INSOMNIA_NORMAL    = registerPotion(new Potion(new EffectInstance(INSOMNIA)), "insomnia");
	public static final Potion INSOMNIA_STRONG    = registerPotion(new Potion(new EffectInstance(INSOMNIA, 0, 1)), "insomnia_strong");
	public static final Potion RESTFULNESS_NORMAL = registerPotion(new Potion(new EffectInstance(RESTFULNESS)), "restfulness");
	public static final Potion RESTFULNESS_STRONG = registerPotion(new Potion(new EffectInstance(RESTFULNESS, 0, 1)), "restfulness_strong");
	public static final Potion REPELLENCE_NORMAL  = registerPotion(new Potion(new EffectInstance(REPELLENCE, 3600)), "repellence");
	public static final Potion REPELLENCE_STRONG  = registerPotion(new Potion(new EffectInstance(REPELLENCE, 1800, 1)), "repellence_strong");
	public static final Potion REPELLENCE_LONG    = registerPotion(new Potion(new EffectInstance(REPELLENCE, 9600)), "repellence_long");
	
	@SubscribeEvent
    public static void onRegisterPotions(RegistryEvent.Register<Potion> event) {
		final IForgeRegistry<Potion> registry = event.getRegistry();
		for(Potion potion : potions) {
			registry.register(potion);
		}
	}
	
	@SubscribeEvent
    public static void onRegisterEffects(RegistryEvent.Register<Effect> event) {
		final IForgeRegistry<Effect> registry = event.getRegistry();
		for(Effect effect : effects) {
			registry.register(effect);
		}
	}
	
	public static void registerRecipes() {
		PotionBrewing.addMix(Potions.AWKWARD, Items.COCOA_BEANS, RESTFULNESS_NORMAL);
		PotionBrewing.addMix(RESTFULNESS_NORMAL, Items.GLOWSTONE_DUST, RESTFULNESS_STRONG);
		PotionBrewing.addMix(RESTFULNESS_NORMAL, Items.FERMENTED_SPIDER_EYE, INSOMNIA_NORMAL);
		PotionBrewing.addMix(INSOMNIA_NORMAL, Items.GLOWSTONE_DUST, INSOMNIA_STRONG);
		PotionBrewing.addMix(Potions.AWKWARD, UAItems.LIONFISH, REPELLENCE_NORMAL);
		PotionBrewing.addMix(REPELLENCE_NORMAL, Items.GLOWSTONE_DUST, REPELLENCE_STRONG);
		PotionBrewing.addMix(REPELLENCE_NORMAL, Items.REDSTONE, REPELLENCE_LONG);
	}
	
	private static Effect registerEffect(Effect effect) {
		effects.add(effect);
		return effect;
	}
	
	private static Potion registerPotion(Potion potion, String name) {
		potion.setRegistryName(name);
		potions.add(potion);
		return potion;
	}
}
