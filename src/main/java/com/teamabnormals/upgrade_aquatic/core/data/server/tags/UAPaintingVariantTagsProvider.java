package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAPaintingVariants.*;

public class UAPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

	public UAPaintingVariantTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(PaintingVariantTags.PLACEABLE).add(SIGHTLESS, MONUMENT, UTENSIL, COIL);
	}
}