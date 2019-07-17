package com.teamabnormals.upgrade_aquatic.common.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageCSetRestTime {
	private int entityId;
	private int sleepTime;
	
	public MessageCSetRestTime(int entityId, int sleepTime) {
		this.entityId = entityId;
		this.sleepTime = sleepTime;
	}
	
	public void serialize(PacketBuffer buf) {
		buf.writeInt(this.entityId);
		buf.writeInt(this.sleepTime);
	}
	
	public static MessageCSetRestTime deserialize(PacketBuffer buf) {
		int entityId = buf.readInt();
		int sleepTime = buf.readInt();
		return new MessageCSetRestTime(entityId, sleepTime);
	}
	
	public static void handle(MessageCSetRestTime message, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			ctx.get().enqueueWork(() -> {
				Entity entity = Minecraft.getInstance().player.world.getEntityByID(message.entityId);
				if(entity instanceof ClientPlayerEntity) {
					ClientPlayerEntity playerC = ((ClientPlayerEntity) entity);
					StatisticsManager statisticsManagerC = playerC.getStats();
					statisticsManagerC.setValue(playerC, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), message.sleepTime);
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
