package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.util.UARegistryHelper;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UASounds {
	public static final UARegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER;
	
	public static final RegistryObject<SoundEvent> THRASHER_THRASH       = HELPER.createSoundEvent("entity.thrasher.thrash");
	public static final RegistryObject<SoundEvent> THRASHER_SONAR_FIRE   = HELPER.createSoundEvent("entity.thrasher.sonar_fire");
	public static final RegistryObject<SoundEvent> THRASHER_FLOP         = HELPER.createSoundEvent("entity.thrasher.flop");
	public static final RegistryObject<SoundEvent> THRASHER_AMBIENT      = HELPER.createSoundEvent("entity.thrasher.ambient");
	public static final RegistryObject<SoundEvent> THRASHER_AMBIENT_LAND = HELPER.createSoundEvent("entity.thrasher.ambient_land");
	public static final RegistryObject<SoundEvent> THRASHER_HURT         = HELPER.createSoundEvent("entity.thrasher.hurt");
	public static final RegistryObject<SoundEvent> THRASHER_HURT_LAND    = HELPER.createSoundEvent("entity.thrasher.hurt_land");
	public static final RegistryObject<SoundEvent> THRASHER_DEATH        = HELPER.createSoundEvent("entity.thrasher.death");
	public static final RegistryObject<SoundEvent> THRASHER_DEATH_LAND   = HELPER.createSoundEvent("entity.thrasher.death_land");
	
	public static final SoundType TOOTH_LANTERN = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_GLASS_BREAK, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL);
}