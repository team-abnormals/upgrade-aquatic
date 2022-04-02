package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeType;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;

public final class UADataSerializers {
	public static final DeferredRegister<DataSerializerEntry> SERIALIZERS = DeferredRegister.create(Keys.DATA_SERIALIZERS, UpgradeAquatic.MOD_ID);

	public static final EntityDataSerializer<PikeType> PIKE_TYPE = new EntityDataSerializer<PikeType>() {
		public void write(FriendlyByteBuf buf, PikeType value) {
			buf.writeEnum(value);
		}

		public PikeType read(FriendlyByteBuf buf) {
			return buf.readEnum(PikeType.class);
		}

		public PikeType copy(PikeType type) {
			return type;
		}
	};

	//TODO: Move to AC
	public static final EntityDataSerializer<EntityDimensions> ENTITY_SIZE = new EntityDataSerializer<EntityDimensions>() {
		@Override
		public void write(FriendlyByteBuf buf, EntityDimensions value) {
			buf.writeFloat(value.width);
			buf.writeFloat(value.height);
			buf.writeBoolean(value.fixed);
		}

		@Override
		public EntityDimensions read(FriendlyByteBuf buf) {
			return new EntityDimensions(buf.readFloat(), buf.readFloat(), buf.readBoolean());
		}

		@Override
		public EntityDimensions copy(EntityDimensions value) {
			return value;
		}
	};

	static {
		SERIALIZERS.register("pike_type", () -> new DataSerializerEntry(PIKE_TYPE));
		SERIALIZERS.register("entity_size", () -> new DataSerializerEntry(ENTITY_SIZE));
	}
}