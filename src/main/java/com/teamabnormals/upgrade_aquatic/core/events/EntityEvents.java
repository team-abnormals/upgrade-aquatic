package com.teamabnormals.upgrade_aquatic.core.events;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EntityEvents {
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntitySpawned(EntityJoinWorldEvent event) {
		if(event.getEntity() instanceof CreatureEntity) {
			CreatureEntity entity = (CreatureEntity)event.getEntity();
			if(entity instanceof DrownedEntity) {
				((DrownedEntity) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>(entity, TurtleEntity.class, 9.0F, 4.4D, 3.9D, EntityPredicates.NOT_SPECTATING::test));
			}
		}
	}
	
}
