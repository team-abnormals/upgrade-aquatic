package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.SoundSubRegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UASounds {
	public static final SoundSubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getSoundSubHelper();
	
	public static final RegistryObject<SoundEvent> THRASHER_THRASH       = HELPER.createSoundEvent("entity.thrasher.thrash");
	public static final RegistryObject<SoundEvent> THRASHER_SONAR_FIRE   = HELPER.createSoundEvent("entity.thrasher.sonar_fire");
	public static final RegistryObject<SoundEvent> THRASHER_FLOP         = HELPER.createSoundEvent("entity.thrasher.flop");
	public static final RegistryObject<SoundEvent> THRASHER_AMBIENT      = HELPER.createSoundEvent("entity.thrasher.ambient");
	public static final RegistryObject<SoundEvent> THRASHER_AMBIENT_LAND = HELPER.createSoundEvent("entity.thrasher.ambient_land");
	public static final RegistryObject<SoundEvent> THRASHER_HURT         = HELPER.createSoundEvent("entity.thrasher.hurt");
	public static final RegistryObject<SoundEvent> THRASHER_HURT_LAND    = HELPER.createSoundEvent("entity.thrasher.hurt_land");
	public static final RegistryObject<SoundEvent> THRASHER_DEATH        = HELPER.createSoundEvent("entity.thrasher.death");
	public static final RegistryObject<SoundEvent> THRASHER_DEATH_LAND   = HELPER.createSoundEvent("entity.thrasher.death_land");
	
	public static final RegistryObject<SoundEvent> JELLYFISH_AMBIENT        = HELPER.createSoundEvent("entity.jellyfish.ambient");
	public static final RegistryObject<SoundEvent> JELLYFISH_HURT           = HELPER.createSoundEvent("entity.jellyfish.hurt");
	public static final RegistryObject<SoundEvent> JELLYFISH_DEATH          = HELPER.createSoundEvent("entity.jellyfish.death");
	public static final RegistryObject<SoundEvent> JELLYFISH_HARVEST        = HELPER.createSoundEvent("entity.jellyfish.harvest");
	public static final RegistryObject<SoundEvent> JELLYFISH_COOLDOWN_START = HELPER.createSoundEvent("entity.jellyfish.cooldown_start");
	public static final RegistryObject<SoundEvent> JELLYFISH_COOLDOWN_END   = HELPER.createSoundEvent("entity.jellyfish.cooldown_end");
	public static final RegistryObject<SoundEvent> JELLYFISH_STING          = HELPER.createSoundEvent("entity.jellyfish.sting");
	public static final RegistryObject<SoundEvent> GHOST_JELLYFISH_VANISH   = HELPER.createSoundEvent("entity.jellyfish.ghost_vanish");
	
	public static final RegistryObject<SoundEvent> ITEM_BUCKET_EMPTY_JELLYFISH = HELPER.createSoundEvent("item.bucket.empty_jellyfish");
	public static final RegistryObject<SoundEvent> ITEM_BUCKET_FILL_JELLYFISH  = HELPER.createSoundEvent("item.bucket.fill_jellyfish");
	
	public static final SoundType TOOTH_LANTERN = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_GLASS_BREAK, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL);
}