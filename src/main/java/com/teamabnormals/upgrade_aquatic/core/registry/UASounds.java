package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UASounds {
	public static final SoundSubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> ENTITY_PIKE_AMBIENT = HELPER.createSoundEvent("entity.pike.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_PIKE_DEATH = HELPER.createSoundEvent("entity.pike.death");
	public static final RegistryObject<SoundEvent> ENTITY_PIKE_HURT = HELPER.createSoundEvent("entity.pike.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_PIKE_FLOP = HELPER.createSoundEvent("entity.pike.flop");
	public static final RegistryObject<SoundEvent> ENTITY_PIKE_BITE = HELPER.createSoundEvent("entity.pike.bite");
	public static final RegistryObject<SoundEvent> ENTITY_PIKE_SPIT = HELPER.createSoundEvent("entity.pike.spit");

	public static final RegistryObject<SoundEvent> ENTITY_PERCH_AMBIENT = HELPER.createSoundEvent("entity.perch.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_PERCH_DEATH = HELPER.createSoundEvent("entity.perch.death");
	public static final RegistryObject<SoundEvent> ENTITY_PERCH_HURT = HELPER.createSoundEvent("entity.perch.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_PERCH_FLOP = HELPER.createSoundEvent("entity.perch.flop");

	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_THRASH = HELPER.createSoundEvent("entity.thrasher.thrash");
	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_SONAR_FIRE = HELPER.createSoundEvent("entity.thrasher.sonar_fire");
	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_FLOP = HELPER.createSoundEvent("entity.thrasher.flop");
	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_AMBIENT = HELPER.createSoundEvent("entity.thrasher.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_AMBIENT_LAND = HELPER.createSoundEvent("entity.thrasher.ambient_land");
	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_HURT = HELPER.createSoundEvent("entity.thrasher.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_HURT_LAND = HELPER.createSoundEvent("entity.thrasher.hurt_land");
	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_DEATH = HELPER.createSoundEvent("entity.thrasher.death");
	public static final RegistryObject<SoundEvent> ENTITY_THRASHER_DEATH_LAND = HELPER.createSoundEvent("entity.thrasher.death_land");

	public static final RegistryObject<SoundEvent> ENTITY_JELLYFISH_AMBIENT = HELPER.createSoundEvent("entity.jellyfish.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_JELLYFISH_HURT = HELPER.createSoundEvent("entity.jellyfish.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_JELLYFISH_DEATH = HELPER.createSoundEvent("entity.jellyfish.death");
	public static final RegistryObject<SoundEvent> ENTITY_JELLYFISH_HARVEST = HELPER.createSoundEvent("entity.jellyfish.harvest");
	public static final RegistryObject<SoundEvent> ENTITY_JELLYFISH_COOLDOWN_START = HELPER.createSoundEvent("entity.jellyfish.cooldown_start");
	public static final RegistryObject<SoundEvent> ENTITY_JELLYFISH_COOLDOWN_END = HELPER.createSoundEvent("entity.jellyfish.cooldown_end");
	public static final RegistryObject<SoundEvent> ENTITY_JELLYFISH_STING = HELPER.createSoundEvent("entity.jellyfish.sting");
	public static final RegistryObject<SoundEvent> ENTITY_GHOST_JELLYFISH_VANISH = HELPER.createSoundEvent("entity.jellyfish.ghost_vanish");

	public static final RegistryObject<SoundEvent> ITEM_BUCKET_EMPTY_JELLYFISH = HELPER.createSoundEvent("item.bucket.empty_jellyfish");
	public static final RegistryObject<SoundEvent> ITEM_BUCKET_FILL_JELLYFISH = HELPER.createSoundEvent("item.bucket.fill_jellyfish");

	public static final SoundType TOOTH_LANTERN = new SoundType(1.0F, 1.0F, SoundEvents.GLASS_BREAK, SoundEvents.END_PORTAL_FRAME_FILL, SoundEvents.END_PORTAL_FRAME_FILL, SoundEvents.END_PORTAL_FRAME_FILL, SoundEvents.END_PORTAL_FRAME_FILL);
}