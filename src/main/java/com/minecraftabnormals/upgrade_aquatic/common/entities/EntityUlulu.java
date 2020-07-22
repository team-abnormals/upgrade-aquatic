package com.minecraftabnormals.upgrade_aquatic.common.entities;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityUlulu extends SlimeEntity {

	public EntityUlulu(EntityType<? extends EntityUlulu> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected boolean spawnCustomParticles() { return true; }
	
	@Override
	protected ResourceLocation getLootTable() {
	      return this.isSmallSlime() ? LootTables.EMPTY : this.getType().getLootTable();
	   }
	
	public EntitySize getSize(Pose poseIn) {
	      return super.getSize(poseIn).scale(0.255F * (float)this.getSlimeSize());
	   }

}
