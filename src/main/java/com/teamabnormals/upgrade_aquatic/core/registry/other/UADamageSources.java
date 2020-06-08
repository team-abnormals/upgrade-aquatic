package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.common.damagesource.JellyfishDamageSource;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractEntityJellyfish;

import net.minecraft.util.DamageSource;

public class UADamageSources {
	public static final DamageSource GUARDIAN_SPINE = new DamageSource("upgrade_aquatic.guardianSpine");
	public static final DamageSource ELDER_GUARDIAN_SPINE = new DamageSource("upgrade_aquatic.elderGuardianSpine");

	public static DamageSource causeJellyfishDamage(AbstractEntityJellyfish jellyfish) {
		return new JellyfishDamageSource(jellyfish);
	}
}