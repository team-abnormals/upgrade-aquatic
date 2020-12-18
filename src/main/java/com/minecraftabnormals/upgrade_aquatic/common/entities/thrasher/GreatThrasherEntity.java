package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class GreatThrasherEntity extends ThrasherEntity {

	public GreatThrasherEntity(EntityType<? extends ThrasherEntity> type, World world) {
		super(type, world);
		this.experienceValue = 55;
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
    	return MobEntity.func_233666_p_()
    		.createMutableAttribute(Attributes.MAX_HEALTH, 125.0D)
    		.createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D)
    		.createMutableAttribute(Attributes.ARMOR, 16.0D);
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
	protected double getStunDamageThreshold() {
		return 8.0F;
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