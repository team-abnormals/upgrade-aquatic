package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.common.damagesource.JellyfishDamageSource;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;

import net.minecraft.util.DamageSource;

public class UADamageSources {
	public static final DamageSource GUARDIAN_SPINE = new DamageSource("upgrade_aquatic.guardianSpine");
	public static final DamageSource ELDER_GUARDIAN_SPINE = new DamageSource("upgrade_aquatic.elderGuardianSpine");

	public static DamageSource causeJellyfishDamage(AbstractJellyfishEntity jellyfish) {
		return new JellyfishDamageSource(jellyfish);
	}
}