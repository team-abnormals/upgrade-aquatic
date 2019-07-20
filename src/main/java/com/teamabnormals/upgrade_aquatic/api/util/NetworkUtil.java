package com.teamabnormals.upgrade_aquatic.api.util;

import com.teamabnormals.upgrade_aquatic.common.network.MessageCSetRestTime;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;

import net.minecraftforge.fml.network.PacketDistributor;

/**
 * @author - SmellyModder(Luke Tonon) & ExpensiveKoala
 * This class holds a list of useful network functions
 */
public class NetworkUtil {
	
	/**
	 * @param entityId{int} - The Player Entity's Id
	 * @param sleepTime{int} - The time since slept to set for the player(Measured in ticks)
	 */
	public static void updateCPlayerRestTime(int entityId, int sleepTime) {
		UpgradeAquatic.CHANNEL.send(PacketDistributor.ALL.noArg(), new MessageCSetRestTime(entityId, sleepTime));
	}
	
}
