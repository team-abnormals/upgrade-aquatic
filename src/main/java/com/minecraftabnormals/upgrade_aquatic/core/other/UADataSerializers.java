package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeType;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.IDataSerializer;

public final class UADataSerializers {
	public static final IDataSerializer<PikeType> PIKE_TYPE = new IDataSerializer<PikeType>() {
		public void write(PacketBuffer buf, PikeType value) {
			buf.writeEnumValue(value);
		}

		public PikeType read(PacketBuffer buf) {
			return buf.readEnumValue(PikeType.class);
		}

		public PikeType copyValue(PikeType type) {
			return type;
		}
	};
	
	public static void register() {
		DataSerializers.registerSerializer(PIKE_TYPE);
	}
}