package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UASounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Reference.MODID);
	
	public static final RegistryObject<SoundEvent> THRASHER_THRASH       = createSoundEvent("entity.thrasher.thrash");
	public static final RegistryObject<SoundEvent> THRASHER_SONAR_FIRE   = createSoundEvent("entity.thrasher.sonar_fire");
	public static final RegistryObject<SoundEvent> THRASHER_FLOP         = createSoundEvent("entity.thrasher.flop");
	public static final RegistryObject<SoundEvent> THRASHER_AMBIENT      = createSoundEvent("entity.thrasher.ambient");
	public static final RegistryObject<SoundEvent> THRASHER_AMBIENT_LAND = createSoundEvent("entity.thrasher.ambient_land");
	public static final RegistryObject<SoundEvent> THRASHER_HURT         = createSoundEvent("entity.thrasher.hurt");
	public static final RegistryObject<SoundEvent> THRASHER_HURT_LAND    = createSoundEvent("entity.thrasher.hurt_land");
	public static final RegistryObject<SoundEvent> THRASHER_DEATH        = createSoundEvent("entity.thrasher.death");
	public static final RegistryObject<SoundEvent> THRASHER_DEATH_LAND   = createSoundEvent("entity.thrasher.death_land");
	
	public static final SoundType TOOTH_LANTERN = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_GLASS_BREAK, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL);
	
	private static RegistryObject<SoundEvent> createSoundEvent(String name) {
		return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(Reference.MODID, name)));
	}
}