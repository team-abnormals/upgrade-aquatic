package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class UADamageTypes {
	public static final ResourceKey<DamageType> JELLYFISH_STING = createKey("jellyfish_sting");

	public static void bootstrap(BootstrapContext<DamageType> context) {
		context.register(JELLYFISH_STING, new DamageType(UpgradeAquatic.MOD_ID + ".jellyfishSting", 0.1F));
	}

	public static DamageSource jellyfishSting(Level level, @Nullable Entity causingEntity) {
		return level.damageSources().source(JELLYFISH_STING, causingEntity);
	}

	public static ResourceKey<DamageType> createKey(String name) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, UpgradeAquatic.location(name));
	}
}