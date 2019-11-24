package com.teamabnormals.upgrade_aquatic.common.network;

import java.util.function.Supplier;

import com.teamabnormals.upgrade_aquatic.api.UpgradeAquaticAPI.ClientInfo;
import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatedEntity;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * PROPERTY OF ENDERGETIC AND MINECRAFT ABNORMALS MODDING
 * Message for telling the client to begin playing an animation
 * @author - SmellyModder(Luke Tonon)
 */
public class MessageCAnimation {
	private int entityId;
	private int animationIndex;
	
	public MessageCAnimation(int entityID, int animationIndex) {
		this.entityId = entityID;
		this.animationIndex = animationIndex;
	}
	
	public void serialize(PacketBuffer buf) {
		buf.writeInt(this.entityId);
		buf.writeInt(this.animationIndex);
	}
	
	public static MessageCAnimation deserialize(PacketBuffer buf) {
		int entityId = buf.readInt();
		int animationIndex = buf.readInt();
		return new MessageCAnimation(entityId, animationIndex);
	}
	
	public static void handle(MessageCAnimation message, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		EndimatedEntity endimatedEntity = (EndimatedEntity) ClientInfo.getClientPlayerWorld().getEntityByID(message.entityId);
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				if(endimatedEntity != null) {
					if(message.animationIndex == -1) {
						endimatedEntity.resetPlayingAnimationToDefault();
					} else {
						endimatedEntity.setPlayingAnimation(endimatedEntity.getAnimations()[message.animationIndex]);
					}
					endimatedEntity.setAnimationTick(0);
				}
			});
			context.setPacketHandled(true);
		}
	}
}