package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.upgrade_aquatic.common.effects.InsomniaEffect;
import com.minecraftabnormals.upgrade_aquatic.common.effects.RepellenceEffect;
import com.minecraftabnormals.upgrade_aquatic.common.effects.RestfulnessEffect;
import com.minecraftabnormals.upgrade_aquatic.common.effects.VibingEffect;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAEffects {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, UpgradeAquatic.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<Effect> INSOMNIA = EFFECTS.register("insomnia", InsomniaEffect::new);
	public static final RegistryObject<Effect> RESTFULNESS = EFFECTS.register("restfulness", RestfulnessEffect::new);
	public static final RegistryObject<Effect> REPELLENCE = EFFECTS.register("repellence", RepellenceEffect::new);
	public static final RegistryObject<Effect> VIBING = EFFECTS.register("vibing", VibingEffect::new);

	public static final RegistryObject<Potion> INSOMNIA_NORMAL = POTIONS.register("insomnia", () -> new Potion(new EffectInstance(INSOMNIA.get())));
	public static final RegistryObject<Potion> INSOMNIA_STRONG = POTIONS.register("insomnia_strong", () -> new Potion(new EffectInstance(INSOMNIA.get(), 0, 1)));
	public static final RegistryObject<Potion> RESTFULNESS_NORMAL = POTIONS.register("restfulness", () -> new Potion(new EffectInstance(RESTFULNESS.get())));
	public static final RegistryObject<Potion> RESTFULNESS_STRONG = POTIONS.register("restfulness_strong", () -> new Potion(new EffectInstance(RESTFULNESS.get(), 0, 1)));
	public static final RegistryObject<Potion> REPELLENCE_NORMAL = POTIONS.register("repellence", () -> new Potion(new EffectInstance(REPELLENCE.get(), 3600)));
	public static final RegistryObject<Potion> REPELLENCE_STRONG = POTIONS.register("repellence_strong", () -> new Potion(new EffectInstance(REPELLENCE.get(), 1800, 1)));
	public static final RegistryObject<Potion> REPELLENCE_LONG = POTIONS.register("repellence_long", () -> new Potion(new EffectInstance(REPELLENCE.get(), 9600)));
	public static final RegistryObject<Potion> VIBING_NORMAL = POTIONS.register("vibing", () -> new Potion(new EffectInstance(VIBING.get(), 3600)));
	public static final RegistryObject<Potion> VIBING_STRONG = POTIONS.register("vibing_strong", () -> new Potion(new EffectInstance(VIBING.get(), 1800, 1)));
	public static final RegistryObject<Potion> VIBING_LONG = POTIONS.register("vibing_long", () -> new Potion(new EffectInstance(VIBING.get(), 9600)));

	public static void registerBrewingRecipes() {
		DataUtil.addMix(Potions.AWKWARD, Items.COCOA_BEANS, RESTFULNESS_NORMAL.get());
		DataUtil.addMix(RESTFULNESS_NORMAL.get(), Items.GLOWSTONE_DUST, RESTFULNESS_STRONG.get());
		DataUtil.addMix(RESTFULNESS_NORMAL.get(), Items.FERMENTED_SPIDER_EYE, INSOMNIA_NORMAL.get());
		DataUtil.addMix(INSOMNIA_NORMAL.get(), Items.GLOWSTONE_DUST, INSOMNIA_STRONG.get());
		DataUtil.addMix(Potions.AWKWARD, UAItems.LIONFISH.get(), REPELLENCE_NORMAL.get());
		DataUtil.addMix(REPELLENCE_NORMAL.get(), Items.GLOWSTONE_DUST, REPELLENCE_STRONG.get());
		DataUtil.addMix(REPELLENCE_NORMAL.get(), Items.REDSTONE, REPELLENCE_LONG.get());
		DataUtil.addMix(REPELLENCE_NORMAL.get(), Items.FERMENTED_SPIDER_EYE, VIBING_NORMAL.get());
		DataUtil.addMix(VIBING_NORMAL.get(), Items.GLOWSTONE_DUST, VIBING_STRONG.get());
		DataUtil.addMix(VIBING_NORMAL.get(), Items.REDSTONE, VIBING_LONG.get());
	}
}