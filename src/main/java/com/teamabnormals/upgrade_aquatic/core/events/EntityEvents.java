package com.teamabnormals.upgrade_aquatic.core.events;

import com.teamabnormals.upgrade_aquatic.api.util.UAEntityPredicates;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBedroll;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SStatisticsPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
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
		if(entity instanceof AbstractFishEntity) {
			if(entity instanceof SalmonEntity) {
				((AbstractFishEntity) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>((CreatureEntity)entity, EntityPike.class, 8.0F, 1.6D, 1.4D, UAEntityPredicates.IS_HIDING_IN_PICKERELWEED::test));
			} else {
				((AbstractFishEntity) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>((CreatureEntity)entity, EntityPike.class, 8.0F, 1.6D, 1.4D, UAEntityPredicates.IS_SPECTRAL::test));
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(entity instanceof PhantomEntity) {
			if(((PhantomEntity) entity).getAttackTarget() instanceof ServerPlayerEntity) {
				ServerPlayerEntity playerMP = (ServerPlayerEntity) ((PhantomEntity) entity).getAttackTarget();
				StatisticsManager statisticsManager = playerMP.getStats();
				if(statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 72000) {
					((PhantomEntity) entity).setAttackTarget(null);
				}
			}
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
		PlayerEntity player = event.player;
		ItemStack headSlotStack = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		if(!event.player.world.isRemote && event.player.world.getGameTime() % 5 == 0 && event.player instanceof ServerPlayerEntity) {
			ServerPlayerEntity sPlayer = (ServerPlayerEntity) event.player;
			StatisticsManager statisticsManager = sPlayer.getStats();
			Object2IntMap<Stat<?>> object2intmap = new Object2IntOpenHashMap<>();
			object2intmap.put(Stats.CUSTOM.get(Stats.TIME_SINCE_REST), statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)));
			sPlayer.connection.sendPacket(new SStatisticsPacket(object2intmap));
		}
		if(player.isServerWorld() && !headSlotStack.isEmpty() && headSlotStack.getItem() == Items.TURTLE_HELMET) {
			int timeTillDamage = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, headSlotStack) > 0 ? 40 * (1 + EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, headSlotStack) / 2) : 40;
			if(player.areEyesInFluid(FluidTags.WATER)) {
				player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 210));
				if(player.world.getGameTime() % timeTillDamage == 0) {
					headSlotStack.damageItem(1, player, (p_213341_0_) -> {
						p_213341_0_.sendBreakAnimation(EquipmentSlotType.HEAD);
					});
				}
			}
		}
	}
	
}
