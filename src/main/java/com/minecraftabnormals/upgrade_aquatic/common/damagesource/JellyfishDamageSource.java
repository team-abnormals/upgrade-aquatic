package com.minecraftabnormals.upgrade_aquatic.common.damagesource;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class JellyfishDamageSource extends EntityDamageSource {
	
	public JellyfishDamageSource(AbstractJellyfishEntity jellyfish) {
		super("upgrade_aquatic.jellyfishSting", jellyfish);
	}
	
	@Override
	public ITextComponent getDeathMessage(LivingEntity entityLivingBase) {
		String message = "death.attack." + this.damageType;
		return new TranslationTextComponent(message, entityLivingBase.getDisplayName(), this.damageSourceEntity.getDisplayName());
	}

}