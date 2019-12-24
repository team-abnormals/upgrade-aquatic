package com.teamabnormals.upgrade_aquatic.core.events;

import com.teamabnormals.upgrade_aquatic.api.util.EntityUtil;
import com.teamabnormals.upgrade_aquatic.api.util.UAEntityPredicates;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBedroll;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
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
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EntityEvents {
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntitySpawned(EntityJoinWorldEvent event) {
		if(event.getWorld().isRemote) return;
		
		Entity entity = event.getEntity();
		if(entity instanceof DrownedEntity) {
			((CreatureEntity) entity).goalSelector.addGoal(3, new AvoidEntityGoal<>((CreatureEntity)entity, TurtleEntity.class, 6.0F, 1.0D, 1.2D));
		}
		if(entity instanceof AbstractFishEntity) {
			((AbstractFishEntity) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>((CreatureEntity)entity, EntityPike.class, 8.0F, 1.6D, 1.4D, UAEntityPredicates.IS_HIDING_IN_PICKERELWEED::test));
			if(entity instanceof TropicalFishEntity) {
				((AbstractFishEntity) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>((CreatureEntity)entity, EntityLionfish.class, 8.0F, 1.6D, 1.4D, EntityPredicates.IS_ALIVE::test));
			}
		}
		if(entity instanceof WaterMobEntity && !(entity instanceof IMob)) {
			if(!(entity instanceof DolphinEntity)) {
				((MobEntity) entity).goalSelector.addGoal(1, new AvoidEntityGoal<>((CreatureEntity)entity, EntityThrasher.class, 20.0F, 1.4D, 1.6D, EntityPredicates.IS_ALIVE::test));
			}
			if(entity instanceof DolphinEntity) {
				((MobEntity) entity).targetSelector.addGoal(0, (new HurtByTargetGoal((DolphinEntity) entity, EntityThrasher.class)).setCallsForHelp());
				((MobEntity) entity).goalSelector.addGoal(1, new MeleeAttackGoal((DolphinEntity) entity, 1.2D, true));
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
	public static void onInteractEntity(EntityInteract event) {
		Entity entity = event.getTarget();
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();
		if(stack.getItem() == Items.WATER_BUCKET && entity.isAlive() && entity instanceof SquidEntity) {
			entity.playSound(SoundEvents.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);
			stack.shrink(1);
			ItemStack bucket = new ItemStack(UAItems.SQUID_BUCKET.get());
			
			if(entity.hasCustomName()) {
				bucket.setDisplayName(entity.getCustomName());
			}
			
			if(!event.getWorld().isRemote) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity)player, bucket);
			}
            
			if(stack.isEmpty()) {
				player.setHeldItem(event.getHand(), bucket);
			} else if(!player.inventory.addItemStackToInventory(bucket)) {
				player.dropItem(bucket, false);
			}
			
			entity.remove();
		}
	}
	
	@SubscribeEvent
	public static void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
		PlayerEntity player = event.getPlayer();
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
	
	@SubscribeEvent
	public static void onPlayerRiding(EntityMountEvent event) {
		Entity mountingEntity = event.getEntityMounting();
		Entity entityBeingMounted = event.getEntityBeingMounted();
		if(mountingEntity instanceof PlayerEntity && event.isDismounting() && entityBeingMounted instanceof EntityThrasher && ((EntityThrasher)entityBeingMounted).isAlive()) {
			if(!((PlayerEntity)mountingEntity).isCreative() && !((PlayerEntity)mountingEntity).isSpectator() && ((PlayerEntity)mountingEntity).getHealth() > 0.0F) {
				if(!((EntityThrasher)entityBeingMounted).isStunned()) {
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		//Common
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.SEAROCKET_WHITE, 1, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.SEAROCKET_PINK, 1, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.PICKERELWEED_BLUE, 1, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.PICKERELWEED_PURPLE, 1, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.FINGER_CORAL_BLOCK, 3, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.ACAN_CORAL_BLOCK, 3, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BRANCH_CORAL_BLOCK, 3, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.PILLOW_CORAL_BLOCK, 3, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.SILK_CORAL_BLOCK, 3, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.PETAL_CORAL_BLOCK, 3, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.MOSS_CORAL_BLOCK, 3, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.ROCK_CORAL_BLOCK, 3, 1, 8, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.STAR_CORAL_BLOCK, 3, 1, 8, 1));
/*		
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.POLAR_KELP, 3, 1, 12, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.TONGUE_KELP, 3, 1, 12, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.OCHRE_KELP, 3, 1, 12, 1));
		event.getGenericTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.THORNY_KELP, 3, 1, 12, 1));
*/		
		//Buckets
		event.getRareTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UAItems.PIKE_BUCKET.get(), 5, 1, 4, 1));
		event.getRareTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UAItems.LIONFISH_BUCKET.get(), 5, 1, 4, 1));
		event.getRareTrades().add(new EntityUtil.ItemsForEmeraldsTrade(UAItems.NAUTILUS_BUCKET.get(), 5, 1, 4, 1));
	}
	
	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		if(event.getType() == VillagerProfession.FISHERMAN) {
			event.getTrades().get(3).add(new EntityUtil.ItemsForEmeraldsAndItemsTrade(UAItems.PIKE.get(), 6, UAItems.PIKE_COOKED.get(), 6, 16, 15));
			event.getTrades().get(4).add(new EntityUtil.EmeraldsForItemsTrade(UAItems.PIKE.get(), 5, 1, 12, 25));	
			event.getTrades().get(4).add(new EntityUtil.ItemsForEmeraldsAndItemsTrade(UAItems.LIONFISH.get(), 6, UAItems.LIONFISH_COOKED.get(), 6, 16, 30));
			event.getTrades().get(5).add(new EntityUtil.EmeraldsForItemsTrade(UAItems.LIONFISH.get(), 3, 1, 12, 30));
		}
		if(event.getType() == VillagerProfession.MASON) {
			event.getTrades().get(5).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.EMBEDDED_AMMONITE, 5, 1, 5, 30));	
		}
		if(event.getType() == VillagerProfession.CLERIC) {
			event.getTrades().get(4).add(new EntityUtil.EmeraldsForItemsTrade(UAItems.THRASHER_TOOTH.get(), 2, 1, 12, 15));	
		}
		if(event.getType() == VillagerProfession.LEATHERWORKER) {
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_LEATHER, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_WHITE, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_ORANGE, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_MAGENTA, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_LIGHT_BLUE, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_YELLOW, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_LIME, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_PINK, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_GRAY, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_LIGHT_GRAY, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_CYAN, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_PURPLE, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_BLUE, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_BROWN, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_GREEN, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_RED, 1, 1, 8, 10));
			event.getTrades().get(2).add(new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BEDROLL_BLACK, 1, 1, 8, 10));
		}
    }
}
