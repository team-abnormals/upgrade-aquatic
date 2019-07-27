package com.teamabnormals.upgrade_aquatic.api.util;

import java.util.function.Predicate;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;

import net.minecraft.entity.Entity;

public class UAEntityPredicates {

	public static final Predicate<Entity> IS_SPECTRAL = (p_200818_0_) -> {
		return ((EntityPike)p_200818_0_).getPikeType() == 7 && !((EntityPike)p_200818_0_).isHidingInPickerelweed();
	};
	
	public static final Predicate<Entity> IS_HIDING_IN_PICKERELWEED = (p_200818_0_) -> {
		return !((EntityPike)p_200818_0_).isHidingInPickerelweed();
	};
	
}
