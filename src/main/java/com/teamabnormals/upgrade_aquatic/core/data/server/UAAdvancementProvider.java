package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.teamabnormals.upgrade_aquatic.common.advancement.BucketEntityDataPredicate;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeVariant;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.*;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAPikeVariants;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements.Strategy;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.AdvancementProvider.AdvancementGenerator;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

public class UAAdvancementProvider implements AdvancementGenerator {

	public static AdvancementProvider create(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		return new AdvancementProvider(output, provider, helper, List.of(new UAAdvancementProvider()));
	}

	@Override
	public void generate(Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {

		ItemStack splashPotion = new ItemStack(Items.SPLASH_POTION);
		splashPotion.set(DataComponents.POTION_CONTENTS, new PotionContents(UAMobEffects.INSOMNIA_NORMAL));
		createAdvancement("sleepless_slave", "nether", ResourceLocation.withDefaultNamespace("nether/brew_potion"), splashPotion, AdvancementType.TASK, true, true, false)
				.addCriterion("convert_phantom", UACriteriaTriggers.convertPhantom())
				.save(consumer, UpgradeAquatic.MOD_ID + ":nether/sleepless_slave");

		ItemStack lingeringPotion = new ItemStack(Items.LINGERING_POTION);
		lingeringPotion.set(DataComponents.POTION_CONTENTS, new PotionContents(UAMobEffects.VIBING_NORMAL));
		createAdvancement("vibe_check", "nether", ResourceLocation.withDefaultNamespace("nether/brew_potion"), lingeringPotion, AdvancementType.TASK, true, true, false)
				.addCriterion("vibing", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasComponents(DataComponentPredicate.builder().expect(DataComponents.POTION_CONTENTS, new PotionContents(UAMobEffects.VIBING_NORMAL)).build())))
				.addCriterion("vibing_long", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasComponents(DataComponentPredicate.builder().expect(DataComponents.POTION_CONTENTS, new PotionContents(UAMobEffects.VIBING_LONG)).build())))
				.addCriterion("vibing_strong", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasComponents(DataComponentPredicate.builder().expect(DataComponents.POTION_CONTENTS, new PotionContents(UAMobEffects.VIBING_STRONG)).build())))
				.requirements(Strategy.OR)
				.save(consumer, UpgradeAquatic.MOD_ID + ":nether/vibe_check");

		createAdvancement("sleep_underwater", "adventure", ResourceLocation.withDefaultNamespace("adventure/sleep_in_bed"), UABlocks.BEDROLL, AdvancementType.TASK, true, true, false)
				.addCriterion("sleep_underwater", UACriteriaTriggers.sleepUnderwater())
				.save(consumer, UpgradeAquatic.MOD_ID + ":adventure/sleep_underwater");

		AdvancementHolder obtainTooth = createAdvancement("tooth_fairy", "adventure", ResourceLocation.withDefaultNamespace("adventure/kill_a_mob"), UAItems.THRASHER_TOOTH.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("thrasher_tooth", InventoryChangeTrigger.TriggerInstance.hasItems(UAItems.THRASHER_TOOTH.get()))
				.save(consumer, UpgradeAquatic.MOD_ID + ":adventure/tooth_fairy");

		createAdvancement("kill_great_thrasher", "adventure", obtainTooth, UAItems.THRASHER_TOOTH.get(), AdvancementType.CHALLENGE, true, true, false)
				.addCriterion("great_thrasher", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(UAEntityTypes.GREAT_THRASHER.get())))
				.save(consumer, UpgradeAquatic.MOD_ID + ":adventure/kill_great_thrasher");

		Advancement.Builder unethicalDentistry = createAdvancement("unethical_dentistry", "adventure", obtainTooth, TOOTH_BLOCK.get(), AdvancementType.TASK, true, true, false);
		for (DeferredBlock<?> block : new DeferredBlock[]{TOOTH_BLOCK, TOOTH_BRICKS, TOOTH_BRICK_STAIRS, TOOTH_BRICK_SLAB, TOOTH_BRICK_WALL, TOOTH_TILES, TOOTH_TILE_STAIRS, TOOTH_TILE_SLAB, TOOTH_TILE_WALL, CHISELED_TOOTH_BRICKS, TOOTH_DOOR, TOOTH_TRAPDOOR, TOOTH_LANTERN}) {
			unethicalDentistry.addCriterion(block.getRegisteredName(), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(block.get()));
		}
		unethicalDentistry.requirements(Strategy.OR).save(consumer, UpgradeAquatic.MOD_ID + ":adventure/unethical_dentistry");

		ItemStack bucket = new ItemStack(UAItems.PIKE_BUCKET.get());
		CompoundTag nbt = new CompoundTag();
		nbt.putString("BucketVariantTag", UAPikeVariants.GOLDEN_SOUTHERN.location().toString());
		lingeringPotion.set(DataComponents.BUCKET_ENTITY_DATA, CustomData.of(nbt));

		Advancement.Builder collectAllPike = createAdvancement("collect_all_pike", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/tactical_fishing"), bucket, AdvancementType.CHALLENGE, true, true, false);
		for (ResourceKey<PikeVariant> variant : provider.lookupOrThrow(UARegistries.PIKE_VARIANT).listElementIds().toList()) {
			CompoundTag tag = new CompoundTag();
			tag.putString("BucketVariantTag", variant.location().toString());
			collectAllPike.addCriterion(variant.location().toString(), InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
					.of(UAItems.PIKE_BUCKET.get())
					.withSubPredicate(UAItemSubPredicates.BUCKET_ENTITY_DATA.get(), BucketEntityDataPredicate.bucketEntityData(CustomData.of(tag)))));
		}
		collectAllPike.save(consumer, UpgradeAquatic.MOD_ID + ":adventure/collect_all_pike");

		createAdvancement("eat_pickerelweed", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/root"), UAItems.BOILED_PICKERELWEED.get(), AdvancementType.TASK, true, true, false)
				.addCriterion("eat_pickerelweed", ConsumeItemTrigger.TriggerInstance.usedItem(UAItems.BOILED_PICKERELWEED.get()))
				.save(consumer, UpgradeAquatic.MOD_ID + ":husbandry/eat_pickerelweed");
	}

	private static Advancement.Builder createAdvancement(String name, String category, AdvancementHolder parent, ItemStack icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(parent).display(icon,
				Component.translatable("advancements." + UpgradeAquatic.MOD_ID + "." + category + "." + name + ".title"),
				Component.translatable("advancements." + UpgradeAquatic.MOD_ID + "." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}

	private static Advancement.Builder createAdvancement(String name, String category, AdvancementHolder parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return createAdvancement(name, category, parent, new ItemStack(icon), frame, showToast, announceToChat, hidden);
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return createAdvancement(name, category, parent, new ItemStack(icon), frame, showToast, announceToChat, hidden);
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemStack icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return createAdvancement(name, category, Advancement.Builder.advancement().build(parent), icon, frame, showToast, announceToChat, hidden);
	}
}