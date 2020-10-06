package com.minecraftabnormals.upgrade_aquatic.core.events;

import java.util.List;

import com.minecraftabnormals.upgrade_aquatic.api.IGlowable;
import com.minecraftabnormals.upgrade_aquatic.api.util.UAEntityPredicates;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.advancement.UACriteriaTriggers;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.BedrollBlock;
import com.minecraftabnormals.upgrade_aquatic.common.entities.LionfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.abnormals_core.core.utils.TradeUtils;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SStatisticsPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MODID)
public class EntityEvents {
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntitySpawned(EntityJoinWorldEvent event) {
		if(event.getWorld().isRemote) return;
		
		Entity entity = event.getEntity();
		if(entity instanceof DrownedEntity) {
			((CreatureEntity) entity).goalSelector.addGoal(3, new AvoidEntityGoal<>((CreatureEntity)entity, TurtleEntity.class, 6.0F, 1.0D, 1.2D));
		}
		if(entity instanceof AbstractFishEntity) {
			((AbstractFishEntity) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>((CreatureEntity)entity, PikeEntity.class, 8.0F, 1.6D, 1.4D, UAEntityPredicates.IS_HIDING_IN_PICKERELWEED::test));
			if(entity instanceof TropicalFishEntity) {
				((AbstractFishEntity) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>((CreatureEntity)entity, LionfishEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.IS_ALIVE::test));
			}
		}
		if(entity instanceof WaterMobEntity && !(entity instanceof IMob)) {
			if(!(entity instanceof DolphinEntity)) {
				((MobEntity) entity).goalSelector.addGoal(1, new AvoidEntityGoal<>((CreatureEntity)entity, ThrasherEntity.class, 20.0F, 1.4D, 1.6D, EntityPredicates.IS_ALIVE::test));
			}
			if(entity instanceof DolphinEntity) {
				((MobEntity) entity).targetSelector.addGoal(0, (new HurtByTargetGoal((DolphinEntity) entity, ThrasherEntity.class)).setCallsForHelp());
				((MobEntity) entity).goalSelector.addGoal(1, new MeleeAttackGoal((DolphinEntity) entity, 1.2D, true));
			}
		}
	}
	
	@SubscribeEvent
	public static void rightClickItem(PlayerInteractEvent.RightClickBlock event) {
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();

		if(!player.isSecondaryUseActive())
		{
			if (stack.getItem() == UAItems.GLOWING_INK_SAC.get()) {
				if (world.getTileEntity(pos) instanceof IGlowable) {
					IGlowable te = (IGlowable) world.getTileEntity(pos);

					if (te != null && te.setGlowing(true)) {
						if (!player.abilities.isCreativeMode) stack.shrink(1);
						if (world.isRemote())
							GlowingInkItem.squirtInk(UAParticles.GLOW_SQUID_INK.get(), world, pos);
						world.playSound(player, pos, SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.BLOCKS, 1.0F, 1.0F);
					}

					event.setCanceled(true);
					event.setCancellationResult(ActionResultType.SUCCESS);
				}
			}

			if (event.getItemStack().getItem() == Items.INK_SAC) {
				if (!world.isRemote()) {
					if (!player.abilities.isCreativeMode)
						event.getItemStack().shrink(1);
				} else {
					world.playSound(player, pos, SoundEvents.ENTITY_SQUID_SQUIRT, SoundCategory.BLOCKS, 1.0F, 1.0F);
					GlowingInkItem.squirtInk(ParticleTypes.SQUID_INK, world, world.getBlockState(pos).isSolid() ? pos.offset(event.getFace()) : pos);
				}

				event.setCanceled(true);
				event.setCancellationResult(ActionResultType.SUCCESS);
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
	public static void onPlayerSleep(PlayerSleepInBedEvent event) {
		PlayerEntity player = event.getPlayer();
		BlockState state = player.getEntityWorld().getBlockState(event.getPos());
		if (event.getResultStatus() == null && state.getFluidState().getLevel() == 8 && state.getBlock() instanceof BedrollBlock) {
			if (player instanceof ServerPlayerEntity && player.isAlive()) {
				ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
				if (!player.world.isRemote()) {
					UACriteriaTriggers.SLEEP_UNDERWATER.trigger(serverPlayer); 
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onInteractEntity(EntityInteract event) {
		Entity entity = event.getTarget();
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();
		if(stack.getItem() == Items.WATER_BUCKET && entity.isAlive() && entity.getType() == EntityType.SQUID) {
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
	public static void onPlayerMount(EntityMountEvent event) {
		Entity mountingEntity = event.getEntityMounting();
		Entity entityBeingMounted = event.getEntityBeingMounted();
		if (mountingEntity instanceof PlayerEntity && entityBeingMounted instanceof ThrasherEntity) {
			PlayerEntity player = (PlayerEntity) mountingEntity;
			ThrasherEntity thrasher = (ThrasherEntity) entityBeingMounted;
			if (event.isDismounting() && player.isAlive() && !player.isCreative() && !player.isSpectator() && thrasher.isAlive()) {
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public static void onDrownedPoseChange(EntityEvent.EyeHeight event) {
		Entity entity = event.getEntity();
		if (entity instanceof DrownedEntity && event.getPose() == Pose.SWIMMING) {
			DrownedEntity drowned = (DrownedEntity) entity;
			event.setNewHeight(0.40F);
			drowned.size = EntitySize.flexible(drowned.isChild() ? 0.7F : 0.6F, 0.6F);
		}
	}
	
	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		List<ITrade> genericTrades = event.getGenericTrades();
		List<ITrade> rareTrades = event.getRareTrades();
		//Common
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.BEACHGRASS.get(), 1, 1, 12, 1));				
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.WHITE_SEAROCKET.get(), 1, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.PINK_SEAROCKET.get(), 1, 1, 8, 1));		
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.BLUE_PICKERELWEED.get(), 1, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.PURPLE_PICKERELWEED.get(), 1, 1, 8, 1));	
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.FLOWERING_RUSH.get(), 1, 1, 9, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.FINGER_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.ACAN_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.BRANCH_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.PILLOW_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.SILK_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.PETAL_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.MOSS_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.ROCK_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.STAR_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.CHROME_CORAL_BLOCK.get(), 3, 1, 8, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.EMBEDDED_AMMONITE.get(), 5, 1, 6, 1));
/*		
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.POLAR_KELP, 3, 1, 12, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.TONGUE_KELP, 3, 1, 12, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.OCHRE_KELP, 3, 1, 12, 1));
		genericTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.THORNY_KELP, 3, 1, 12, 1));
*/		
		//Buckets
		rareTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.DRIFTWOOD_LOG.get(), 2, 1, 16, 1));
		rareTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.PRISMARINE_CORAL_BLOCK.get(), 6, 1, 4, 1));
		rareTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UAItems.PIKE_BUCKET.get(), 5, 1, 4, 1));
		rareTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UAItems.LIONFISH_BUCKET.get(), 5, 1, 4, 1));
		rareTrades.add(new TradeUtils.ItemsForEmeraldsTrade(UAItems.NAUTILUS_BUCKET.get(), 5, 1, 4, 1));
	}
	
	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		if(event.getType() == VillagerProfession.FISHERMAN) {
			event.getTrades().get(3).add(new TradeUtils.ItemsForEmeraldsAndItemsTrade(UAItems.PIKE.get(), 6, 1, UAItems.COOKED_PIKE.get(), 6, 16, 15));
			event.getTrades().get(4).add(new TradeUtils.EmeraldsForItemsTrade(UAItems.PIKE.get(), 5, 1, 12, 25));	
			event.getTrades().get(4).add(new TradeUtils.ItemsForEmeraldsAndItemsTrade(UAItems.LIONFISH.get(), 6, 1, UAItems.COOKED_LIONFISH.get(), 6, 16, 30));
			event.getTrades().get(5).add(new TradeUtils.EmeraldsForItemsTrade(UAItems.LIONFISH.get(), 3, 1, 12, 30));
		}
		if(event.getType() == VillagerProfession.MASON) {
			event.getTrades().get(5).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.TOOTH_TILES.get(), 5, 1, 12, 30));	
			event.getTrades().get(5).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.TOOTH_BRICKS.get(), 5, 1, 12, 30));	
		}
		if(event.getType() == VillagerProfession.CLERIC) {
			event.getTrades().get(4).add(new TradeUtils.EmeraldsForItemsTrade(UAItems.THRASHER_TOOTH.get(), 1, 1, 12, 15));	
		}
		if(event.getType() == VillagerProfession.LEATHERWORKER) {
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.WHITE_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.ORANGE_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.MAGENTA_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.LIGHT_BLUE_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.YELLOW_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.LIME_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.PINK_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.GRAY_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.LIGHT_GRAY_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.CYAN_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.PURPLE_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.BLUE_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.BROWN_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.GREEN_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.RED_BEDROLL.get(), 1, 1, 8, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(UABlocks.BLACK_BEDROLL.get(), 1, 1, 8, 10));
		}
    }
}
