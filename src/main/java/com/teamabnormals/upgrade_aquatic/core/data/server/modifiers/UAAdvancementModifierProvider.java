package com.teamabnormals.upgrade_aquatic.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.ParentModifier;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UAMobEffects;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public final class UAAdvancementModifierProvider extends AdvancementModifierProvider {
	private static final EntityType<?>[] MOBS_TO_KILL = new EntityType[]{UAEntityTypes.THRASHER.get(), UAEntityTypes.GREAT_THRASHER.get(), UAEntityTypes.FLARE.get()};

	public UAAdvancementModifierProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(UpgradeAquatic.MOD_ID, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		MobEffectsPredicate predicate = MobEffectsPredicate.effects();
		UAMobEffects.MOB_EFFECTS.getEntries().forEach(mobEffect -> {
			MobEffect effect = mobEffect.get();
			if (!effect.isInstantenous()) predicate.and(effect);
		});
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, predicate));
		this.entry("nether/all_potions").selects("nether/all_potions").addModifier(new EffectsChangedModifier("all_effects", false, predicate));

		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		UAItems.HELPER.getDeferredRegister().getEntries().forEach(registryObject -> {
			Item item = registryObject.get();
			if (item.isEdible()) {
				balancedDiet.addCriterion(ForgeRegistries.ITEMS.getKey(item).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(RequirementsStrategy.AND).build());

		CriteriaModifier.Builder fishyBusiness = CriteriaModifier.builder(this.modId);
		fishyBusiness.addCriterion("pike", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(UAItems.PIKE.get()).build()));
		fishyBusiness.addCriterion("lionfish", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(UAItems.LIONFISH.get()).build()));
		fishyBusiness.addCriterion("perch", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(UAItems.PERCH.get()).build()));
		this.entry("husbandry/fishy_business").selects("husbandry/fishy_business").addModifier(fishyBusiness.addIndexedRequirements(0, false, "pike", "lionfish", "perch").build());

		CriteriaModifier.Builder killAMob = CriteriaModifier.builder(this.modId);
		CriteriaModifier.Builder killAllMobs = CriteriaModifier.builder(this.modId);
		ArrayList<String> names = new ArrayList<>();
		for (EntityType<?> entityType : MOBS_TO_KILL) {
			String name = ForgeRegistries.ENTITY_TYPES.getKey(entityType).getPath();
			KilledTrigger.TriggerInstance triggerInstance = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityType));
			killAMob.addCriterion(name, triggerInstance);
			killAllMobs.addCriterion(name, triggerInstance);
			names.add(name);
		}

		this.entry("adventure/kill_a_mob").selects("adventure/kill_a_mob").addModifier(killAMob.addIndexedRequirements(0, false, names.toArray(new String[0])).build());
		this.entry("adventure/kill_all_mobs").selects("adventure/kill_all_mobs").addModifier(killAllMobs.requirements(RequirementsStrategy.AND).build());

		CriteriaModifier.Builder tacticalFishing = CriteriaModifier.builder(this.modId);
		names = new ArrayList<>();
		for (var object : UAItems.HELPER.getDeferredRegister().getEntries()) {
			Item item = object.get();
			if (item instanceof MobBucketItem) {
				String name = ForgeRegistries.ITEMS.getKey(item).getPath();
				tacticalFishing.addCriterion(name, FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(item).build()));
				names.add(name);
			}
		}
		tacticalFishing.addCriterion("squid_bucket", FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(UAItems.SQUID_BUCKET.get()).build()));
		names.add("squid_bucket");
		tacticalFishing.addCriterion("glow_squid_bucket", FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(UAItems.GLOW_SQUID_BUCKET.get()).build()));
		names.add("glow_squid_bucket");
		tacticalFishing.addCriterion("jellyfish_bucket", FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(UAItems.JELLYFISH_BUCKET.get()).build()));
		names.add("jellyfish_bucket");
		this.entry("husbandry/tactical_fishing").selects("husbandry/tactical_fishing").addModifier(tacticalFishing.addIndexedRequirements(0, false, names.toArray(new String[0])).build());

		this.entry("adventure/throw_trident").selects("adventure/throw_trident").addModifier(new ParentModifier(new ResourceLocation(UpgradeAquatic.MOD_ID, "adventure/tooth_fairy")));
	}
}
