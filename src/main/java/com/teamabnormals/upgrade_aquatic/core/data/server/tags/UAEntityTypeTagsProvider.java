package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UAEntityTypeTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes.*;

public class UAEntityTypeTagsProvider extends EntityTypeTagsProvider {

	public UAEntityTypeTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(EntityTypeTags.AXOLOTL_HUNT_TARGETS).add(PIKE.get(), PERCH.get(), LIONFISH.get());
		this.tag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(THRASHER.get(), GREAT_THRASHER.get());

		this.tag(UAEntityTypeTags.JELLYFISH).add(BOX_JELLYFISH.get(), CASSIOPEA_JELLYFISH.get(), IMMORTAL_JELLYFISH.get());
		this.tag(UAEntityTypeTags.THRASHER_SONAR_TARGETS).add(EntityType.COD, EntityType.SALMON, EntityType.TROPICAL_FISH, EntityType.DOLPHIN, EntityType.AXOLOTL, PERCH.get(), PIKE.get(), NAUTILUS.get()).addTag(UAEntityTypeTags.JELLYFISH);
	}
}
