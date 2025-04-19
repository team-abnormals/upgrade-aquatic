package com.teamabnormals.upgrade_aquatic.api.util;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAPikeVariants;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;

import java.util.function.Predicate;

public class UAEntityPredicates {
	public static final Predicate<Entity> IS_SPECTRAL = (entity) -> ((Pike) entity).getVariant().is(UAPikeVariants.SPECTRAL) && !((Pike) entity).isHidingInPickerelweed();
	public static final Predicate<Entity> IS_HIDING_IN_PICKERELWEED = (entity) -> !((Pike) entity).isHidingInPickerelweed();
	public static final Predicate<Entity> IS_CHILD = (entity) -> ((Animal) entity).isBaby();
}