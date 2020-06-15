package com.teamabnormals.upgrade_aquatic.common.entities;

import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityGoose extends ChickenEntity {

	public EntityGoose(EntityType<? extends EntityGoose> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(Items.CHICKEN_SPAWN_EGG);
	}
	
	public EntityGoose createChild(AgeableEntity ageable) {
		return UAEntities.GOOSE.get().create(this.world);
	}
	
	@Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
