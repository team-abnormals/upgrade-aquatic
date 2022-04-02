package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.common.damagesource.JellyfishDamageSource;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import net.minecraft.world.damagesource.DamageSource;

public class UADamageSources {
	public static final DamageSource GUARDIAN_SPINE = new DamageSource("upgrade_aquatic.guardianSpine");
	public static final DamageSource ELDER_GUARDIAN_SPINE = new DamageSource("upgrade_aquatic.elderGuardianSpine");

	public static DamageSource causeJellyfishDamage(AbstractJellyfish jellyfish) {
		return new JellyfishDamageSource(jellyfish);
	}
}