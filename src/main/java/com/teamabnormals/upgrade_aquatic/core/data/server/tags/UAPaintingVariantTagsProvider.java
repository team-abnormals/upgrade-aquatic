package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAPaintingVariants;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class UAPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

	public UAPaintingVariantTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(PaintingVariantTags.PLACEABLE).add(UAPaintingVariants.SIGHTLESS.getKey());
	}
}