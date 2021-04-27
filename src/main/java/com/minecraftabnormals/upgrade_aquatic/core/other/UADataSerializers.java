package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeType;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.entity.EntitySize;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class UADataSerializers {
	public static final DeferredRegister<DataSerializerEntry> SERIALIZERS = DeferredRegister.create(ForgeRegistries.DATA_SERIALIZERS, UpgradeAquatic.MOD_ID);

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

	//TODO: Move to AC
	public static final IDataSerializer<EntitySize> ENTITY_SIZE = new IDataSerializer<EntitySize>() {
		@Override
		public void write(PacketBuffer buf, EntitySize value) {
			buf.writeFloat(value.width);
			buf.writeFloat(value.height);
			buf.writeBoolean(value.fixed);
		}

		@Override
		public EntitySize read(PacketBuffer buf) {
			return new EntitySize(buf.readFloat(), buf.readFloat(), buf.readBoolean());
		}

		@Override
		public EntitySize copyValue(EntitySize value) {
			return value;
		}
	};

	static {
		SERIALIZERS.register("pike_type", () -> new DataSerializerEntry(PIKE_TYPE));
		SERIALIZERS.register("entity_size", () -> new DataSerializerEntry(ENTITY_SIZE));
	}
}