package com.minecraftabnormals.upgrade_aquatic.common.network;

import java.util.function.Supplier;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractEntityJellyfish;
import com.teamabnormals.abnormals_core.client.ClientInfo;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Message that tells the client to rotate a Jellyfish
 * @author - SmellyModder(Luke Tonon)
 */
public class MessageRotateJellyfish {
	private int entityId;
	private int tickLength;
	private float yaw;
	private float pitch;
	
	public MessageRotateJellyfish(int entityId, int tickLength, float yaw, float pitch) {
		this.entityId = entityId;
		this.tickLength = tickLength;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public void serialize(PacketBuffer buf) {
		buf.writeInt(this.entityId);
		buf.writeInt(this.tickLength);
		buf.writeFloat(this.yaw);
		buf.writeFloat(this.pitch);
	}
	
	public static MessageRotateJellyfish deserialize(PacketBuffer buf) {
		return new MessageRotateJellyfish(buf.readInt(), buf.readInt(), buf.readFloat(), buf.readFloat());
	}
	
	public static void handle(MessageRotateJellyfish message, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		if(context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Entity entity = ClientInfo.getClientPlayerWorld().getEntityByID(message.entityId);
				if(entity instanceof AbstractEntityJellyfish) {
					((AbstractEntityJellyfish) entity).getRotationController().rotate(message.yaw, message.pitch, message.tickLength);
				}
			});
			context.setPacketHandled(true);
		}
	}
}