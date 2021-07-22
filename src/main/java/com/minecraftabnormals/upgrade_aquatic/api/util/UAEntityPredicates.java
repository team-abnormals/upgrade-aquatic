package com.minecraftabnormals.upgrade_aquatic.api.util;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;

import java.util.function.Predicate;

public class UAEntityPredicates {

	public static final Predicate<Entity> IS_SPECTRAL = (entity) -> {
		return ((PikeEntity) entity).getPikeType() == PikeType.SPECTRAL && !((PikeEntity) entity).isHidingInPickerelweed();
	};
	
	public static final Predicate<Entity> IS_HIDING_IN_PICKERELWEED = (entity) -> {
		return !((PikeEntity) entity).isHidingInPickerelweed();
	};
	
	public static final Predicate<Entity> IS_CHILD = (entity) -> {
		return ((AnimalEntity) entity).isBaby();
	};
	
}