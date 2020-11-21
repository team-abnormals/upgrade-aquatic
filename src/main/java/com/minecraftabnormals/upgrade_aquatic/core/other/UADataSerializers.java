package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeType;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class UADataSerializers {
	public static final DeferredRegister<DataSerializerEntry> SERIALIZERS = DeferredRegister.create(ForgeRegistries.DATA_SERIALIZERS, UpgradeAquatic.MODID);

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

	static {
		SERIALIZERS.register("pike_type", () -> new DataSerializerEntry(PIKE_TYPE));
	}
}