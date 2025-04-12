package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.effect.InsomniaMobEffect;
import com.teamabnormals.upgrade_aquatic.common.effect.RepellenceMobEffect;
import com.teamabnormals.upgrade_aquatic.common.effect.RestfulnessMobEffect;
import com.teamabnormals.upgrade_aquatic.common.effect.VibingMobEffect;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UAMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, UpgradeAquatic.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, UpgradeAquatic.MOD_ID);

	public static final DeferredHolder<MobEffect, MobEffect> INSOMNIA = MOB_EFFECTS.register("insomnia", InsomniaMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> RESTFULNESS = MOB_EFFECTS.register("restfulness", RestfulnessMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> REPELLENCE = MOB_EFFECTS.register("repellence", RepellenceMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> VIBING = MOB_EFFECTS.register("vibing", VibingMobEffect::new);

	public static final DeferredHolder<Potion, Potion> INSOMNIA_NORMAL = POTIONS.register("insomnia", () -> new Potion(new MobEffectInstance(INSOMNIA)));
	public static final DeferredHolder<Potion, Potion> INSOMNIA_STRONG = POTIONS.register("insomnia_strong", () -> new Potion(new MobEffectInstance(INSOMNIA, 0, 1)));
	public static final DeferredHolder<Potion, Potion> RESTFULNESS_NORMAL = POTIONS.register("restfulness", () -> new Potion(new MobEffectInstance(RESTFULNESS)));
	public static final DeferredHolder<Potion, Potion> RESTFULNESS_STRONG = POTIONS.register("restfulness_strong", () -> new Potion(new MobEffectInstance(RESTFULNESS, 0, 1)));
	public static final DeferredHolder<Potion, Potion> REPELLENCE_NORMAL = POTIONS.register("repellence", () -> new Potion(new MobEffectInstance(REPELLENCE, 3600)));
	public static final DeferredHolder<Potion, Potion> REPELLENCE_STRONG = POTIONS.register("repellence_strong", () -> new Potion(new MobEffectInstance(REPELLENCE, 1800, 1)));
	public static final DeferredHolder<Potion, Potion> REPELLENCE_LONG = POTIONS.register("repellence_long", () -> new Potion(new MobEffectInstance(REPELLENCE, 9600)));
	public static final DeferredHolder<Potion, Potion> VIBING_NORMAL = POTIONS.register("vibing", () -> new Potion(new MobEffectInstance(VIBING, 3600)));
	public static final DeferredHolder<Potion, Potion> VIBING_STRONG = POTIONS.register("vibing_strong", () -> new Potion(new MobEffectInstance(VIBING, 1800, 1)));
	public static final DeferredHolder<Potion, Potion> VIBING_LONG = POTIONS.register("vibing_long", () -> new Potion(new MobEffectInstance(VIBING, 9600)));

	@SubscribeEvent
	public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addMix(Potions.AWKWARD, Items.COCOA_BEANS, RESTFULNESS_NORMAL);
		event.getBuilder().addMix(RESTFULNESS_NORMAL, Items.GLOWSTONE_DUST, RESTFULNESS_STRONG);
		event.getBuilder().addMix(RESTFULNESS_NORMAL, Items.FERMENTED_SPIDER_EYE, INSOMNIA_NORMAL);
		event.getBuilder().addMix(INSOMNIA_NORMAL, Items.GLOWSTONE_DUST, INSOMNIA_STRONG);
		event.getBuilder().addMix(Potions.AWKWARD, UAItems.LIONFISH.get(), REPELLENCE_NORMAL);
		event.getBuilder().addMix(REPELLENCE_NORMAL, Items.GLOWSTONE_DUST, REPELLENCE_STRONG);
		event.getBuilder().addMix(REPELLENCE_NORMAL, Items.REDSTONE, REPELLENCE_LONG);
		event.getBuilder().addMix(REPELLENCE_NORMAL, Items.FERMENTED_SPIDER_EYE, VIBING_NORMAL);
		event.getBuilder().addMix(VIBING_NORMAL, Items.GLOWSTONE_DUST, VIBING_STRONG);
		event.getBuilder().addMix(VIBING_NORMAL, Items.REDSTONE, VIBING_LONG);
	}
}