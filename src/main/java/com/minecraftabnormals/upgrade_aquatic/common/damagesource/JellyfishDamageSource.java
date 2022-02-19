package com.minecraftabnormals.upgrade_aquatic.common.damagesource;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class JellyfishDamageSource extends EntityDamageSource {

	public JellyfishDamageSource(AbstractJellyfishEntity jellyfish) {
		super("upgrade_aquatic.jellyfishSting", jellyfish);
	}

	@Override
	public Component getLocalizedDeathMessage(LivingEntity entityLivingBase) {
		String message = "death.attack." + this.msgId;
		return new TranslatableComponent(message, entityLivingBase.getDisplayName(), this.entity.getDisplayName());
	}

}