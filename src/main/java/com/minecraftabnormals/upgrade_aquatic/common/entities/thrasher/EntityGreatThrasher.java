package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityGreatThrasher extends EntityThrasher {

	public EntityGreatThrasher(EntityType<? extends EntityThrasher> type, World world) {
		super(type, world);
		this.experienceValue = 55;
	}

	@Override
	public float getMountDistance() {
		return 2.1F;
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
    	return MobEntity.func_233666_p_().
    			func_233815_a_(EntityThrasher.STUN_DAMAGE_THRESHOLD, 125.0D).
    			func_233815_a_(Attributes.MAX_HEALTH, 125.0D).
    			func_233815_a_(Attributes.ATTACK_DAMAGE, 8.0D).
    			func_233815_a_(Attributes.ARMOR, 16.0D);
    }
	
	@Override
	public double getMountedYOffset() {
		return 0.875F;
	}
	
	@Override
	protected float getSoundPitch() {
		return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.75F;
	}
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.GREAT_THRASHER_SPAWN_EGG.get());
	}

}