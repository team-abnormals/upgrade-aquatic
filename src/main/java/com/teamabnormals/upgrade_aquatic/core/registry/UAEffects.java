package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.List;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.common.effects.EffectInsomnia;
import com.teamabnormals.upgrade_aquatic.common.effects.EffectRestfulness;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
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
	
	public static final Potion INSOMNIA_NORMAL    = registerPotion(new Potion(new EffectInstance(INSOMNIA)), "insomnia");
	public static final Potion INSOMNIA_STRONG    = registerPotion(new Potion(new EffectInstance(INSOMNIA, 0, 1)), "insomnia_strong");
	public static final Potion RESTFULNESS_NORMAL = registerPotion(new Potion(new EffectInstance(RESTFULNESS)), "restfulness");
	public static final Potion RESTFULNESS_STRONG = registerPotion(new Potion(new EffectInstance(RESTFULNESS, 0, 1)), "restfulness_strong");
	
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
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD), Ingredient.fromItems(Items.COCOA_BEANS), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), RESTFULNESS_NORMAL));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), RESTFULNESS_NORMAL), Ingredient.fromItems(Items.GLOWSTONE_DUST), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), RESTFULNESS_STRONG));
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
