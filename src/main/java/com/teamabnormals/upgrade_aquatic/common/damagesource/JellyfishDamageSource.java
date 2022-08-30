package com.teamabnormals.upgrade_aquatic.common.damagesource;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;

public class JellyfishDamageSource extends EntityDamageSource {

	public JellyfishDamageSource(AbstractJellyfish jellyfish) {
		super("upgrade_aquatic.jellyfishSting", jellyfish);
	}

	@Override
	public Component getLocalizedDeathMessage(LivingEntity entityLivingBase) {
		String message = "death.attack." + this.msgId;
		return new TranslatableComponent(message, entityLivingBase.getDisplayName(), this.entity.getDisplayName());
	}
}