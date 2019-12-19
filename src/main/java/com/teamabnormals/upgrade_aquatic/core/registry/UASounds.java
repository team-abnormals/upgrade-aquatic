package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UASounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Reference.MODID);
	
	public static final RegistryObject<SoundEvent> THRASHER_THRASH       = SOUNDS.register("entity.thrasher.thrash", () -> new SoundEvent(createSoundLocation("entity.thrasher.thrash")));
	public static final RegistryObject<SoundEvent> THRASHER_SONAR_FIRE   = SOUNDS.register("entity.thrasher.sonar_fire", () -> new SoundEvent(createSoundLocation("entity.thrasher.sonar_fire")));
	public static final RegistryObject<SoundEvent> THRASHER_AMBIENT      = SOUNDS.register("entity.thrasher.ambient", () -> new SoundEvent(createSoundLocation("entity.thrasher.ambient")));
	public static final RegistryObject<SoundEvent> THRASHER_AMBIENT_LAND = SOUNDS.register("entity.thrasher.ambient_land", () -> new SoundEvent(createSoundLocation("entity.thrasher.ambient_land")));
	public static final RegistryObject<SoundEvent> THRASHER_HURT         = SOUNDS.register("entity.thrasher.hurt", () -> new SoundEvent(createSoundLocation("entity.thrasher.hurt")));
	public static final RegistryObject<SoundEvent> THRASHER_HURT_LAND    = SOUNDS.register("entity.thrasher.hurt_land", () -> new SoundEvent(createSoundLocation("entity.thrasher.hurt_land")));
	public static final RegistryObject<SoundEvent> THRASHER_DEATH        = SOUNDS.register("entity.thrasher.death", () -> new SoundEvent(createSoundLocation("entity.thrasher.death")));
	public static final RegistryObject<SoundEvent> THRASHER_DEATH_LAND   = SOUNDS.register("entity.thrasher.death_land", () -> new SoundEvent(createSoundLocation("entity.thrasher.death_land")));
	
	private static ResourceLocation createSoundLocation(String name) {
		return new ResourceLocation(Reference.MODID, name);
	}
}