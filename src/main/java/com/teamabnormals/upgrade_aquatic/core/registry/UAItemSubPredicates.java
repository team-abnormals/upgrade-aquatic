package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.advancement.BucketEntityDataPredicate;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.advancements.critereon.ItemSubPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicate.Type;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class UAItemSubPredicates {
	public static final DeferredRegister<ItemSubPredicate.Type<?>> ITEM_SUB_PREDICATES = DeferredRegister.create(Registries.ITEM_SUB_PREDICATE_TYPE, UpgradeAquatic.MOD_ID);

	public static final DeferredHolder<Type<?>, Type<BucketEntityDataPredicate>> BUCKET_ENTITY_DATA = ITEM_SUB_PREDICATES.register("bucket_entity_data", () -> new Type<>(BucketEntityDataPredicate.CODEC));
}