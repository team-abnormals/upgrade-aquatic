package com.teamabnormals.upgrade_aquatic.common.entities.thrasher;

import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome.SpawnListEntry;

public class EntityGreatThrasher extends EntityThrasher {

	public EntityGreatThrasher(EntityType<? extends EntityThrasher> type, World world) {
		super(type, world);
		this.experienceValue = 55;
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(STUN_DAMAGE_THRESHOLD).setBaseValue(8.0D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(125.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(16.0D);
	}
	
	@Override
	public float getMountDistance() {
		return 2.1F;
	}
	
	@Override
	public double getMountedYOffset() {
		return 0.875F;
	}
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.GREAT_THRASHER_SPAWN_EGG.get());
	}
	
	public static void addSpawn() {
		Biomes.DEEP_FROZEN_OCEAN.getSpawns(EntityClassification.WATER_CREATURE).add(new SpawnListEntry(UAEntities.GREAT_THRASHER.get(), 10, 1, 1));
	}

}
