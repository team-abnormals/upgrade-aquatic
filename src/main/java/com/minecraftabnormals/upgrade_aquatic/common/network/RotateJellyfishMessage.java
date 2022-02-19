package com.minecraftabnormals.upgrade_aquatic.common.network;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;
import com.teamabnormals.blueprint.client.ClientInfo;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Message that tells the client to rotate a Jellyfish
 *
 * @author - SmellyModder(Luke Tonon)
 */
public class RotateJellyfishMessage {
	private final int entityId;
	private final int tickLength;
	private final float yaw;
	private final float pitch;

	public RotateJellyfishMessage(int entityId, int tickLength, float yaw, float pitch) {
		this.entityId = entityId;
		this.tickLength = tickLength;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public void serialize(FriendlyByteBuf buf) {
		buf.writeInt(this.entityId);
		buf.writeInt(this.tickLength);
		buf.writeFloat(this.yaw);
		buf.writeFloat(this.pitch);
	}

	public static RotateJellyfishMessage deserialize(FriendlyByteBuf buf) {
		return new RotateJellyfishMessage(buf.readInt(), buf.readInt(), buf.readFloat(), buf.readFloat());
	}

	public static void handle(RotateJellyfishMessage message, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Entity entity = ClientInfo.getClientPlayerLevel().getEntity(message.entityId);
				if (entity instanceof AbstractJellyfishEntity) {
					((AbstractJellyfishEntity) entity).getRotationController().rotate(message.yaw, message.pitch, message.tickLength);
				}
			});
			context.setPacketHandled(true);
		}
	}
}