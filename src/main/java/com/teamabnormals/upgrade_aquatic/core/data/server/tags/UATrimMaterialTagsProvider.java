package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintTrimMaterialTags;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UATrimMaterials;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class UATrimMaterialTagsProvider extends TagsProvider<TrimMaterial> {

	public UATrimMaterialTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, Registries.TRIM_MATERIAL, provider, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(BlueprintTrimMaterialTags.GENERATES_OVERRIDES).add(UATrimMaterials.TOOTH);
	}
}