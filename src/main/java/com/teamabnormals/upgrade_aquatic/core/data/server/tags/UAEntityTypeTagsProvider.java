package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UAEntityTypeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes.*;

public class UAEntityTypeTagsProvider extends EntityTypeTagsProvider {

	public UAEntityTypeTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, UpgradeAquatic.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(EntityTypeTags.AXOLOTL_HUNT_TARGETS).add(PIKE.get(), PERCH.get(), LIONFISH.get());
		this.tag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(THRASHER.get(), GREAT_THRASHER.get());

		this.tag(UAEntityTypeTags.JELLYFISH).add(BOX_JELLYFISH.get(), CASSIOPEA_JELLYFISH.get(), IMMORTAL_JELLYFISH.get());
		this.tag(UAEntityTypeTags.THRASHER_SONAR_TARGETS).add(EntityType.COD, EntityType.SALMON, EntityType.TROPICAL_FISH, EntityType.DOLPHIN, EntityType.AXOLOTL, PERCH.get(), PIKE.get(), NAUTILUS.get()).addTag(UAEntityTypeTags.JELLYFISH);
	}
}
