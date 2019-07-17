package com.teamabnormals.upgrade_aquatic.core.events;

import com.teamabnormals.upgrade_aquatic.api.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBedroll;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EntityEvents {
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntitySpawned(EntityJoinWorldEvent event) {
		if (event.getWorld().isRemote) {
            return;
        }
		Entity entity = event.getEntity();
		if(entity instanceof DrownedEntity) {
			((CreatureEntity) entity).goalSelector.addGoal(3, new AvoidEntityGoal<>((CreatureEntity)entity, TurtleEntity.class, 6.0F, 1.0D, 1.2D));
		}
	}
	
	@SubscribeEvent
	public static void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
		PlayerEntity player = event.getEntityPlayer();
		if(player.getEntityWorld().getBlockState(event.getNewSpawn()).getBlock() instanceof BlockBedroll) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		if(!event.player.world.isRemote && event.player.world.getGameTime() % 5 == 0 && event.player instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) event.player;
			StatisticsManager statisticsManager = player.getStats();
			NetworkUtil.updateCPlayerRestTime(event.player.getEntityId(), statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)));
		}
		if(event.player instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) event.player;
			StatisticsManager statisticsManager = player.getStats();
			System.out.println("server" + statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)));
		} else if(event.player instanceof ClientPlayerEntity) {
			ClientPlayerEntity player = (ClientPlayerEntity) event.player;
			StatisticsManager statisticsManager = player.getStats();
			System.out.println("client" + statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)));
		}
	}
	
}
