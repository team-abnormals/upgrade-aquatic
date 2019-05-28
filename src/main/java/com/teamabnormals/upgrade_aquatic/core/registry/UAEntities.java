package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityDrownedVillager;
import com.teamabnormals.upgrade_aquatic.core.registry.util.RegistryUtils;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAEntities {
	/*
	 * Entity Types
	 */
	public static final EntityType<EntityDrownedVillager> DROWNED_VILLAGER_ENTITY_TYPE = EntityType.register(Reference.MODID + ":drowned_villager", EntityType.Builder.create(EntityDrownedVillager.class, EntityDrownedVillager::new).tracker(256, 1, true));
	
	/*                                
	 * Spawn Eggs
	 */
	public static Item ENTITY_DROWNED_VILLAGER_SPAWN_EGG = RegistryUtils.createSpawnEggForEntity(DROWNED_VILLAGER_ENTITY_TYPE, 5680028, 7969893, ItemGroup.MISC);
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(ENTITY_DROWNED_VILLAGER_SPAWN_EGG);
    }
}
