package com.minecraftabnormals.upgrade_aquatic.core.events;

import com.minecraftabnormals.upgrade_aquatic.api.IGlowable;
import com.minecraftabnormals.upgrade_aquatic.api.util.UAEntityPredicates;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.advancement.UACriteriaTriggers;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.BedrollBlock;
import com.minecraftabnormals.upgrade_aquatic.common.entities.LionfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.common.items.GlowingInkItem;
import com.minecraftabnormals.upgrade_aquatic.core.UAConfig;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.blueprint.core.util.TradeUtil;
import com.teamabnormals.blueprint.core.util.TradeUtil.BlueprintTrade;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAwardStatsPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.stats.StatsCounter;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class EntityEvents {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntitySpawned(EntityJoinWorldEvent event) {
		if (event.getWorld().isClientSide) return;

		Entity entity = event.getEntity();
		if (entity instanceof Drowned) {
			((PathfinderMob) entity).goalSelector.addGoal(3, new AvoidEntityGoal<>((PathfinderMob) entity, Turtle.class, 6.0F, 1.0D, 1.2D));
		}
		if (entity instanceof AbstractFish) {
			((AbstractFish) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>((PathfinderMob) entity, PikeEntity.class, 8.0F, 1.6D, 1.4D, UAEntityPredicates.IS_HIDING_IN_PICKERELWEED::test));
			if (entity instanceof TropicalFish) {
				((AbstractFish) entity).goalSelector.addGoal(2, new AvoidEntityGoal<>((PathfinderMob) entity, LionfishEntity.class, 8.0F, 1.6D, 1.4D, EntitySelector.ENTITY_STILL_ALIVE::test));
			}
		}
		if (entity instanceof WaterAnimal && !(entity instanceof Enemy)) {
			if (!(entity instanceof Dolphin)) {
				((Mob) entity).goalSelector.addGoal(1, new AvoidEntityGoal<>((PathfinderMob) entity, ThrasherEntity.class, 20.0F, 1.4D, 1.6D, EntitySelector.ENTITY_STILL_ALIVE::test));
			}
			if (entity instanceof Dolphin) {
				((Mob) entity).targetSelector.addGoal(0, (new HurtByTargetGoal((Dolphin) entity, ThrasherEntity.class)).setAlertOthers());
				((Mob) entity).goalSelector.addGoal(1, new MeleeAttackGoal((Dolphin) entity, 1.2D, true));
			}
		}
	}

	@SubscribeEvent
	public static void rightClickItem(PlayerInteractEvent.RightClickBlock event) {
		Level world = event.getWorld();
		BlockPos pos = event.getPos();
		Player player = event.getPlayer();
		ItemStack stack = event.getItemStack();

		if (!player.isSecondaryUseActive()) {
			if (stack.getItem() == UAItems.GLOWING_INK_SAC.get()) {
				if (world.getBlockEntity(pos) instanceof IGlowable te) {
					if (te.setGlowing(true)) {
						if (!player.getAbilities().instabuild) stack.shrink(1);
						if (!world.isClientSide()) GlowingInkItem.squirtInk(UAParticles.GLOW_SQUID_INK.get(), pos);
						world.playSound(player, pos, SoundEvents.SQUID_SQUIRT, SoundSource.BLOCKS, 1.0F, 1.0F);
						event.setCanceled(true);
						event.setCancellationResult(InteractionResult.SUCCESS);
					}
				}
			}

			if (stack.getItem() == Items.INK_SAC) {
				if (world.getBlockEntity(pos) instanceof IGlowable te) {
					if (te.setGlowing(false)) {
						if (!player.getAbilities().instabuild) stack.shrink(1);
						if (!world.isClientSide()) GlowingInkItem.squirtInk(ParticleTypes.SQUID_INK, pos);
						world.playSound(player, pos, SoundEvents.SQUID_SQUIRT, SoundSource.BLOCKS, 1.0F, 1.0F);
						event.setCanceled(true);
						event.setCancellationResult(InteractionResult.SUCCESS);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if (entity instanceof Phantom) {
			if (((Phantom) entity).getTarget() instanceof ServerPlayer serverPlayer) {
				StatsCounter statisticsManager = serverPlayer.getStats();
				if (statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 72000) {
					((Phantom) entity).setTarget(null);
				}
			}
		}
		if (entity instanceof Drowned && UAConfig.COMMON.drownedSwimmingAnimation.get() && entity.isEffectiveAi()) {
			Pose pose = entity.getDeltaMovement().horizontalDistanceSqr() >= 0.000625F && entity.getCommandSenderWorld().getFluidState(entity.blockPosition().below()).is(FluidTags.WATER) ? Pose.SWIMMING : Pose.STANDING;
			if (entity.getPose() != pose)
				entity.setPose(pose);
		}
	}

	@SubscribeEvent
	public static void onPlayerSleep(PlayerSleepInBedEvent event) {
		Player player = event.getPlayer();
		BlockState state = player.getCommandSenderWorld().getBlockState(event.getPos());
		if (event.getResultStatus() == null && state.getFluidState().getAmount() == 8 && state.getBlock() instanceof BedrollBlock) {
			if (player instanceof ServerPlayer serverPlayer && player.isAlive()) {
				if (!player.level.isClientSide()) {
					UACriteriaTriggers.SLEEP_UNDERWATER.trigger(serverPlayer);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
		Entity entity = event.getEntity();
		BlockPos spawn = event.getNewSpawn();
		if (spawn != null && entity.getCommandSenderWorld().getBlockState(spawn).getBlock() instanceof BedrollBlock)
			event.setCanceled(true);
	}

	@SubscribeEvent
	public static void onInteractEntity(EntityInteract event) {
		Entity entity = event.getTarget();
		Player player = event.getPlayer();
		ItemStack stack = event.getItemStack();
		if (stack.getItem() == Items.WATER_BUCKET && entity.isAlive() && entity instanceof Squid) {
			ItemStack bucket = ItemStack.EMPTY;
			if (entity.getType() == EntityType.SQUID) {
				bucket = new ItemStack(UAItems.SQUID_BUCKET.get());
			} else if (entity.getType() == UAEntities.GLOW_SQUID.get()) {
				bucket = new ItemStack(UAItems.GLOW_SQUID_BUCKET.get());
			} else {
				return;
			}

			player.swing(event.getHand());
			entity.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
			stack.shrink(1);

			if (entity.hasCustomName()) {
				bucket.setHoverName(entity.getCustomName());
			}

			if (!event.getWorld().isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
			}

			if (stack.isEmpty()) {
				player.setItemInHand(event.getHand(), bucket);
			} else if (!player.getInventory().add(bucket)) {
				player.drop(bucket, false);
			}

			entity.discard();
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		Player player = event.player;
		ItemStack headSlotStack = player.getItemBySlot(EquipmentSlot.HEAD);
		if (!event.player.level.isClientSide && event.player.level.getGameTime() % 5 == 0 && event.player instanceof ServerPlayer serverPlayer) {
			StatsCounter statisticsManager = serverPlayer.getStats();
			Object2IntMap<Stat<?>> object2intmap = new Object2IntOpenHashMap<>();
			object2intmap.put(Stats.CUSTOM.get(Stats.TIME_SINCE_REST), statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)));
			serverPlayer.connection.send(new ClientboundAwardStatsPacket(object2intmap));
		}
		if (player.isEffectiveAi() && !headSlotStack.isEmpty() && headSlotStack.getItem() == Items.TURTLE_HELMET) {
			int timeTillDamage = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, headSlotStack) > 0 ? 40 * (1 + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, headSlotStack) / 2) : 40;
			if (player.isEyeInFluid(FluidTags.WATER)) {
				player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 210));
				if (player.level.getGameTime() % timeTillDamage == 0) {
					headSlotStack.hurtAndBreak(1, player, (p_213341_0_) -> {
						p_213341_0_.broadcastBreakEvent(EquipmentSlot.HEAD);
					});
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerMount(EntityMountEvent event) {
		Entity mountingEntity = event.getEntityMounting();
		Entity entityBeingMounted = event.getEntityBeingMounted();
		if (mountingEntity instanceof Player && entityBeingMounted instanceof ThrasherEntity) {
			Player player = (Player) mountingEntity;
			ThrasherEntity thrasher = (ThrasherEntity) entityBeingMounted;
			if (event.isDismounting() && player.isAlive() && !player.isCreative() && !player.isSpectator() && thrasher.isAlive() && !thrasher.isStunned()) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onDrownedPoseChange(EntityEvent.Size event) {
		Entity entity = event.getEntity();
		if (entity instanceof Drowned && entity.getPose() == Pose.SWIMMING && UAConfig.COMMON.drownedSwimmingAnimation.get()) {
			event.setNewSize(new EntityDimensions(event.getOldSize().width, 0.40F, false));
			event.setNewEyeHeight(0.40F);
		}
	}

	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		TradeUtil.addWandererTrades(event,
				new BlueprintTrade(1, UABlocks.BEACHGRASS.get().asItem(), 1, 12, 1),
				new BlueprintTrade(1, UABlocks.WHITE_SEAROCKET.get().asItem(), 1, 8, 1),
				new BlueprintTrade(1, UABlocks.PINK_SEAROCKET.get().asItem(), 1, 8, 1),
				new BlueprintTrade(1, UABlocks.BLUE_PICKERELWEED.get().asItem(), 1, 8, 1),
				new BlueprintTrade(1, UABlocks.PURPLE_PICKERELWEED.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.FINGER_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.ACAN_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.BRANCH_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.PILLOW_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.SILK_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.PETAL_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.MOSS_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.ROCK_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.STAR_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(3, UABlocks.CHROME_CORAL_BLOCK.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, UABlocks.EMBEDDED_AMMONITE.get().asItem(), 1, 5, 1)
		);

		TradeUtil.addRareWandererTrades(event,
				new BlueprintTrade(2, UABlocks.DRIFTWOOD_LOG.get().asItem(), 1, 16, 1),
				new BlueprintTrade(5, UAItems.PIKE_BUCKET.get(), 1, 4, 1),
				new BlueprintTrade(5, UAItems.LIONFISH_BUCKET.get(), 1, 4, 1),
				new BlueprintTrade(5, UAItems.NAUTILUS_BUCKET.get(), 1, 4, 1)
		);
	}

	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		TradeUtil.addVillagerTrades(event, VillagerProfession.FARMER, TradeUtil.APPRENTICE,
				new BlueprintTrade(1, UAItems.MULBERRY.get(), 5, 16, 5)
		);

		if (event.getType().equals(VillagerProfession.FISHERMAN)) {
			TradeUtil.addVillagerTrades(event, TradeUtil.APPRENTICE,
					new BlueprintTrade(new ItemStack(UAItems.PERCH.get(), 6), new ItemStack(Items.EMERALD), new ItemStack(UAItems.COOKED_PERCH.get(), 6), 16, 5, 0.05F)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.JOURNEYMAN,
					new BlueprintTrade(UAItems.PERCH.get(), 14, 1, 16, 20),
					new BlueprintTrade(new ItemStack(UAItems.PIKE.get(), 6), new ItemStack(Items.EMERALD), new ItemStack(UAItems.COOKED_PIKE.get(), 6), 16, 10, 0.05F)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.EXPERT,
					new BlueprintTrade(UAItems.PIKE.get(), 5, 1, 16, 25),
					new BlueprintTrade(new ItemStack(UAItems.LIONFISH.get(), 6), new ItemStack(Items.EMERALD), new ItemStack(UAItems.COOKED_LIONFISH.get(), 6), 16, 15, 0.05F)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.MASTER,
					new BlueprintTrade(UAItems.LIONFISH.get(), 3, 1, 16, 30)
			);
		}

		TradeUtil.addVillagerTrades(event, VillagerProfession.MASON, TradeUtil.MASTER,
				new BlueprintTrade(5, UABlocks.TOOTH_TILES.get().asItem(), 1, 12, 30),
				new BlueprintTrade(5, UABlocks.TOOTH_BRICKS.get().asItem(), 1, 12, 30)
		);

		if (UAConfig.COMMON.clericsBuyThrasherTeeth.get())
			TradeUtil.addVillagerTrades(event, VillagerProfession.CLERIC, TradeUtil.EXPERT,
					new BlueprintTrade(UAItems.THRASHER_TOOTH.get(), 1, 1, 12, 15)
			);

		if (UAConfig.COMMON.leatherworkersSellBedrolls.get())
			TradeUtil.addVillagerTrades(event, VillagerProfession.LEATHERWORKER, TradeUtil.APPRENTICE,
					new BlueprintTrade(1, UABlocks.BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.WHITE_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.ORANGE_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.MAGENTA_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.LIGHT_BLUE_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.YELLOW_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.LIME_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.PINK_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.GRAY_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.LIGHT_GRAY_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.CYAN_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.PURPLE_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.BLUE_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.BROWN_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.GREEN_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.RED_BEDROLL.get().asItem(), 1, 8, 10),
					new BlueprintTrade(1, UABlocks.BLACK_BEDROLL.get().asItem(), 1, 8, 10)
			);
	}
}
