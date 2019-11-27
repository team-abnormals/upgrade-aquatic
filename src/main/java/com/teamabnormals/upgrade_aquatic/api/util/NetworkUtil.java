package com.teamabnormals.upgrade_aquatic.api.util;

import javax.annotation.Nullable;

import org.apache.commons.lang3.ArrayUtils;

import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatedEntity;
import com.teamabnormals.upgrade_aquatic.api.endimator.Endimation;
import com.teamabnormals.upgrade_aquatic.common.network.MessageCAnimation;
import com.teamabnormals.upgrade_aquatic.common.network.MessageCUpdateAttackTarget;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * @author - SmellyModder(Luke Tonon) & ExpensiveKoala
 * This class holds a list of useful network functions
 */
public class NetworkUtil {
	
	/**
	 * Sends an animation message to the clients to update an entity's animations
	 * @param entity - The Entity to send the packet for
	 * @param animationToPlay - The animation to play
	 */
	public static void setPlayingAnimationMessage(EndimatedEntity entity, Endimation animationToPlay) {
		if(!entity.isWorldRemote()) {
			UpgradeAquatic.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new MessageCAnimation(entity.getEntityId(), ArrayUtils.indexOf(entity.getAnimations(), animationToPlay)));
		}
	}
	
	/**
	 * Updates attack target for a specific entity on client and server
	 * @param attacker
	 * @param entity
	 */
	public static void setAttackTargetMessage(CreatureEntity attacker, @Nullable LivingEntity entity) {
		if(!attacker.world.isRemote) {
			int entityId = entity != null ? entity.getEntityId() : -1;
			UpgradeAquatic.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> attacker), new MessageCUpdateAttackTarget(attacker.getEntityId()));
		}
	}
	
}
