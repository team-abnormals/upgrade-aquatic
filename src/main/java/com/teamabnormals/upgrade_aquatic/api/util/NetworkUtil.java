package com.teamabnormals.upgrade_aquatic.api.util;

import com.teamabnormals.upgrade_aquatic.common.network.MessageCSetRestTime;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

/**
 * @author - SmellyModder(Luke Tonon) & ExpensiveKoala
 * This class holds a list of useful network functions
 */
public class NetworkUtil {
	
	/**
	 * @param entityId{int} - The Player Entity's Id
	 * @param sleepTime{int} - The time since slept to set for the player(Measured in ticks)
	 */
	public static void updateCPlayerRestTime(int entityId, int sleepTime, ServerPlayerEntity player) {
		UpgradeAquatic.CHANNEL.sendTo(new MessageCSetRestTime(entityId, sleepTime), player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
	}
	
}
