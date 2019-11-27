package com.teamabnormals.upgrade_aquatic.common.network;

import java.util.function.Supplier;

import com.teamabnormals.upgrade_aquatic.api.UpgradeAquaticAPI.ClientInfo;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageCUpdateAttackTarget {
	private int entityId;
	private int attackTargetId;

	public MessageCUpdateAttackTarget(int entityID) {
		this.entityId = entityID;
	}
	
	public void serialize(PacketBuffer buf) {
		buf.writeInt(this.entityId);
	}
	
	public static MessageCUpdateAttackTarget deserialize(PacketBuffer buf) {
		int entityId = buf.readInt();
		return new MessageCUpdateAttackTarget(entityId);
	}
	
	public static void handle(MessageCUpdateAttackTarget message, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		CreatureEntity entity = (CreatureEntity) ClientInfo.getClientPlayerWorld().getEntityByID(message.entityId);
		if(context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				if(entity != null) {
					entity.setAttackTarget(null);
				}
			});
			context.setPacketHandled(true);
		}
	}
}