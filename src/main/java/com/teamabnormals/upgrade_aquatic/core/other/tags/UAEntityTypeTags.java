package com.teamabnormals.upgrade_aquatic.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class UAEntityTypeTags {
	public static final TagKey<EntityType<?>> JELLYFISH = entityTypeTag("jellyfish");
	public static final TagKey<EntityType<?>> THRASHER_SONAR_TARGETS = entityTypeTag("thrasher_sonar_targets");

	public static TagKey<EntityType<?>> entityTypeTag(String name) {
		return TagUtil.entityTypeTag(UpgradeAquatic.MOD_ID, name);
	}
}
