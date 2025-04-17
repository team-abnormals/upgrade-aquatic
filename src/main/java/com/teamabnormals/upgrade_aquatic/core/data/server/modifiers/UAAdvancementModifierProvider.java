package com.teamabnormals.upgrade_aquatic.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.ParentModifier;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UAMobEffects;
import net.minecraft.advancements.AdvancementRequirements.Strategy;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public final class UAAdvancementModifierProvider extends AdvancementModifierProvider {
	private static final EntityType<?>[] MOBS_TO_KILL = new EntityType[]{UAEntityTypes.THRASHER.get(), UAEntityTypes.GREAT_THRASHER.get(), UAEntityTypes.FLARE.get()};

	public UAAdvancementModifierProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(UpgradeAquatic.MOD_ID, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		MobEffectsPredicate.Builder predicate = MobEffectsPredicate.Builder.effects();
		UAMobEffects.MOB_EFFECTS.getEntries().forEach(mobEffect -> {
			if (!mobEffect.get().isInstantenous()) predicate.and(mobEffect);
		});
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, predicate.build().get()));
		this.entry("nether/all_potions").selects("nether/all_potions").addModifier(new EffectsChangedModifier("all_effects", false, predicate.build().get()));

		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		Collection<DeferredHolder<Item, ? extends Item>> items = UAItems.HELPER.getDeferredRegister().getEntries().stream().filter(i -> i.get().getDefaultInstance().getFoodProperties(null) != null).toList();
		items.forEach(item -> {
			balancedDiet.addCriterion(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(Strategy.AND).build());

		CriteriaModifier.Builder fishyBusiness = CriteriaModifier.builder(this.modId);
		fishyBusiness.addCriterion("pike", FishingRodHookedTrigger.TriggerInstance.fishedItem(Optional.empty(), Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(UAItems.PIKE.get()).build())));
		fishyBusiness.addCriterion("lionfish", FishingRodHookedTrigger.TriggerInstance.fishedItem(Optional.empty(), Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(UAItems.LIONFISH.get()).build())));
		fishyBusiness.addCriterion("perch", FishingRodHookedTrigger.TriggerInstance.fishedItem(Optional.empty(), Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(UAItems.PERCH.get()).build())));
		this.entry("husbandry/fishy_business").selects("husbandry/fishy_business").addModifier(fishyBusiness.addIndexedRequirements(0, false, "pike", "lionfish", "perch").build());

		CriteriaModifier.Builder killAMob = CriteriaModifier.builder(this.modId);
		CriteriaModifier.Builder killAllMobs = CriteriaModifier.builder(this.modId);
		ArrayList<String> names = new ArrayList<>();
		for (EntityType<?> entityType : MOBS_TO_KILL) {
			String name = BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath();
			Criterion<KilledTrigger.TriggerInstance> triggerInstance = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityType));
			killAMob.addCriterion(name, triggerInstance);
			killAllMobs.addCriterion(name, triggerInstance);
			names.add(name);
		}

		this.entry("adventure/kill_a_mob").selects("adventure/kill_a_mob").addModifier(killAMob.addIndexedRequirements(0, false, names.toArray(new String[0])).build());
		this.entry("adventure/kill_all_mobs").selects("adventure/kill_all_mobs").addModifier(killAllMobs.requirements(Strategy.AND).build());

		CriteriaModifier.Builder tacticalFishing = CriteriaModifier.builder(this.modId);
		names = new ArrayList<>();
		for (var object : UAItems.HELPER.getDeferredRegister().getEntries()) {
			Item item = object.get();
			if (item instanceof MobBucketItem) {
				String name = BuiltInRegistries.ITEM.getKey(item).getPath();
				tacticalFishing.addCriterion(name, FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(item)));
				names.add(name);
			}
		}

		tacticalFishing.addCriterion("jellyfish_bucket", FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(UAItems.JELLYFISH_BUCKET.get())));
		names.add("jellyfish_bucket");
		this.entry("husbandry/tactical_fishing").selects("husbandry/tactical_fishing").addModifier(tacticalFishing.addIndexedRequirements(0, false, names.toArray(new String[0])).build());

		this.entry("adventure/throw_trident").selects("adventure/throw_trident").addModifier(new ParentModifier(UpgradeAquatic.location("adventure/tooth_fairy")));
	}
}
