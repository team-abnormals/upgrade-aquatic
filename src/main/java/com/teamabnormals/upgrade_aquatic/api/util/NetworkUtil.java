package com.teamabnormals.upgrade_aquatic.api.util;

import org.apache.commons.lang3.ArrayUtils;

import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatedEntity;
import com.teamabnormals.upgrade_aquatic.api.endimator.Endimation;
import com.teamabnormals.upgrade_aquatic.common.network.MessageCAnimation;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;

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
	
}
