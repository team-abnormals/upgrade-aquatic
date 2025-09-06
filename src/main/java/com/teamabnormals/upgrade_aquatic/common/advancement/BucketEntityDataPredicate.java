package com.teamabnormals.upgrade_aquatic.common.advancement;

import com.mojang.serialization.Codec;
import net.minecraft.advancements.critereon.SingleComponentItemPredicate;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public record BucketEntityDataPredicate(CustomData data) implements SingleComponentItemPredicate<CustomData> {
	public static final Codec<BucketEntityDataPredicate> CODEC = CustomData.CODEC
			.xmap(BucketEntityDataPredicate::new, BucketEntityDataPredicate::data);

	@Override
	public DataComponentType<CustomData> componentType() {
		return DataComponents.BUCKET_ENTITY_DATA;
	}

	public boolean matches(ItemStack stack, CustomData value) {
		return value.matchedBy(this.data.copyTag());
	}

	public static BucketEntityDataPredicate bucketEntityData(CustomData data) {
		return new BucketEntityDataPredicate(data);
	}
}